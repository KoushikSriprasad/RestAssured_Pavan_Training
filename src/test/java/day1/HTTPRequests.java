package day1;

import org.testng.annotations.Test;
// Static packages 3
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
/*
given(): Content type, Set Cookies, add auth, add param, set headers info etc..

when(): get, put, post , delete
		get: Read Request
		put: update exisisting with body
		post: Crate new request
		delete: delete request using id no
then(): All validations status code, extract response, extract headers, & Response body

// Add 3 Static pacakages we get this in static import

*/

public class HTTPRequests {
	int id;
	@Test(priority=1)
	void getUsers()
	{
		given()
		
		.when() 
			.get("https://reqres.in/api/users?page=2")
		
		.then()
			.statusCode(200)
			.body("page",equalTo(2))
			.log().all();
	}
	
	@Test(priority=2)
	void createUser()
	{
		HashMap data = new HashMap();
		data.put("name","Prasad");
		data.put("job", "Learner");
		
		
	id=	given()
			.contentType("application/json")
			.body(data)
		
		.when()
			.post("https://reqres.in/api/users")
			.jsonPath().getInt("id");
		
		//.then()
		//	.statusCode(201)
		//	.log().all();
		
		
	}
	
	@Test(priority=3,dependsOnMethods= {"createUser"})
	void updateUser()
	{
		HashMap data = new HashMap();
		data.put("name","SriPrasad");
		data.put("job", "Tester");
		
		
		given()
			.contentType("application/json")
			.body(data)
		
		.when()
			.put("https://reqres.in/api/users/"+id)
			
		.then()
		.statusCode(200)
		.log().all();
			
	
	}
	
	@Test(priority=4)
	void deleteUser()
	{
		given()
		
		.when()
		.delete("https://reqres.in/api/users/"+id)
		.then()
		.statusCode(204)
		.log().all();
		
	}
}
