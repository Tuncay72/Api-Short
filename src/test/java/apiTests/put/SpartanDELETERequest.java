package apiTests.put;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.given;

public class SpartanDELETERequest {

    @BeforeAll
    @Test
    public static void setUp(){
        baseURI = "http://18.206.198.139";
        port=8000;
        basePath="/api";
    }

    @DisplayName("PATCH Request")
    @Test
    public void test(){

        given()
                .contentType(ContentType.JSON)
                .pathParam("id",100).
        when()
                .delete("/spartans/{id}").
        then()
                .statusCode(204)
        ;

        //verify the spartan deleted
        given()
                .contentType(ContentType.JSON)
                .pathParam("id",100).
        when()
                .delete("/spartans/{id}").
        then()
                .statusCode(404)
        ;

    }
    
}
