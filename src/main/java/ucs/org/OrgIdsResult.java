package ucs.org;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author Macrow
 * @date 2022-03-25
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
public class OrgIdsResult {
    private String orgPermissionType;
    private List<String> orgIds;
}