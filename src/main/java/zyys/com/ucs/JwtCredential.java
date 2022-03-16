package zyys.com.ucs;

import io.grpc.CallCredentials;
import io.grpc.Metadata;
import io.grpc.Status;

import java.util.concurrent.Executor;

/**
 * @author Macrow
 * @date 2022-03-16
 */
public class JwtCredential extends CallCredentials {
    private final String jwtTokenString;

    JwtCredential(String jwtTokenString) {
        this.jwtTokenString = jwtTokenString;
    }

    @Override
    public void applyRequestMetadata(final RequestInfo requestInfo, final Executor executor,
                                     final MetadataApplier metadataApplier) {
        executor.execute(() -> {
            try {
                Metadata headers = new Metadata();
                headers.put(Constant.AUTHORIZATION_METADATA_KEY,
                        String.format("%s %s", Constant.BEARER_TYPE, jwtTokenString));
                metadataApplier.apply(headers);
            } catch (Throwable e) {
                metadataApplier.fail(Status.UNAUTHENTICATED.withCause(e));
            }
        });
    }

    @Override
    public void thisUsesUnstableApi() {
        // noop
    }
}