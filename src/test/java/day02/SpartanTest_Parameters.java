package day02;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;

public class SpartanTest_Parameters {
    @BeforeAll
    public static void setUp(){
        RestAssured.baseURI =  "http://18.206.198.139:8000";
        RestAssured.basePath = "/api";
    }

    @DisplayName("Testing /spartans/{id}")
    @Test
    public void testSingleSpartan(){
        given()
                .log().all()
                .pathParam("id",100).
        when()
                .get("spartans/{id}").
        then()
                .statusCode(is(200))   ;
    }


    @DisplayName("Testing /spartans/{id}")
    @Test
    public void testSingleSpartan2() {
        given()
                .log().all().
        when()
               .get("spartans/{id}",100).
        then()
              .statusCode(is(200));

    }

    @DisplayName("Testing /spartans/{id} Body")
    @Test
    public void testSingleSpartanBody(){

        given()
                .log().all()
                .pathParam("id",100).
        when()
                .get("spartans/{id}").
        then()
                .log().all()
                .statusCode(is(200))
                .body("id",is(100))
                .body("name",is("Terence"))
                .body("gender", is("Male"))
                .body("phone",is(1311814806))   ;


    }
}
