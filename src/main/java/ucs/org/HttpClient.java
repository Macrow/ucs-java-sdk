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
    private final String address;
    private final int port;
    private final boolean ssl;
    private String accessCodeHeader;
    private String randomKeyHeader;
    private final String accessCode;
    private String token = null;
    private int timeout = Constant.DEFAULT_TIMEOUT_IN_SECONDS;
    private String baseUrl = null;

    public HttpClient(String address, int port, boolean ssl, String accessCode) {
        this.address = address;
        this.port = port;
        this.ssl = ssl;
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
    public UcsResult ValidateJwt() {
        return request(Constant.ValidateJwtURL, Method.GET, null);
    }

    @Override
    public UcsResult ValidatePermOperationByCode(String code) {
        Map<String, Object> formData = new HashMap<>();
        formData.put("code", code);
        return request(Constant.ValidatePermOperationByCodeURL, Method.POST, formData);
    }

    @Override
    public UcsResult ValidatePermAction(String service, String path, String method) {
        Map<String, Object> formData = new HashMap<>();
        formData.put("service", service);
        formData.put("path", path);
        formData.put("method", method);
        return request(Constant.ValidatePermActionURL, Method.POST, formData);
    }

    @Override
    public UcsResult ValidatePermOrgById(String orgId) {
        Map<String, Object> formData = new HashMap<>();
        formData.put("id", orgId);
        return request(Constant.ValidatePermOrgByIdURL, Method.POST, formData);
    }

    private UcsResult request(String url, Method method, Map<String, Object> formData) {
        prepare();
        boolean success = false;
        String reason = Constant.UNKNOWN_MSG;
        try {
            HttpRequest req;
            switch (method) {
                case POST:
                    req = HttpRequest.post(this.baseUrl + url);
                    break;
                case PUT:
                    req = HttpRequest.put(this.baseUrl + url);
                    break;
                case PATCH:
                    req = HttpRequest.patch(this.baseUrl + url);
                    break;
                case DELETE:
                    req = HttpRequest.delete(this.baseUrl + url);
                    break;
                case GET:
                default:
                    req = HttpRequest.get(this.baseUrl + url);
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
                        HttpResult.PermitResult permit = result.getResult().toBean(HttpResult.PermitResult.class);
                        if (permit.getPermit()) {
                            success = true;
                        } else {
                            reason = Constant.UNAUTHORIZED_MSG;
                        }
                    }
                }
                if (!result.getMessage().isEmpty()) {
                    reason = result.getMessage();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return UcsResult.builder()
                .success(success)
                .reason(success ? "" : reason)
                .build();
    }

    private void prepare() {
        if (this.token == null || this.token.isEmpty()) {
            throw new IllegalArgumentException("please provide token first");
        }
        if (this.baseUrl == null || this.baseUrl.isEmpty()) {
            this.baseUrl = String.format("http%s://%s:%d", (this.ssl ? "s" : ""), this.address, this.port);
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
