package json.request.project;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ParamsCreateProject {
    private String name;
}