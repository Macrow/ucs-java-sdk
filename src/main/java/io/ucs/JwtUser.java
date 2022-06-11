package io.ucs;

import lombok.*;

import java.util.Date;

/**
 * @author Macrow
 * @date 2022-03-16
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JwtUser {
    private String id;
    private String name;
    private String deviceId;
    private String deviceName;
    private String issuer;
    private Date issueAt;
    private Date expireAt;
    private String token;
}
