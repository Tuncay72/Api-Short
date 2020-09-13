package day03;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class PutRequestExample {
    @BeforeAll
    @Test
    public static void setUp() {
        RestAssured.baseURI = "http://18.206.198.139";
        RestAssured.port = 8000;
        RestAssured.basePath = "/api";
    }

    @DisplayName("Updating existing Data")
    @Test
    public void test1(){
        String upDateBody = "{\"name\": \"Jason\",\n" +
                "        \"gender\": \"Male\",\n" +
                "        \"phone\": 1234567809}"
                ;

        given()
                .contentType(ContentType.JSON)
                .body(upDateBody)
                .log().all().
        when()
                .put("/spartans/{id}",104).
        then()
                .log().all()
                .statusCode(204);
    }


    @DisplayName("Delete Data using Delete method")
    @Test
    public void testDelete(){

        when()
                .delete("/spartans/{id}",756).
        then()
                .statusCode(204)
        ;

    }
}
