package logintests;

import base.BaseTest;
import data.user.Users;
import org.testng.annotations.*;
import pages.KanboardMainPage;
import pages.LoginPage;

public class LoginTests extends BaseTest {

    @Test
    public void testSuccessLogin() {
        new LoginPage()
                .open()
                .login(Users.ADMIN);

        new KanboardMainPage()
                .getBasePage()
                .assertDashboardTitle("Dashboard for admin");
    }

    @Test
    public void testUnsuccessfulLogin() {
        new LoginPage()
                .open()
                .login(Users.NOT_EXISTENT)
                .assertAlertErrorText("Bad username or password");
    }
}