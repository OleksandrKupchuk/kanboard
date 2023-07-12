package project;

import api.BoardApi;
import api.ProjectApi;
import data.*;
import data.task.Tasks;
import data.user.Users;
import org.testng.annotations.Test;
import pages.*;
import api.UserApi;

import static data.user.UserRole.APP_ADMIN;

public class ProjectTest {

    @Test
    public void testCreateProject() {
        new LoginPage()
                .open()
                .login(Users.ADMIN);

        new KanboardMainPage()
                .clickHeaderNewProjectButton();

        new CreateProjectPage()
                .setName(ProjectsName.MANUAL)
                .setIdentifier("TeamOne")
                .getBasePage()
                .clickSaveButton()
                .getBasePage()
                .assertDashboardTitle(ProjectsName.MANUAL);

        new KanboardMainPage()
                .open()
                .assertProjectName(ProjectsName.MANUAL);
    }

    @Test
    public void createProjectTaskInBacklogColumn() {
        UserApi userApi = new UserApi()
                .create()
                .setRole(APP_ADMIN);

        ProjectApi projectApi = new ProjectApi()
                .create(ProjectsName.AUTOMATION)
                .addUser(userApi);

        BoardApi boardApi = new BoardApi()
                .getBoard(projectApi.getProjectID());

        new LoginPage()
                .open()
                .login(userApi.getUser());

        new KanboardMainPage()
                .clickProjectName(ProjectsName.AUTOMATION);

        new ProjectPage()
                .clickAddTask(ProjectColumn.BACKLOG);

        new TaskCreatePage()
                .setTitle(Tasks.AUTOMATION.getTitle())
                .setDescription(Tasks.AUTOMATION.getDescription())
                .setPriority(Tasks.AUTOMATION.getPriority())
                .setEstimate(Tasks.AUTOMATION.getEstimation())
                .setStartDate(Tasks.AUTOMATION.getStartDate())
                .getBasePage()
                .clickSaveButton();

        new ProjectPage()
                .assertExistTaskInBackgroundColumn(boardApi.getBacklogColumnID(), Tasks.AUTOMATION.getTitle());

        projectApi.remove(projectApi.getProjectID());
        userApi.remove(userApi.getUserID());
    }

//    @Test
//    public void closeProjectTaskInBacklogColumn() {
//        UserApi userApi = new UserApi()
//                .create(Users.SIMPLE)
//                .setRole(APP_ADMIN);
//
//        ProjectApi projectApi = new ProjectApi()
//                .create(ProjectsName.AUTOMATION)
//                .addUser(userApi);
//
//        new LoginPage()
//                .open()
//                .login(Users.SIMPLE);
//
//        new KanboardMainPage()
//                .clickProjectName(projectApi.getName());
//
//        new ProjectPage()
//                .clickAddTask(ProjectColumn.BACKLOG);
//
//        new TaskCreatePage()
//                    .setTitle(Tasks.AUTOMATION.getTitle())
//                    .setDescription(Tasks.AUTOMATION.getDescription())
//                    .setPriority(Tasks.AUTOMATION.getPriority())
//                    .setEstimate(Tasks.AUTOMATION.getEstimation())
//                    .setStartDate(Tasks.AUTOMATION.getStartDate())
//                    .getBasePage()
//                    .clickSaveButton();
//
//        new ProjectPage()
//                .assertExistTaskInBackgroundColumn(projectApi.getBacklogColumnID(), Tasks.AUTOMATION.getTitle());
//
//        projectApi.remove();
//        userApi.remove();
//    }

    @Test
    public void addCommentToTheTask() {
        String commentForTask = "Very long comment for task";

        UserApi userApi = new UserApi()
                .create()
                .setRole(APP_ADMIN);

        ProjectApi projectApi = new ProjectApi()
                .create(ProjectsName.AUTOMATION)
                .addUser(userApi);

        BoardApi boardApi = new BoardApi()
                .getBoard(projectApi.getProjectID());

        new LoginPage()
                .open()
                .login(userApi.getUser());

        new KanboardMainPage()
                .clickProjectName(ProjectsName.AUTOMATION);

        new ProjectPage()
                .clickAddTask(ProjectColumn.BACKLOG);

        new TaskCreatePage()
                    .setTitle(Tasks.AUTOMATION.getTitle())
                    .setDescription(Tasks.AUTOMATION.getDescription())
                    .setPriority(Tasks.AUTOMATION.getPriority())
                    .setEstimate(Tasks.AUTOMATION.getEstimation())
                    .setStartDate(Tasks.AUTOMATION.getStartDate())
                    .getBasePage()
                    .clickSaveButton();

        new ProjectPage()
                .assertExistTaskInBackgroundColumn(boardApi.getBacklogColumnID(), Tasks.AUTOMATION.getTitle())
                .clickOnTask(boardApi.getBacklogColumnID(), Tasks.AUTOMATION.getTitle());

        new TaskPage()
                .clickCommentButton()
                .getcommentModalPage()
                .setComment(commentForTask)
                .clickSaveButton()
                .assertVisibleComment(commentForTask);

        projectApi.remove(projectApi.getProjectID());
        userApi.remove(userApi.getUserID());
    }
}