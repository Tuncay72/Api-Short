package day08;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import org.junit.jupiter.api.BeforeAll;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import pojo.Spartan;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

/**
----- Data Drive your POST /api/Spartans request
        ----  Create a csv file called allSpartans.csv under src/test/resources folder
        add 3 column :
                     name , gender , phone
        add 6 rows of valid data
        then try to send post request using these data


        How did I write query directly in the IntelliJ and see the result
        Used a plugin called Database Navigator
        and provided the connection info

 */

public class SpartanApp_DataDriver {


    @BeforeAll
    public static void init(){
        RestAssured.baseURI = "http://18.206.198.139:8000";
        RestAssured.basePath = "/api";
    }

    @ParameterizedTest(name ="iteration {index} | csvName:{0}, csvGender{1},csvPhone{2}")
    @CsvFileSource(resources = "/allSpartans.csv",numLinesToSkip = 1)
    public void testAllSpartans(String csvName,String csvGender, long csvPhone){

//        System.out.println("csvName = " + csvName);
//        System.out.println("csvGender = " + csvGender);
//        System.out.println("csvPhone = " + csvPhone);
        // I need the post body
        Spartan spBody = new Spartan(csvName, csvGender, csvPhone) ;

        given()
                .contentType(ContentType.JSON)
                .body(spBody).
        when()
                .post("/spartans").
        then()
                .statusCode(201)
                .body("success", is("A Spartan is Born!") )
                .body("data.name" , is( csvName) )
                .body("data.gender", is(csvGender) )
                .body("data.phone" , is(csvPhone))
                .body("data.id" , is(not(0)) ) ; // just checking the is not 0
    }





}
