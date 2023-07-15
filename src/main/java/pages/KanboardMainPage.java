package pages;

import base.BasePage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static data.ProfileMenu.*;

public class KanboardMainPage {
    private BasePage<KanboardMainPage> basePage = new BasePage<>(this);
    private SelenideElement logo = $(".logo");
    private SelenideElement newProjectButton = $x("//div[@class='page-header']//a[contains(text(), 'New project')]");
    private String projectName = "//div[@class='table-list']//a[text()='%s']";
    private SelenideElement profileMenu = $x("//div[@class='menus-container']//div[@class='avatar-letter']");
    private String dropDownProfileMenu = "//ul[@class='dropdown-submenu-open']//a[text()='%s']";

    public BasePage<KanboardMainPage> getBasePage() {
        return basePage;
    }

    public KanboardMainPage logout(){
        clickOnProfileMenu();
        selectProfileMenu(LOGOUT);
        return this;
    }

    public KanboardMainPage clickOnLogo(){
        logo.click();
        return this;
    }

    public KanboardMainPage clickNewProjectButton(){
        newProjectButton.click();
        return this;
    }

    public KanboardMainPage clickOnProjectName(String projectName){
        $x(String.format(this.projectName, projectName)).click();
        return this;
    }

    public KanboardMainPage clickOnProfileMenu(){
        profileMenu.click();
        return this;
    }

    public KanboardMainPage selectProfileMenu(String profileMenu){
        $x(String.format(dropDownProfileMenu, profileMenu)).click();
        return this;
    }

    public KanboardMainPage assertExistProjectName(String projectName){
        $x(String.format(this.projectName, projectName)).shouldBe(Condition.exist);
        return this;
    }

    public KanboardMainPage assertNotExistProjectName(String projectName){
        $x(String.format(this.projectName, projectName)).shouldNotBe(Condition.exist);
        return this;
    }
}