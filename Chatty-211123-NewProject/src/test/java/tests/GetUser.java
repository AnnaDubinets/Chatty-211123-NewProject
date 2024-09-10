package tests;

import dto.LoginRequest;
import dto.LoginResponse;
import dto.UserData;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static tests.BaseTest.*;

public class GetUser {

    @Test
    public void validUserDataTest() {

        LoginRequest loginRequest = new LoginRequest("johnsmith@gmail.com", "qwerty070709");

        Response response = postRequest("api/auth/login", 200, loginRequest);

        //assertFalse(accessToken.getAccessToken().isEmpty());
        //String refreshToken = response.body().jsonPath().getString("refreshToken");

        String accessToken = response.body().jsonPath().getString("accessToken");
       // System.out.println(accessToken);
        assertFalse(accessToken.isEmpty());


        Response response1 = getRequestWithAccessToken("api/me", 200, "accessToken");
        String id = response1.body().jsonPath().getString("id");
       System.out.println(id);

    }

}

