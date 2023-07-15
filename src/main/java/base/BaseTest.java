package base;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import config.Config;
import org.testng.annotations.*;

import java.util.Properties;

public class BaseTest {
    @BeforeMethod
    public void setUp(){
//        Configuration.browser = Config.getProperty(Config.WEB_BROWSER_NAME);
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