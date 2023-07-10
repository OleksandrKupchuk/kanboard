package user;

import htttpmethod.POST;
import io.restassured.response.Response;
import json.request.CreateRequest;
import json.request.ParamsCreateUser;
import json.request.ParamsUpdateUser;
import json.response.CreateUserResponse;

import static method.user.UserMethod.*;

public class User {

    private int userID;

    public int getUserID() {
        return userID;
    }

    public User create(String userName, String password) {
        ParamsCreateUser params = ParamsCreateUser.builder()
                .username(userName)
                .password(password)
                .build();

        CreateRequest createUserRequest = CreateRequest.builder()
                .method(CREATE_USER)
                .params(params)
                .build();

        Response response = POST.send(createUserRequest);
        userID = response.as(CreateUserResponse.class).getResult();
        return this;
    }

    public User setRole(String role) {
        ParamsUpdateUser params = ParamsUpdateUser.builder()
                .id(userID)
                .role(role)
                .build();

        CreateRequest createUserRequest = CreateRequest.builder()
                .method(UPDATE_USER)
                .params(params)
                .build();

        POST.send(createUserRequest);
        return this;
    }
}