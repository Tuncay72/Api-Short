package apiTests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utility.ConfigurationReader;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class SingleSchemaValidation {

    @BeforeAll
    public static void init(){
        RestAssured.baseURI = ConfigurationReader.getProperty("spartan1.base_url");
        RestAssured.basePath ="/api";
    }

    @Test
    public void test1(){
        given()
                .accept(ContentType.JSON)
                .pathParam("id",55).
        when()
                .get("/spartans/{id}").
        then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("singleSpartanSchema.json"))
        ;
    }
}
