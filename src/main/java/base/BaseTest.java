package base;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import config.Config;
import org.testng.annotations.*;

public class BaseTest {

    @BeforeMethod
    public void setUp(){
        Configuration.browser = Config.WEB_BROWSER_NAME;
        Configuration.headless = Config.BROWSER_HEADLESS;
        Selenide.open();
        WebDriverRunner.getWebDriver().manage().window().maximize();
    }

    @AfterMethod
    public void tearDown(){
        Selenide.closeWindow();
        Selenide.closeWebDriver();
    }
}