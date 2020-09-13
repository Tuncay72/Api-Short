package day10;

import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sun.security.krb5.Config;
import utility.ConfigurationReader;
import utility.LibraryUtil;

import javax.security.auth.login.Configuration;
import java.awt.image.RescaleOp;

import static io.restassured.RestAssured.expect;
import static io.restassured.RestAssured.given;

public class LibraryAppReusingTheSpecification {
    static RequestSpecification reqSpec;
    static ResponseSpecification resSpec;


    @BeforeAll
    public static void init(){
        RestAssured.baseURI = ConfigurationReader.getProperty("library1.base_url");
        RestAssured.basePath = "/rest/v1";

        String theToken = LibraryUtil.loginAndGetToken("librarian69@library","KNPXrm3S");
        // we have build a Reusable request Specification for setting contentType

        reqSpec = given()
                         .accept(ContentType.JSON)   // we want json back
                         .log().all()                   // we want to log all
                         .header("x-library-token",theToken);     // we want to set the token header
        // is there easy way to directly create ResponseSpec object without the builder , YES THERE IS !!!!
        resSpec = expect().statusCode(200)        // expecting the Response status code 200
                          .contentType(ContentType.JSON)     // contentType is json
                          .logDetail(LogDetail.ALL);      // want to log all of them
    }

    @DisplayName("Testing GET /get_book_categories with Endpoint spec")
    @Test
    public void testLibrary(){
        given()
                .spec(reqSpec).
        when()
                .get("/get_book_categories").
        then()
                .spec(resSpec)
                ;

    }

    @DisplayName("Testing Get /get_all_users Endpoint with spedc")
    @Test
    public void testGetAllUsers(){

        given()
                .spec(reqSpec).
        when()
                .get("/get_all_users").
        then()
                .spec(resSpec)
                ;
    }

    @DisplayName("Testing GET /dashboard_stats Endpoint with spec")
    @Test
    public void testDashboardStats(){
        given()
                .spec(reqSpec).
        when()
                .get("/dashboard_stats").
        then()
                .spec(resSpec)
                ;
    }
}
