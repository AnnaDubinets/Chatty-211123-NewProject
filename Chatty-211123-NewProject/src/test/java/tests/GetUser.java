package tests;

import dto.LoginRequest;
import dto.LoginResponse;
import dto.UserData;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static tests.BaseTest.getRequest;
import static tests.BaseTest.postRequest;

public class GetUser {

    @Test
    public void validUserDataTest() {

        LoginRequest loginRequest = new LoginRequest("johnsmith@gmail.com", "qwerty070709");

        Response response = postRequest("api/auth/login", 200, loginRequest);

        //assertFalse(accessToken.getAccessToken().isEmpty());
        //String refreshToken = response.body().jsonPath().getString("refreshToken");

        LoginResponse accessToken = response.body().jsonPath().getObject("",LoginResponse.class);

        assertFalse(accessToken.getAccessToken().isEmpty());

        UserData user = given().baseUri("http://chatty.telran-edu.de:8989/")
                .header("\n" +
                        "Authorization", "accessToken")
                .when().log().all()
                .get("/api/me")
                .then().log().all().statusCode(200).extract().body()
                .jsonPath().getObject("", UserData.class);



        //UserData userData = response.body().jsonPath().getObject("", UserData.class);

        //LoginResponse accessToken = response.body().jsonPath().getObject(" ", LoginResponse.class);

        //String loginResponseAccessToken = response.body().jsonPath().getObject("", LoginResponse.class); //забрать все
        //assertFalse(loginResponseAccessToken.isEmpty());


    }

}

