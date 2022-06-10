package ucs.org;

import java.util.Map;

/**
 * @author Macrow
 * @date 2022-03-17
 */
public interface Client {
    Client SetTimeout(int timeout);
    Client SetUserToken(String token);
    Client SetClientIdAndSecret(String clientId, String clientSecret);
    Client SetHttpHeaderNames(String accessCodeHeader, String randomKeyHeader, String userTokenHeader, String clientTokenHeader);

    UcsResult<Object> UserValidateJwt();
    UcsResult<PermitResult> UserValidatePermOperationByCode(String code);
    UcsResult<PermitResult> UserValidatePermAction(String service, String path, String method);
    UcsResult<PermitResult> UserValidatePermOrgById(String orgId);
    UcsResult<PermitResult> UserValidatePermActionWithOrgId(String service, String path, String method, String orgId);
    UcsResult<OrgIdsResult> UserQueryOrgIdsByAction(String service, String path, String method);

    <T> UcsResult<T> ClientRequest(String method, String url, Map<String, Object> data);
}
