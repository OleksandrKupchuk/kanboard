package pages.project;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class ProjectPage {
    private String addTaskButton = "//span[@class='dropdown']/a[text()='%s ']/../../..//a[@title='Add a new task']";
    private String column = "//td[contains(concat(' ', normalize-space(@class), ' '), 'board-column-%s')]";
    private String columnSelector = ".board-column-%s";
    private String taskInColumn = "//td[contains(concat(' ', normalize-space(@class), ' '), 'board-column-%s')]//div[@class='task-board-title']/a[text()='%s']";
    private String taskStatus = "//strong[text() = '%s:']/../span[text()[contains(.,'%s')]]";
    private SelenideElement actionProjectMenuButton = $(".action-menu.dropdown-menu");
    private String dropDownMenu = "//ul[@class='dropdown-submenu-open']//a[text()='%s']";

    public ProjectPage clickAddTaskButton(String nameColumn) {
        $x(String.format(addTaskButton, nameColumn)).click();
        return this;
    }

    public ProjectPage clickOnTask(int idColumn, String taskName) {
        $x(String.format(taskInColumn, idColumn, taskName)).click();
        return this;
    }

    public ProjectPage clickOnActionProjectMenuButton() {
        actionProjectMenuButton.click();
        return this;
    }

    public ProjectPage selectMenuFromDropdown(String nameMenu) {
        $x(String.format(dropDownMenu, nameMenu)).click();
        return this;
    }

    public ProjectPage assertExistTaskInColumn(int idColumn, String taskName){
        $x(String.format(taskInColumn, idColumn, taskName)).shouldBe(Condition.exist);
        return this;
    }

    public ProjectPage assertNotExistTaskInColumn(int idColumn, String taskName){
        $x(String.format(taskInColumn, idColumn, taskName)).shouldNotBe(Condition.exist);
        return this;
    }

    public ProjectPage assertTaskField(String nameField, String value){
        $x(String.format(taskStatus, nameField, value)).shouldBe(Condition.exist);
        return this;
    }

    public ProjectPage dragTask(int fromIdColumn, int toIdColumn){
        Actions action = new Actions(WebDriverRunner.getWebDriver());
        WebElement fromColumn = $x(String.format(column, fromIdColumn));
        WebElement toColumn = $x(String.format(column, toIdColumn));
        action.dragAndDrop(fromColumn, toColumn).build().perform();
        return this;
    }
}