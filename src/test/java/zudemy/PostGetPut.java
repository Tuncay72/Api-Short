package zudemy;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.testng.annotations.DataProvider;

import java.util.jar.JarEntry;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class PostGetPut {


    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = "http://216.10.245.166";
       // RestAssured.basePath = "/library/Addbook.php";
    }
    



    
    @Test
    public void testPostData(){

        Response response =
                given()
                     .log().all()
                     .accept(ContentType.JSON)
                     .body(Library.Addbook("adsfs","6465")).
                when()
                      .post("/Library/Addbook.php").
                then().statusCode(200).extract().response()
                       ;

        JsonPath jp = response.jsonPath();
        System.out.println("ID = " + jp.get("ID"));

    }


}