package com.interview.sii.controller;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
class LectureControllerTest {

    @Test
    void testMakeReservation_UserAlreadyExists() throws JSONException {

        JSONObject requestBody = new JSONObject()
                .put("login", "user1")
                .put("email", "wrongEmail@gmail.pl");

        given()
                .contentType("application/json")
                .body(requestBody.toString())
                .when()
                .post("/lectures/{lectureId}/reservations", 5)
                .then()
                .statusCode(400)
                .body(equalTo("Podany login " + requestBody.getString("login") + " jest juz zajety! "));
    }

    @Test
    void testMakeReservation_LectureNotFoundException() throws JSONException {

        JSONObject requestBody = new JSONObject()
                .put("login", "")
                .put("email", "");

        int lectureId = 99;

        given()
                .contentType("application/json")
                .body(requestBody.toString())
                .when()
                .post("/lectures/{lectureId}/reservations", lectureId)
                .then()
                .statusCode(400)
                .body(equalTo("Lecture with id " + lectureId + " not found!"));
    }

    @Test
    void testMakeReservation_MaximumParticipantException() throws JSONException {

        JSONObject requestBody = new JSONObject()
                .put("login", "")
                .put("email", "");

        int lectureId = 1;

        given()
                .contentType("application/json")
                .body(requestBody.toString())
                .when()
                .post("/lectures/{lectureId}/reservations", lectureId)
                .then()
                .statusCode(400)
                .body(equalTo("Maximum participants for lecture " + lectureId + " reached!"));
    }

    @Test
    void testMakeReservation_MaximumLecturesOnPathException() throws JSONException {

        JSONObject requestBody = new JSONObject()
                .put("login", "user7")
                .put("email", "user7@gmail.pl");

        int lectureId = 8;

        given()
                .contentType("application/json")
                .body(requestBody.toString())
                .when()
                .post("/lectures/{lectureId}/reservations", lectureId)
                .then()
                .statusCode(400)
                .body(equalTo("You already have registered lecture on this path!"));
    }

    @Test
    void testMakeReservation_OK() throws JSONException {

        JSONObject requestBody = new JSONObject()
                .put("login", "newTestUser")
                .put("email", "newTestUser@email.pl");

        int lectureId = 8;

        given()
                .contentType("application/json")
                .body(requestBody.toString())
                .when()
                .post("/lectures/{lectureId}/reservations", lectureId)
                .then()
                .statusCode(200)
                .body(equalTo(""));
    }


    @Test
    void deleteReservation_OK() throws JSONException {

        JSONObject requestBody = new JSONObject()
                .put("login", "user1")
                .put("email", "user1@gmail.pl");

        int lectureId = 8;

        given()
                .contentType("application/json")
                .body(requestBody.toString())
                .when()
                .delete("/lectures/{lectureId}/reservations", lectureId)
                .then()
                .statusCode(200)
                .body(equalTo(""));
    }

    @Test
    void deleteReservation_UserNotFoundException() throws JSONException {

        JSONObject requestBody = new JSONObject()
                .put("login", "wrongUser")
                .put("email", "wrongUser@gmail.pl");

        int lectureId = 8;

        given()
                .contentType("application/json")
                .body(requestBody.toString())
                .when()
                .delete("/lectures/{lectureId}/reservations", lectureId)
                .then()
                .statusCode(400)
                .body(equalTo("User with login "
                        + requestBody.getString("login")
                        + " and email: " + requestBody.getString("email")
                        + " not found!"));
    }
}