package pages;

import base.BasePage;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class CreateProjectPage extends BasePage {
    private SelenideElement nameField = $("#form-name");
    private SelenideElement identifierField = $("#form-identifier");
    private SelenideElement saveButton = $(".btn.btn-blue");

    public CreateProjectPage setName(String name){
        nameField.setValue(name);
        return this;
    }

    public CreateProjectPage setIdentifier(String identifier){
        identifierField.setValue(identifier);
        return this;
    }

    public CreateProjectPage clickSaveButton(){
        saveButton.click();
        return this;
    }
}