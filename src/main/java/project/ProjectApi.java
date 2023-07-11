package project;

import htttpmethod.POST;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import json.request.Request;
import json.request.project.ParamsAddProjectUser;
import json.request.project.ParamsCreateProject;
import json.request.project.ParamsRemoveProject;
import json.request.project.ParamsUpdateProject;
import json.response.CreateResponse;
import user.UserApi;

import static method.project.ProjectMethod.*;

public class ProjectApi {
    private String projectID;
    private String name;
    private int backlogColumnID;
    private int readyColumnID;
    private int workInProgressColumnID;
    private int doneColumnID;

    public String getName(){
        return name;
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

    public ProjectApi create(String name) {
        this.name = name;

        ParamsCreateProject params = ParamsCreateProject.builder()
                .name(name)
                .build();

        Request createUserRequest = Request.builder()
                .method(CREATE_PROJECT)
                .params(params)
                .build();

        Response response = POST.send(createUserRequest);
        projectID = response.as(CreateResponse.class).getResult().toString();
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

    public void updateProject() {
        ParamsUpdateProject params = ParamsUpdateProject.builder()
                .project_id(11)
                .owner_id(4)
                .build();

        Request createUserRequest = Request.builder()
                .method(UPDATE_PROJECT)
                .params(params)
                .build();

        POST.send(createUserRequest);
    }

    public void remove() {
        ParamsRemoveProject params = ParamsRemoveProject.builder()
                .project_id(projectID)
                .build();

        Request request = Request.builder()
                .method(REMOVE_PROJECT)
                .params(params)
                .build();

        POST.send(request);
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
        JsonPath jsonpath= response.jsonPath();
        backlogColumnID = jsonpath.getInt("result[0].columns[0].id");
        readyColumnID = jsonpath.getInt("result[0].columns[1].id");
        workInProgressColumnID = jsonpath.getInt("result[0].columns[2].id");
        doneColumnID = jsonpath.getInt("result[0].columns[3].id");
    }
}