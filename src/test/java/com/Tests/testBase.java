package com.Tests;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class testBase {

	public static String token=null; 
	static Map<String, String> map=new HashMap<String, String>();
	
	private static Logger logger = LogManager.getLogger();
	
	@BeforeClass
   public static void login_Valid_Credentials_Test() {
		
		RestAssured.baseURI="https://us-central1-qa01-tekarch-accmanager.cloudfunctions.net";
		Response res=RestAssured.given().body("{\"username\":\"sarvamangala@ta.com\",\"password\":\"sarvamangala\"}").when()
				.contentType("application/json").post("/login");
		
		logger.info("login_Valid_Credentials_Test is started");
		
		int statusCode=res.statusCode();
		System.out.println("Status code: "+statusCode);
		Assert.assertTrue(statusCode==201);
		logger.info("Valid Response code "+statusCode +" is displayed");
		
		System.out.println("Response body: "+res.asString());
		logger.info("Response body is displayed");
		
		//Extract the token from response
		
		 token=res.jsonPath().getString("token");
		System.out.println(token);
		
		token=token.replace("[","").replace("]","");
	
		map.put("token",token);
		System.out.println(token);
		logger.info("Extracted the token");
		
	}
	
}
