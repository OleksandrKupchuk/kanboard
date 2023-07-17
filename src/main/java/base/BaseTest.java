package base;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.testng.annotations.*;

public class BaseTest {
    @BeforeMethod
    public void setUp(){
        Configuration.browser = System.getProperty("browser_name");
        Configuration.headless =  Boolean.parseBoolean(System.getProperty("browser_headless"));
        Selenide.open();
        WebDriverRunner.getWebDriver().manage().window().maximize();
    }

    @AfterMethod
    public void tearDown(){
        Selenide.closeWindow();
        Selenide.closeWebDriver();
    }
}