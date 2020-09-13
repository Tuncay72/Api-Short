package apiTests.pojo;


import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class SpartanTestPOJODeserialization {

    @BeforeAll
    @Test
    public static void setUp(){
              baseURI="http://18.206.198.139";
              port = 8000;
              basePath ="/api";
    }
    @DisplayName("De-Serialization")
    @Test
    public void test(){

       Response response =
                given()
                        .accept(ContentType.JSON)
                        .pathParam("id", 15).
                when()
                        //.log().all()
                        .get("/spartans/{id}")
                ;

        response.prettyPrint();


       //GSON de-serialization
       // how to convert json response to our spartan class
         Spartan spartan1 = response.body().as(Spartan.class);

        System.out.println("spartan1.toString() = " + spartan1.toString());

        assertThat(spartan1.getName(),is("Meta"));
        assertThat(spartan1.getId(),is(15));
        assertThat(spartan1.getGender(),is("Female"));
        assertThat(spartan1.getPhone(),is(1938695106l));
        //assertThat(spartan1.getPhone(), is(new Long(1938695106)));
    }



    @Test
    public void test2(){

        Gson gson = new Gson();

        String myJsonBody =  "{\n" +
                "    \"id\": 15,\n" +
                "    \"name\": \"Meta\",\n" +
                "    \"gender\": \"Female\",\n" +
                "    \"phone\": 1938695106\n" +
                "}";

        //using gson method do de-serialization our json body
        Spartan spartanMeta = gson.fromJson(myJsonBody,Spartan.class) ;
        System.out.println("spartanMeta = " + spartanMeta);

        //serialization Java object --> JSON BODY
        Spartan spartan = new Spartan(101,"Mike","Male",321342123l);

        String jsonBody = gson.toJson(spartan);

        System.out.println("jsonBody = " + jsonBody);

    }
}
