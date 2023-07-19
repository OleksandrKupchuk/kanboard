package api;

import htttpmethod.POST;
import json.request.Request;
import json.request.task.ParamsTaskId;
import json.request.task.ParamsCreateTask;
import json.response.Response;
import logger.Logger;

import static method.TaskMethod.*;

public class TaskApi {
    private Logger logger = new Logger();
    private int taskID;
    private io.restassured.response.Response responseCreate;
    private io.restassured.response.Response responseRemove;
    public int getTaskID(){return taskID;}
    public io.restassured.response.Response getResponseCreate(){return responseCreate;}
    public io.restassured.response.Response getResponseRemove(){return responseRemove;}

    public TaskApi create(ParamsCreateTask params){
        Request createUserRequest = Request.builder()
                .method(CREATE_TASK)
                .params(params)
                .build();

        logger.log(CREATE_TASK);
        responseCreate = POST.send(createUserRequest);
        taskID = (int)responseCreate.as(Response.class).getResult();
        return this;
    }

    public io.restassured.response.Response getTaskById(){
        if (taskID == 0){
            throw new RuntimeException("Please create a task before receiving details");
        }

        ParamsTaskId params = ParamsTaskId.builder()
                .task_id(taskID)
                .build();

        Request createUserRequest = Request.builder()
                .method(GET_TASK)
                .params(params)
                .build();

        logger.log(GET_TASK);
        return POST.send(createUserRequest);
    }

    public TaskApi remove(){
        ParamsTaskId params = ParamsTaskId.builder()
                .task_id(taskID)
                .build();

        Request createUserRequest = Request.builder()
                .method(REMOVE_TASK)
                .params(params)
                .build();

        logger.log(REMOVE_TASK);
        responseRemove = POST.send(createUserRequest);
        return this;
    }
}