package pages.task;

import com.codeborne.selenide.Condition;
import pages.modal.*;

import static com.codeborne.selenide.Selenide.$x;

public class TaskPage {
    private CommentModalWindow<TaskPage> commentModalWindow = new CommentModalWindow<>(this);
    private CloseElementModalWindow<TaskPage> closeTaskModalWindow = new CloseElementModalWindow<>(this);
    private String sidebarButton = "//div[@class='sidebar sidebar-icons']//li/a[text() = '%s']";
    private String commentForTask = "//div[@class='comment ']//p[text()='%s']";

    public CommentModalWindow<TaskPage> getCommentModalWindow() {
        return commentModalWindow;
    }
    public CloseElementModalWindow<TaskPage> getCloseTaskModalWindow() {
        return closeTaskModalWindow;
    }

    public TaskPage clickSidebarButton(String nameButton){
        $x(String.format(sidebarButton, nameButton)).click();
        return this;
    }

    public TaskPage assertVisibleComment(String comment){
        $x(String.format(commentForTask, comment)).shouldBe(Condition.visible);
        return this;
    }
}