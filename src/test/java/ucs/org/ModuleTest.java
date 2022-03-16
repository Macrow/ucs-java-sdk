package ucs.org;

import cn.hutool.json.JSONUtil;
import org.junit.Test;

public class ModuleTest {
    final String CERT = "-----BEGIN CERTIFICATE-----\n" +
            "MIIEOTCCAqGgAwIBAgIQVcpmr67YkofgWY2kgr7CNDANBgkqhkiG9w0BAQsFADB9\n" +
            "MR4wHAYDVQQKExVta2NlcnQgZGV2ZWxvcG1lbnQgQ0ExKTAnBgNVBAsMIG1hY3Jv\n" +
            "d0BNYWNyb3ctbWJwLmxvY2FsIChNYWNyb3cpMTAwLgYDVQQDDCdta2NlcnQgbWFj\n" +
            "cm93QE1hY3Jvdy1tYnAubG9jYWwgKE1hY3JvdykwHhcNMjIwMzE2MDMxMTA5WhcN\n" +
            "MjQwNjE2MDMxMTA5WjBUMScwJQYDVQQKEx5ta2NlcnQgZGV2ZWxvcG1lbnQgY2Vy\n" +
            "dGlmaWNhdGUxKTAnBgNVBAsMIG1hY3Jvd0BNYWNyb3ctbWJwLmxvY2FsIChNYWNy\n" +
            "b3cpMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA2ERrTEnQUHviw17Q\n" +
            "qsoSMz0VPcL7nnVRJL85oC+xKuCRYN2VxGI4kda3p5PQICM9Hn/mS6TrgoG8hV0B\n" +
            "6k1rLUbc8vWbCUF1aTzH4yuBsdJMAhMp49cuTfvI6dpPNuKbIiP1VnatwUJK1Uwc\n" +
            "cEtJ4WwW0XLl6Y9dZSFZmModY3b/DBOYsQMCdzQdRh2hHLRKcA2Lqt7pwKQyUQcq\n" +
            "7nC+au12iYItA78W5cSI6jUY8MWlEWrikbZWTMaCFmfcc9vphFhgM5Nu8kXIZkQ8\n" +
            "q7aEonoAa2NSKZyBn+E5qk3nL0TpInWwzFIKJF9Fg/hj/eMYXGMCxBLZ/hvsMdwD\n" +
            "CqL2/QIDAQABo14wXDAOBgNVHQ8BAf8EBAMCBaAwEwYDVR0lBAwwCgYIKwYBBQUH\n" +
            "AwEwHwYDVR0jBBgwFoAUWxPQtO3iGndFhebFMci4Dzp//cwwFAYDVR0RBA0wC4IJ\n" +
            "bG9jYWxob3N0MA0GCSqGSIb3DQEBCwUAA4IBgQCc0YQSZZtGerarWqNmUsPhaMsX\n" +
            "k3SiHZSCXYdP8QF/b6QQvVaFUpV+FOC4eySrVe3U/JfB7qNmGJITAr5Q5kM5qsue\n" +
            "D35LNz57xYtxRhRD1sqI7Asvp6crtrdlNeYPKVeS50/lqQ8IJDnEbHa0/V6QxBVf\n" +
            "JRQ9n15rznVJO6B512k8QVl0qNfiBfzwsW8AVyTGglooHw3GPvZX7ctZ/InRX3WV\n" +
            "bbtdvgXfqYIKtNb/X5q8O5zwjhneUbrlRSIFYwZDDBKMyyOi6hRtSM/5ZHhoOTgw\n" +
            "2a1hxt9kpaXjMfOFygPGDlVE+eIU0ERFJ4QKC5Uc1AAwqomqoYbwphTrd2FaFGLB\n" +
            "/upVraZs8XO9G7DGRZ9ZiTTa8k1oXBOMtbrn2hc+sRuZvycp0fI2uUOwzdaW6fhA\n" +
            "HgWsFCHMumasp4fm6wy8mWmpSMnj1OBvqLhtjfUjOULX4EFI9hp9L3xXfsV614/5\n" +
            "VYEgTSBFOCr6W/2vEvXLnWzdR8gdyrsOyshbiCE=\n" +
            "-----END CERTIFICATE-----";
    final String KEY = "-----BEGIN PUBLIC KEY-----\n" +
            "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA6Y8ohl2AjcYSDkOLzU9a\n" +
            "rh4I/nsHGZ8fGY2ojOKzRvFAOxMoL46qqiPSYSr5tsAMuI9+mT8eOI2g6EJffyA2\n" +
            "PcbWohN51g+BnYVhI+rZc2GDTtxeR6VIAbMiPv/7hnpGaf/6+eJXzCz2m7SWtsnp\n" +
            "p9MLYGQIgSdXwEn5JmcCNOWl3ES2AhDEAOvgkA0t019vAT5j+eOC3yEmWmjA/mK3\n" +
            "XoME02v2y4wRjqR9woGI/q24KQ79lIzOeH7xmJ46NCqVMyVagQ7n5KPEECsckBAv\n" +
            "exQvcpelg4C5uA3igl9kyOP8dyvEJKJys9WdO1RU454qn5Kb5CR07ltFC91p4XGo\n" +
            "CQIDAQAB\n" +
            "-----END PUBLIC KEY-----";
    final String TOKEN = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJkaWQiOiJhZG1pbl93ZWIiLCJleHAiOjE2Nzg5MzA2NjAsImlhdCI6MTY0NzM5NDY2MCwiaWQiOiJjOGZqYzl1NDliM2hibW92NWl1ZyIsImlzcyI6InVjcyIsIm5hbWUiOiJyb290In0.YVlCteAlS01LwxShyFXPlU_PTvaanRSwB1aZ78PMbr73cZabA-v7K9x3yz_LQJ9NKTUbnO_gLGxTVPgCwOR7hiyxy2QO5ZNxGOqJybJVe-1LfQ_zKIHzVKHBg3qZoRkcS-IbdfqQiSk_sfOlaWOsoMokEQvtsEXEt9g60b3sKz2UU5b57-0PUmZfawrE-3hsPTOk4APpQDmTZo2y609DHtTuozVNRXTsn7EE4ItPHBHp7ofJpUGrHbXj80Eu7G25aEkmlZw-oaQ06qH7O2VHjfTX1rjVCFALHXew8SPrRsuQrAjaoTWF-uCSaVZIqGDtzHjljeeBkG0K0Jr0lFcLgA";

