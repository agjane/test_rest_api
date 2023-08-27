package tests;

import org.junit.jupiter.api.Test;

public class TryTests {

    @Test
    void checkTotal () {
        get("https://selenoid.autotests.cloud/status")
                .then()
                .statusCode(200)
                .body("total", is (20));
    }
}

