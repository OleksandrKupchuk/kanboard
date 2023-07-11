package data.task;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Task {
    private String title;
    private String description;
    private int priority;
    private int estimation;
    private String startDate;
}