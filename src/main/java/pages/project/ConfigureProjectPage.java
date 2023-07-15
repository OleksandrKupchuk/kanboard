package pages.project;

import pages.modal.CloseElementModalWindow;

import static com.codeborne.selenide.Selenide.$x;

public class ConfigureProjectPage {
    private CloseElementModalWindow<ConfigureProjectPage> closeModalWindow = new CloseElementModalWindow<>(this);
    private String sidebarMenu = "//div[@class='sidebar']//ul//a[text() = '%s']";

    public CloseElementModalWindow<ConfigureProjectPage> getCloseModalWindow(){return closeModalWindow;}

    public ConfigureProjectPage selectSidebarMenu(String nameMenu){
        $x(String.format(sidebarMenu, nameMenu)).click();
        return this;
    }
}