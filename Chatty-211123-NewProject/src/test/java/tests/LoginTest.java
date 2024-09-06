package tests;

import dto.LoginRequest;
import dto.LoginResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static tests.BaseTest.postRequest;

public class LoginTest {

    @Test
    public void successLogin(){


        LoginRequest loginRequest = new LoginRequest("johnsmith@gmail.com", "qwerty070709");

        Response response = postRequest("api/auth/login", 200, loginRequest);

        LoginResponse accessToken = response.body().jsonPath().getObject("",LoginResponse.class);

        assertFalse(accessToken.getAccessToken().isEmpty());


        LoginResponse refreshToken = response.body().jsonPath().getObject("",LoginResponse.class);

        assertFalse(refreshToken.getRefreshToken().isEmpty());

        LoginResponse expiration = response.body().jsonPath().getObject("",LoginResponse.class);

        assertFalse(expiration.getRefreshToken().isEmpty());

    }
}

