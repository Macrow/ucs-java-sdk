package ucs.org;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Macrow
 * @date 2022-03-20
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
public class RenewTokenResult {
    private String token;
}