package ucs.org;

/**
 * @author Macrow
 * @date 2022-03-17
 */
public interface Client {
    Client SetTimeout(int timeout);

    Client SetToken(String token);

    Client SetHttpHeaderNames(String accessCodeHeader, String randomKeyHeader);

    UcsResult<Object> ValidateJwt();

    UcsResult<PermitResult> ValidatePermOperationByCode(String code);

    UcsResult<PermitResult> ValidatePermAction(String service, String path, String method);

    UcsResult<PermitResult> ValidatePermOrgById(String orgId);

    UcsResult<PermitResult> ValidatePermActionWithOrgId(String service, String path, String method, String orgId);

    UcsResult<OrgIdsResult> QueryOrgIdsByAction(String service, String path, String method);

    UcsResult<AccessTokenResult> OAuth2TokenByAuthorizationCode(String code, String clientId, String clientSecret, String deviceId, String deviceName);

    UcsResult<AccessTokenResult> OAuth2TokenByPassword(String username, String password, String deviceId, String deviceName);
}
