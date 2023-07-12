package json.request.project;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ParamsGetProjectByName {
    private String name;
}