package tests.UploadFile;

import dto.ImageRequest;
import dto.LoginRequest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static tests.BaseTest.*;

public class UploadImage {

    @Test
    public void successUploadImage(){
        LoginRequest loginRequest = new LoginRequest("johnsmith@gmail.com", "qwerty0707");
        Response response = postRequest("api/auth/login", 200, loginRequest);
        String loginResponseAccessToken = response.body().jsonPath().getString("accessToken");



        //File imageFile = new File("\"C:\\Users\\Acer\\Desktop\\q1.jpg\"");

        uploadFileMultipartContentType("api/images", 201, loginResponseAccessToken);

//        Response response1 = given()
//                .contentType(ContentType.MULTIPART)
//                .multiPart("file", imageFile)
//                .when()
//                .post("api/images")
//                .then()
//                .extract().response();

        // Print the response
        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.asString());

//        Response response = given()
//                .header("Authorization", "Bearer " + accessToken)
//                .multiPart("file", imageFile)
//                .when()
//                .post("/api/images")
//                .then()
//                .statusCode(200)
//                .extract().response();

        ImageRequest imageRequest = new ImageRequest("\"C:\\Users\\Acer\\Desktop\\q1.jpg\"");

        Response response2 = postRequestWithAccessToken("api/images", 201, imageRequest, loginResponseAccessToken);
    }
}
