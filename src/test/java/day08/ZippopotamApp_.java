package day08;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


/**
 *  --- Data Drive your GET / api.zippopotam.us/us/:state/:city
 *         --- Create a csv file called state_city.csv
 *         add 3 column  state , city , numberOfZipcodes
 *         VA ,  Fairfax , 9(send the request and prepare this number)
 *         assert the state , city
 *         and number of zipcodes you got from the response
 */


 class ZippopotamApp_DataDriven {
//
//    @BeforeAll
//    public static void init(){
//        RestAssured.basePath =  "http://api.zippopotam.us/us";
//
//
//    }

    @ParameterizedTest(name = "iteration {index} | expectedState{0}, expectedCity{1}, numberOfZipcodes{2}" )
    @CsvFileSource(resources = "/state_city.csv",numLinesToSkip = 1)
    public void testCityToZipcode(String expectedState, String expectedCity ,int numberOfZipcodes) {

//        System.out.println("state = " + state);
//        System.out.println("city = " + city);
//        System.out.println("numberOfZipcodes = " + numberOfZipcodes);
        //import io.restassured.response.Response;
        Response response = given()
                .pathParam("state", expectedState)
                .pathParam("city", expectedCity)
                .baseUri("http://api.zippopotam.us/us").
                        when()
                .get("/{state}/{city}")
               // .prettyPeek()
                ;

        // assert the state and city match in the response
        JsonPath jp = response.jsonPath() ;
        // why do we use single quote? because there can not be a space in json path
        // we are using the '' to treat the 2 word as one
        System.out.println("state = " + jp.getString("'state abbreviation'")  ) ;
        System.out.println("city = "  + jp.getString("'place name'"));

        assertThat( jp.getString("'state abbreviation'"), is(expectedState) );
        assertThat( jp.getString("'place name'"), is(expectedCity) );

        
        List<String> zipList = jp.getList("places.'post code'");
        System.out.println("zipList = " + zipList);
        //zipList.forEach(foreachzipList-> System.out.println("foreachzipList = " + foreachzipList));

        assertThat(zipList , hasSize( numberOfZipcodes ) );

        //.get("/{state}/{city}" , state , city )
    }


}
    
    

