package com.hha.apiexamples.Issue;

import com.hha.apiexamples.Testcases.BaseAssertion;
import com.hha.apiexamples.Testcases.BaseTest;
import com.hha.apiexamples.Utils.PayloadGenerator;
import com.hha.apiexamples.Utils.RESTCalls;
import com.hha.apiexamples.Utils.URL;
import com.jayway.restassured.response.Response;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.annotations.*;

public class CreateIssue {

	private String sessionID;
	Response response;

	private static Logger log = LogManager.getLogger(CreateIssue.class.getName());

	@BeforeMethod
	public void setUp() {
		sessionID = BaseTest.doLogin();
	}

	@Test
	public void createIssue() {
		log.info("Starting Create Issue Test");
		String URI = URL.getEndPoint("/rest/api/2/issue/");
		String createIssuePayLaod = PayloadGenerator.generatePayLoadString("CreateBug.json");
		response = RESTCalls.POSTRequest(URI, createIssuePayLaod, sessionID);
		BaseAssertion.verifyStatusCode(response, 201);
	}
}
