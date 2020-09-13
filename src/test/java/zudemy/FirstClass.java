package zudemy;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import java.util.List;

import static io.restassured.RestAssured.*;

public class FirstClass {

    @BeforeAll
    public static void init() {


        RestAssured.baseURI =   "https://rahulshettyacademy.com";
        //basePath = "/maps/api" ;
    }

    String postData = "{\n" +
            "\n" +
            "\"dashboard\": {\n" +
            "\n" +
            "\"purchaseAmount\": 910,\n" +
            "\n" +
            "\"website\": \"rahulshettyacademy.com\"\n" +
            "\n" +
            "},\n" +
            "\n" +
            "\"courses\": [\n" +
            "\n" +
            "{\n" +
            "\n" +
            "\"title\": \"Selenium Python\",\n" +
            "\n" +
            "\"price\": 50,\n" +
            "\n" +
            "\"copies\": 6\n" +
            "\n" +
            "},\n" +
            "\n" +
            "{\n" +
            "\n" +
            "\"title\": \"Cypress\",\n" +
            "\n" +
            "\"price\": 40,\n" +
            "\n" +
            "\"copies\": 4\n" +
            "\n" +
            "},\n" +
            "\n" +
            "{\n" +
            "\n" +
            "\"title\": \"RPA\",\n" +
            "\n" +
            "\"price\": 45,\n" +
            "\n" +
            "\"copies\": 10\n" +
            "\n" +
            "}\n" +
            "\n" +
            "]\n" +
            "\n" +
            "}" ;

    
    @Test
    public void test1() {


        JsonPath jp =new JsonPath(postData);
        int total = jp.getInt("dashboard.purchaseAmount");
        System.out.println("total = " + total);
        
        int count = jp.getInt("courses.size")  ;
        System.out.println("count = " + count);
    

        System.out.println("First title = " + jp.getString("courses[0].title"));

        List<String> titles = jp.getList("courses.title");
        //titles.forEach(eachTitles -> System.out.println("eachTitles = " + eachTitles));

        for (int i =1; i <=count ; i++) {
            System.out.println( "Titles ="  +jp.getString("courses["+i+"].title"));
        }
        
//        for (String title : titles){
//            System.out.println("title = " + title);
//        }

        List<Integer> prices =  jp.getList("courses.price");
        prices.forEach(eachPrice-> System.out.println("eachPrice = " + eachPrice));
        for (Integer price : prices){
            System.out.println("price = " + price);
        }

        List<Integer > copieses =jp.getList("courses.copies");
        copieses.forEach(eachCopies-> System.out.println("each = " + eachCopies));
        for (Integer copies : copieses) {
            System.out.println("copieses = " + copies);
        }

        //  spartan2List.forEach( each-> System.out.println(each) );

        System.out.println(" = ====================================================");

        for (int i =0; i < count ; i++) {
          String courseTitle  =jp.get("courses["+i+"].title");
          if (courseTitle.equalsIgnoreCase("RPA")){
              int copies = jp.get("courses["+i+"].copies");
              System.out.println("copies = " + copies);
               break;
          }
        }
    }

    @Test
    public void sumOfCourses(){
        JsonPath jp = new JsonPath(postData);
        int count = jp.getInt("courses.size");
        int sumOfAmount=0;
        for (int i =0; i<count ; i++){

            int price = jp.getInt("courses["+i+"].price") ;
            int copies = jp.getInt("courses["+i+"].copies") ;
            int amount = price*copies;
             sumOfAmount =sumOfAmount+amount;

             System.out.println(amount);
        }

        System.out.println("sumOfAmount = " + sumOfAmount);

        int purchaseAmount = jp.getInt("dashboard.purchaseAmount");

        //System.out.println("purchaseAmount = " + purchaseAmount);
        
        assertThat(purchaseAmount,is(equalTo(sumOfAmount)));
    }
    
    
}
