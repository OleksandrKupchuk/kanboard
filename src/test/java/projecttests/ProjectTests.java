package projecttests;

import data.ProjectColumn;
import org.testng.annotations.Test;
import pages.CreateProjectPage;
import pages.KanboardMainPage;
import pages.LoginPage;
import pages.ProjectPage;
import project.Project;
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
                .create("User7", "password")
                .setRole(APP_ADMIN);


        Project project = new Project()
                .create("Test Project User")
                .addUser(user);

        new LoginPage()
                .open()
                .login(user.getUserName(), user.getPassword());

        new KanboardMainPage()
                .clickProjectName(project.getName());

        new ProjectPage()
                .clickAddTask(ProjectColumn.BACKLOG);

        project.remove();
        user.remove();
        System.out.println("dfsfsdfsdf");
    }
}