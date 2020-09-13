package day02;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class SpartanSearchTest_QueryParam {
    //http://54.174.216.245:8000/api/spartans/search?gender=male&nameContains=ea

    @BeforeAll
    public static void setUp(){
        RestAssured.baseURI = "http://18.206.198.139";
                        port=8000;
        RestAssured.basePath = "/api" ;
    }

    @DisplayName("Testing /spartans/search Endpoint")
    @Test
    public void testSearch(){
        given()
                .log().all()
                .queryParam("gender","Male")
                .queryParam("nameContains","ea").
        when()
                .get("spartans/search").
        then()
                .log().all()
                .statusCode(200)
                //assert number of elements according to your result here
                .body("numberOfElements", is(12))
                ;
    }
}
