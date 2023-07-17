package htttpmethod;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class POST {
    public static final String URL = System.getProperty("url_project_api", "http://localhost:80/jsonrpc.php");
    private static final String USERNAME = "jsonrpc";
    public static final String TOKEN = System.getProperty("kanboard_token", "7a68c425c7923d0f144dc5598b09ccb15eab9bff85c6239f481c21814bd3");;

    public static Response send(Object body){
        Response response = RestAssured.given()
                .auth()
                .basic(USERNAME, TOKEN)
                .body(body)
                .post(URL);

        response.prettyPrint();
        return response;
    }
}