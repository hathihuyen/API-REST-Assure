package com.hha.apiexamples.Testcases;

import io.restassured.http.ContentType;
import org.testng.annotations.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Example3Test {

    /**
     * Test that retrieves the list of circuits for the 2017 Formula 1 season in JSON format
     * and checks that there are 20 circuits in the list
     */
    @Test
    public void test_NumberOfCircuitsFor2017Season_ShouldBe20() {

        given().
        when().
                get("http://ergast.com/api/f1/2017/circuits.json").
        then().
                assertThat().
                body("MRData.CircuitTable.Circuits.circuitId",hasSize(20));
    }

    /**
     * Check:
     * - The response status code is equal to 200
     * - The response content type equals "application/json"
     * - The value of the response header "Content-Length" equals "4567"
     */
    @Test
    public void test_ResponseHeaderData_ShouldBeCorrect() {

        given().
        when().
                get("http://ergast.com/api/f1/2017/circuits.json").
        then().
                assertThat().
                statusCode(200).
        and().
                contentType(ContentType.JSON).
        and().
                header("Content-Length",equalTo("4551"));
    }

    /**
     * Using the query parameter
     */
    @Test
    public void test_Md5CheckSumForTest_ShouldBe098f6bcd4621d373cade4e832627b4f6() {

        String originalText = "test";
        String expectedMd5CheckSum = "098f6bcd4621d373cade4e832627b4f6";

        given().
                param("text",originalText).
        when().
                get("http://md5.jsontest.com").
        then().
                assertThat().
                body("md5",equalTo(expectedMd5CheckSum));
    }

    /**
     * Instead of param(), path parameters are defined using the pathParam() method.
     * In addition, we need to define which part of the endpoint path represents the path variable,
     * which is done using the curly bracket notation seen in the example above.
     */
    @Test
    public void test_NumberOfCircuits_ShouldBe20_Parameterized() {

        String season = "2017";
        int numberOfRaces = 20;

        given().
                pathParam("raceSeason",season).
                when().
                get("http://ergast.com/api/f1/{raceSeason}/circuits.json").
                then().
                assertThat().
                body("MRData.CircuitTable.Circuits.circuitId",hasSize(numberOfRaces));
    }

    /**
     * Create a DataProvider object containing the required test data
     * @return Set of records containing years and the number of Formula 1 races in each year
     */
    @DataProvider(name="seasonAndNumberOfRaces")
    public Object[][] createTestDataRecords(){
        return new Object[][]{
                {"2017", 20},
                {"2016", 21},
                {"1966", 9}
        };
    }

    /**
     * Pass the test data object to the parameterized test through test method parameters
     * @param season
     * @param numberOfRaces
     */
    @Test(dataProvider="seasonsAndNumberOfRaces")
    private void test_NumberOfCircuits_ShouldBe_DataDriven(String season, int numberOfRaces) {

        given().
                pathParam("raceSeason",season).
        when().
                get("http://ergast.com/api/f1/{raceSeason}/circuits.json").
        then().
                assertThat().
                body("MRData.CircuitTable.Circuits.circuitId",hasSize(numberOfRaces));
    }
}
