package demotest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import util.RestUtil;

public class RestAPIautomation extends RestUtil {

	@Test
	public void getTest() {

		// BaseURL
		RestAssured.baseURI = "https://reqres.in";

		given().when().get("/api/users/1").then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and()
				.body("data.id", equalTo(1)).and().body("data.first_name", equalTo("George"));

		System.out.println("TestCase Passed");

	}

	@Test
	public  void getTest1(){


		RestAssured.baseURI=readFile("BaseURI");

		given().when().get("weather/city/Hyderabad").then().assertThat().statusCode(200).and().header("Content-Type","application/json").and()
				.body("City",equalTo("Hyderabad"));

	}


}