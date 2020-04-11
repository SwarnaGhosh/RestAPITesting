package demotest;

import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import net.minidev.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import util.RestUtil;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;

/*@author=Swarnaprabha Ghosh*/

public class RestPostJsonPath extends RestUtil {

    JSONObject jsonObject=new JSONObject();

    @BeforeClass
    public void creatingPayload(){

        jsonObject.put("FirstName", getFirstName());
        jsonObject.put("LastName", getLastName());
        jsonObject.put("UserName", getUserName());
        jsonObject.put("Password", getPassword());
        jsonObject.put("Email", getEmail());

        RestAssured.baseURI=readFile("BaseURIPost");
        RestAssured.basePath="/register";
    }

    @Test
    public void postJSON(){

        given()
                .body(jsonObject.toJSONString())
        .when()
                .post()
        .then()
                .assertThat().statusCode(201).and()
                .contentType(ContentType.JSON).and()
                .body("SuccessCode",equalTo("OPERATION_SUCCESS"))
                .body("Message",equalTo("Operation completed successfully"));

    }
}
