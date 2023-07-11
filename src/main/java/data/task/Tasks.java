package data.task;

public class Tasks {
    public static Task AUTOMATION = Task.builder()
            .title("Automation")
            .description("Need add automation")
            .priority(TaskPriority.TWO.getValue())
            .estimation(5)
            .startDate("09/11/2023")
            .build();

    public static Task MANUAL = Task.builder()
            .title("Manual")
            .description("Need manual testing")
            .priority(TaskPriority.ONE.getValue())
            .estimation(1)
            .startDate("09/11/2023")
            .build();
}
