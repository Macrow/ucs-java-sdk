package ucs.org;

import cn.hutool.json.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Macrow
 * @date 2022-03-17
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
public class HttpResult {
    private JSONObject result;
    private Integer code;
    private String message;

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    public static class PermitResult {
        private Boolean permit;
    }
}
