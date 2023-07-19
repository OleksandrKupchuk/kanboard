package api;

import com.github.javafaker.Faker;
import data.user.User;
import htttpmethod.POST;
import json.request.Request;
import json.request.user.ParamsCreateUser;
import json.request.user.ParamsGetUserByName;
import json.request.user.ParamsRemoveUser;
import json.request.user.ParamsUpdateUser;
import json.response.Response;
import logger.Logger;

import static method.UserMethod.*;

public class UserApi {
    private Logger logger = new Logger();
    private User user = new User();
    private io.restassured.response.Response responseCreate;
    private io.restassured.response.Response responseRemove;
    private int userID;

    public int getUserID() {
        return userID;
    }

    public io.restassured.response.Response getResponseCreate() {
        return responseCreate;
    }

    public io.restassured.response.Response getResponseRemove() {
        return responseRemove;
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

        logger.log(CREATE_USER);
        responseCreate = POST.send(request);
        userID = (int) responseCreate.as(Response.class).getResult();
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

        logger.log(UPDATE_USER);
        POST.send(request);
        return this;
    }

    public io.restassured.response.Response getUserByName() {
        if (user.getUserName().isEmpty()){
            throw new RuntimeException("Please create a user before receiving details");
        }

        ParamsGetUserByName params = ParamsGetUserByName.builder()
                .username(user.getUserName())
                .build();

        Request request = Request.builder()
                .method(GET_USER_BY_NAME)
                .params(params)
                .build();

        logger.log(GET_USER_BY_NAME);
        return POST.send(request);
    }

    public UserApi remove() {
        ParamsRemoveUser params = ParamsRemoveUser.builder()
                .user_id(userID)
                .build();

        Request request = Request.builder()
                .method(REMOVE_USER)
                .params(params)
                .build();

        logger.log(REMOVE_USER);
        responseRemove = POST.send(request);
        return this;
    }
}