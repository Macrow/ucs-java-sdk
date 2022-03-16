package zyys.com.ucs;

import cn.hutool.core.io.IoUtil;
import io.grpc.*;
import zyys.com.ucs.pb.AuthServiceGrpc;
import zyys.com.ucs.pb.UcsPb;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

/**
 * @author Macrow
 * @date 2022-03-16
 */
public class Client {
    private final String address;
    private final int port;
    private AuthServiceGrpc.AuthServiceBlockingStub blockingStub;
    private ManagedChannel channel;
    private JwtCredential jwtCredential = null;
    private ChannelCredentials tlsCredential = null;
    private int timeout = Constant.DEFAULT_TIMEOUT_IN_SECONDS;

    public Client(String address, int port) {
        this.address = address;
        this.port = port;
    }

    public Client(String cert, String address, int port) {
        this.address = address;
        this.port = port;
        TlsChannelCredentials.Builder tlsBuilder = TlsChannelCredentials.newBuilder();
        try {
            tlsBuilder.trustManager(IoUtil.toStream(cert, StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("please provide correct cert");
        }
        this.tlsCredential = tlsBuilder.build();
    }

    public Client SetToken(String token) {
        this.jwtCredential = new JwtCredential(token);
        return this;
    }

    public Client SetTimeout(int timeout) {
        this.timeout = timeout;
        return this;
    }

    public ValidateResult ValidateJwt() {
        return authentication(UcsPb.AuthenticationRequest.newBuilder().build());
    }

    public ValidateResult ValidatePermOperationByCode(String code) {
        return authorization(UcsPb.AuthorizationRequest.newBuilder()
                .setOperationCode(code)
                .build());
    }

    public ValidateResult ValidatePermAction(String service, String path, String method) {
        return authorization(UcsPb.AuthorizationRequest.newBuilder()
                .setAction(UcsPb.Action.newBuilder()
                        .setService(service)
                        .setPath(path)
                        .setMethod(method)
                        .build())
                .build());
    }

    public ValidateResult ValidatePermOrgById(String orgId) {
        return authorization(UcsPb.AuthorizationRequest.newBuilder()
                .setOrgId(orgId)
                .build());
    }

    private ValidateResult authentication(UcsPb.AuthenticationRequest req) {
        this.prepare();
        try {
            UcsPb.Result res = blockingStub
                    .withCallCredentials(jwtCredential)
                    .withDeadlineAfter(timeout, TimeUnit.SECONDS)
                    .authentication(req);
            return ValidateResult.builder()
                    .success(res.getSuccess())
                    .code(res.getError().getCode())
                    .reason(res.getError().getReason())
                    .build();
        } catch (Exception e) {
            if (e.getMessage().contains(Constant.DEADLINE_EXCEEDED)) {
                return ValidateResult.builder().success(false).code(4).reason("timeout").build();
            }
            e.printStackTrace();
            return ValidateResult.builder().success(false).code(9).reason("unknown").build();
        } finally {
            this.channel.shutdown();
        }
    }

    private ValidateResult authorization(UcsPb.AuthorizationRequest req) {
        this.prepare();
        try {
            UcsPb.Result res = blockingStub
                    .withCallCredentials(jwtCredential)
                    .withDeadlineAfter(timeout, TimeUnit.SECONDS)
                    .authorization(req);
            return ValidateResult.builder()
                    .success(res.getSuccess())
                    .code(res.getError().getCode())
                    .reason(res.getError().getReason())
                    .build();
        } catch (Exception e) {
            if (e.getMessage().contains(Constant.DEADLINE_EXCEEDED)) {
                return ValidateResult.builder().success(false).code(4).reason("timeout").build();
            }
            e.printStackTrace();
            return ValidateResult.builder().success(false).code(9).reason("unknown").build();
        } finally {
            this.channel.shutdown();
        }
    }

    private void prepare() {
        if (this.jwtCredential == null) {
            throw new IllegalArgumentException("please provide token first");
        }
        if (this.tlsCredential == null) {
            this.channel = ManagedChannelBuilder.forAddress(address, port).usePlaintext().build();
        } else {
            this.channel = Grpc.newChannelBuilderForAddress(address, port, tlsCredential).build();
        }
        this.blockingStub = AuthServiceGrpc.newBlockingStub(channel);
    }
}
