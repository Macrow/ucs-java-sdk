package ucs.org;

import cn.hutool.json.JSONConfig;
import cn.hutool.json.JSONUtil;
import org.junit.Test;

public class ModuleTest {
    final String TOKEN = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJkaWQiOiJhZG1pbl93ZWIiLCJkbiI6IkNocm9tZSIsImV4cCI6MTY4NjMyNjcwMywiaWF0IjoxNjU0NzkwNzAzLCJpZCI6ImNhaDFrOHV2OW1jNnU1dTdmaWNnIiwiaXNzIjoidWNzIiwibmFtZSI6InJvb3QifQ.GtGvfltbGmV79SWoxaPX6dYrTyaHGLak_Zg3D7PfujJWMDBi5R8s0POS2TRm7LNFZxUeqRancjj9EGPnKdWsw9oH_nCPBjVhF_YY0U9CqtMnI6WAIrtIt9ouOJxfIW_TmJQumHzaqrclULAoL-_-LgoKJFiLuHhcOtsuinK0eH0UHsF7ruW0YY1a1E3pg6gKVjom17Y1V1RaLjDQpirqojfQtqZjgpaaa2IRBMSvmtLXfG1BFAIQd_SSr3EqugxQItkp5rQdMJzNWHhHn041pmE2dHXA1n7UmBha9z1q2jo8u4EkPzUYD2AxRJVs3k1GiesByGR_UOyrVbmS1ojYFQ";
    final String CLIENT_ID = "wsTDJzgAKg";
    final String CLIENT_SECRET = "123456";

    @Test
    public void testHttpNormal() {
        Client c = new HttpClient("http://localhost:8019", "1A2B3C4D");
        c = c.SetUserToken(TOKEN).SetClientIdAndSecret(CLIENT_ID, CLIENT_SECRET);
        testValidateStaff(c);
    }

    @Test
    public void testHttpTLS() {
        Client c = new HttpClient("https://localhost:8019", "1A2B3C4D");
        c = c.SetUserToken(TOKEN);
        testValidateStaff(c);
    }

    private void testValidateStaff(Client c) {
        UcsResult res;
        res = c.UserValidateJwt();
        print(res);

        res = c.UserValidatePermOperationByCode("UCS_USER_LIST");
        print(res);

        res = c.UserValidatePermAction("ucs", "/api/v1/ucs/users", "get");
        print(res);

        res = c.UserValidatePermActionWithOrgId("ucs", "/api/v1/ucs/users", "get", "c8fjca649b3hbmov5n60");
        print(res);

        res = c.UserValidatePermActionWithOrgId("ucs", "/api/v1/ucs/users", "get", "fasdfasdfasdf");
        print(res);

        res = c.UserValidatePermOrgById("fasdfasdfasdf");
        print(res);

        res = c.UserValidatePermOrgById("c8fjca649b3hbmov5n60");
        print(res);

        res = c.UserQueryOrgIdsByAction("ucs", "/api/v1/ucs/users", "get");
        print(res);

        UcsResult<Object> clientRes = c.ClientRequest("POST", "/api/v1/ucs/client/validate", null);
        print(clientRes);
    }

    private void print(UcsResult res) {
        System.out.println(JSONUtil.toJsonStr(res, JSONConfig.create().setIgnoreNullValue(false)));
    }
}
