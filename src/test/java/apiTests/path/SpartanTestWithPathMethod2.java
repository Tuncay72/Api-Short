package apiTests.path;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.restassured.response.Response;
import java.util.List;
import static io.restassured.RestAssured.*;

public class SpartanTestWithPathMethod2 {
    @BeforeAll
    @Test
    public static void setUp(){
        RestAssured.baseURI = "http://18.206.198.139";
        RestAssured.port = 8000;
        RestAssured.basePath = "/api";
    }

    @DisplayName("Get spesific one  or List with path")
    @Test
    public void test1(){
        Response response = get("/spartans");

        int firstID = response.path("id[0]");
        System.out.println("firstID = " + firstID);
        
        String firstName = response.path("name[0]");
        System.out.println("firstName = " + firstName);

        System.out.println("response.contentType() = " + response.contentType());

        //get the last firstName
        String lastName = response.path("name[-1]");
        System.out.println("lastName = " + lastName);
        
        //get the all names and print them
        List<String> names = response.path("name");
        System.out.println("names = " + names);
        System.out.println("names.size() = " + names.size());

        for(String name : names){
            System.out.println("name = " + name);
        }

        System.out.println("=======================================");

        List<Object>  phoneNumbers = response.path("phone");
        System.out.println("phoneNumber = " + phoneNumbers);

        for(Object phoneNumber : phoneNumbers ){
            System.out.println("phone ="  +phoneNumber);
        }
    }
}
