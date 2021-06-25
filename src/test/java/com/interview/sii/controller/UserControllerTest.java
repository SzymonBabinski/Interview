package com.interview.sii.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.is;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
class UserControllerTest {

    @Test
    void getUsersLecturesTest_OK() {
        when()
                .get("/users/lectures/{login}", "user1")
                .then().statusCode(200).body("size()", is(2));
    }

    @Test
    void getUsersLectures_NotFound() {
        when()
                .get("/users/lectures/{login}", "wrongUser")
                .then()
                .statusCode(404);
    }


}