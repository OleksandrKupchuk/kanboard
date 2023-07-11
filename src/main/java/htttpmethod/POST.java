package htttpmethod;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class POST {
    private static final String URL = "http://localhost:80/jsonrpc.php";
    private static final String USERNAME = "jsonrpc";
    private static final String TOKEN = "de8197bbc55e4ebeb59ed30f687bddfcbcf4c1edee95a3ae04df440bb33b";

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