package com.hha.apiexamples.Testcases;

import com.hha.apiexamples.Utils.RestUtil;
import org.junit.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class BasicApiTest extends BaseTest {
    @Test
    public void T01_StatusCodeTest() {
        //Verify the http response status returned. Check Status Code is 200?
        res = RestUtil.getResponsebyPath("/gen/clients");
        jp = RestUtil.getJsonPath(res);
        Assert.assertEquals(200, res.getStatusCode());
    }

    @Test
    public void T02_GetClients() {
        res = RestUtil.getResponsebyPath("/gen/clients");
        jp = RestUtil.getJsonPath(res);
        ArrayList clientList = jp.get();
        System.out.println(clientList.size());
    }

    @Test
    public void T03_GetAndroidModelPackageOptions() {
        res = RestUtil.getResponsebyPath("/gen/clients/android");
        jp = RestUtil.getJsonPath(res);
        System.out.println("Opt: " + jp.get("modelPackage.opt"));
        System.out.println("Description: " + jp.get("modelPackage.description"));
        System.out.println("Type: " + jp.get("modelPackage.type"));
    }
}
