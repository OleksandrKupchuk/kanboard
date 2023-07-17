package pages.task;

import base.BasePage;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class TaskCreatePage {
    private BasePage<TaskCreatePage> basePage = new BasePage<>(this);
    private SelenideElement title = $("#form-title");
    private SelenideElement description = $x("//textarea[contains(@name, 'description')]");
    private SelenideElement priority = $("#form-priority");
    private String priorityDropDown = "//select[@id='form-priority']/option[@value='%s']";
    private SelenideElement estimate = $("#form-time_estimated");
    private SelenideElement startDate = $("#form-date_started");
    private String day = "//td[@data-handler='selectDay']/a[text()='11']";

    public BasePage<TaskCreatePage> getBasePage() {
        return basePage;
    }

    public TaskCreatePage setTitle(String title){
        this.title.setValue(title);
        return this;
    }

    public TaskCreatePage setDescription(String description){
        this.description.setValue(description);
        return this;
    }

    public TaskCreatePage setPriority(int priority){
        this.priority.click();
        $x(String.format(priorityDropDown, priority)).click();
        return this;
    }

    public TaskCreatePage setEstimate(int hours){
        this.estimate.setValue(Integer.toString(hours));
        return this;
    }

    public TaskCreatePage setStartDate(int day){
        this.startDate.click();
        $x(String.format(this.day, day)).click();
        return this;
    }

    /**
     * Format MM/DD/YYYY
     */
    public TaskCreatePage setStartDate(String data){
        this.startDate.setValue(data);
        return this;
    }
}