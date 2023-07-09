package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class ProjectPage {
    private SelenideElement buttonAddTask = $x("//span[@class='dropdown']/a[text()='Ready ']/../../..//a[@title='Add a new task']");

    public ProjectPage addTask(String nameColumn){
        buttonAddTask.click();
        return this;
    }
}