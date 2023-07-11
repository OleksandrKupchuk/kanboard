package project;

import htttpmethod.POST;
import io.restassured.response.Response;
import json.request.Request;
import json.request.project.ParamsAddProjectUser;
import json.request.project.ParamsCreateProject;
import json.request.project.ParamsRemoveProject;
import json.request.project.ParamsUpdateProject;
import json.response.CreateResponse;
import user.User;

import static method.project.ProjectMethod.*;

public class Project {
    private String projectID;
    private String name;

    public String getName(){
        return name;
    }

    public Project create(String name) {
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
        return this;
    }

    public Project addUser(User user) {
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
}