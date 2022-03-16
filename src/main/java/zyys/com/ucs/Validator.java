package zyys.com.ucs;

import cn.hutool.core.codec.Base64Decoder;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Date;

/**
 * @author Macrow
 * @date 2022-03-16
 */
public class Validator {
    public static final String JwtTokenClaimsId = "id";
    public static final String JwtTokenClaimsName = "name";
    public static final String JwtTokenClaimsDeviceId = "did";
    public static final String JwtTokenClaimsIssuer = "iss";
    public static final String JwtTokenClaimsIssueAt = "iat";
    public static final String JwtTokenClaimsExpireAt = "exp";

    private final PublicKey parsedPublicKey;

    public Validator(String signKey) {
        String publicKeyPEM = signKey
                .replace("-----BEGIN PUBLIC KEY-----", "")
                .replaceAll(System.lineSeparator(), "")
                .replace("-----END PUBLIC KEY-----", "");
        byte[] encoded = Base64Decoder.decode(publicKeyPEM);
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(encoded);
            this.parsedPublicKey = keyFactory.generatePublic(keySpec);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("invalid public key");
        }
    }

    public JwtUser ValidateJwt(String tokenString) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(this.parsedPublicKey)
                .build()
                .parseClaimsJws(tokenString)
                .getBody();

        return JwtUser.builder()
                .id(claims.get(JwtTokenClaimsId, String.class))
                .name(claims.get(JwtTokenClaimsName, String.class))
                .did(claims.get(JwtTokenClaimsDeviceId, String.class))
                .iss(claims.get(JwtTokenClaimsIssuer, String.class))
                .iat(claims.get(JwtTokenClaimsIssueAt, Date.class))
                .exp(claims.get(JwtTokenClaimsExpireAt, Date.class))
                .token(tokenString)
                .build();
    }
}
