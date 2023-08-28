package tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.get;
import static io.restassured.http.ContentType.JSON;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static  org.hamcrest.Matchers.is;

public class TryTests {

    @Test
    void checkTotal() {

        Integer expectedTotal = 20;
        Integer actualTotal = given()
                .log().uri()
                .log().body()
                .when()
                .get("https://selenoid.autotests.cloud/status")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract()
                .path("total");

        assertEquals(expectedTotal, actualTotal);
    }

    @Test

    void check401WDHubStatus() {
        given()
                .log().uri()
                .log().body()
                .when()
                .get("https://selenoid.autotests.cloud/wd/hub/status")
                .then()
                .log().status()
                .log().body()
                .statusCode(401);
    }
    @Test
    void checkWDHubStatusOpenAuth() {
        given()
                .log().uri()
                .log().body()
                .when()
                .get("https://user1:1234@selenoid.autotests.cloud/wd/hub/status")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("value.ready", is(true));
    }

    @Test
    void checkWDHubStatusHiddenAuth() {
        given()
                .auth().basic("user1","1234")
                .log().uri()
                .log().body()
                .when()
                .get("https://selenoid.autotests.cloud/wd/hub/status")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("value.ready", is(true));
    }
    @Test

    void postReq() {
        String body = "{\"email\": \"eve.holt@reqres.in\"," +
        "\"password\": \"cityslicha\" }";

        given()
                .log().uri()
                .log().body()
                .contentType(JSON)
                .body(body)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("token", is("QpwL5tke4Pnpja7X4"));
    }

    @Test

    void postReqNegative400() {
        String body = "{\"email\": \"eve.holt@reqres.in\"," +
                "\"password\": \"cityslicha\" }";

        given()
                .log().uri()
                .log().body()
                .body("123")
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is ("Missing email or username"));
    }
}
