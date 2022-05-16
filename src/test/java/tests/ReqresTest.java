package tests;

import objects.reqres.User;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ReqresTest {


    @Test
    public void listUsersTest() {
        given()
                .log().all()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    public void singleUserTest() {
        given()
                .log().all()
                .when()
                .get("https://reqres.in/api/users/2")
                .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    public void singleUserNotFoundTest() {
        given()
                .log().all()
                .when()
                .get("https://reqres.in/api/users/23")
                .then()
                .log().all()
                .statusCode(404);
    }

    @Test
    public void listResourceTest() {
        given()
                .log().all()
                .when()
                .get("https://reqres.in/api/unknown")
                .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    public void singleResourceTest() {
        given()
                .log().all()
                .when()
                .get("https://reqres.in/api/unknown/2")
                .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    public void singleResourceNotFoundTest() {
        given()
                .log().all()
                .when()
                .get("https://reqres.in/api/unknown/23")
                .then()
                .log().all()
                .statusCode(404);
    }

    @Test
    public void postCreateUserTest() {
        User user = User.builder()
                .name("morpheus")
                .job("leader")
                .build();
        given()
                .body(user)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .log().all()
                .statusCode(201);
    }

    @Test
    public void updatePutTest() {
        User user = User.builder()
                .name("morpheus")
                .job("zion resident")
                .build();
        given()
                .body(user)
                .when()
                .put("https://reqres.in/api/users/2")
                .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    public void updatePatchTest() {
        User user = User.builder()
                .name("morpheus")
                .job("zion resident")
                .build();
        given()
                .body(user)
                .when()
                .patch("https://reqres.in/api/users/2")
                .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    public void deleteTest() {
        given()
                .when()
                .delete("https://reqres.in/api/users/2")
                .then()
                .log().all()
                .statusCode(204);
    }

    //failed
    @Test
    public void successfulRegisterTest() {
        User user = User.builder()
                .email("eve.holt@reqres.in")
                .password("pistol")
                .build();
        given()
                .header("Content-Type", "application/json")
                .body(user)
                .when()
                .post("https://reqres.in/api/register")
                .then()
                .log().all()
                .statusCode(200);
    }


    @Test
    public void unsuccessfulRegisterTest() {
        User user = User.builder()
                .email("sydney@fife")
                .build();
        given()
                .body(user)
                .when()
                .post("https://reqres.in/api/register")
                .then()
                .log().all()
                .statusCode(400);
    }

    //failed
    @Test
    public void successfulLoginTest() {
        User user = User.builder()
                .email("eve.holt@reqres.in")
                .password("cityslicka")
                .build();
        given()
                .header("Content-Type", "application/json")
                .body(user)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    public void unsuccessfulLoginTest() {
        User user = User.builder()
                .email("peter@klaven")
                .build();
        given()
                .body(user)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().all()
                .statusCode(400);
    }

    @Test
    public void delayedResponseTest() {
        given()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .log().all()
                .statusCode(200);
    }
}
