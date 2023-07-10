package project;

import htttpmethod.POST;
import json.request.CreateRequest;
import json.request.ParamsAddProjectUser;
import json.request.ParamsCreateProject;
import json.request.ParamsUpdateProject;

public class Project {
    public void create(String name) {
        ParamsCreateProject params = ParamsCreateProject.builder()
                .name(name)
                .build();

        CreateRequest createUserRequest = CreateRequest.builder()
                .method("createProject")
                .params(params)
                .build();

        POST.send(createUserRequest);
    }

    public void addProjectUser() {
        String[] params = new ParamsAddProjectUser.Builder()
                .setProjectID("11")
                .setUserID("4")
                .build();

        CreateRequest createUserRequest = CreateRequest.builder()
                .method("addProjectUser")
                .params(params)
                .build();

        POST.send(createUserRequest);
    }

    public void updateProject() {
        ParamsUpdateProject params = ParamsUpdateProject.builder()
                .project_id(11)
                .owner_id(4)
                .build();

        CreateRequest createUserRequest = CreateRequest.builder()
                .method("updateProject")
                .params(params)
                .build();

        POST.send(createUserRequest);
    }
}