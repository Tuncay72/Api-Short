package day12;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utility.ConfigurationReader;
import java.io.File;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class SchimaValidationTest {
    //lets jump right in !!!
    @BeforeAll
    @Test
    public static void init(){
        RestAssured.baseURI = ConfigurationReader.getProperty("spartan1.base_url");
        RestAssured.basePath = "/api";
    }

    @DisplayName("Testing Get /spartans response against Schema")
    @Test
    public void testAllSpartansSchema(){
        when()
                .get("/spartans").
        then()
                .body(matchesJsonSchemaInClasspath("allSpartansSchema.json") )
                ;
    }

    @DisplayName("Testing GET /spartans/search response against Schema")
    @Test
    public void testGetSpartansSearch(){
        //Search female in query param and validate response against schema

        given()
                .queryParam("gender","Female").
        when()
                .get("/spartans/search").
        then()
                .body(matchesJsonSchemaInClasspath("SearchSchema.json"))
        ;
    }

    @DisplayName("Testing Get /spartans response against Schema")
    @Test
    public void testAllSpartansSchemaInRootPath(){
        //create  a File object that point to the schema
        //use matchesJsonSchema method that accept a file
        //and do your validation
        File mySchema =  new File("allSpartansSchema2.json") ;

        when()
                .get("/spartans").
                then()
                .body(matchesJsonSchema(mySchema))
        ;
    }

}
