package day04;

import static com.fasterxml.jackson.databind.node.JsonNodeType.POJO;
import static io.restassured.RestAssured.*;

import day04.pojo.Spartan;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


public class PostRequestWithPOJO  {

    @BeforeAll
    public static void init(){
        baseURI   ="http://18.206.198.139";
        port      =8000;
        basePath  ="/api";
}

    @Test
    public void   postRequestWithPojo(){

      Spartan sp1 = new Spartan("Irina Li","Female",123123231);

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(sp1).
        when()
                .post("/spartans").
        then()
                .log().all()
                .statusCode(201) ;
    }
}
