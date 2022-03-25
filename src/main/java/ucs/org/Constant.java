package ucs.org;

import io.grpc.Metadata;

import static io.grpc.Metadata.ASCII_STRING_MARSHALLER;

/**
 * @author Macrow
 * @date 2022-03-16
 */
interface Constant {
    String OrgPermissionTypeTree = "tree";
    String OrgPermissionTypeSelf = "self";
    String OrgPermissionTypeNone = "none";
    String OrgPermissionTypeAll = "all";
    String OrgPermissionTypeCustom = "custom";

    String TIMEOUT_MSG = "timeout";
    String UNKNOWN_MSG = "unknown";
    String UNAUTHORIZED_MSG = "权限不足";

    String DefaultHeaderRandomKey = "Random-Key";
    String DefaultHeaderAccessCode = "Access-Code";

    String ValidateJwtURL = "/api/v1/ucs/current/blank";
    String ValidatePermOperationByCodeURL = "/api/v1/ucs/current/check-operation";
    String ValidatePermActionURL = "/api/v1/ucs/current/check-action";
    String ValidatePermOrgByIdURL = "/api/v1/ucs/current/check-org";
    String ValidatePermActionWithOrgIdURL = "/api/v1/ucs/current/check-action-with-org-id";
    String QueryOrgIdsByActionURL = "/api/v1/ucs/current/query-action-org-ids";

    int DEFAULT_TIMEOUT_IN_SECONDS = 3;
    String DEADLINE_EXCEEDED = "DEADLINE_EXCEEDED";
    String BEARER_NAME = "Authorization";
    String BEARER_TYPE = "Bearer";
    Metadata.Key<String> AUTHORIZATION_METADATA_KEY = Metadata.Key.of("Authorization", ASCII_STRING_MARSHALLER);
}