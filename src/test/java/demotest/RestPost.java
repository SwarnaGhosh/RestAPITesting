package demotest;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import util.RestUtil;

import java.util.HashMap;

/*@author=Swarnaprabha Ghosh*/

public class RestPost extends RestUtil {

	private static HashMap map=new HashMap();

	@BeforeClass
	public void createPayLoad(){
        map.put("FirstName", getFirstName());
		map.put("LastName", getLastName());
		map.put("UserName", getUserName());
		map.put("Password", getPassword());
		map.put("Email", getEmail());
		RestAssured.baseURI=readFile("BaseURIPost");
		RestAssured.basePath="/register";
	}

	@Test
	public void postRequestNew(){

		given()
				.contentType("application/json")
				.body(map)
		.when()
				.post()
		.then()
		        .statusCode(201).and()
				.header("Content-Type","application/json")
				.body("SuccessCode",equalTo("OPERATION_SUCCESS"))
				.body("Message",equalTo("Operation completed successfully"));
	}
}