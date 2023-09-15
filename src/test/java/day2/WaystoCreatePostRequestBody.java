package day2;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

/*
 Diffrent Ways to create Post Requests
 1. Post request body using HashMap
 2. Post request body Creation using Org.json
 3. Post request body Creation using POJO class
 4. Post request using external json file data
 */

public class WaystoCreatePostRequestBody {
	// 1. Post request body using HashMap

	//	@Test(priority=1)
	void  testPostUsingHashMap()
	{
		HashMap data = new HashMap();
		data.put("name", "prasad");
		data.put("Location","India");
		data.put("phone", "9739911088");
		
		String courseArr[] = {"Java","Selenium"};
		data.put("Courses", courseArr);
		
		
		given()
			.contentType("application/json")
			.body(data)
		.when()
			.post("http://localhost:3000/students")
		.then()
			.statusCode(201)
			.body("name", equalTo("prasad"))
			.body("Location", equalTo("India"))
			.body("phone", equalTo("9739911088"))
			.body("Courses[0]", equalTo("Java"))
			.body("Courses[1]", equalTo("Selenium"))
			.header("Content-Type", "application/json; charset=utf-8")
			.log().all();
	}
	
	
	// 2. Post request body Creation using Org.json
	// add dipendency in pom.xml -> org.json 
	// .body(data.toString) it should be converted to string format

	//	@Test(priority=1)
	void  testPostUsingOrgJsonLibrary()
	{
		JSONObject data = new JSONObject();
		data.put("name", "prasad");
		data.put("Location","India");
		data.put("phone", "9739911088");
		
		String courseArr[] = {"Java","Selenium"};
		data.put("Courses", courseArr);
		
		
		given()
			.contentType("application/json")
			.body(data.toString())
		.when()
			.post("http://localhost:3000/students")
		.then()
			.statusCode(201)
			.body("name", equalTo("prasad"))
			.body("Location", equalTo("India"))
			.body("phone", equalTo("9739911088"))
			.body("Courses[0]", equalTo("Java"))
			.body("Courses[1]", equalTo("Selenium"))
			.header("Content-Type", "application/json; charset=utf-8")
			.log().all();
	}

//	3. Post request body Creation using POJO class
	// create separate POJO Class 
	// adding String datas and using sounrce generate getters and setters in separate pojo class

	//@Test(priority=1)
	void  testPostUsingPojoClass()
	{
		
		Pojo_PostRequest data = new Pojo_PostRequest();
		data.setName("prasad");
		data.setLocation("India");
		data.setPhone("9739911088");
		
		String courseArr[]= {"Java","Selenium"};
		data.setCourses(courseArr);
			
		given()
			.contentType("application/json")
			.body(data)
		.when()
			.post("http://localhost:3000/students")
		.then()
			.statusCode(201)
			.body("name", equalTo("prasad"))
			.body("Location", equalTo("India"))
			.body("phone", equalTo("9739911088"))
			.body("Courses[0]", equalTo("Java"))
			.body("Courses[1]", equalTo("Selenium"))
			.header("Content-Type", "application/json; charset=utf-8")
			.log().all();
	}

//	4. Post request using external json file data
	// 1. create a file Body.json
	
	@Test(priority=1)
	void  testPostUsingExternalJsonFile() throws FileNotFoundException
	{
		File f = new File(".\\Body.json");
		FileReader fr = new FileReader(f);
		JSONTokener jt = new JSONTokener(fr);
		
		JSONObject data = new JSONObject(jt);
			
		given()
			.contentType("application/json")
			.body(data.toString())
		.when()
			.post("http://localhost:3000/students")
		.then()
			.statusCode(201)
			.body("name", equalTo("prasad"))
			.body("Location", equalTo("India"))
			.body("phone", equalTo("9739911088"))
			.body("Courses[0]", equalTo("Java"))
			.body("Courses[1]", equalTo("Selenium"))
			.header("Content-Type", "application/json; charset=utf-8")
			.log().all();
	}

	
	
	
	// Deleting Student Record
	@Test(priority=2)
	void testDelete()
	{
		given()
			
		.when()
			.delete("http://localhost:3000/students/1")
		
		.then()
			.statusCode(404);
	}
	
	
}
