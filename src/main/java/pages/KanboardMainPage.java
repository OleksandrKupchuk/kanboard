package pages;

import base.BasePage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class KanboardMainPage {
    private BasePage<KanboardMainPage> basePage = new BasePage<>(this);
    private SelenideElement logo = $(".logo");
    private SelenideElement headerNewProjectButton = $x("//div[@class='page-header']//a[contains(text(), 'New project')]");
    private String projectName = "//div[@class='table-list']//a[text()='%s']";

    public BasePage<KanboardMainPage> getBasePage() {
        return basePage;
    }
    public KanboardMainPage open(){
        logo.click();
        return this;
    }

    public KanboardMainPage clickHeaderNewProjectButton(){
        headerNewProjectButton.click();
        return this;
    }

    public KanboardMainPage clickProjectName(String projectName){
        $x(String.format(this.projectName, projectName)).click();
        return this;
    }

    public KanboardMainPage assertProjectName(String projectName){
        $x(String.format(this.projectName, projectName)).shouldBe(Condition.exist);
        return this;
    }
}