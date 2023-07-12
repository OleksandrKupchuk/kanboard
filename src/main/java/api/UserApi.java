package api;

import com.github.javafaker.Faker;
import data.user.User;
import htttpmethod.POST;
import io.restassured.response.Response;
import json.request.Request;
import json.request.user.ParamsCreateUser;
import json.request.user.ParamsGetUserByName;
import json.request.user.ParamsRemoveUser;
import json.request.user.ParamsUpdateUser;
import json.response.CreateResponse;

import static method.UserMethod.*;

public class UserApi {
    private User user = new User();
    private Response responseCreate;
    private Response responseRemove;
    private Response responseUserByName;
    private int userID;

    public int getUserID() {
        return userID;
    }

    public Response getResponseCreate() {
        return responseCreate;
    }

    public Response getResponseRemove() {
        return responseRemove;
    }

    public Response getResponseUserByName() {
        return responseUserByName;
    }

    public User getUser() {
        return user;
    }

    public UserApi create() {
        Faker faker = new Faker();
        String randomName = faker.name().firstName();
        String randomPassword = faker.internet().password();

        user.setUserName(randomName);
        user.setPassword(randomPassword);

        ParamsCreateUser params = ParamsCreateUser.builder()
                .username(randomName)
                .password(randomPassword)
                .build();

        Request request = Request.builder()
                .method(CREATE_USER)
                .params(params)
                .build();

        responseCreate = POST.send(request);
        userID = (int) responseCreate.as(CreateResponse.class).getResult();
        return this;
    }

    public UserApi setRole(String role) {
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

    public UserApi getUserByName(String name) {
        ParamsGetUserByName params = ParamsGetUserByName.builder()
                .username(name)
                .build();

        Request request = Request.builder()
                .method(GET_USER_BY_NAME)
                .params(params)
                .build();

        responseUserByName = POST.send(request);
        return this;
    }

    public UserApi remove(int userID) {
        ParamsRemoveUser params = ParamsRemoveUser.builder()
                .user_id(userID)
                .build();

        Request request = Request.builder()
                .method(REMOVE_USER)
                .params(params)
                .build();

        responseRemove = POST.send(request);
        return this;
    }
}