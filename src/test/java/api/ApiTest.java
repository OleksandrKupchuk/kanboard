package api;

import data.task.ColorId;
import data.text.ProjectName;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import json.request.task.ParamsCreateTask;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ApiTest {

    @Test(groups = {"api"})
    public void createAndDeleteUser(){
        JsonPath jsonPath;
        Response response;

        UserApi userApi = new UserApi()
                .create();

        response = userApi.getResponseCreate();
        jsonPath = response.jsonPath();

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertNotEquals(jsonPath.get("result"), false);

        response = userApi.getUserByName();
        jsonPath = response.jsonPath();

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(jsonPath.get("result.username"), userApi.getUser().getUserName());

        userApi.remove();

        response = userApi.getResponseRemove();
        jsonPath = response.jsonPath();
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertTrue(jsonPath.get("result"));

        response = userApi.getUserByName();
        jsonPath = response.jsonPath();

        Assert.assertNull(jsonPath.get("result"));
    }

    @Test(groups = {"api"})
    public void createAndDeleteProject(){
        JsonPath jsonPath;
        Response response;

        ProjectApi projectApi = new ProjectApi()
                .create(ProjectName.MANUAL);

        response = projectApi.getResponseCreate();
        jsonPath = response.jsonPath();
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertNotEquals(jsonPath.get("result"), false);

        response = projectApi.getProjectById();
        jsonPath = response.jsonPath();
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(jsonPath.get("result.name"), ProjectName.MANUAL);

        projectApi.remove();

        response = projectApi.getResponseRemove();
        jsonPath = response.jsonPath();
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertTrue(jsonPath.get("result"));

        jsonPath = projectApi.getProjectById().jsonPath();
        Assert.assertNull(jsonPath.get("result"));
    }

    @Test(groups = {"api"})
    public void createAndDeleteTask(){
        JsonPath jsonPath;
        Response response;

        ProjectApi projectApi = new ProjectApi()
                .create(ProjectName.MANUAL);

        ParamsCreateTask taskParams = ParamsCreateTask.builder()
                .title("Feature One")
                .project_id(projectApi.getProjectID())
                .description("Need added this feature")
                .color_id(ColorId.GREEN)
                .build();

        TaskApi taskApi = new TaskApi()
                .create(taskParams);

        response = taskApi.getResponseCreate();
        jsonPath = response.jsonPath();

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertNotEquals(jsonPath.get("result"), false);

        response = taskApi.getTaskById();
        jsonPath = response.jsonPath();

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(jsonPath.get("result.title"), taskParams.getTitle());
        Assert.assertEquals((int) jsonPath.get("result.id"), taskApi.getTaskID());

        taskApi.remove();

        response = taskApi.getResponseRemove();
        jsonPath = response.jsonPath();
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertTrue(jsonPath.get("result"));

        jsonPath = taskApi.getTaskById().jsonPath();

        Assert.assertNull(jsonPath.get("result"));
    }
}