package day02;

import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class Practice1 {
    Response response = get("http://54.174.216.245:8000/api/hello");

    @DisplayName("Get All characters simple test")
    @Test
    public void testBreakingBad(){
        //https://www.breakingbadapi.com/api/characters
        when()
                .get("https://www.breakingbadapi.com/api/characters")
               //.prettyPeek()
        .then()
                .statusCode(is(200))
                .header("Content-Type", is("application/json; charset=utf-8"));

//
//        System.out.println("response.statusCode() = " + response.statusCode());
//        System.out.println("response.statusLine() = " + response.statusLine());
//        System.out.println("response.contentType() = " + response.contentType());
//        System.out.println("response.getBody() = " + response.getBody());
//        System.out.println("response.htmlPath() = " + response.htmlPath());
//        System.out.println("response.jsonPath() = " + response.jsonPath());
//
//        System.out.println("response.asString() = " + response.asString());

        //response.print();     //response.prettyPrint();


      //  response.prettyPeek();
    }
    
}
