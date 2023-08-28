package tests;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.is;

public class TryTests {

    @Test
    void checkTotal() {
        get("https://selenoid.autotests.cloud/status")
                .then()
                .statusCode(200)
                .body("total", is(20));

    }
}

