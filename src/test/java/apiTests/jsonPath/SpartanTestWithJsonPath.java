package apiTests.jsonPath;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class SpartanTestWithJsonPath {
    /**
     * Given accept type is json
     * And path param spartan id is 11
     * When user sends a get request to "/spartans/{id}"
     * Then status code is 200
     * And content-type is json
     * and
         "id" = 11,
          "name" = "Nona",
          "gender" = "Female",
          "phone" = 7959094216
     */

    @BeforeAll
    @Test
    public static void setUp(){

        baseURI = "http://18.206.198.139";
        port = 8000;
        basePath="/api";
    }

    @DisplayName("Get a spartan value with JsonPath")
    @Test
    public void test1(){
        Response response = given()
                                   .contentType(ContentType.JSON)
                                   .pathParam("id", 11).
                            when()
                                   .log().all()
                                   .get("/spartans/{id}")
                                    ;
        
        
        //verify status code
        assertThat(response.statusCode(),is(200));

        //verify contentType is Json
        assertThat(response.contentType(), is("application/json;charset=UTF-8"));

        System.out.println("response.contentType() = " + response.contentType());

        //how to read value with path() method
        int id = response.path("id");
        System.out.println("id = " + id);

        System.out.println("================================================================");

        //how to read value with JsonPath
        JsonPath jsonData = response.jsonPath();



        //System.out.println("jsonData.getJsonObject(\"contentType\") = " + jsonData.getString("ContentType.JSON"));


        int id1 = jsonData.getInt("id");
        String name = jsonData.getString("name");
        String gender = jsonData.getString("gender");
        long phone = jsonData.getLong("phone");
//        String contentType = jsonData.getString("Content-Type");
//        System.out.println("contentType = " + contentType);

        System.out.println("id1 = " + id1);
        System.out.println("name = " + name);
        System.out.println("gender = " + gender);
        System.out.println("phone = " + phone);
       // System.out.println("contentType = " + contentType);
        
        System.out.println("================================");

        jsonData.prettyPrint();

        assertThat(id1,is(11));
        assertThat(name, is("Nona"));
        assertThat(gender,is("Female"));
        assertThat(phone,is(7959094216l));
        
       // assertThat(contentType, is("application/json; charset=UTF-8"));




    }
}
