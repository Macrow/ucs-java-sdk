package io.ucs;

import cn.hutool.json.JSONConfig;
import cn.hutool.json.JSONUtil;
import io.ucs.sdk.Client;
import io.ucs.sdk.ClientAuthType;
import io.ucs.sdk.UcsHttpClient;
import io.ucs.sdk.entity.UcsResult;
import org.junit.Test;

public class ModuleTest {
    final String TOKEN = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJkaWQiOiJhZG1pbl93ZWIiLCJkbiI6IkNocm9tZSIsImV4cCI6MTY4ODU1Mjk4NSwiaWF0IjoxNjU3MDE2OTg1LCJpZCI6ImNiMWIxY2V2OW1jNzZiZDdwdWxnIiwiaXNzIjoidWNzIiwibmFtZSI6InJvb3QifQ.KHEycravvnJba6GoXrYSlytBzLwZhDqBQz5LaeaD_77SdgSibD_WTJrJ8AKju4KN00RQTCLG_yKS8kuFovcxUsR0ysKPCZ8QbyGG1UT0BdMj5ZXJ5KFX8Y-IqG-L6RER_ZNQYdOa9CRv4vbT5gJp-EXa1XZi48n7u-jcUZUeklPezgAjmBLOd-DjD3VWDhrhTJUoX1WXt6Lrk9G5NotRNUxkjvuiAlmf5jooWul_wH-U-YTIUiYZPpAQBrS2kW-WhrI3-3wuHQhJCJdK3muv2YvWQby56HJdo8gz2TvK8lZbvTgNHLWV8zA_z6yIqn6N83031Xj3_NdhlKsStO4atQ";
    final String ACCESS_CODE = "1A2B3C4D";
    final String CLIENT_ID = "wsTDJzgAKg";
    final String CLIENT_SECRET = "123456";

    @Test
    public void testHttpNormal() {
        Client c = new UcsHttpClient("http://localhost:8019");
        c = c.setAccessCode(ACCESS_CODE).setUserToken(TOKEN).setClientIdAndSecret(CLIENT_ID, CLIENT_SECRET);
        testValidateStaff(c);
    }

    @Test
    public void testHttpTLS() {
        Client c = new UcsHttpClient("https://localhost:8019");
        c = c.setAccessCode(ACCESS_CODE).setUserToken(TOKEN).setClientIdAndSecret(CLIENT_ID, CLIENT_SECRET);
        testValidateStaff(c);
    }

    private void testValidateStaff(Client c) {
        UcsResult res;
        res = c.setRandomKey(UcsHttpClient.generateRandomKey()).userValidateJwt();
        res = c.setRandomKey(UcsHttpClient.generateRandomKey()).clientValidate(ClientAuthType.ID_AND_SECRET);
        print(res);

        res = c.setRandomKey(UcsHttpClient.generateRandomKey()).userValidatePermByOperation("UCS_USER_LIST", true, true);
        print(res);

        res = c.setRandomKey(UcsHttpClient.generateRandomKey()).userValidatePermByAction("ucs", "get", "/api/v1/ucs/users", false, true);
        print(res);

        UcsResult<Object> userRes = c.userRequest(Object.class, "GET", "/api/v1/ucs/users", null);
        print(userRes);

        UcsResult<Object> clientRes = c.clientRequest(Object.class, "GET", "/api/v1/ucs/client/validate", null, ClientAuthType.ID_AND_SECRET);
        print(clientRes);
    }

    private void print(UcsResult res) {
        System.out.println(JSONUtil.toJsonStr(res, JSONConfig.create().setIgnoreNullValue(false)));
    }
}
