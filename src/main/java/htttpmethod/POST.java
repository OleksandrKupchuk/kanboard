package htttpmethod;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static config.Config.*;

public class POST {
    private static final String USERNAME = "jsonrpc";

    public static Response send(Object body){
        Response response = RestAssured.given()
                .auth()
                .basic(USERNAME, TOKEN)
                .body(body)
                .post(URL_API);

        response.prettyPrint();
        return response;
    }
}