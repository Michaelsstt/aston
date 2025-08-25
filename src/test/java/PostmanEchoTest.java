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
                    .body("url", equalTo("https://postman-echo.com/get"));
    }

    @Test
    void testPostRowText() {
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
    void testPostFromData() {
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
}
