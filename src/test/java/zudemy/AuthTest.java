package zudemy;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class AuthTest {



    @Test
    public void test1(){
        Response accessTokenResponse =given()  .contentType(ContentType.JSON)
                 .queryParam("redirect_url","https://rahulshettyacademy.com/getCourse.php")
                 .queryParam("grant_type","authorization_code")
                  .when()
                 .log().all()
                 .post("https://wwww.googleapis.com/oauth2/v4/token");


        JsonPath jp =  accessTokenResponse.jsonPath();
        
        String accessToken = jp.getString("access_token");
        System.out.println("accessToken = " + accessToken);
   
    }
}
