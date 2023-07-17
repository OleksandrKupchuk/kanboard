package pages.project;

import base.BasePage;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class CreateProjectPage {
    private BasePage<CreateProjectPage> basePage = new BasePage<>(this);
    private SelenideElement nameField = $("#form-name");
    private SelenideElement identifierField = $("#form-identifier");

    public BasePage<CreateProjectPage> getBasePage() {
        return basePage;
    }

    public CreateProjectPage setName(String name) {
        nameField.setValue(name);
        return this;
    }

    public CreateProjectPage setIdentifier(String identifier) {
        identifierField.setValue(identifier);
        return this;
    }
}