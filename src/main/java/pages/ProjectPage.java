package pages;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.$x;

public class ProjectPage {
    private String buttonAddTask = "//span[@class='dropdown']/a[text()='%s ']/../../..//a[@title='Add a new task']";
    private String taskInColumn = "//td[contains(concat(' ', normalize-space(@class), ' '), 'board-column-%s')]//div[@class='task-board-title']/a[text()='%s']";

    public ProjectPage clickAddTask(String nameColumn) {
        $x(String.format(buttonAddTask, nameColumn)).click();
        return this;
    }

    public ProjectPage clickOnTask(int idColumn, String taskName) {
        $x(String.format(taskInColumn, idColumn, taskName)).click();
        return this;
    }

    public ProjectPage assertExistTaskInBackgroundColumn(int idColumn, String taskName){
        $x(String.format(taskInColumn, idColumn, taskName)).shouldBe(Condition.exist);
        return this;
    }
}