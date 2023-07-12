package api;

import htttpmethod.POST;
import io.restassured.response.Response;
import json.request.Request;
import json.request.project.*;
import json.response.CreateResponse;

import static method.ProjectMethod.*;

public class ProjectApi {
    private int projectID;
    private Response responseCreate;
    private Response responseRemove;

    public int getProjectID(){
        return projectID;
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
        projectID = (int)responseCreate.as(CreateResponse.class).getResult();
        return this;
    }

    public ProjectApi addUser(UserApi user) {
        String[] params = new ParamsAddProjectUser.Builder()
                .setProjectID(Integer.toString(projectID))
                .setUserID(Integer.toString(user.getUserID()))
                .build();

        Request createUserRequest = Request.builder()
                .method(ADD_PROJECT_USER)
                .params(params)
                .build();

        POST.send(createUserRequest);
        return this;
    }

    public ProjectApi addUser(int projectID, UserApi user) {
        String[] params = new ParamsAddProjectUser.Builder()
                .setProjectID(Integer.toString(projectID))
                .setUserID(Integer.toString(user.getUserID()))
                .build();

        Request createUserRequest = Request.builder()
                .method(ADD_PROJECT_USER)
                .params(params)
                .build();

        POST.send(createUserRequest);
        return this;
    }

    public Response getProjectById(int projectID) {
        ParamsGetProjectById params = ParamsGetProjectById.builder()
                .project_id(projectID)
                .build();

        Request createUserRequest = Request.builder()
                .method(GET_PROJECT_BY_ID)
                .params(params)
                .build();

        return POST.send(createUserRequest);
    }

    public void remove(int projectID) {
        ParamsRemoveProject params = ParamsRemoveProject.builder()
                .project_id(Integer.toString(projectID))
                .build();

        Request request = Request.builder()
                .method(REMOVE_PROJECT)
                .params(params)
                .build();

        responseRemove = POST.send(request);
    }
}