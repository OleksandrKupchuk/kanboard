package projecttests;

import org.testng.annotations.Test;
import pages.CreateProjectPage;
import pages.KanboardMainPage;
import pages.LoginPage;

public class ProjectTests {

    @Test
    public void testSuccessLogin() {
        new LoginPage()
                .open()
                .login("admin", "admin");

        new KanboardMainPage()
                .clickHeaderNewProjectButton();

        new CreateProjectPage()
                .setName("ProjectOne")
                .setIdentifier("TeamOne")
                .clickSaveButton()
                .assertDashboardTitle("ProjectOne");

        new KanboardMainPage()
                .open()
                .assertProjectName("ProjectOne");
    }
}