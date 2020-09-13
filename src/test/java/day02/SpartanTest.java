package day02;

import io.restassured.RestAssured;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class SpartanTest {
    @DisplayName("Get All Spartans")
    @Test
    public void testAllSpartans(){
        //String spartanURL=http://18.206.198.139:8000/api/spartans ;
        //how to set base URL , part, base path so i can reuse them

        RestAssured.baseURI= "http://18.206.198.139:8000/api/spartans";
        RestAssured.basePath="/api";

        // it will create the request URL as is
        // baseURI + basePath + what is after get( "This Part" )
        when()
                .get("/spartans").
                then()
                .statusCode(is(200) ) ;
    }
    @DisplayName("Get All Spartan 2 ")
    @Test
    public void testAllSpartans2(){
        // send the same request specifiying the accept header is application/json
        // use baseuri basepath , check status code 200 , contentType header is json

        given()
                .baseUri("http://18.206.198.139:8000")
                .basePath("/api")
                .accept("application/json").
                when()
                .get("/spartans").
                then()
                .statusCode( is(200) )
                //.contentType("application/json;charset=UTF-8")
                //.contentType( is("application/json;charset=UTF-8")  )
                .contentType( ContentType.JSON )  ;

    }
}
