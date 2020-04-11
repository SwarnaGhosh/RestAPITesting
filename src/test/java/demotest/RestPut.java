package demotest;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import util.RestUtil;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

/*@author=Swarnaprabha Ghosh*/

public class RestPut extends RestUtil {

    HashMap map=new HashMap();

    String name=getFirstName();
    String job=getLastName();

    @BeforeClass
    public void createPayLoad(){
        map.put("name", name);
        map.put("job", job);
        RestAssured.baseURI=readFile("BaseURIPut");
        RestAssured.basePath="/api/users/2";
    }

    @Test
    public void putMethod(){

        given()
                .contentType("application/json")
                .body(map)
        .when()
                .put()
        .then()
                .assertThat().statusCode(200)
                .contentType("application/json")
                .body("name",containsStringIgnoringCase("John")).and()
                .body("job",containsString(job));


    }

}
