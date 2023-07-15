package login;

import base.BaseTest;
import com.epam.reportportal.testng.ReportPortalTestNGListener;
import data.user.Users;
import org.testng.annotations.*;
import pages.KanboardMainPage;
import pages.LoginPage;

import static data.text.DashboardText.*;
import static data.text.LoginText.*;

@Listeners({ReportPortalTestNGListener.class})
public class LoginTest extends BaseTest {
    @Test
    public void testSuccessLogin() {
        new LoginPage()
                .open()
                .login(Users.ADMIN);

        new KanboardMainPage()
                .getBasePage()
                .assertDashboardTitle(DASHBOARD_TITLE);
    }

    @Test
    public void testUnsuccessfulLogin() {
        new LoginPage()
                .open()
                .login(Users.NOT_EXISTENT)
                .assertAlertErrorText(BAD_CREDENTIAL);
    }
}