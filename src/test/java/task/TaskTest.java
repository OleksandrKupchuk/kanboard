package task;

import api.BoardApi;
import api.ProjectApi;
import api.TaskApi;
import api.UserApi;
import data.task.ColorId;
import data.text.ProjectColumn;
import data.text.ProjectName;
import data.task.TaskField;
import data.task.TaskStatus;
import data.task.Tasks;
import json.request.task.ParamsCreateTask;
import org.testng.annotations.Test;
import pages.KanboardMainPage;
import pages.LoginPage;
import pages.TaskCreatePage;
import pages.TaskPage;
import pages.project.ProjectHeaderElement;
import pages.project.ProjectPage;

import static data.text.TaskSidebarButtons.*;
import static data.text.TaskText.*;
import static data.user.UserRole.*;

public class TaskTest {

    @Test(groups = {"ui", "api"})
    public void testCreateTaskInBacklogColumn() {
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
    public void testCloseTaskInBacklogColumn() {
        UserApi userApi = new UserApi()
                .create()
                .setRole(APP_ADMIN);

        ProjectApi projectApi = new ProjectApi()
                .create(ProjectName.AUTOMATION)
                .addUser(userApi);

        BoardApi boardApi = new BoardApi()
                .getBoard(projectApi.getProjectID());

        ParamsCreateTask taskParams = ParamsCreateTask.builder()
                .title("Feature Two")
                .project_id(projectApi.getProjectID())
                .description("Need added this feature")
                .color_id(ColorId.GREEN)
                .column_id(boardApi.getReadyColumnID())
                .build();

        new TaskApi()
                .create(taskParams);

        new LoginPage()
                .open()
                .login(userApi.getUser());

        new KanboardMainPage()
                .clickOnProjectName(ProjectName.AUTOMATION);

        new ProjectPage()
                .assertExistTaskInColumn(boardApi.getReadyColumnID(), taskParams.getTitle())
                .clickOnTask(boardApi.getReadyColumnID(), taskParams.getTitle())
                .assertTaskField(TaskField.STATUS, TaskStatus.OPEN);

        new TaskPage()
                .clickSidebarButton(CLOSE_TASK)
                .getCloseTaskModalWindow()
                .assertAlertInfo(CLOSE_TASK_ALERT_INFO, taskParams.getTitle())
                .clickYesButton();

        new ProjectPage()
                .assertTaskField(TaskField.STATUS, TaskStatus.CLOSE);

        new ProjectHeaderElement()
                .clickBoardButton();

        new ProjectPage()
                .assertNotExistTaskInColumn(boardApi.getReadyColumnID(), taskParams.getTitle());

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
                .getCommentModalWindow()
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

        ParamsCreateTask taskParams = ParamsCreateTask.builder()
                .title("Feature Three")
                .project_id(projectApi.getProjectID())
                .description("Need added this feature")
                .color_id(ColorId.BLUE)
                .column_id(boardApi.getBacklogColumnID())
                .build();

        new TaskApi()
                .create(taskParams);

        new LoginPage()
                .open()
                .login(userApi.getUser());

        new KanboardMainPage()
                .clickOnProjectName(ProjectName.AUTOMATION);

        new ProjectPage()
                .assertExistTaskInColumn(boardApi.getBacklogColumnID(), taskParams.getTitle())
                .dragTask(boardApi.getBacklogColumnID(), boardApi.getDoneColumnID())
                .assertExistTaskInColumn(boardApi.getDoneColumnID(), taskParams.getTitle());

        projectApi.remove(projectApi.getProjectID());
        userApi.remove(userApi.getUserID());
    }
}