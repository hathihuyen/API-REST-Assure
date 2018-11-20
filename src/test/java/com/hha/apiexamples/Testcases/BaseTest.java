package com.hha.apiexamples.Testcases;

//import Utils.TestUtils;
import com.hha.apiexamples.Utils.RestUtil;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

/**
 *
 */
public class BaseTest {

    public Response res = null; //Response
    public JsonPath jp = null; //JsonPath

    //Instantiate a Helper Test Methods (testUtils) Object
    //TestUtils testUtils = new TestUtils();

    @BeforeClass
    public void setup (){
        //Test Setup
        RestUtil.setBaseURI("http://generator.swagger.io/"); //Setup Base URI
        RestUtil.setBasePath("api"); //Setup Base Path
        RestUtil.setContentType(ContentType.JSON); //Setup Content Type
    }

    @AfterClass
    public void afterTest (){
        //Reset Values
        RestUtil.resetBaseURI();
        RestUtil.resetBasePath();
    }
}