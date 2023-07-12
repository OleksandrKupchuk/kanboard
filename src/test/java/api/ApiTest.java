package api;

import data.ProjectsName;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ApiTest {

    @Test
    public void createAndDeleteUser(){
        JsonPath jsonPath;

        UserApi userApi = new UserApi()
                .create();

        Assert.assertEquals(userApi.getCreateResponse().statusCode(), 200);

        userApi.getUserByName(userApi.getUser().getUserName());
        jsonPath = userApi.getUserByNameResponse().jsonPath();

        Assert.assertEquals(userApi.getUserByNameResponse().statusCode(), 200);
        Assert.assertEquals(jsonPath.get("result.username"), userApi.getUser().getUserName());

        userApi.remove(userApi.getUserID());

        jsonPath = userApi.getRemoveResponse().jsonPath();
        Assert.assertEquals(userApi.getRemoveResponse().statusCode(), 200);
        Assert.assertTrue(jsonPath.get("result"));

        userApi.getUserByName(userApi.getUser().getUserName());
        jsonPath = userApi.getUserByNameResponse().jsonPath();

        Assert.assertNull(jsonPath.get("result"));
    }

    @Test
    public void createAndDeleteProject(){
        JsonPath jsonPath;

        ProjectApi projectApi = new ProjectApi()
                .create(ProjectsName.MANUAL);

        Assert.assertEquals(projectApi.getResponseCreate().statusCode(), 200);

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
}