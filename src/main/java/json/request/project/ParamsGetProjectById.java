package json.request.project;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ParamsGetProjectById {
    private int project_id;
}