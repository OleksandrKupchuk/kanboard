package pages;

import base.BasePage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import pages.modal.CommentModalPage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class TaskPage {
    private BasePage<TaskPage> basePage = new BasePage<>(this);
    private CommentModalPage<TaskPage> commentModalPage = new CommentModalPage<>(this);
    private SelenideElement commentButton = $(".fa.fa-comment-o.fa-fw.js-modal-small");
    private String commentForTask = "//div[@class='comment ']//p[text()='%s']";

    public BasePage<TaskPage> getBasePage() {
        return basePage;
    }
    public CommentModalPage<TaskPage> getcommentModalPage() {
        return commentModalPage;
    }

    public TaskPage clickCommentButton(){
        commentButton.click();
        return this;
    }

    public TaskPage assertVisibleComment(String comment){
        $x(String.format(commentForTask, comment)).shouldBe(Condition.visible);
        return this;
    }
}