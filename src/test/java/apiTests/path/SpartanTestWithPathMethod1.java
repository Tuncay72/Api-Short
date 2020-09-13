package apiTests.path;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class SpartanTestWithPathMethod1 {

    /**
    Given accept type is Json
     And path param id is 10
     When user sends a get request to "/spartans/{id}"
     Then status code is 200
     And contentType is "application/json;char"
     And response payload values matc the following
          id is 10
          name is Lorenza
          gender is Female
          phone is  3312820936
*/

    @BeforeAll
    @Test
    public static void setUp(){
        RestAssured.baseURI = "http://18.206.198.139";
        RestAssured.port = 8000;
        RestAssured.basePath="/api";
    }

    @DisplayName("Get spesific spartan")
    @Test
    public void test1(){
        Response response = given()
                                   .contentType(ContentType.JSON)
                                   .pathParam("id", 10).
                             when()
                                   .log().all()
                                   .get("/spartans/{id}");

        response.prettyPrint();
       // response.prettyPeek();
       //response.peek();

//        //verify status code
        assertThat(response.statusCode(),is(200));
//
//        //verify content-type
        assertThat(response.contentType(),is(equalTo("application/json;charset=UTF-8")));
//
        System.out.println("id :"+ response.body().path("id").toString());
        System.out.println("name :"+ response.body().path("name").toString());
        System.out.println("gender :"+ response.body().path("gender").toString());
        System.out.println("phone :"+ response.body().path("phone").toString());

        int id = response.path("id");
        String name = response.body().path("name");
        String gender = response.path("gender");
        long phone = response.path("phone");

//        System.out.println("id = " + id);
//        System.out.println("name = " + name);
//        System.out.println("gender = " + gender);
//        System.out.println("phone = " + phone);

        assertThat(id,is(10));
        assertThat(name,is("Lorenza"));
        assertThat(gender,is("Female"));
        assertThat(phone, is(3312820936l));

    }

}

