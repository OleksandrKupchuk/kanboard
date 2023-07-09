package base;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.testng.annotations.*;

public class BaseTest {

    @BeforeMethod
    public void setUp(){
        Configuration.browser = "chrome";
        Selenide.open();
    }

    @AfterMethod
    public void tearDown(){
        Selenide.closeWindow();
        Selenide.closeWebDriver();
    }
}