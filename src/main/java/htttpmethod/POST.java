package htttpmethod;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import logger.Logger;

import static config.Config.*;

public class POST {
    private static Logger LOGGER = new Logger();
    private static final String USERNAME = "jsonrpc";

    public static Response send(Object body){
        Response response = RestAssured.given()
                .auth()
                .basic(USERNAME, TOKEN)
                .body(body)
                .post(URL_API);

        response.prettyPrint();
        LOGGER.log(response.prettyPrint());
        return response;
    }
}