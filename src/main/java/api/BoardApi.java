package api;

import htttpmethod.POST;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import json.request.Request;
import json.request.project.ParamsRemoveProject;

import static method.BoardMethod.GET_BOARD;

public class BoardApi {
    private int backlogColumnID;
    private int readyColumnID;
    private int workInProgressColumnID;
    private int doneColumnID;

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

    public BoardApi getBoard(int projectID) {
        ParamsRemoveProject params = ParamsRemoveProject.builder()
                .project_id(Integer.toString(projectID))
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
        return this;
    }
}