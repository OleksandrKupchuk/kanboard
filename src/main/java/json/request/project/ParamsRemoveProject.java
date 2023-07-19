package json.request.project;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ParamsRemoveProject {
    private String project_id;
}