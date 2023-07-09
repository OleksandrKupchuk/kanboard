package base;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class BasePage {
    private SelenideElement dashboardTitle = $(".title");

    public BasePage assertDashboardTitle(String title){
        dashboardTitle.shouldHave(Condition.text(title));
        return this;
    }
}