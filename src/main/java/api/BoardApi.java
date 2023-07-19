package api;

import htttpmethod.POST;
import io.restassured.path.json.JsonPath;
import json.request.Request;
import json.request.project.ParamsGetProjectById;
import logger.Logger;

import static method.BoardMethod.*;

public class BoardApi {
    private Logger logger = new Logger();
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
        ParamsGetProjectById params = ParamsGetProjectById.builder()
                .project_id(projectID)
                .build();

        Request request = Request.builder()
                .method(GET_BOARD)
                .params(params)
                .build();

        logger.log(GET_BOARD);
        JsonPath jsonpath = POST.send(request).jsonPath();
        backlogColumnID = jsonpath.getInt("result[0].columns[0].id");
        readyColumnID = jsonpath.getInt("result[0].columns[1].id");
        workInProgressColumnID = jsonpath.getInt("result[0].columns[2].id");
        doneColumnID = jsonpath.getInt("result[0].columns[3].id");
        return this;
    }
}