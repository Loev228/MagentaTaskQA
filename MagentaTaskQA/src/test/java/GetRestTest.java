
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import pojos.ResourcePojo;
import pojos.UserPojo;
import java.util.List;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class GetRestTest {

    private final static String baseURI ="https://reqres.in/api";
    @Test
    public void getUser() {
        given().
                baseUri(baseURI).
                basePath("/users").
                contentType(ContentType.JSON)
                .when()
                .get("/2")
                .then()
                .statusCode(200);
    }

    @Test
    public void getListUsers() {
      List<UserPojo> users =
              given()
                      .baseUri(baseURI)
                      .basePath("/users")
                .contentType(ContentType.JSON)
              .get()
              .jsonPath().
              getList("data",UserPojo.class);

      assertThat(users).isNotNull();

    }

    @Test
    public void SingleUserNotFound() {
        given()
                .baseUri(baseURI)
                .basePath("/users/23").
                contentType(ContentType.JSON)
                .when()
                .get().
                then().statusCode(404);
    }

    @Test
    public void getListResource() {
        List<ResourcePojo> resources =  given().
                baseUri(baseURI)
                .basePath("/unknown")
                .contentType(ContentType.JSON).get()
                .jsonPath().getList("data",ResourcePojo.class);

        assertThat(resources).isNotNull();
    }

    @Test
    public void getSingleResource() {
        given().baseUri(baseURI).
                basePath("/unknown/2").
                contentType(ContentType.JSON)
                .when().
                get()
                .then()
                .statusCode(200);
    }

    @Test
    public void SingleResourceNotFound() {
        given().baseUri(baseURI)
                .basePath("/unknown/23").
                contentType(ContentType.JSON)
                .when()
                .get()
                .then()
                .statusCode(404);
    }

    @Test
    public void getDelayedResponse() {
        given().baseUri(baseURI)
                .basePath("/users?delay=3").
                contentType(ContentType.JSON)
                .when()
                .get().
                then()
                .statusCode(200);
    }
}
