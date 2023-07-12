package api;

import htttpmethod.POST;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import json.request.Request;
import json.request.project.*;
import json.response.CreateResponse;

import static method.project.ProjectMethod.*;

public class ProjectApi {
    private String projectID;
    private int backlogColumnID;
    private int readyColumnID;
    private int workInProgressColumnID;
    private int doneColumnID;
    private Response responseCreate;
    private Response responseRemove;

    public String getProjectID(){
        return projectID;
    }

    public int getBacklogColumnID() {
        return backlogColumnID;
    }

    public int getReadyColumnID() {
        return readyColumnID;
    }

    public int getWorkInProgressColumnID() {
        return workInProgressColumnID;
    }

    public int getDoneColumnID() {
        return doneColumnID;
    }

    public Response getResponseCreate() {
        return responseCreate;
    }

    public Response getResponseRemove() {
        return responseRemove;
    }

    public ProjectApi create(String name) {
        ParamsCreateProject params = ParamsCreateProject.builder()
                .name(name)
                .build();

        Request createUserRequest = Request.builder()
                .method(CREATE_PROJECT)
                .params(params)
                .build();

        responseCreate = POST.send(createUserRequest);
        projectID = responseCreate.as(CreateResponse.class).getResult().toString();
        getBoard(projectID);
        return this;
    }

    public ProjectApi addUser(UserApi user) {
        String[] params = new ParamsAddProjectUser.Builder()
                .setProjectID(projectID)
                .setUserID(Integer.toString(user.getUserID()))
                .build();

        Request createUserRequest = Request.builder()
                .method(ADD_PROJECT_USER)
                .params(params)
                .build();

        POST.send(createUserRequest);
        return this;
    }

    public Response getProjectById(String projectID) {
        ParamsGetProjectById params = ParamsGetProjectById.builder()
                .project_id(Integer.parseInt(projectID))
                .build();

        Request createUserRequest = Request.builder()
                .method(GET_PROJECT_BY_ID)
                .params(params)
                .build();

        return POST.send(createUserRequest);
    }

    public void remove(String projectID) {
        ParamsRemoveProject params = ParamsRemoveProject.builder()
                .project_id(projectID)
                .build();

        Request request = Request.builder()
                .method(REMOVE_PROJECT)
                .params(params)
                .build();

        responseRemove = POST.send(request);
    }

    private void getBoard(String projectID) {
        ParamsRemoveProject params = ParamsRemoveProject.builder()
                .project_id(projectID)
                .build();

        Request request = Request.builder()
                .method(GET_BOARD)
                .params(params)
                .build();

        Response response = POST.send(request);
        JsonPath jsonpath = response.jsonPath();
        backlogColumnID = jsonpath.getInt("result[0].columns[0].id");
        readyColumnID = jsonpath.getInt("result[0].columns[1].id");
        workInProgressColumnID = jsonpath.getInt("result[0].columns[2].id");
        doneColumnID = jsonpath.getInt("result[0].columns[3].id");
    }
}