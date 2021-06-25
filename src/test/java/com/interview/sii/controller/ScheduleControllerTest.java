package com.interview.sii.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.is;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
class ScheduleControllerTest {

    @Test
    void testGetSchedules() {
        when().get("/schedules").then()
                .statusCode(200).body("size()", is(3));
    }

}