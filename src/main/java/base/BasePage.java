package base;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class BasePage<T> {
    private T page;
    private SelenideElement dashboardTitle = $(".title");
    protected SelenideElement saveButton = $(".btn.btn-blue");

    public BasePage(T page) {
        this.page = page;
    }

    public T assertDashboardTitle(String title) {
        dashboardTitle.shouldHave(Condition.text(title));
        return page;
    }

    public T clickSaveButton() {
        this.saveButton.click();
        return page;
    }
}