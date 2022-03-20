package ucs.org;

import cn.hutool.core.io.IoUtil;
import io.grpc.*;
import ucs.org.pb.AuthServiceGrpc;
import ucs.org.pb.UcsPb;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

/**
 * @author Macrow
 * @date 2022-03-16
 */
public class RpcClient implements Client {
    private final String address;
    private AuthServiceGrpc.AuthServiceBlockingStub blockingStub;
    private ManagedChannel channel;
    private JwtCredential jwtCredential = null;
    private ChannelCredentials tlsCredential = null;
    private int timeout = Constant.DEFAULT_TIMEOUT_IN_SECONDS;

    public RpcClient(String address) {
        this.address = address;
    }

    public RpcClient(String cert, String address) {
        this.address = address;
        TlsChannelCredentials.Builder tlsBuilder = TlsChannelCredentials.newBuilder();
        try {
            tlsBuilder.trustManager(IoUtil.toStream(cert, StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("please provide correct cert");
        }
        this.tlsCredential = tlsBuilder.build();
    }

    @Override
    public Client SetTimeout(int timeout) {
        if (timeout > 0) {
            this.timeout = timeout;
        }
        return this;
    }

    @Override
    public Client SetToken(String token) {
        this.jwtCredential = new JwtCredential(token);
        return this;
    }

    @Override
    public Client SetHttpHeaderNames(String accessCodeHeader, String randomKeyHeader) {
        return this;
    }

    @Override
    public UcsResult<Object> ValidateJwt() {
        return authentication(UcsPb.AuthenticationRequest.newBuilder().build());
    }

    @Override
    public UcsResult<PermitResult> ValidatePermOperationByCode(String code) {
        return authorization(UcsPb.AuthorizationRequest.newBuilder()
                .setOperationCode(code)
                .build());
    }

    public UcsResult<PermitResult> ValidatePermAction(String service, String path, String method) {
        return authorization(UcsPb.AuthorizationRequest.newBuilder()
                .setAction(UcsPb.Action.newBuilder()
                        .setService(service)
                        .setPath(path)
                        .setMethod(method)
                        .build())
                .build());
    }

    @Override
    public UcsResult<PermitResult> ValidatePermOrgById(String orgId) {
        return authorization(UcsPb.AuthorizationRequest.newBuilder()
                .setOrgId(orgId)
                .build());
    }

    private UcsResult<Object> authentication(UcsPb.AuthenticationRequest req) {
        this.prepare();
        try {
            UcsPb.Result res = blockingStub
                    .withCallCredentials(jwtCredential)
                    .withDeadlineAfter(timeout, TimeUnit.SECONDS)
                    .authentication(req);
            return UcsResult.builder()
                    .success(res.getSuccess())
                    .message(res.getMessage())
                    .build();
        } catch (Exception e) {
            if (e.getMessage().contains(Constant.DEADLINE_EXCEEDED)) {
                return UcsResult.builder().success(false).message(Constant.TIMEOUT_MSG).build();
            }
            e.printStackTrace();
            return UcsResult.builder().success(false).message(Constant.UNKNOWN_MSG).build();
        } finally {
            this.channel.shutdown();
        }
    }

    private UcsResult<PermitResult> authorization(UcsPb.AuthorizationRequest req) {
        this.prepare();
        try {
            UcsPb.Result res = blockingStub
                    .withCallCredentials(jwtCredential)
                    .withDeadlineAfter(timeout, TimeUnit.SECONDS)
                    .authorization(req);
            return UcsResult.<PermitResult>builder()
                    .success(res.getSuccess())
                    .message(res.getMessage())
                    .result(PermitResult.builder().permit(res.getSuccess()).build())
                    .build();
        } catch (Exception e) {
            if (e.getMessage().contains(Constant.DEADLINE_EXCEEDED)) {
                return UcsResult.<PermitResult>builder()
                        .success(false)
                        .message(Constant.TIMEOUT_MSG)
                        .result(PermitResult.builder().permit(false).build())
                        .build();
            }
            e.printStackTrace();
            return UcsResult.<PermitResult>builder()
                    .success(false)
                    .message(Constant.UNKNOWN_MSG)
                    .result(PermitResult.builder().permit(false).build())
                    .build();
        } finally {
            channel.shutdown();
        }
    }

    private void prepare() {
        if (this.jwtCredential == null) {
            throw new IllegalArgumentException("please provide token first");
        }
        String[] array = address.split(":");
        if (array.length != 2) {
            throw new IllegalArgumentException("wrong address");
        }
        int port;
        try {
            port = Integer.parseInt(array[1]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("wrong address");
        }
        if (this.tlsCredential == null) {
            this.channel = ManagedChannelBuilder.forAddress(array[0], port).usePlaintext().build();
        } else {
            this.channel = Grpc.newChannelBuilderForAddress(array[0], port, tlsCredential).build();
        }
        this.blockingStub = AuthServiceGrpc.newBlockingStub(channel);
    }
}
