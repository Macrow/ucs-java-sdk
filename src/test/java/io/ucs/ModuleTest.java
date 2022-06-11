package io.ucs;

import cn.hutool.json.JSONConfig;
import cn.hutool.json.JSONUtil;
import org.junit.Test;

public class ModuleTest {
    final String TOKEN = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJkaWQiOiJhZG1pbl93ZWIiLCJkbiI6IkNocm9tZSIsImV4cCI6MTY4NjQyMzE3MCwiaWF0IjoxNjU0ODg3MTcwLCJpZCI6ImNhaDFrOHV2OW1jNnU1dTdmaWNnIiwiaXNzIjoidWNzIiwibmFtZSI6InJvb3QifQ.IhgvqpWe9TJvSm1x39HH0LSiKwoZp1ge6GQgDOSKKcbAzArUEFaKJfpoJQUCJVJeq-I8TpUVSEjdwRh8Hty03L0G79POlqb87u-hzh29RmfP9tFNPY565Zm9GyB0kybiWA68ZQriDiTZaUEk1K2N4sq85HIpArV04haSvE9lJ46v2wrNprcVRxjWFWWxAt1qeBZFPuUtFk93A1OIWn2PbxE_fmlE1qVjqwukpanIKR9y3O2geC4F4-ed9qA8VZl0N8IHjMLABE-oIPa0Tlvt9tVoJ1sx0LqlA5GphZHXARDzgr2hdytuE_OxJeyULkadKVvqMZgeNRnwL404DoSx-Q";
    final String CLIENT_ID = "wsTDJzgAKg";
    final String CLIENT_SECRET = "123456";

    @Test
    public void testHttpNormal() {
        Client c = new HttpClient("http://localhost:8019", "1A2B3C4D");
        c = c.setUserToken(TOKEN).setClientIdAndSecret(CLIENT_ID, CLIENT_SECRET);
        testValidateStaff(c);
    }

    @Test
    public void testHttpTLS() {
        Client c = new HttpClient("https://localhost:8019", "1A2B3C4D");
        c = c.setUserToken(TOKEN).setClientIdAndSecret(CLIENT_ID, CLIENT_SECRET);
        testValidateStaff(c);
    }

    private void testValidateStaff(Client c) {
        UcsResult res;
        res = c.userValidateJwt();
        print(res);

        res = c.userValidatePermByOperation("UCS_USER_LIST");
        print(res);

        res = c.userValidatePermByAction("ucs", "get", "/api/v1/ucs/users");
        print(res);

        UcsResult<Object> userRes = c.userRequest("GET", "/api/v1/ucs/users", null);
        print(userRes);

        UcsResult<Object> clientRes = c.clientRequest("POST", "/api/v1/ucs/client/validate", null);
        print(clientRes);
    }

    private void print(UcsResult res) {
        System.out.println(JSONUtil.toJsonStr(res, JSONConfig.create().setIgnoreNullValue(false)));
    }
}
