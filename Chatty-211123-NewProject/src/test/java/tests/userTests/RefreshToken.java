package tests.userTests;

import dto.LoginRequest;
import dto.LoginResponse;
import dto.TokenRequest;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static tests.BaseTest.*;

public class RefreshToken {
    @Test
    public void refreshTokenTest() {
        LoginRequest loginRequest = new LoginRequest("johnsmith@gmail.com", "qwerty0707");
        Response response = postRequest("api/auth/login", 200, loginRequest);
        String loginResponseRefreshToken = response.body().jsonPath().getString("refreshToken");
        //get refresh token from login and send it in body
        TokenRequest tokenRequest = new TokenRequest(loginResponseRefreshToken);
        Response response1 = postRequest("api/auth/refresh", 201, tokenRequest);
    }
}