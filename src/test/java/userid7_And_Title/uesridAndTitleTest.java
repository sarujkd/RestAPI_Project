package userid7_And_Title;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONArray;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.xml.xsom.impl.scd.Iterators.Map;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class uesridAndTitleTest {

	@Test
	public void test_UseridAndTitle() throws JsonGenerationException, JsonMappingException, IOException {
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com/posts";
		Response res = RestAssured.get();
         File fp=new File("./Response.json");
         ObjectMapper maper=new ObjectMapper();
          
       
        String json=res.asString();
       // System.out.println(json);
        maper.writerWithDefaultPrettyPrinter().writeValue( fp,json);
        System.out.println(fp);
       
		// System.out.println("Response :" + res.asString());
		int statusCode = res.getStatusCode();
		System.out.println("statusCode : " + statusCode);
		Assert.assertTrue(statusCode == 200);

		ArrayList<Integer> useridArr = res.jsonPath().get("userId");
		ArrayList<String> titleArr = res.jsonPath().get("title");

		System.out.println("Title of userid=7 are: ");

		for (int i = 0; i < useridArr.size(); i++) {
			int id = useridArr.get(i);
			if (id == 7) {
				System.out.println("id= " + id + " ---->  title = " + titleArr.get(i));
			}
		}

	}
}
