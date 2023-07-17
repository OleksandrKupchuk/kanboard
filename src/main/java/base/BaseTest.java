package base;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.testng.annotations.*;

public class BaseTest {
    String name_chrome = "test";
    boolean headless_chrome = true;
    @BeforeMethod
    public void setUp(){
        name_chrome = System.getProperty("browser_name");
        headless_chrome = Boolean.parseBoolean(System.getProperty("browser_headless"));
        Configuration.browser = System.getProperty("browser_name");
        Configuration.headless =  Boolean.parseBoolean(System.getProperty("browser_headless"));
//        Configuration.timeout = 7000;
        Selenide.open();
        WebDriverRunner.getWebDriver().manage().window().maximize();
    }

    @AfterMethod
    public void tearDown(){
        Selenide.closeWindow();
        Selenide.closeWebDriver();
    }
}