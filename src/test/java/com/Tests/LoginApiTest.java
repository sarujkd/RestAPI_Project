package com.Tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class LoginApiTest extends testBase{

	
	private static Logger logger = LogManager.getLogger();
	
		
       @Test(enabled=false)
   public void TC1_login_Invalid_Username_Test() {
	
    	   RestAssured.baseURI="https://us-central1-qa01-tekarch-accmanager.cloudfunctions.net";
	Response res=RestAssured.given().body("{\"username\":\"sarva@ta.com\",\"password\":\"sarvamangala\"}").when()
			.contentType("application/json").post("/login");
	
	logger.info("login_Invalid_Username_Test is started");
	
	//Get the status code
	int statusCode=res.statusCode();
	System.out.println("Status code: "+statusCode);
	Assert.assertTrue(statusCode==401);
	logger.info("Response code "+statusCode +" is displayed");
	
	
	System.out.println("\nResponse body: "+res.asString());
	logger.info("Response body is displayed");
	
	//Extract the ststus from the response body
	String status=res.jsonPath().getString("status");
	System.out.println("\nStatus :"+status);
	Assert.assertEquals(status,"username and password is incorrect");
	
	logger.info("login_Invalid_Username_Test is passed");
}

       @Test(enabled=false)
       public void TC2_login_Invalid_password_Test() {
    	
    	   RestAssured.baseURI="https://us-central1-qa01-tekarch-accmanager.cloudfunctions.net";
    	Response res=RestAssured.given().body("{\"username\":\"sarvamangala@ta.com\",\"password\":\"sarva\"}").when()
    			.contentType("application/json").post("/login");
    	
    	logger.info("login_Invalid_Username_Test is started");
    	
    	//Get the status code
    	int statusCode=res.statusCode();
    	System.out.println("Status code: "+statusCode);
    	Assert.assertTrue(statusCode==401);
    	logger.info("Response code "+statusCode +" is displayed");
    	
    	
    	System.out.println("\nResponse body: "+res.asString());
    	logger.info("Response body is displayed");
    	
    	//Extract the ststus from the response body
    	String status=res.jsonPath().getString("status");
    	System.out.println("\nStatus :"+status);
    	Assert.assertEquals(status,"username and password is incorrect");
    	
    	logger.info("login_Invalid_Username_Test is passed");
    }

       @Test
       public void TC3_login_With_Invalid_Method_Test() {
    	
    	   RestAssured.baseURI="https://us-central1-qa01-tekarch-accmanager.cloudfunctions.net";
    	Response res=RestAssured.given().body("{\"username\":\"sarvamangala@ta.com\",\"password\":\"sarvamangala\"}").when()
    			.contentType("application/json").put("/login");
    	
    	logger.info("login_With_Invalid_Method__Test is started");
    	
    	//Get the status code
    	int statusCode=res.statusCode();
    	System.out.println("Status code: "+statusCode);
    	Assert.assertTrue(statusCode==403);
    	logger.info("Response code "+statusCode +" is displayed");
    	
    	
    	String Responsebody= res.asString();
    	System.out.println("\nResponse body :"+Responsebody);
    	logger.info("Response body is displayed");
    	
    	Assert.assertEquals(Responsebody,"Forbidden!");
    	logger.info("login_With_Invalid_Method__Test is passed");
    	
    }

}
