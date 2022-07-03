package io.ucs;

import cn.hutool.json.JSONConfig;
import cn.hutool.json.JSONUtil;
import io.ucs.sdk.Client;
import io.ucs.sdk.ClientAuthType;
import io.ucs.sdk.UcsHttpClient;
import io.ucs.sdk.entity.UcsResult;
import org.junit.Test;

public class ModuleTest {
    final String TOKEN = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJkaWQiOiJhZG1pbl93ZWIiLCJkbiI6IkNocm9tZSIsImV4cCI6MTY4ODM3NTYxMiwiaWF0IjoxNjU2ODM5NjEyLCJpZCI6ImNiMGxyYnV2OW1jNzE5NzY0ZXBnIiwiaXNzIjoidWNzIiwibmFtZSI6InJvb3QifQ.4IQ5Ewy6FCB8cs2gWulS57iSC7AVUr5B4klNXOSYRof0yX3V4UktrVV1SX9mlhv3oc3Js_tLY9CtPizX8f5yGlWlkjyRZYrg0ueKOFnquRrsF3n7SwqIMCVDRxD9ale1vxxn4aSL8H-ZH3yXzXoqIy-dJPYqqmlO362L0WfxT6jyRLzVTr7pis9MZuircPJVnC5HTvKL4_Qb2V_7zvC3mly1s6lEnlXQ4waTsRrCzsh57px19YluWDJY_jlXFKTBdu6Mot11CNz_n0UpRSsUaQYr9BuhGpEauvjpAPqYr3JTDzDxl02OJ-OrCjTV_E4N0lxRlIS57uqiFSXTzNlXtA";
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

        res = c.userValidatePermByOperation("UCS_USER_LIST", true, true);
        print(res);

        res = c.userValidatePermByAction("ucs", "get", "/api/v1/ucs/users", false, true);
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
