package htttpmethod;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class POST {
    private static final String URL = "http://localhost:80/jsonrpc.php";
    private static final String USERNAME = "jsonrpc";
    private static final String TOKEN = "f30b746b3b130f06848d50c2c119bb79c4336f065deb4ad25e5cf81624bb";

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