package logintests;

import base.BaseTest;
import org.testng.annotations.*;
import pages.KanboardMainPage;
import pages.LoginPage;

public class LoginTests extends BaseTest {

    @Test
    public void testSuccessLogin() {
        new LoginPage()
                .open()
                .login("admin", "admin");

        new KanboardMainPage()
                .assertDashboardTitle("Dashboard for admin");
    }

    @Test
    public void testUnsuccessfulLogin() {
        new LoginPage()
                .open()
                .login("admin1", "admin1")
                .assertAlertErrorText("Bad username or password");
    }
}