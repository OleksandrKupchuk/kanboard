package api;

import htttpmethod.POST;
import json.request.Request;
import json.request.task.ParamsTaskId;
import json.request.task.ParamsCreateTask;
import json.response.Response;

import static method.TaskMethod.*;

public class TaskApi {
    private int taskID;
    private io.restassured.response.Response responseCreate;
    private io.restassured.response.Response responseGetTaskById;
    private io.restassured.response.Response responseRemove;
    public int getTaskID(){return taskID;}
    public io.restassured.response.Response getResponseCreate(){return responseCreate;}
    public io.restassured.response.Response getResponseGetTaskById(){return responseGetTaskById;}
    public io.restassured.response.Response getResponseRemove(){return responseRemove;}

    public TaskApi create(ParamsCreateTask params){
        Request createUserRequest = Request.builder()
                .method(CREATE_TASK)
                .params(params)
                .build();

        responseCreate = POST.send(createUserRequest);
        taskID = (int)responseCreate.as(Response.class).getResult();
        return this;
    }

    public io.restassured.response.Response getTaskById(int taskID){
        ParamsTaskId params = ParamsTaskId.builder()
                .task_id(taskID)
                .build();

        Request createUserRequest = Request.builder()
                .method(GET_TASK)
                .params(params)
                .build();

        return POST.send(createUserRequest);
    }

    public TaskApi remove(int taskID){
        ParamsTaskId params = ParamsTaskId.builder()
                .task_id(taskID)
                .build();

        Request createUserRequest = Request.builder()
                .method(REMOVE_TASK)
                .params(params)
                .build();

        responseRemove = POST.send(createUserRequest);
        return this;
    }
}