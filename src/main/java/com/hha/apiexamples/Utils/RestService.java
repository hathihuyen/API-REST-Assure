package com.hha.apiexamples.Utils;

import com.jayway.restassured.response.Response;

import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.containsString;

public class RestService {
    public void getUserData(String userId){
        given()
                .contentType("application/json")
                .when()
                .get("/user/" + userId)
                .then()
                .body(containsString("id"))
                .body(containsString("login"))
                .body(containsString("www"))
                .assertThat().statusCode(200);

    }

    public void addNewUser(String login, String www){
        given()
                .contentType("application/json")
        .body("{\"login\":\"" + login + "\",\n"
                + "\"www\":\"" + www + "\"}")
        .when()
            .post("/addNewUser")
        .then()
            .body(containsString("id"))
            .body(containsString("login"))
            .body(containsString("www"))
            .statusCode(200);
    }

    public void getHealthcheckPing() {
        when().get("/ping")
                .then()
                .log().all()
                .body(containsString("pong!"));
    }

    //extracting login value from getSpecificUserData() method response
    public void getSpecificUserData(String userId) {
        Response response =
                given().log().all()
                    .contentType("application/json")
                .when()
                    .get("/user/" + userId)
                .then()
                    .statusCode(200)
                .extract().response();
        //log.info("----retrieved user data for user: {}" , (String) response.path("login"));
    }
}
