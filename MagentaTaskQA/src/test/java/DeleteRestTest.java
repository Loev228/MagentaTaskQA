import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class DeleteRestTest {

@Test
    public void DeleteUser() {
    given()
            .baseUri("https://reqres.in/api")
            .basePath("/users/2")
            .contentType(ContentType.JSON)
            .when().
            delete().
            then().
            statusCode(204);
}
}
