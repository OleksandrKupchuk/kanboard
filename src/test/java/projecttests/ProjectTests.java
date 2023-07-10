package projecttests;

import org.testng.annotations.Test;
import pages.CreateProjectPage;
import pages.KanboardMainPage;
import pages.LoginPage;
import user.User;

import static userrole.UserRole.APP_ADMIN;

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

    @Test
    public void createProjectTask() {
        User user = new User()
                .create("User5", "password")
                .setRole(APP_ADMIN);


    }
}