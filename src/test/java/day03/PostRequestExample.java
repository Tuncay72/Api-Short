package day03;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class PostRequestExample {

    @BeforeAll
    @Test
    public static void setUp() {
        RestAssured.baseURI = "http://18.206.198.139";
        RestAssured.port = 8000;
        RestAssured.basePath = "/api";
    }

    @DisplayName("Testing POST /api/spartans")
    @Test
    public void testAddSpartan(){
        String myBodyData = "{\"name\": \"Tuncay\",\n" +
                "        \"gender\": \"Male\",\n" +
                "        \"phone\": 1234567890\n" +
                "    }";
        System.out.println("myBodyData = " + myBodyData);

        given()
                .contentType(ContentType.JSON)
                .body(myBodyData)
                .log().all().
        when()
                .post("/spartans").
        then()
                .log().all()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .body("success",is("A Spartan is Born!"))
                .body("data.name", is("Tuncay"));
    }

    // Please create another test
    // make a post request , store the response Object
    // save the id into int variable
    // save the name into String
    //as a homework, save the Spartan data filed into the map
    //so your map will contain id, gender, phone
    // print them out.
    @DisplayName("Practice extracting data")
    @Test
    public void postRequestExtractingData(){

        String myDataBody = "{\"name\": \"Asli\",\n" +
                " \"gender\": \"Female\",\n" +
                "   \"phone\": 1234567098}";

        Response response = given()
                                   .contentType(ContentType.JSON)
                                   .body(myDataBody)
                                   .log().all().
                            when()
                                    .post("/spartans")
                                    .prettyPeek();

        System.out.println("response.path(\"data.id\") = " + response.path("data.id"));
        System.out.println("response.path(\"data.name\") = " + response.path("data.name"));
        System.out.println("response.path(\"data.phone\") = " + response.path("data.phone"));

        System.out.println("===========================");

        JsonPath jp = response.jsonPath();
        System.out.println("Id using jsonPath  = " + jp.getInt("data.id"));
        System.out.println("Name using jsonPath = " + jp.getString("data.name"));


    }
}
