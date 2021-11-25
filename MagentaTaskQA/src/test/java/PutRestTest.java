
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class PutRestTest {

    @Test
    public void UpdateUser() {

        String requestBody = "{\n" +
                "  \"name\": \"morpheus\",\n" +
                "  \"job\": \"zion resident\",\n}";
       Response response = given()
               .baseUri("https://reqres.in/api").and()
               .body(requestBody)
               .when()
               .put("/users/2")
               .then()
               .extract()
               .response();


        Assertions.assertNotNull(response.jsonPath().getString("updatedAt"));
        Assertions.assertEquals(200,response.statusCode());

    }


}
