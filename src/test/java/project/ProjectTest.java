package project;

import api.BoardApi;
import api.ProjectApi;
import api.TaskApi;
import data.*;
import data.task.TaskField;
import data.task.TaskStatus;
import data.task.Tasks;
import data.user.Users;
import org.testng.annotations.Test;
import pages.*;
import api.UserApi;
import pages.project.*;

import static data.TaskSidebarButtons.*;
import static data.text.ProjectText.*;
import static data.text.TaskText.*;
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

    @Test(groups = {"ui", "api"})
    public void testCreateProjectTaskInBacklogColumn() {
        UserApi userApi = new UserApi()
                .create()
                .setRole(APP_ADMIN);

        ProjectApi projectApi = new ProjectApi()
                .create(ProjectName.AUTOMATION)
                .addUser(userApi);

        BoardApi boardApi = new BoardApi()
                .getBoard(projectApi.getProjectID());

        new LoginPage()
                .open()
                .login(userApi.getUser());

        new KanboardMainPage()
                .clickOnProjectName(ProjectName.AUTOMATION);

        new ProjectPage()
                .clickAddTaskButton(ProjectColumn.BACKLOG);

        new TaskCreatePage()
                .setTitle(Tasks.AUTOMATION.getTitle())
                .setDescription(Tasks.AUTOMATION.getDescription())
                .setPriority(Tasks.AUTOMATION.getPriority())
                .setEstimate(Tasks.AUTOMATION.getEstimation())
                .setStartDate(Tasks.AUTOMATION.getStartDate())
                .getBasePage()
                .clickSaveButton();

        new ProjectPage()
                .assertExistTaskInColumn(boardApi.getBacklogColumnID(), Tasks.AUTOMATION.getTitle());

        projectApi.remove(projectApi.getProjectID());
        userApi.remove(userApi.getUserID());
    }

    @Test(groups = {"ui", "api"})
    public void testCloseProjectTaskInBacklogColumn() {
        UserApi userApi = new UserApi()
                .create()
                .setRole(APP_ADMIN);

        ProjectApi projectApi = new ProjectApi()
                .create(ProjectName.AUTOMATION)
                .addUser(userApi);

        BoardApi boardApi = new BoardApi()
                .getBoard(projectApi.getProjectID());

        Task task = Task.builder()
                .title("Feature Two")
                .project_id(projectApi.getProjectID())
                .description("Need added this feature")
                .color_id(ColorId.GREEN)
                .column_id(boardApi.getReadyColumnID())
                .build();

        new TaskApi()
                .create(task);

        new LoginPage()
                .open()
                .login(userApi.getUser());

        new KanboardMainPage()
                .clickOnProjectName(ProjectName.AUTOMATION);

        new ProjectPage()
                .assertExistTaskInColumn(boardApi.getReadyColumnID(), task.getTitle())
                .clickOnTask(boardApi.getReadyColumnID(), task.getTitle())
                .assertTaskField(TaskField.STATUS, TaskStatus.OPEN);

        new TaskPage()
                .clickSidebarButton(CLOSE_TASK)
                .getCloseTaskModalWindow()
                .assertAlertInfo(CLOSE_TASK_ALERT_INFO, task.getTitle())
                .clickYesButton();

        new ProjectPage()
                .assertTaskField(TaskField.STATUS, TaskStatus.CLOSE);

        new ProjectHeaderElement()
                .clickBoardButton();

        new ProjectPage()
                .assertNotExistTaskInColumn(boardApi.getReadyColumnID(), task.getTitle());

        projectApi.remove(projectApi.getProjectID());
        userApi.remove(userApi.getUserID());
    }

    @Test(groups = {"ui", "api"})
    public void testAddCommentToTheTask() {
        String commentForTask = "Very long comment for task";

        UserApi userApi = new UserApi()
                .create()
                .setRole(APP_ADMIN);

        ProjectApi projectApi = new ProjectApi()
                .create(ProjectName.AUTOMATION)
                .addUser(userApi);

        BoardApi boardApi = new BoardApi()
                .getBoard(projectApi.getProjectID());

        new LoginPage()
                .open()
                .login(userApi.getUser());

        new KanboardMainPage()
                .clickOnProjectName(ProjectName.AUTOMATION);

        new ProjectPage()
                .clickAddTaskButton(ProjectColumn.BACKLOG);

        new TaskCreatePage()
                .setTitle(Tasks.AUTOMATION.getTitle())
                .setDescription(Tasks.AUTOMATION.getDescription())
                .setPriority(Tasks.AUTOMATION.getPriority())
                .setEstimate(Tasks.AUTOMATION.getEstimation())
                .setStartDate(Tasks.AUTOMATION.getStartDate())
                .getBasePage()
                .clickSaveButton();

        new ProjectPage()
                .assertExistTaskInColumn(boardApi.getBacklogColumnID(), Tasks.AUTOMATION.getTitle())
                .clickOnTask(boardApi.getBacklogColumnID(), Tasks.AUTOMATION.getTitle());

        new TaskPage()
                .clickSidebarButton(ADD_COMMENT)
                .getCommentModalPage()
                .setComment(commentForTask)
                .clickSaveButton()
                .assertVisibleComment(commentForTask);

        projectApi.remove(projectApi.getProjectID());
        userApi.remove(userApi.getUserID());
    }

    @Test(groups = {"ui", "api"})
    public void testDragTaskFromBackgroundToDoneColumn() {
        UserApi userApi = new UserApi()
                .create()
                .setRole(APP_ADMIN);

        ProjectApi projectApi = new ProjectApi()
                .create(ProjectName.AUTOMATION)
                .addUser(userApi);

        BoardApi boardApi = new BoardApi()
                .getBoard(projectApi.getProjectID());

        Task task = Task.builder()
                .title("Feature Three")
                .project_id(projectApi.getProjectID())
                .description("Need added this feature")
                .color_id(ColorId.BLUE)
                .column_id(boardApi.getBacklogColumnID())
                .build();

        new TaskApi()
                .create(task);

        new LoginPage()
                .open()
                .login(userApi.getUser());

        new KanboardMainPage()
                .clickOnProjectName(ProjectName.AUTOMATION);

        new ProjectPage()
                .assertExistTaskInColumn(boardApi.getBacklogColumnID(), task.getTitle())
                .dragTask(boardApi.getBacklogColumnID(), boardApi.getDoneColumnID())
                .assertExistTaskInColumn(boardApi.getDoneColumnID(), task.getTitle());

        projectApi.remove(projectApi.getProjectID());
        userApi.remove(userApi.getUserID());
    }
}