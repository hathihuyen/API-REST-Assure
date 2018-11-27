package com.hha.apiexamples.Testcases;

import com.hha.apiexamples.Utils.RestService;
import org.junit.Test;

import static io.restassured.RestAssured.*;


public class TestAPIWithBBD {

    @Test
    public void shouldRetrieveUserData(){
        RestService service = new RestService();
        service.getUserData("1");
    }

    @Test
    public void shouldAddNewUser(){
        RestService service = new RestService();
        service.addNewUser("lukakjkjljl", "www.google.com");
    }


}
