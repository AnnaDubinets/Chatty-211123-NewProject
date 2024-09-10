package tests;

import dto.LoginRequest;
import dto.UpdateUserRequest;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static tests.BaseTest.postRequest;
import static tests.BaseTest.putRequestWithAccessToken;

public class UpdatePasswordTest {
    @Test
    public void updatePasswordTest() {
        String email = "johnsmith@gmail.com";
        String password = "qwerty07";
        LoginRequest requestBody = new LoginRequest(email, password);
        Response response = postRequest("/api/auth/login", 200, requestBody);
        String loginResponseAccessToken = response.body().jsonPath().getString("accessToken");
        assertFalse(loginResponseAccessToken.isEmpty());

        UpdateUserRequest updateRequest = new UpdateUserRequest();
        requestBody.setPassword("qwerty0707");

        String updateUrl = "http://chatty.telran-edu.de:8989/api/users/1fd247ee-cc2e-4df9-b0c5-7fb03b434df9";
        Response updateResponse = putRequestWithAccessToken(updateUrl, 200, updateRequest, loginResponseAccessToken);



    }
}
