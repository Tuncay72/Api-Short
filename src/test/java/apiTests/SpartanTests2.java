package apiTests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SpartanTests2 {
    String spartanBaseUrl = "http://18.206.198.139:8000";

    /*
    When user send GET request to /api/spartans e3nd point
    Then status code must be 200
    And body should contains Allen
     */

    @Test
    public void viewSpartanTest2(){
        Response response = RestAssured.get(spartanBaseUrl+"/api/spartans");

        // Verify status code 200
        Assert.assertEquals(response.statusCode(),200);

        //Verify body contains Allen
        Assert.assertFalse(response.body().asString().contains("Allen"));
    }


    /**
      Given accept type is Json
      When user send a get request to spartanAllURL
      Then response status code is 200
      And response body should be Json format
       */
    @Test
    public void viewSpartanTest3() {
        Response response = RestAssured.given().accept(ContentType.JSON)
                          .when().get(spartanBaseUrl+"/api/spartans");

        //verify status code 200
        Assert.assertEquals(response.statusCode(),200);

        //verify response body json
        Assert.assertEquals(response.contentType(),"application/json;charset=UTF-8");

       // System.out.println("response.contentType() = " + response.contentType());

    }
}
