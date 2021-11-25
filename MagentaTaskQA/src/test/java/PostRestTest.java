import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import pojos.*;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertThat;
import static org.assertj.core.api.Assertions.assertThat;

public class PostRestTest {

    private final static String baseURI ="https://reqres.in/api";

    @Test
    public void CreateUser() {
        CreateUserRequest tempReq = new CreateUserRequest();
        tempReq.setName("testName");
        tempReq.setJob("testJob");

        CreateUserResponse tempRes = given()
                .baseUri(baseURI)
                .basePath("/users")
                .contentType(ContentType.JSON)
                .body(tempReq)
                .when().post().then().statusCode(201).extract().as(CreateUserResponse.class);

        assertThat(tempRes).isNotNull().extracting(CreateUserResponse::getName)
                .isEqualTo(tempReq.getName());
        assertThat(tempRes).isNotNull().extracting(CreateUserResponse::getJob)
                .isEqualTo(tempReq.getJob());
    }

    @Test
    public void LoginRequestSuccessful() {
        LoginPojo log = new LoginPojo();
        log.setEmail("eve.holt@reqres.in");
        log.setPassword("cityslicka");

        String token = given()
                .baseUri(baseURI)
                .basePath("/login")
                .contentType(ContentType.JSON)
                .body(log)
                .when().post().then().statusCode(200).extract().asString();

        assertThat(token).isNotNull();
    }

    @Test
    public void LoginRequestUnSuccessful() {
        LoginPojo log = new LoginPojo();
        log.setEmail("eve.holt@reqres.in");

        String errorMessage = given()
                .baseUri(baseURI)
                .basePath("/login")
                .contentType(ContentType.JSON)
                .body(log)
                .when().post().then().statusCode(400).extract().asString();

        assertThat(errorMessage).isNotNull();
    }

    @Test
    public void RegisterSuccessful() {

        LoginPojo registerReq = new LoginPojo();
        registerReq.setPassword("pistol");
        registerReq.setEmail("eve.holt@reqres.in");

        RegisterResponse response = given().baseUri(baseURI)
                .basePath("/register").
                contentType(ContentType.JSON)
                .body(registerReq)
                .when()
                .post()
                .then()
                .statusCode(200)
                .extract()
                .as(RegisterResponse.class);
        assertThat(response.getToken()).isNotNull();
        assertThat(response.getId()).isNotNull();
    }

    @Test
    public void RegisterUnSuccessful() {

        LoginPojo registerReq = new LoginPojo();
        registerReq.setEmail("eve.holt@reqres.in");

        String errorMessage = given().baseUri(baseURI)
                .basePath("/register").
                contentType(ContentType.JSON)
                .body(registerReq)
                .when()
                .post()
                .then()
                .statusCode(400)
                .extract()
                .asString();
        assertThat(errorMessage).isNotNull();
    }
}
