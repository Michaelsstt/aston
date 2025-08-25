import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.junit.jupiter.api.BeforeAll;

public class BaseApiTest {

    protected static RequestSpecification requestSpec;

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://postman-echo.com";
        requestSpec = new RequestSpecBuilder()
                .setBaseUri("https://postman-echo.com")
                .setContentType(ContentType.JSON)
                .build();
    }

}
