package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private SelenideElement userNameField = $("#form-username");
    private SelenideElement passwordField = $("#form-password");
    private SelenideElement signInButton = $(".btn.btn-blue");
    private SelenideElement alertError = $(".alert.alert-error");

    public LoginPage open(){
        Selenide.open("http://localhost/login");
        return this;
    }

    public LoginPage login(String userName, String password){
        setUserName(userName);
        setPassword(password);
        clickSignInButton();
        return this;
    }

    public LoginPage setUserName(String userName){
        userNameField.setValue(userName);
        return this;
    }

    public LoginPage setPassword(String password){
        passwordField.setValue(password);
        return this;
    }

    public LoginPage clickSignInButton(){
        signInButton.click();
        return this;
    }

    public LoginPage assertAlertErrorText(String description){
        alertError.shouldHave(Condition.exactText(description));
        return this;
    }
}