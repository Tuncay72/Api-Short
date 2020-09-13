package zudemy;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

import static org.testng.Assert.assertEquals;

public class RestJira_CreateBug {

    @Test
    public void testRestJiraCrateBug(){

        RestAssured.baseURI = "http://base_url:8080";



        String payload1 = "{\n" +
                "     \"username\": \"rahulonlinetutor\"," +
                "     \"password\": \"Jira12345\"\n" +
                "}";

        SessionFilter session = new SessionFilter();
        //Login Scenario
        String response1 =
                given()
                       .header("Content-Type", "application/json")
                       .body(payload1)
                       .log().all()
                       .filter(session).
                when()
                       .post("/rest/auth/1/session").
                then()
                       .extract().response().asString()
                ;


        String payload2 = "{\n" +
                "    \"body\": \"First comment\",\n" +
                "    \"visibility\": {\n" +
                "        \"type\": \"role\",\n" +
                "        \"value\": \"Administrators\"\n" +
                "    }\n" +
                "}" ;

        String expectedMessage = "First comment";

        //Add Comment
        String addCommentResponse =
        given()
                .pathParam("issueIdOrKey","id(1234)")
                .header("Content-Type", "application/json")
                .log().all()
                .body(payload2)
                .filter(session).
        when()
                .post("/rest/api/2/issue/{issueIdOrKey}/comment").
        then()
                .log().all()
                .assertThat().statusCode(201).extract().response().toString()
        ;
        JsonPath jp = new JsonPath(addCommentResponse );
        String commentID = jp.getString("id");



        //Add Attachment
        given()
                .header("X-Atlassian-Token"," no-check")
                .filter(session)
                .pathParam("issueIdOrKey","id(1234)")
                .multiPart("file",new File("jira.txt"))
                .header("Content-Type","multipart/form-data").
        when()
                .post("/rest/api/2/issue/{issueIdOrKey}/attachments").
        then()
                .log().all()
                .statusCode(200)
        ;


        //Get issue
        String issueDetails =
         given()
                .filter(session)
                .pathParam("issueIdOrKey","id(1234)")
                .queryParam("fields","comment")
                .log().all().
        when()
                .get(" /rest/api/2/issue/{issueIdOrKey}").
        then()
                .log().all()
                .extract().response().asString()
        ;
        System.out.println("issueDetails = " + issueDetails);

       

        JsonPath jp2 = new JsonPath(issueDetails);
        int commentsCount = jp.getInt("fields.comment.comments.size()");

        for (int i =0; i<commentsCount;i++) {
            String commentIdIssue = jp2.getString("fields.comment.comments["+i+"].id");
            if (commentIdIssue.equalsIgnoreCase(commentID)) {
                String message = jp2.getString("fields.comment.comments["+i+"].body");
                
                System.out.println(message);

                assertEquals(message,expectedMessage);
            }
        }
    }
}
