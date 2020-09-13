package apiTests.put;

import apiTests.pojo.Spartan;
import io.restassured.http.ContentType;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class SpartanPUTRequest {


    @BeforeAll
    @Test
    public static void setUp(){
        baseURI = "http://18.206.198.139";
        port=8000;
        basePath="/api";
    }

    @DisplayName("PUT Request")
    @Test
    public void test(){

        //Different ways to send Json Body
          //String
          //Using collection(Map)
          //POJO

        //using map

        Map<String, Object> putMap = new HashMap<>();

        putMap.put("name","Terence2");
        putMap.put("gender","Male");
        putMap.put("phone",5432167890L);

        //we wanna send body with updated value and content-type header
        given()
                .contentType(ContentType.JSON)
                .pathParam("id",100)
                .body(putMap).
        when()
                .put("/spartans/{id}").
        then()
                .assertThat().statusCode(204)
        ;
    }



}
