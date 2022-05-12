package tests;

import com.google.gson.Gson;
import objects.reqres.ResourceList;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ListResourceTest {

    @Test
    public void listResourceTest() {
        String body = given()
                .when()
                .get("https://reqres.in/api/unknown")
                .then()
                .log().all()
                .statusCode(200)
                .extract().body().asString();

        ResourceList resourceList = new Gson().fromJson(body, ResourceList.class);
        int year = resourceList.getData().get(0).getYear();
        System.out.println(year);
        String name = resourceList.getData().get(0).getName();
        System.out.println(name);
        String color = resourceList.getData().get(0).getColor();
        System.out.println(color);
    }
}
