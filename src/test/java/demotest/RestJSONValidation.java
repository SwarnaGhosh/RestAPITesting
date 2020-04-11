package demotest;
import io.restassured.RestAssured;
import javafx.application.Application;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import util.RestUtil;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

/*@author=Swarnaprabha Ghosh*/

public class RestJSONValidation extends RestUtil {

    @BeforeClass
    public void setData(){

        RestAssured.baseURI=readFile("BaseURIJSONValidation");
        RestAssured.basePath="/posts/1" ;
    }

    @Test
    public void checkStatusCode(){

                 given()
                .when().get()
                .then().assertThat().statusCode(200).log().all();
    }
}
