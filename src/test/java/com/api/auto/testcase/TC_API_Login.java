package com.api.auto.testcase;

import static org.testng.Assert.assertEquals;

import static org.testng.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.api.auto.utils.PropertiesFileUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class TC_API_Login {
		private String account;
		private String password;
		private Response response;
		private ResponseBody responseBody;
		private JsonPath jsonBody;
		
		@BeforeClass
		public void init() {
			  String baseUrl = PropertiesFileUtils.getProperty("baseurl");
			    String loginPath = PropertiesFileUtils.getProperty("loginPath");
			    account = PropertiesFileUtils.getProperty("account");
			    password = PropertiesFileUtils.getProperty("password");

			    RestAssured.baseURI = baseUrl;

			    Map<String, Object> body = new HashMap<String, Object>();
			    body.put("account", account);
			    body.put("password", password);
			    

			    RequestSpecification request = RestAssured.given()
			            .contentType(ContentType.JSON) 
			            .body(body);

			    response = request.post(loginPath);
			    responseBody = response.body();
			    jsonBody = responseBody.jsonPath();

			    System.out.println(" " + responseBody.asPrettyString());
		}
		
		@Test(priority = 0)
		
		public void TC01_Validate200Ok() {
			assertEquals(response.getStatusCode(), 200, "Status Check Failed!");
		}
		
		@Test(priority = 1)
		public void TC02_ValidateMessage() {
			assertTrue(responseBody.asString().contains("message"));
			String expectMessage = "Đăng nhập thành công";
			String actualMessage = jsonBody.getString("message");
			assertEquals(actualMessage, expectMessage,"message is not matching");
		}
		
		@Test(priority = 2)
		public void TC03_ValidateToken() {
			assertTrue(responseBody.asString().contains("token"));
			String token = jsonBody.getString("token");
			PropertiesFileUtils.saveToken("token", token);
		}
		
		@Test(priority = 3)
		public void TC04_ValidateUserType() {
			assertTrue(jsonBody.getMap("user").containsKey("type"));
			String actualType = jsonBody.getString("user.type");
			String expectedType = "UNGVIEN";
			assertEquals(actualType, expectedType, "Type is not \"UNGVIEN\"");
		}
		
		@Test(priority = 4)
		public void TC05_ValidateAccount() {
			
			assertTrue(jsonBody.getMap("user").containsKey("account"));			
			String actualAccount = jsonBody.getString("user.account");
			String expectedAccount = PropertiesFileUtils.getProperty("account");
			assertEquals(actualAccount, expectedAccount, "account is not matching");
			
			assertTrue(jsonBody.getMap("user").containsKey("password"));
			String acctualPassword = jsonBody.getString("user.password");
			String expectedPassword = PropertiesFileUtils.getProperty("password");
			assertEquals(acctualPassword, expectedPassword, "password is not matching");
		}
}
