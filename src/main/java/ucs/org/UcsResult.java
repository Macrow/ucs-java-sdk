package ucs.org;

import lombok.*;

/**
 * @author Macrow
 * @date 2022-03-16
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
public class UcsResult {
    private Boolean success;
    private String reason;
}
