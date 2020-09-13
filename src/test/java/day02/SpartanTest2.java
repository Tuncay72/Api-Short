package day02;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class SpartanTest2 {

    @BeforeAll
    public static void setUp(){

        RestAssured.baseURI= "http://1206.198.139";
        RestAssured.port = 8000;
        RestAssured.basePath="/api";

    }

    @DisplayName("Testing /Hello again")
    @Test
    public void testHello(){
        // quick tasks
        // add another test for hello endpoint by reusing the baseURI , basePath above
        // specify you want to get a text result back
        // check status code is 200
        // contentType is Text
        // body is Hello from Sparta

        given()
                .accept(ContentType.TEXT).  //specify you want to get a text result back
        when()
                .get("/hello").  //sending request to baseURI+basePath+ /hello
        then()
               .statusCode(is(200))    //checking status code
               .contentType(ContentType.TEXT)        //checking content type is text
               .body(equalTo("Hello from Sparta")) ;   //checking the body
    }

    @DisplayName("Get All Spartans")
    @Test
    public void testAllSpartans(){
        //String spartanURL=http://18.206.198.139:8000/api/spartans ;
        //how to set base URL , part, base path so i can reuse them

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

        given()
                .accept("application/json").
                when()
                .get("/spartans").
                then()
                .statusCode( is(200) )
                //.contentType("application/json;charset=UTF-8")
                //.contentType( is("application/json;charset=UTF-8")  )
                .contentType( ContentType.JSON )  ;

    }

    @DisplayName("Get Single Spartan")
    @Test
    public void testSingleSpartan(){
        // I want to log the request I sent so I see what is the uRL , methods and so on
        given()
                .log().all().
//                .log().uri().
        when()
                .get("/spartans/971").
//                .prettyPeek().
        then()
                .log().all()
//                .log().body()
//                .log().ifValidationFails()
                .statusCode( is(200)) ;
    }
}
