package zyys.com.ucs;

import io.grpc.Metadata;
import static io.grpc.Metadata.ASCII_STRING_MARSHALLER;

/**
 * @author Macrow
 * @date 2022-03-16
 */
final class Constant {
    static final int DEFAULT_TIMEOUT_IN_SECONDS = 3;
    static final String DEADLINE_EXCEEDED = "DEADLINE_EXCEEDED";
    static final String BEARER_TYPE = "Bearer";

    static final Metadata.Key<String> AUTHORIZATION_METADATA_KEY = Metadata.Key.of("Authorization", ASCII_STRING_MARSHALLER);

    private Constant() {
        throw new AssertionError();
    }
}