package apiTests.post;

import apiTests.pojo.Spartan;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class SpartanPOSTRequests {


    @BeforeAll
    @Test
    public static void setUp(){
        baseURI = "http://18.206.198.139";
        port=8000;
        basePath="/api";
    }

    @DisplayName("Post data")
    @Test
    public void test1(){
        /**
         * Given accept type and content type is json
         * And request Json body is:
         *                     {
         *                       "name": "Tony",
         *                       "gender": "Male",
         *                       "phone": 1234567890
         *                     }
         * When user sends POST request to '/Spartan/'
         * Then status code  201
         * And content type should be applications/json
         * And json payload / response should contain:
         *          "A Spartan is Born!" message
         *          and same data what is posted
         */

         //first way to using PUT method
        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .body("             {\n" +
                        "                      \"name\": \"Tony\",\n" +
                        "                      \"gender\": \"Male\",\n" +
                        "                      \"phone\": 1234567890\n" +
                        "               }\n").
                        when().post("/spartans");

        response.prettyPrint();

        //validations
        //verify the status code 201
        assertThat(response.statusCode(),is(201));
        assertThat(response.contentType(),is("application/json"));
        //verify success message
        assertThat(response.path("success"),is("A Spartan is Born!"));

        //System.out.println("response.body().path(\"data.name\") = " + response.body().path("data.name"));
        //verify request body  (Do in jsonPath)
        JsonPath jp = response.jsonPath();

        assertThat(jp.getString("data.name"),is("Tony"));
        assertThat(jp.getString("data.gender"),is("Male"));
        assertThat(jp.getLong("data.phone"),is(1234567890l));
    }

    @DisplayName("Post Request With Map")
    @Test
    public void test2(){

        //Second way to use PUT method
        Map<String,Object> requestMap = new HashMap<>();
        requestMap.put("name","Tony") ;
        requestMap.put("gender","Male");
        requestMap.put("phone",1234567890);

        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .body(requestMap).
                        when().post("/spartans");
        response.prettyPrint();

        assertThat(response.statusCode(),is(201));
    }

    @DisplayName("Post RequestWith POJO")
    @Test
    public void test3(){
        //Create Spartan object and used as a body for post request

        //Serialization
        
        Spartan spartan = new Spartan();
        spartan.setName("Alexy");
        spartan.setGender("Female");
        spartan.setPhone(9087654321l);

        Response response =
                given()
                        .accept(ContentType.JSON)
                        .and().contentType(ContentType.JSON)
                        .body(spartan).
                when()
                        .log().all()
                        .post("/spartans")
                ;

       // response.prettyPrint();

        assertThat(response.statusCode(), is(201));

        //==========================GET REQUEST===================================
        //De-Serialization
        Response response2 = given().accept(ContentType.JSON)
                .pathParam("id", 213).
                        when()
                .get("/spartans/{id}");

        Spartan spartanResponse = response2.body().as(Spartan.class);
        System.out.println("spartanResponse.toString() = " + spartanResponse.toString());

    }

}
