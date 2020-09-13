package apiTests.jsonPath;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class SpartanJsonCollections {
    /**
     * Given accept type is json
     * And path param spartan id is 11
     * When user sends a get request to "/spatans/{id}"
     * Then status code is 200
     * And content type is json
     * and
     *            "id" = 11,
     *           "name" = "Nona",
     *           "gender" = "Female",
     *           "phone" = 7959094216

     */

    @BeforeAll
    @Test
    public  static void setUp(){
        baseURI= "http://18.206.198.139";
        port = 8000;
        basePath="/api";
    }



    @Test
    public void test(){
      Response response =  given()
                .accept(ContentType.JSON)
                .pathParam("id", 11).
        when()
                .get("/spartans/{id}");

        //convert Json response to Java Collection
        Map<String, Object> spartanMap =  response.body().as(Map.class);
        System.out.println(spartanMap.get("id"));
        System.out.println(spartanMap.get("name"));

        //one example verification one side map/ expected value

        assertThat(spartanMap.get("name"),is("Nona"));

    }

    @Test
    public void test2(){
        Response response = given().accept(ContentType.JSON).
                when().get("/spartans");

           // response.prettyPrint();

            //convert full json body to list of maps
        List<Map<String, Object>>  listOfSpartans = response.body().as(List.class);

           //print all data of first spartan
        System.out.println("listOfSpartans.get(0) = " + listOfSpartans.get(0));

        Map<String, Object> firstSpartan = listOfSpartans.get(0);
        System.out.println("firstSpartan = " + firstSpartan.get("name"));

        int counter =1;
        for(Map<String, Object> map : listOfSpartans ) {
            System.out.println(counter +" -spartan "+map);
        }
    }
}
