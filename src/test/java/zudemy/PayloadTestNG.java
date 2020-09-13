package zudemy;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PayloadTestNG {

    @Test(dataProvider = "BooksData")
    public void testPostData2(String isbn,String aisle){
        RestAssured.baseURI = "http://216.10.245.166";

        Response response =
                given()
                        .log().all()
                        .accept(ContentType.JSON)
                        .body(Library.Addbook(isbn,aisle)).
                        when()
                        .post("/Library/Addbook.php").
                        then().statusCode(200).extract().response()
                ;

        JsonPath jp = response.jsonPath();
        System.out.println("ID = " + jp.get("ID"));

    }


    @DataProvider(name="BooksData")
    public Object[][] testGetData() {


        return new Object[][]  {{"dfghd","9364"}, {"cweate","41253"}, {"omletto","5433"}} ;


    }

}
