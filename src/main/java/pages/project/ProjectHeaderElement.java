package pages.project;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class ProjectHeaderElement {
    private SelenideElement boardButton = $(".view-board");

    public ProjectHeaderElement clickBoardButton(){
        boardButton.click();
        return this;
    }
}