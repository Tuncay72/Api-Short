package day07;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class NewsAPI_Test {

    @DisplayName("News API get all News Author if the Source id is not null")
    @Test
    public void testNews(){


        String apiToken = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIyMDI1IiwiYXVkIjoic3R1ZGVudC10ZWFtLWxlYWRlciJ9.Fo9bllgK_UoOS4SGB0OXmo2-J3_F9YA5i-ZGLSykIXI";
                        //eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIyMDI1IiwiYXVkIjoic3R1ZGVudC10ZWFtLWxlYWRlciJ9.Fo9bllgK_UoOS4SGB0OXmo2-J3_F9YA5i-ZGLSykIXI
        // get it from here https://newsapi.org/register
        // Via the Authorization HTTP header. Bearer

        //GET http://newsapi.org/v2/top-headlines?country=us
        Response response =
                given()
                     .baseUri("http://newsapi.org") // you can specify baseURI directly here
                         // if you only have one request and have no intention ofo sharing
                         // with diffferent request
                     .basePath("/v2")
                        // space between Bearer and the token required
                     .header("Authorization", "Bearer "+apiToken)
                     .queryParam("country","us")
                     .queryParam("apiKey","d88e052e22754f989be313f57b24a36d")
                     .log().all().
                when()
                     .get("/top-headlines");

        JsonPath jp = response.jsonPath() ;

        List <String> allAuthor =  jp.getList("articles.author") ;
        
        System.out.println("allAuthor = " + allAuthor);
        //System.out.println("allAuthor = " + allAuthor.size() );
        // allAuthor.forEach( eachAuthor -> System.out.println("eachAuthor = " + eachAuthor));
        // filter out the result by checking the source fields  --->> id filed null or not

        List <String> allAuthorFiltered1
                =  jp.getList("articles.findAll { it.source.id !=null }.title") ;


        for (String title : allAuthorFiltered1){
            System.out.println("title = " + title);
        }


        List <String> allAuthorFiltered2
                =  jp.getList("articles.findAll { it.source.id !=null }.author") ;
        for (String author : allAuthorFiltered2){
            System.out.println("author = " + author);
        }

       // System.out.println("allAuthorFiltered = " + allAuthorFiltered.size() );


    }
}