    @Test
    public void testValidator() {
        Validator v = new Validator(KEY);
        JwtUser jwtUser = v.ValidateJwt(TOKEN);
        System.out.println(JSONUtil.toJsonStr(jwtUser));
    }

    @Test
    public void testNormal() {
        Client c = new Client("localhost", 8919);
        c = c.SetToken(TOKEN);
        testValidateStaff(c);
    }

    @Test
    public void testTLS() {
        Client c = new Client(CERT, "localhost", 8919);
        c = c.SetToken(TOKEN);
        testValidateStaff(c);
    }

    private void testValidateStaff(Client c) {
        ValidateResult res = c.ValidateJwt();
        System.out.println(JSONUtil.toJsonStr(res));

        res = c.ValidatePermOperationByCode("UCS_USER_LIST");
        System.out.println(JSONUtil.toJsonStr(res));

        res = c.ValidatePermAction("ucs", "/api/v1/ucs/users", "get");
        System.out.println(JSONUtil.toJsonStr(res));

        res = c.ValidatePermOrgById("fasdfasdfasdf");
        System.out.println(JSONUtil.toJsonStr(res));

        res = c.ValidatePermOrgById("c8fjca649b3hbmov5n60");
        System.out.println(JSONUtil.toJsonStr(res));
    }
}