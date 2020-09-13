package apiTests.path;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matcher;
import org.hamcrest.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class SpartanTestWithHamcrest {

    @BeforeAll
    @Test
    public static  void setUp(){

        baseURI = "http://18.206.198.139";
        port = 8000;
        basePath = "/api";

    }

    /**
     * Given accept type is Json
     * And path param id is 15
     * When user send request to "/spartans/{id}"
     * Then status code us 200
     * And content type is Json
     * And Json data as following :
     *               "id": 15,
     *             "name": "Meta",
     *             "gender": "Female",
     *             "phone":  1938695106
     */

    @Test
    public void test1(){
         given()
                       .contentType(ContentType.JSON)
                       .pathParam("id", 15).
                when()
                       .get("/spartans/{id}").
                then()
                        .statusCode(200)
                        .contentType("application/json;charset=UTF-8");
    }



    @Test
    public void test2(){
        given()
                .log().all()
                .accept(ContentType.JSON)
                .pathParam("id",15).
        when()
                .get("/spartans/{id}").
        then()
                .log().all()
                .statusCode(200)
                .contentType("application/json;charset=UTF-8")
                .body("id", is(equalTo(15)),
     "name",is(equalTo("Meta")),
                            "gender",Matchers.equalTo("Female"),
                            "phone",Matchers.equalTo(1938695106)     );
    }
}
