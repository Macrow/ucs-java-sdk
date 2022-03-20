package ucs.org;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpStatus;
import cn.hutool.http.Method;
import cn.hutool.json.JSONUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Macrow
 * @date 2022-03-17
 */
public class HttpClient implements Client {
    private final String baseUrl;
    private String accessCodeHeader;
    private String randomKeyHeader;
    private final String accessCode;
    private String token = null;
    private int timeout = Constant.DEFAULT_TIMEOUT_IN_SECONDS;

    public HttpClient(String baseUrl, String accessCode) {
        this.baseUrl = baseUrl;
        this.accessCode = accessCode;
        this.accessCodeHeader = Constant.DefaultHeaderAccessCode;
        this.randomKeyHeader = Constant.DefaultHeaderRandomKey;
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
        this.token = token;
        return this;
    }

    @Override
    public Client SetHttpHeaderNames(String accessCodeHeader, String randomKeyHeader) {
        this.accessCodeHeader = accessCodeHeader;
        this.randomKeyHeader = randomKeyHeader;
        return this;
    }

    @Override
    public UcsResult<Object> ValidateJwt() {
        return request(Constant.ValidateJwtURL, Method.GET, null);
    }

    @Override
    public UcsResult<PermitResult> ValidatePermOperationByCode(String code) {
        Map<String, Object> formData = new HashMap<>();
        formData.put("code", code);
        return request(Constant.ValidatePermOperationByCodeURL, Method.POST, formData);
    }

    @Override
    public UcsResult<PermitResult> ValidatePermAction(String service, String path, String method) {
        Map<String, Object> formData = new HashMap<>();
        formData.put("service", service);
        formData.put("path", path);
        formData.put("method", method);
        return request(Constant.ValidatePermActionURL, Method.POST, formData);
    }

    @Override
    public UcsResult<PermitResult> ValidatePermOrgById(String orgId) {
        Map<String, Object> formData = new HashMap<>();
        formData.put("id", orgId);
        return request(Constant.ValidatePermOrgByIdURL, Method.POST, formData);
    }

    private <T> UcsResult<T> request(String url, Method method, Map<String, Object> formData) {
        prepare();
        boolean success = false;
        String message = Constant.UNKNOWN_MSG;
        T res = null;
        try {
            HttpRequest req;
            switch (method) {
                case POST:
                    req = HttpRequest.post(baseUrl + url);
                    break;
                case PUT:
                    req = HttpRequest.put(baseUrl + url);
                    break;
                case PATCH:
                    req = HttpRequest.patch(baseUrl + url);
                    break;
                case DELETE:
                    req = HttpRequest.delete(baseUrl + url);
                    break;
                case GET:
                default:
                    req = HttpRequest.get(baseUrl + url);
                    break;
            }

            req.timeout(this.timeout * 1000)
                    .method(method)
                    .header(Constant.BEARER_NAME, Constant.BEARER_TYPE + " " + this.token, false)
                    .header(this.accessCodeHeader, this.accessCode, false)
                    .header(this.randomKeyHeader, getRandomKey(6), false);
            if (formData != null) {
                req = req.form(formData);
            }
            HttpResponse httpResponse = req.execute();
            if (httpResponse.getStatus() == HttpStatus.HTTP_OK) {
                HttpResult result = JSONUtil.toBean(httpResponse.body(), HttpResult.class);
                if (result.getCode() == 0) {
                    if (result.getResult().isEmpty()) {
                        success = true;
                    } else {
                        if (result.getResult().get("permit") != null) {
                            PermitResult permitResult = result.getResult().toBean(PermitResult.class);
                            if (permitResult.getPermit()) {
                                success = true;
                            } else {
                                message = Constant.UNAUTHORIZED_MSG;
                            }
                            res = (T) permitResult;
                        }
                    }
                }
                if (!result.getMessage().isEmpty()) {
                    message = result.getMessage();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            success = false;
            message = Constant.UNKNOWN_MSG;
            res = null;
        }
        return UcsResult.<T>builder()
                .success(success)
                .message(success ? "" : message)
                .result(res)
                .build();
    }

    private void prepare() {
        if (this.token == null || this.token.isEmpty()) {
            throw new IllegalArgumentException("please provide token first");
        }
        if (this.baseUrl == null || this.baseUrl.isEmpty()) {
            throw new IllegalArgumentException("please provide baseUrl first");
        }
    }

    private String getRandomKey(int length) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < length; i++) {
            result.append(RandomUtil.randomInt(0, 9));
        }
        return result.toString();
    }
}
