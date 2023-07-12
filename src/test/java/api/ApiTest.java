package api;

import data.ColorId;
import data.ProjectsName;
import data.Task;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ApiTest {

    @Test
    public void createAndDeleteUser(){
        JsonPath jsonPath;

        UserApi userApi = new UserApi()
                .create();

        jsonPath = userApi.getResponseCreate().jsonPath();

        Assert.assertEquals(userApi.getResponseCreate().statusCode(), 200);
        Assert.assertNotEquals(jsonPath.get("result"), false);

        userApi.getUserByName(userApi.getUser().getUserName());
        jsonPath = userApi.getResponseUserByName().jsonPath();

        Assert.assertEquals(userApi.getResponseUserByName().statusCode(), 200);
        Assert.assertEquals(jsonPath.get("result.username"), userApi.getUser().getUserName());

        userApi.remove(userApi.getUserID());

        jsonPath = userApi.getResponseRemove().jsonPath();
        Assert.assertEquals(userApi.getResponseRemove().statusCode(), 200);
        Assert.assertTrue(jsonPath.get("result"));

        userApi.getUserByName(userApi.getUser().getUserName());
        jsonPath = userApi.getResponseUserByName().jsonPath();

        Assert.assertNull(jsonPath.get("result"));
    }

    @Test
    public void createAndDeleteProject(){
        JsonPath jsonPath;

        ProjectApi projectApi = new ProjectApi()
                .create(ProjectsName.MANUAL);

        jsonPath = projectApi.getResponseCreate().jsonPath();
        Assert.assertEquals(projectApi.getResponseCreate().statusCode(), 200);
        Assert.assertNotEquals(jsonPath.get("result"), false);

        jsonPath = projectApi.getProjectById(projectApi.getProjectID()).jsonPath();
        Assert.assertEquals(projectApi.getProjectById(projectApi.getProjectID()).statusCode(), 200);
        Assert.assertEquals(jsonPath.get("result.name"), ProjectsName.MANUAL);

        projectApi.remove(projectApi.getProjectID());

        jsonPath = projectApi.getResponseRemove().jsonPath();
        Assert.assertEquals(projectApi.getResponseRemove().statusCode(), 200);
        Assert.assertTrue(jsonPath.get("result"));

        jsonPath = projectApi.getProjectById(projectApi.getProjectID()).jsonPath();
        Assert.assertNull(jsonPath.get("result"));
    }

    @Test
    public void createAndDeleteTask(){
        JsonPath jsonPath;

        ProjectApi projectApi = new ProjectApi()
                .create(ProjectsName.MANUAL);

        Task task = Task.builder()
                .title("Feature One")
                .project_id(projectApi.getProjectID())
                .description("Need added this feature")
                .color_id(ColorId.GREEN)
                .build();

        TaskApi taskApi = new TaskApi()
                .create(task);

        jsonPath = taskApi.getResponseCreate().jsonPath();

        Assert.assertEquals(taskApi.getResponseCreate().statusCode(), 200);
        Assert.assertNotEquals(jsonPath.get("result"), false);

        jsonPath = taskApi.getTaskById(taskApi.getTaskID()).jsonPath();

        Assert.assertEquals(taskApi.getTaskById(taskApi.getTaskID()).statusCode(), 200);
        Assert.assertEquals(jsonPath.get("result.title"), task.getTitle());
        Assert.assertEquals((int) jsonPath.get("result.id"), taskApi.getTaskID());

        taskApi.remove(taskApi.getTaskID());

        jsonPath = taskApi.getResponseRemove().jsonPath();
        Assert.assertEquals(taskApi.getResponseRemove().statusCode(), 200);
        Assert.assertTrue(jsonPath.get("result"));

        jsonPath = taskApi.getTaskById(taskApi.getTaskID()).jsonPath();

        Assert.assertNull(jsonPath.get("result"));
    }
}