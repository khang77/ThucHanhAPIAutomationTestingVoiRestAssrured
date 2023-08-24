package com.api.auto.testcase;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import java.util.HashMap;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.api.auto.utils.PropertiesFileUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class TC_API_CreateWork {
	private String token;
	private Response response;
	private ResponseBody responseBody;
	private JsonPath jsonBody;
	
	private String myWork = "QC";
	private String myExperience = "5 năm";
	private String myEducation = "University";
	
	@BeforeClass
	public void init() {
		String baseUrl = PropertiesFileUtils.getProperty("baseurl");
		String createWorkPath = PropertiesFileUtils.getProperty("createWorkPath");
		token = PropertiesFileUtils.getToken("token");
		
		RestAssured.baseURI = baseUrl;
		HashMap<String, Object> body = new HashMap<String, Object>();
		body.put("nameWork", myWork);
		body.put("experience", myExperience);
		body.put("education", myEducation);
		
		RequestSpecification request = RestAssured.given()
				.contentType(ContentType.JSON)
				.header("token", token)
				.body(body);
		
		response = request.post(createWorkPath);
		responseBody = response.body();
		jsonBody = responseBody.jsonPath();
		
		System.out.println(" " + responseBody.asPrettyString());
	}
	@Test(priority = 0)
	public void TC01_Validate201Created() {
		assertEquals(response.getStatusCode(), 201, "Status Check Failed!");
	}
	
	@Test(priority = 1)
	public void TC02_ValidateWorkId() {
		assertTrue(responseBody.asString().contains("id"));
	}
	
	@Test(priority = 2)
	public void TC03_ValidateNameOfWorkMatched() {
		assertTrue(responseBody.asString().contains("nameWork"));
		String actualNameWork = jsonBody.getString("nameWork");
		assertEquals(actualNameWork, myWork, "nameWork is not matching");
	}
	
	@Test(priority = 3)
	public void TC04_ValidateExperienceMatched() {
		assertTrue(responseBody.asString().contains("experience"));
		String actualExperience = jsonBody.getString("experience");
		assertEquals(actualExperience, myExperience, "experience is not matching");
	}

	@Test(priority = 4)
	public void TC05_ValidateEducationMatched() {
		assertTrue(responseBody.asString().contains("education"));
		String actualEducation = jsonBody.getString("education");
		assertEquals(actualEducation, myEducation, "education is not matching");
	}
}
