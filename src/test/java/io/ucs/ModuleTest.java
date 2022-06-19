package io.ucs;

import cn.hutool.json.JSONConfig;
import cn.hutool.json.JSONUtil;
import io.ucs.sdk.Client;
import io.ucs.sdk.ClientAuthType;
import io.ucs.sdk.UcsHttpClient;
import io.ucs.sdk.entity.UcsResult;
import org.junit.Test;

public class ModuleTest {
    final String TOKEN = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJkaWQiOiJhZG1pbl93ZWIiLCJkbiI6IkNocm9tZSIsImV4cCI6MTY4NzA1ODI1OSwiaWF0IjoxNjU1NTIyMjU5LCJpZCI6ImNhaDFrOHV2OW1jNnU1dTdmaWNnIiwiaXNzIjoidWNzIiwibmFtZSI6InJvb3QifQ.m2uOt7IlZpfng_UhBM2aeVETjhABp0sreAeqgJRT6QejXhaogNY3qXjr-ANi_oXqsVkA0Tof3z2qCMwl0mrHc5WEHXPvCRr_gOJ184z10Lf1z6cxaaQ4gt1R3TlCHst3DIlyl4iRAstLjfnlmm3aTWYZMjK-d3FXKA6i2yWZAXMInEoijpNMlYFGaojFfEZjlTPTp_Lmj4Spus7s8f_AjvckUJfYcymvRJHR9M7YEgRq2Lu_E-y4IsCGt9PphDah12JFv8-qg6UWFheiNIgg5rcQ0KKZcal73wpm9tmVEpJbn8SBsRV_tMfIOvjC8Vvbfh_-DoYWD3ZNtivrd8VMbg";
    final String CLIENT_ID = "wsTDJzgAKg";
    final String CLIENT_SECRET = "123456";

    @Test
    public void testHttpNormal() {
        Client c = new UcsHttpClient("http://localhost:8019", "1A2B3C4D");
        c = c.setUserToken(TOKEN).setClientIdAndSecret(CLIENT_ID, CLIENT_SECRET);
        testValidateStaff(c);
    }

    @Test
    public void testHttpTLS() {
        Client c = new UcsHttpClient("https://localhost:8019", "1A2B3C4D");
        c = c.setUserToken(TOKEN).setClientIdAndSecret(CLIENT_ID, CLIENT_SECRET);
        testValidateStaff(c);
    }

    private void testValidateStaff(Client c) {
        UcsResult res;
        res = c.userValidateJwt();
        print(res);

        res = c.userValidatePermByOperation("UCS_USER_LIST", false);
        print(res);

        res = c.userValidatePermByAction("ucs", "get", "/api/v1/ucs/users", false);
        print(res);

        UcsResult<Object> userRes = c.userRequest(Object.class, "GET", "/api/v1/ucs/users", null);
        print(userRes);

        UcsResult<Object> clientRes = c.clientRequest(Object.class, "POST", "/api/v1/ucs/client/validate", null, ClientAuthType.ID_AND_SECRET);
        print(clientRes);
    }

    private void print(UcsResult res) {
        System.out.println(JSONUtil.toJsonStr(res, JSONConfig.create().setIgnoreNullValue(false)));
    }
}
