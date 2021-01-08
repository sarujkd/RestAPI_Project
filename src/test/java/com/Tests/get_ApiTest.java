package com.Tests;

import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class get_ApiTest extends testBase {

	private static Logger logger = LogManager.getLogger();

	@Test(priority=1)
	public void getMethod_Test() {

		Response res = RestAssured.given().contentType("application/json").headers(map).get("/getdata");

		int statusCode = res.statusCode();

		logger.info("getMethod_Test is started");

		System.out.println("Get Response : " + res.asString());
		System.out.println("Status code :" + statusCode);
		logger.info("Response code " + statusCode + " is displayed");
		Assert.assertTrue(statusCode == 200);

		logger.info("getMethod_Test is passed");

	}

	@Test(priority=2)
	public void check_accountno_Test() {

		Response res = RestAssured.given().contentType("application/json").headers(map).get("/getdata");

		int statusCode = res.statusCode();

		logger.info("get_accountsList_Test is started");

		System.out.println("Status code :" + statusCode);
		Assert.assertTrue(statusCode == 200);

		logger.info("Response code " + statusCode + " is displayed");

		System.out.println("Get Response : " + res.asString());
		List<String> accountno = new LinkedList<String>();

		accountno = res.jsonPath().getList("data[0].accountno");

		System.out.println("Total number of accountnos: " + accountno.size());

		// check if 90909090 account number is present
		String accno = "90909090";
		for (String acc : accountno) {
			System.out.println(acc.toString());

			if (acc.equals(accno)) {
				System.out.println(accno + " is present");
				break;
			}
		}
		logger.info(accno + " is present");
		logger.info("get_accountsList_Test is passed");

	}

	@Test(priority=3)
	public void getSalary_Test() {

		Response res = RestAssured.given().when().contentType("application/json").headers(map).get("/getdata");

		int statusCode = res.statusCode();

				System.out.println("Get Response : " + res.asString());

		String salary = res.jsonPath().get("data[0].salary[100]");
		logger.info("getSalary_Test is started");

		System.out.println("Status code :" + statusCode);
		logger.info("Response code " + statusCode + " is displayed");
		Assert.assertTrue(statusCode == 200);


		
		System.out.println("100th salary :"+ salary);
		logger.info("getSalary_Test is passed");

	}

}
