package user;

import htttpmethod.POST;
import io.restassured.response.Response;
import json.request.Request;
import json.request.user.ParamsCreateUser;
import json.request.user.ParamsRemoveUser;
import json.request.user.ParamsUpdateUser;
import json.response.CreateResponse;

import static method.user.UserMethod.*;

public class User {

    private int userID;
    private String userName;
    private String password;

    public int getUserID() {
        return userID;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public User create(String userName, String password) {
        this.userName = userName;
        this.password = password;

        ParamsCreateUser params = ParamsCreateUser.builder()
                .username(userName)
                .password(password)
                .build();

        Request request = Request.builder()
                .method(CREATE_USER)
                .params(params)
                .build();

        Response response = POST.send(request);
        userID = (int)response.as(CreateResponse.class).getResult();
        return this;
    }

    public User setRole(String role) {
        ParamsUpdateUser params = ParamsUpdateUser.builder()
                .id(userID)
                .role(role)
                .build();

        Request request = Request.builder()
                .method(UPDATE_USER)
                .params(params)
                .build();

        POST.send(request);
        return this;
    }

    public User remove() {
        ParamsRemoveUser params = ParamsRemoveUser.builder()
                .user_id(userID)
                .build();

        Request request = Request.builder()
                .method(REMOVE_USER)
                .params(params)
                .build();

        POST.send(request);
        return this;
    }
}