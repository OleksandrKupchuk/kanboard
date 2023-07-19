package data.task;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TaskPriority {
    ZERO(0),
    ONE(1),
    TWO(2),
    THREE(3);

    private int value;
}