#### Thực Hành API Automation Testing với Rest Assured  

![Eclipse](https://img.shields.io/badge/Eclipse-FE7A16.svg?style=for-the-badge&logo=Eclipse&logoColor=white)
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)

#### 🌻 YÊU CẦU:  
Thực hành luồng cơ bản sau và kiểm chứng thông tin API:  
      1. Đăng nhập hệ thống với thông tin tài khoản cho trước  
      2. Tạo một thông tin công việc bất kỳ
       
🔴 1. Đăng nhập hệ thống với tài khoản sau:  
account: testerFunix  
password: Abc13579  

![project_required drawio](https://github.com/khang77/ThucHanhAPIAutomationTestingVoiRestAssrured/assets/92577611/3752c881-52fd-43bd-88c2-0f49f5bb6371)  

![project_required_2](https://github.com/khang77/ThucHanhAPIAutomationTestingVoiRestAssrured/assets/92577611/c7fce10f-b1d3-44d1-9f69-b6b6b43862e1)  

🔴 2. Tạo 1 thông tin công việc:  

![project_required - 3 drawio](https://github.com/khang77/ThucHanhAPIAutomationTestingVoiRestAssrured/assets/92577611/3abbfc66-1cbb-45f1-9e6a-c302dd100d90)  

![project_required_4 - Copy](https://github.com/khang77/ThucHanhAPIAutomationTestingVoiRestAssrured/assets/92577611/c33aa17e-811c-48f0-a410-9212c638c646)

#### 🚩🚩🚩 Các bước thực hiện:  

![workd _step drawio](https://github.com/khang77/ThucHanhAPIAutomationTestingVoiRestAssrured/assets/92577611/ef74e7d5-eed5-41c1-949c-6a7df0a39d1a)  

✍️ _Sử dụng Body dưới dạng Hashmap:_
```java
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
```

✍️ _Assertion kiểm tra thông tin:_
```java
@Test(priority = 0)
public void TC01_Validate201Created() {
  assertEquals(response.getStatusCode(), 201, "Status Check Failed!");
}
```
```java
@Test(priority = 1)
public void TC02_ValidateWorkId() {
  assertTrue(responseBody.asString().contains("id"));
}
```
💯 TestNG Reports  

![results](https://github.com/khang77/ThucHanhAPIAutomationTestingVoiRestAssrured/assets/92577611/c89570be-576f-4c35-89f2-a2a332defa54)




