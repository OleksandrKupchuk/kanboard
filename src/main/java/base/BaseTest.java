package base;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.testng.annotations.*;

public class BaseTest {
    @BeforeMethod
    public void setUp(){
        Configuration.browser = System.getProperty("browser_name");
        Selenide.open();
        WebDriverRunner.getWebDriver().manage().window().maximize();
    }

    @AfterMethod
    public void tearDown(){
        Selenide.closeWindow();
        Selenide.closeWebDriver();
    }
}