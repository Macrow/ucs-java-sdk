package ucs.org;

import io.grpc.Metadata;

import static io.grpc.Metadata.ASCII_STRING_MARSHALLER;

/**
 * @author Macrow
 * @date 2022-03-16
 */
interface Constant {
    int TIMEOUT_CODE = 4;
    int UNKNOWN_CODE = 9;
    String TIMEOUT_MSG = "timeout";
    String UNKNOWN_MSG = "unknown";

    int DEFAULT_TIMEOUT_IN_SECONDS = 3;
    String DEADLINE_EXCEEDED = "DEADLINE_EXCEEDED";
    String BEARER_TYPE = "Bearer";
    Metadata.Key<String> AUTHORIZATION_METADATA_KEY = Metadata.Key.of("Authorization", ASCII_STRING_MARSHALLER);
}