package day03;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class Practice1 {

    @BeforeAll
    // in JUnit 5 @BeforeAll @AfterAll is static method
    // if we do not make it static it does not work because that's how it's defined in the doc
    public static void init(){
        // example of setting the port separately from baseURI
        RestAssured.baseURI = "http://18.206.198.139" ;
        RestAssured.port = 8000 ;
        RestAssured.basePath = "/api" ;
    }

    @DisplayName("Simple Test")
    @Test
    public  void test1(){
        given()
                .log().all()
                .queryParam("gender","Female").
        when()
                .get("/spartans/search").
        then()
                .statusCode(200);
    }

}
