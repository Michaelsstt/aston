
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PostmanEchoTest extends BaseApiTest {

    @Test
    void testGetRequest() {
                given()
                    .spec(requestSpec)
                    .queryParam("param1", "value1")
                    .queryParam("param2", "value2")
                .when()
                    .get("/get")
                .then()
                    .statusCode(200)
                    .body("args.param1", equalTo("value1"))
                    .body("args.param2", equalTo("value2"))
                    .body("url", equalTo("https://postman-echo.com/get?param1=value1&param2=value2"));
    }

    @Test
    void testPostRawText() {
        String rawText = "raw text data";

        given()
            .spec(requestSpec)
            .body(rawText)
        .when()
            .post("/post")
        .then()
            .statusCode(200)
            .body("data", equalTo(rawText))
            .body("url", equalTo("https://postman-echo.com/post"));
    }

    @Test
    void testPostFormData() {
        given()
            .spec(requestSpec)
            .contentType("application/x-www-form-urlencoded; charset=utf-8")
            .formParam("key1", "value1")
            .formParam("key2", "value2")
        .when()
            .post("/post")
        .then()
            .statusCode(200)
            .body("form.key1", equalTo("value1"))
            .body("form.key2", equalTo("value2"))
            .body("url", equalTo("https://postman-echo.com/post"));

    }

    @Test
    void testPutRequest() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("name", "Sponge Bob");
        requestBody.put("email", "badbob@mail.com");

        given()
            .spec(requestSpec)
            .body(requestBody)
        .when()
            .put("/put")
        .then()
            .statusCode(200)
            .body("json.name", equalTo("Sponge Bob"))
            .body("url", equalTo("https://postman-echo.com/put"));
    }

    @Test
    void testPatchRequest() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("update", "new value");

        given()
            .spec(requestSpec)
            .body(requestBody)
        .when()
            .patch("/patch")
        .then()
            .statusCode(200)
            .body("json.update", equalTo("new value"))
            .body("url", equalTo("https://postman-echo.com/patch"));

    }

    @Test
    void testDeleteRequest() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("id", "1234");

        given()
            .spec(requestSpec)
            .body(requestBody)
        .when()
            .delete("/delete")
        .then()
            .statusCode(200)
            .body("json.id", equalTo("1234"))
            .body("url", equalTo("https://postman-echo.com/delete"));
    }
}
