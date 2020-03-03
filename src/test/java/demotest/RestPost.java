package demotest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class RestPost {

	@Test(enabled = true)
	public void postMethod() {

		// RestAssured.baseURI = "http://216.10.245.166";

		RestAssured.baseURI = "https://reqres.in";

		given().header("content-type", "application/json")
				.body("{" + "\"name\": \"morpheus\"," + "\"job\": \"leader\"" + "}").when().post("/api/users").then()
				.assertThat().statusCode(201).and().contentType(ContentType.JSON).and()
				.body("name", equalTo("morpheus"));

	}

	@Test
	public void createPlaceAPI() {
		RestAssured.baseURI = "http://216.10.245.166";
		given().

				queryParam("key", "qaclick123")
				.body("{" + "\"location\": {" + "\"lat\": -33.8669710," + "\"lng\": 151.1958750" + "},"
						+ "\"accuracy\": 50," + "\"name\": \"Google Shoes!\"," + "\"phone_number\": \"(02) 9374 4000\","
						+ "\"address\": \"48 Pirrama Road, Pyrmont, NSW 2009, Australia\","
						+ "\"types\": [\"shoe_store\"]," + "\"website\": \"http://www.google.com.au/\","
						+ "\"language\": \"en-AU\"" + "}")
				.when().post("/maps/api/place/add/json").then().assertThat().statusCode(200).and()
				.contentType(ContentType.JSON).and().body("status", equalTo("OK"));

	}
}