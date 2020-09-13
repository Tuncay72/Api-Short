package apiTests.put;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class SpartanPATCHRequest {


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

        //Different ways to send Json Body
          //String
          //Using collection(Map)
          //POJO

        //using map

        Map<String, Object> patchMap = new HashMap<>();

        patchMap.put("name","Terence4");


        //we wanna send body with updated value and content-type header
        given()
                .contentType(ContentType.JSON)
                .pathParam("id",100)
                .body(patchMap).
        when()
                .patch("/spartans/{id}").
        then()
                .statusCode(204)  ;
    }
}
