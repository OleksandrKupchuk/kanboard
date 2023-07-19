package pages.modal;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$x;

public class CommentModalWindow<T> {
    private T page;
    private SelenideElement textArea = $x("//div[@id='modal-overlay']//textarea");
    private SelenideElement saveButton = $x("//div[@id='modal-overlay']//button");
    public CommentModalWindow(T page){
        this.page = page;
    }

    public CommentModalWindow<T> setComment(String comment){
        textArea.setValue(comment);
        return this;
    }

    public T clickSaveButton(){
        saveButton.click();
        return page;
    }
}