package pages.modal;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class CloseElementModalWindow<T> {
    private T page;
    private SelenideElement alertInfo = $(".alert.alert-info");
    private SelenideElement yesButton = $("#modal-confirm-button");
    public CloseElementModalWindow(T page){
        this.page = page;
    }

    public T clickYesButton(){
        yesButton.click();
        return page;
    }

    public CloseElementModalWindow<T> assertAlertInfo(String alertInfo, String taskName){
        String text = String.format(alertInfo, taskName);
        this.alertInfo.shouldHave(Condition.text(text));
        return this;
    }
}