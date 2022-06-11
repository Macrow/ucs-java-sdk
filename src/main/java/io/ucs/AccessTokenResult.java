package io.ucs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Macrow
 * @date 2022-04-01
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
public class AccessTokenResult {
    private String accessToken;
}