package api;

import htttpmethod.POST;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import json.request.*;
import org.testng.annotations.Test;
import user.User;

import static userrole.UserRole.*;

public class KanboardApi {
    private static final String URL = "http://localhost:80/jsonrpc.php";

    @Test
    public void createProject() {
        ParamsCreateProject params = ParamsCreateProject.builder()
                .name("Project TestApi")
                .build();

        CreateRequest createUserRequest = CreateRequest.builder()
                .method("createProject")
                .params(params)
                .build();

        Response response = RestAssured.given()
                .auth()
                .basic("admin", "admin")
                .body(createUserRequest)
                .post(URL);

        response.prettyPrint();
    }

    @Test
    public void addProjectUser() {
        String[] params = new ParamsAddProjectUser.Builder()
                .setProjectID("11")
                .setUserID("4")
                .build();

        CreateRequest createUserRequest = CreateRequest.builder()
                .method("addProjectUser")
                .params(params)
                .build();

        Response response = RestAssured.given()
                .auth()
                .basic("jsonrpc", "f30b746b3b130f06848d50c2c119bb79c4336f065deb4ad25e5cf81624bb")
                .body(createUserRequest)
                .post(URL);

        response.prettyPrint();
        //1518863034
    }

    @Test
    public void updateProject() {
        ParamsUpdateProject params = ParamsUpdateProject.builder()
                .project_id(11)
                .owner_id(4)
                .build();

        CreateRequest createUserRequest = CreateRequest.builder()
                .method("updateProject")
                .params(params)
                .build();

        Response response = RestAssured.given()
                .auth()
                .basic("jsonrpc", "f30b746b3b130f06848d50c2c119bb79c4336f065deb4ad25e5cf81624bb")
                .body(createUserRequest)
                .post(URL);

        response.prettyPrint();
    }

    @Test
    public void updateUser() {
        ParamsUpdateUser params = ParamsUpdateUser.builder()
                .id(4)
                .role(APP_ADMIN)
                .build();

        CreateRequest createUserRequest = CreateRequest.builder()
                .method("updateProject")
                .params(params)
                .build();

        Response response = RestAssured.given()
                .auth()
                .basic("jsonrpc", "f30b746b3b130f06848d50c2c119bb79c4336f065deb4ad25e5cf81624bb")
                .body(createUserRequest)
                .post(URL);

        response.prettyPrint();
    }
}