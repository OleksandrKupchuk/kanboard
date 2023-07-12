package json.request.task;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ParamsCreateTask {
    private String title;
    private int project_id;
    private String color_id;
    private int column_id;
    private String description;
}