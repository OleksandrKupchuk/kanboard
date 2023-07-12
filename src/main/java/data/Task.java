package data;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Task {
    private String title;
    private int project_id;
    private String color_id;
    private int column_id;
    private String description;
}