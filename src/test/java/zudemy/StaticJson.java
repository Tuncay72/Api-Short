package zudemy;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class StaticJson {

    /**
       How to handle with static json payloads
     */

    @Test
    public void addBook() throws IOException {
        RestAssured.baseURI = "http://216.10.245.166";

        Response response =
                given()
                        .log().all()
                        .accept(ContentType.JSON)
                        .body(GenerateStringFromResource("C:\\Users\\hp\\Desktop\\Addbook.json")).
                        when()
                        .post("/Library/Addbook.php").
                        then().statusCode(200).extract().response()
                ;

        JsonPath jp = response.jsonPath();
        System.out.println("ID = " + jp.get("ID"));

    }

    public static String GenerateStringFromResource(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)))  ;
    }


}
