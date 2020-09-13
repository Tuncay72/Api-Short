package day05;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.Spartan;

import static io.restassured.RestAssured.given;

public class SecureSpartanTest {
    //This instances are created from the image : Cybertek_Latest_BasicAuth

    //54.160.106.84
    //100.24.235.129
    //23.23.75.140
    //Add @BeforeAll and use the spartanApp ID with basic auth

    @BeforeAll
    public static void init(){
        RestAssured.baseURI= "http://18.206.198.139";
        RestAssured.port=8000;
        RestAssured.basePath = "/api";
    }

    //add a test
    // make a simple get request /spartan/{id}

    @DisplayName("Get single Spartan Secured")
    @Test
    public void test1(){
        given()
                .contentType(ContentType.JSON)
                .log().all()
                .pathParam("id",174).
        when()
                .get("/spartans/{id}").
        then()
                .log().all()
                .statusCode(401);
    }

    @DisplayName("Test GET /spartan/{id} Endpoint with credentials")
    @Test
    public void testGettingSpartanWithCredentials(){

        int newId = createRandomSpartan();

        given()
                .log().ifValidationFails()
                .auth().basic("admin", "admins")
                .pathParam("id",newId).
        when()
                .get("/spartans/{id}").
        then()
                .statusCode(200)
        ;
    }


    public static int createRandomSpartan(){
        Faker faker = new Faker();
        String firstName =faker.name().firstName();
        System.out.println("firstName = " + firstName);

        //String name   = new Faker().name().firstName();
        String gender = new Faker().demographic().sex();
        long phone    = new Faker().number().numberBetween(1000000000,9999999999L) ;
        Spartan sp    = new Spartan(firstName,gender,phone);

        Response response =
                      given()
                             .contentType(ContentType.JSON)
                             .body(sp).
                      when()
                             .post("/spartans")
                      ;

        return response.path("data.id")  ;

    }
}
