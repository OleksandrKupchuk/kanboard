package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class TaskPage<T> {
    private T page;
    private SelenideElement title = $("#form-title");

    public TaskPage<T> setTitle(String name){
        title.setValue(name);
        return this;
    }
}