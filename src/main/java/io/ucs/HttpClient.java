package io.ucs;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpStatus;
import cn.hutool.http.Method;
import cn.hutool.json.JSONUtil;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Macrow
 * @date 2022-03-17
 */
public class HttpClient implements Client {
    private String baseUrl;
    private String accessCode;
    private String accessCodeHeader;
    private String randomKeyHeader;
    private String userTokenHeader;
    private String clientTokenHeader;
    private String userToken = null;
    private String clientId = null;
    private String clientSecret = null;
    private int timeout = Constant.DEFAULT_TIMEOUT_IN_SECONDS;

    public HttpClient(String baseUrl, String accessCode) {
        this.baseUrl = baseUrl;
        this.accessCode = accessCode;
        this.accessCodeHeader = Constant.DefaultHeaderAccessCode;
        this.randomKeyHeader = Constant.DefaultHeaderRandomKey;
        this.userTokenHeader = Constant.BEARER_NAME;
        this.clientTokenHeader = Constant.CLIENT_HEADER_NAME;
    }

    @Override
    public Client setTimeout(int timeout) {
        if (timeout > 0) {
            this.timeout = timeout;
        }
        return this;
    }

    @Override
    public Client setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }

    @Override
    public Client setAccessCode(String accessCode) {
        this.accessCode = accessCode;
        return this;
    }

    @Override
    public Client setUserToken(String token) {
        this.userToken = token;
        return this;
    }

    @Override
    public Client setClientIdAndSecret(String clientId, String clientSecret) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        return this;
    }

    @Override
    public Client setHttpHeaderNames(String accessCodeHeader, String randomKeyHeader, String userTokenHeader, String clientTokenHeader) {
        this.accessCodeHeader = accessCodeHeader;
        this.randomKeyHeader = randomKeyHeader;
        this.userTokenHeader = userTokenHeader;
        this.clientTokenHeader = clientTokenHeader;
        return this;
    }

    @Override
    public UcsResult<JwtUser> userValidateJwt() {
        return request(Constant.ValidateJwtURL, Method.GET, null, RequestType.USER);
    }

    @Override
    public UcsResult<PermitResult> userValidatePermByOperation(String code) {
        Map<String, Object> formData = new HashMap<>();
        formData.put("code", code);
        return request(Constant.ValidatePermOperationByCodeURL, Method.POST, formData, RequestType.USER);
    }

    @Override
    public UcsResult<PermitResult> userValidatePermByAction(String service, String method, String path) {
        Map<String, Object> formData = new HashMap<>();
        formData.put("service", service);
        formData.put("method", method);
        formData.put("path", path);
        return request(Constant.ValidatePermActionURL, Method.POST, formData, RequestType.USER);
    }

    @Override
    public <T> UcsResult<T> userRequest(String method, String url, Map<String, Object> data) {
        return request(url, getHttpMethod(method), data, RequestType.USER);
    }

    @Override
    public <T> UcsResult<T> clientRequest(String method, String url, Map<String, Object> data) {
        return request(url, getHttpMethod(method), data, RequestType.CLIENT);
    }

    private Method getHttpMethod(String method) {
        if (StrUtil.isBlank(method)) {
            throw new IllegalArgumentException("方法不支持");
        }
        switch (method.toUpperCase()) {
            case "GET":
                return Method.GET;
            case "POST":
                return Method.POST;
            case "PUT":
                return Method.PUT;
            case "PATCH":
                return Method.PATCH;
            case "DELETE":
                return Method.DELETE;
            default:
                throw new IllegalArgumentException("方法不支持");
        }
    }

    private <T> UcsResult<T> request(String url, Method method, Map<String, Object> formData, RequestType requestType) {
        String message;
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
                .header(this.accessCodeHeader, this.accessCode, true)
                .header(this.randomKeyHeader, getRandomKey(6), true);
        switch (requestType) {
            case USER:
                prepareForUserRequest();
                req.header(this.userTokenHeader, Constant.BEARER_TYPE + " " + this.userToken, true);
                break;
            case CLIENT:
                prepareForClientRequest();
                req.header(this.clientTokenHeader, Base64.encode(this.clientId + "@" + this.clientSecret, StandardCharsets.UTF_8), true);
                break;
            default:
                throw new IllegalArgumentException("不支持的请求类型");
        }
        if (formData != null) {
            req = req.form(formData);
        }
        try (HttpResponse httpResponse = req.execute()) {
            if (httpResponse.getStatus() == HttpStatus.HTTP_OK) {
                HttpResult<T> result = JSONUtil.toBean(httpResponse.body(), HttpResult.class);
                if (result.getCode() == 0) {
                    return UcsResult.<T>builder()
                            .success(true)
                            .message("")
                            .result(result.getResult())
                            .build();
                } else {
                    message = result.getMessage();
                }
            } else {
                message = Constant.MSG_HTTP_FAILED;
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = Constant.MSG_UNKNOWN;
        }
        return UcsResult.<T>builder()
                .success(false)
                .message(message)
                .result(null)
                .build();
    }

    private void prepareForUserRequest() {
        if (StrUtil.isBlank(this.baseUrl)) {
            throw new IllegalArgumentException("please provide baseUrl first");
        }
        if (StrUtil.isBlank(this.userToken)) {
            throw new IllegalArgumentException("please provide token first");
        }
    }

    private void prepareForClientRequest() {
        if (StrUtil.isBlank(this.baseUrl)) {
            throw new IllegalArgumentException("please provide baseUrl first");
        }
        if (StrUtil.isBlank(this.clientId) || StrUtil.isBlank(this.clientSecret)) {
            throw new IllegalArgumentException("please provide client id/secret");
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
