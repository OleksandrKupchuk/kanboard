package project;

import api.ProjectApi;
import data.text.ConfigureProjectMenu;
import data.text.ProjectMenu;
import data.text.ProjectName;
import data.user.Users;
import org.testng.annotations.Test;
import pages.*;
import api.UserApi;
import pages.project.*;

import static data.text.ProjectText.*;
import static data.user.UserRole.*;

public class ProjectTest {

    @Test(groups = {"ui"})
    public void testCreateProject() {
        new LoginPage()
                .open()
                .login(Users.ADMIN);

        new KanboardMainPage()
                .clickNewProjectButton();

        new CreateProjectPage()
                .setName(ProjectName.MANUAL)
                .getBasePage()
                .clickSaveButton()
                .getBasePage()
                .assertDashboardTitle(ProjectName.MANUAL);

        new KanboardMainPage()
                .clickOnLogo()
                .assertExistProjectName(ProjectName.MANUAL)
                .logout();
    }

    @Test(groups = {"ui", "api"})
    public void testCloseProject() {
        UserApi userApi = new UserApi()
                .create()
                .setRole(APP_ADMIN);

        new ProjectApi()
                .create(ProjectName.AUTOMATION)
                .addUser(userApi);

        new LoginPage()
                .open()
                .login(userApi.getUser());

        new KanboardMainPage()
                .assertExistProjectName(ProjectName.AUTOMATION)
                .clickOnProjectName(ProjectName.AUTOMATION);

        new ProjectPage()
                .clickOnActionProjectMenuButton()
                .selectMenuFromDropdown(ProjectMenu.CONFIGURE_PROJECT);

        new ConfigureProjectPage()
                .selectSidebarMenu(ConfigureProjectMenu.CLOSE_PROJECT)
                .getCloseModalWindow()
                .assertAlertInfo(CLOSE_PROJECT_ALERT_INFO, ProjectName.AUTOMATION)
                .clickYesButton();

        new ProjectHeaderElement()
                .clickBoardButton();

        new KanboardMainPage()
                .assertNotExistProjectName(ProjectName.AUTOMATION)
                .logout();
    }
}