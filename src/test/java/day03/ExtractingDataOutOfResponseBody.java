package day03;

import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class ExtractingDataOutOfResponseBody {

    // provide your baseURI in the test
    // add query parameters
    // apikey yourAPIkey here
    // t   = Boss Baby
    // Save the response into response object

    @DisplayName("Getting Movie Info")
    @Test
    public void test1(){
        Response response =
                given()
                        .log().all()
                        .baseUri("http://www.omdbapi.com")
                        .queryParam("apikey","26aa5b74")
                        .queryParam("t","Boss Baby").
                when()
                        .get();

        //response.prettyPrint();
        System.out.println("response.statusCode() = " + response.statusCode());

        // if you want to get single data out for example just title , just year
        // use path method of Response object and provide the jsonPath


        System.out.println("response.path(\"Title\") = " + response.path("Title"));
        System.out.println("response.path(\"year\") = " + response.path("Year"));
        System.out.println("response.path(\"director\") = " + response.path("Director"));
        System.out.println("response.path(\"actors\") = " + response.path("Actors"));

        // getting the first Rating source
        // the jsonPath for first Rating source is Ratings[0].Source
        
//        String firstRatingSRC=response.path("Ratings[0].Source");
//        System.out.println("firstRatingSRC = " + firstRatingSRC);

        System.out.println("response.path(\"Ratings[0].Source\") = " + response.path("Ratings[0].Source"));

    }
}
