package api;

import data.Task;
import htttpmethod.POST;
import io.restassured.response.Response;
import json.request.Request;
import json.request.task.ParamsTaskId;
import json.request.task.ParamsCreateTask;
import json.response.CreateResponse;
import json.response.GetTaskResponse;

import static method.TaskMethod.*;

public class TaskApi {
    private int taskID;
    private Response responseCreate;
    private Response responseGetTaskById;
    private Response responseRemove;
    public int getTaskID(){return taskID;}
    public Response getResponseCreate(){return responseCreate;}
    public Response getResponseGetTaskById(){return responseGetTaskById;}
    public Response getResponseRemove(){return responseRemove;}

    public TaskApi create(Task task){
        ParamsCreateTask params = ParamsCreateTask.builder()
                .title(task.getTitle())
                .project_id(task.getProject_id())
                .description(task.getDescription())
                .color_id(task.getColor_id())
                .column_id(task.getColumn_id())
                .build();

        Request createUserRequest = Request.builder()
                .method(CREATE_TASK)
                .params(params)
                .build();

        responseCreate = POST.send(createUserRequest);
        taskID = (int)responseCreate.as(CreateResponse.class).getResult();
        return this;
    }

    public Response getTaskById(int taskID){
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