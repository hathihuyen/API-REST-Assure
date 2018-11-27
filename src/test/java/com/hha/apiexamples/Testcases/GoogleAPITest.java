package com.hha.apiexamples.Testcases;

import com.jayway.restassured.path.json.JsonPath;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.testng.annotations.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GoogleAPITest {
    public static String baseURI = "https://maps.googleapis.com";

    @Test
    public void getRequestGoogleMap(){
        RestAssured.baseURI = baseURI;
        given()
                .param("location", "-33.8670522,151.1957362")
                .param("radius", "500")
                .param("type", "restaurant")
                .param("key", "AIzaSyB-ZliaFkPtyfykn7E2nW2yxgBPAvRVUMo")
                .log().all()
        .when()
                .get("/maps/api/place/nearbysearch/json")
        .then()
                .assertThat().statusCode(200)
        .and()
                .contentType(ContentType.JSON)
                .log().all()
        .and()
                .body("results[0].name", equalTo("The Little Snail Restaurant"))
        .and()
                .body("results[13].name", equalTo("Harvest Buffet"))
        .and()
                .body("results[5].vicinity", equalTo("80 Pyrmont Street, Pyrmont"))
        .and()
                .header("server", "pablo");
    }

    @Test
    public void verifyResponseGoogleMap() {
        RestAssured.baseURI = baseURI;
        String requestBody = "{"
                + "\"location\": {"
                    + "\"lat\": -33.866971123445,"
                    + "\"lng\": 151.1958750"
                    + "},"
                + "\"accuracy\": 50,"
                + "\"name\": \"Google Shoes!\", "
                + "\"phone_number\": \"(02) 8899 8888\", "
                + "\"address\": \"34 pirram Road, Pyrmont, NSW 2009, Australia\", "
                + "\"types\": [\"shoe_store\"], "
                + "\"website\": \"http://www.google.com.au/\", "
                + "\"language\": \"en-AU\""
                + "}";
        Response res = given()
                            .queryParam("key", "AIzaSlkj-Zlialkjjj38785838UIO")
                            .body(requestBody)
                        .when()
                            .post("/maps/api/place/add/json")
                        .then()
                            .assertThat().statusCode(200)
                        .extract()
                            .response();
        JsonPath jsonResponse = new JsonPath(res.asString());

        String placeId = jsonResponse.get("place_id");

        given().queryParam("key", "AIzaSlkj-Zlialkjjj38785838UIO")
                .body("{\"place_id\": \"" + placeId + "\"}")
        .when()
                .post("/maps/api/place/delete/json")
        .then()
                .assertThat().statusCode(200)
        .and()
                .body("status", equalTo("OK"));
    }

    @Test
    public void verifyResponseGoogleMapXML() throws IOException {
        RestAssured.baseURI = baseURI;
        String requestBody = generateString("PostXMLPayload.xml");
        Response res = given()
                            .queryParam("key", "AIzaSlkj-Zlialkjjj38785838UIO")
                            .body(requestBody)
                        .when()
                            .post("/maps/api/place/add/xml")
                        .then()
                            .assertThat().statusCode(200)
                        .extract()
                            .response();
        XmlPath xmlResponse = new XmlPath(res.asString());

        String placeId = xmlResponse.get("PlaceAddResponse.place_id");

        given().queryParam("key", "AIzaSlkj-Zlialkjjj38785838UIO")
                .body("{\"place_id\": \"" + placeId + "\"}")
        .when()
                .post("/maps/api/place/delete/json")
        .then()
                .assertThat().statusCode(200)
        .and()
                .body("status", equalTo("OK"));
    }

    private String generateString(String fileName) throws IOException {
        String filePath = System.getProperty("user.dir") + "//Payloads//" + fileName;
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }
}
