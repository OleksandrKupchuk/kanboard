package json.request;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ParamsUpdateProject {
    private int project_id;
    private String name;
    private String description;
    private int owner_id;
    private String identifier;
    private String start_date;
    private String end_date;
}