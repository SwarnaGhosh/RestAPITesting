package demotest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class RestRequestChainingPost {

	Properties prop = new Properties();

	@BeforeTest
	public void getHost() {
		try {
			FileInputStream file = new FileInputStream("target/classes/environment.properties");
			prop.load(file);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void addAndRemove() {
		RestAssured.baseURI = prop.getProperty("HOST");
		String req = "{" + "\"location\": {" + "\"lat\": -33.8669710," + "\"lng\": 151.1958750" + "},"
				+ "\"accuracy\": 50," + "\"name\": \"Google Shoes!\"," + "\"phone_number\": \"(02) 9374 4000\","
				+ "\"address\": \"48 Pirrama Road, Pyrmont, NSW 2009, Australia\"," + "\"types\": [\"shoe_store\"],"
				+ "\"website\": \"http://www.google.com.au/\"," + "\"language\": \"en-AU\"" + "}";

		Response response = given().queryParam("key", "qaclick123").body(req).when().post("/api/place/add/json").then()
				.assertThat().statusCode(200).and().contentType(ContentType.JSON).and().body("status", equalTo("OK"))
				.and().extract().response();

		System.out.println(response.asString());

		JsonPath js = new JsonPath(response.asString());
		String placeid = js.get("place_id");
		System.out.println(placeid);

		String reqdelete = "{" + "\"place_id\": \"" + placeid + "\"" + "}";

		given().queryParam("key", "qaclick123").body(reqdelete).when().post("/api/place/delete/json").then()
				.assertThat().statusCode(200).and().contentType(ContentType.JSON).and().body("status", equalTo("OK"));

	}

	@Test(enabled = false)
	public void deletePlace() {

		RestAssured.baseURI = prop.getProperty("HOST");
		String req = "{" + "\"location\": {" + "\"lat\": -33.8669710," + "\"lng\": 151.1958750" + "},"
				+ "\"accuracy\": 50," + "\"name\": \"Google Shoes!\"," + "\"phone_number\": \"(02) 9374 4000\","
				+ "\"address\": \"48 Pirrama Road, Pyrmont, NSW 2009, Australia\"," + "\"types\": [\"shoe_store\"],"
				+ "\"website\": \"http://www.google.com.au/\"," + "\"language\": \"en-AU\"" + "}";

	}

}
