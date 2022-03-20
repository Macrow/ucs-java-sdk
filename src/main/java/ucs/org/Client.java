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
}
