package day09;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class ZipDataDriven {

    @Test
    public void testSingle() {

        Response response =
                given()
                      .pathParam("state", "VA")
                      .pathParam("city", "Fairfax")
                      .baseUri("http://api.zippopotam.us/us").
                when()
                      .get("/{state}/{city}")
                      .prettyPeek()
                ;

        // you can use then method to keep chaining your response assertions
        response.then().statusCode(200);
    }
}
