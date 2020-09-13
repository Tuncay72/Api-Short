package apiTests.path;


import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;


public class SpartanTestWithPathParameters {
    /**
     *  Given accept type is Json
     *      And id parameter value is 18
     *      When user sends a get request to "/spartans/{id}"
     *      Then status code is 200
     *      And contentType is "application/json;char"
     *      And "Allen" should be in response payload"

     */

    @BeforeAll
    public static void setUpClass(){
        baseURI="http://18.206.198.139";
        port = 8000;
        basePath = "/api";
    }

    @Test
    public void pathTest1(){
        Response response =
                given()
                       .accept(ContentType.JSON)
                       .pathParam("id", 18).
                 when()
                        .log().all()
                        .get("/spartans/{id}")
                ;

        response.prettyPrint();

        System.out.println("response.contentType() = " + response.contentType());

        //verify status code
        assertThat(response.statusCode(),is(200));

        //verify contentType
         assertThat(response.contentType(),is("application/json;charset=UTF-8"));

         //verify Jason exist
        assertTrue(response.body().asString().contains("Jason"));
    }
}
