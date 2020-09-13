package apiTests.path;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
//import static org.junit.jupiter.api.Assertions.assertTrue; //assertTrue yerine "*" koymasak kosulumuz saglanmaz
import static org.junit.jupiter.api.Assertions.*;



public class SpartanTestWithQueryParam {

  /**
     * Given accept type is Json
     * And query parameter value are:
      gender/Female
      nameContains / "J"
     *When user send a Get request to "/spartans/search"
     * Then response status code should be 200
     * And response contentType :
     *And "Female" should be in response payload
     * And "Janette" should be in response payload
  */

    @BeforeAll
    @Test
    public static void test1(){
        baseURI = "http://18.206.198.139";
        port = 8000;
        basePath="/api";
    }

    @Test
    public void QueryParam() {
        Response response =
                given()
                        .accept(ContentType.JSON).
                and()
                        .queryParam("gender", "Female")
                        .queryParam("nameContains", "J").
                when()
                        .get("/spartans/search");


        //verify status code 200
        assertThat(response.statusCode(), is(200));

        System.out.println("response.statusCode() = " + response.statusCode());

       assertTrue(response.body().asString().contains("J"));


        System.out.println("response.body().asString().contains(/J/) = "
                            + response.body().asString().contains("J"));
    }


    @Test
    public void Path() {
        Response response =
                given()
                        .accept(ContentType.JSON).
                and()
                        .pathParam("id", 10).


                when()
                        .get("/spartans/{id}");

        response.prettyPrint();

        //verify status code 200
        assertThat(response.statusCode(), is(200));

        //verify content type
        assertThat(response.contentType(),is("application/json;charset=UTF-8"));

        System.out.println("response.statusCode() = " + response.statusCode());

        assertTrue(response.body().asString().contains("L"));

       System.out.println("response.body().asString().contains(\"J\") = " + response.body().asString().contains("L"));

        System.out.println( "id "+response.body().path("id").toString());
        System.out.println( "name: "+response.body().path("name").toString());
        System.out.println( "gender "+response.body().path("gender").toString());
    }

    @Test
    public void queryParams2(){

        //creating map for query params

        Map<String,Object> paramsMap = new HashMap<>();
        paramsMap.put("gender","Female");
        paramsMap.put("nameContains", "J");

        //sends request
        Response response = given()
                .accept(ContentType.JSON)
                .queryParams(paramsMap).
                        when()
                .get("/spartans/search");

        assertThat(response.statusCode(),is(200));

        assertThat(response.contentType(),is("application/json;charset=UTF-8"));

        assertTrue(response.body().asString().contains("Janette"));

        response.prettyPrint();


    }

    }


