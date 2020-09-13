package day02;

import com.sun.javafx.sg.prism.NGImageView;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class OpenMovieDB_Test {
    //http://www.omdbapi.com/?apikey=be8cd0d1&t=Boss Baby

    @BeforeAll
    public static void init(){
       baseURI = "http://www.omdbapi.com";

    }

    @DisplayName("Test Movie API")
    @Test
    public void testMovies(){
        given()
             
                .log().all()
                .queryParam("apikey", "be8cd0d1")
                .queryParam("t","Boss Baby")
                .queryParam("plot","full").
        when()
                .get().
        then()
                .log().all()
                .statusCode(200)
                .body("Title", containsString("Boss Baby"))
                .body("Ratings[0].Value",is("6.3/10"))
                .body("Ratings[-1].Value", is("50/100"))
        ;

    }
}
