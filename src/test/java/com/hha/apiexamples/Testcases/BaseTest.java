package com.hha.apiexamples.Testcases;

import com.hha.apiexamples.Utils.*;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.annotations.*;


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

    private static Logger log = LogManager.getLogger(BaseTest.class.getName());

    public static String doLogin(){
        Response response;
        log.info("Starting Test Case : doLogin");
        String loginPayload = PayloadGenerator.generatePayLoadString("JiraLogin.json");

        String endPointURI = URL.getEndPoint("/rest/auth/1/session");
        response = RESTCalls.POSTRequest(endPointURI, loginPayload);
        log.info(response.getBody().asString());
        String strResponse = TestUtils.getResposeString(response);
        io.restassured.path.json.JsonPath jsonRes = TestUtils.jsonParser(strResponse);
        String sessionID = jsonRes.getString("session.value");
        log.info("JIRA JSession ID : " + sessionID);
        return sessionID;
    }
}