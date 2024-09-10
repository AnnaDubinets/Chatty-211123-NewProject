package tests;

import dto.LoginRequest;
import dto.UpdateUserRequest;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static tests.BaseTest.*;

public class UpdatePasswordTest {
    @Test
    public void updatePasswordTest() {
        String email = "johnsmith@gmail.com";
        String password = "qwerty0707";
        LoginRequest requestBody = new LoginRequest(email, password);
        Response response = postRequest("/api/auth/login", 200, requestBody);
        String loginResponseAccessToken = response.body().jsonPath().getString("accessToken");
        assertFalse(loginResponseAccessToken.isEmpty());

        UpdateUserRequest updateRequest = new UpdateUserRequest();
        requestBody.setPassword("qwerty07");

        Response response1 = getRequestWithAccessToken("api/me", 200, loginResponseAccessToken);
        String id = response1.body().jsonPath().getString("id");
        Response updateResponse = putRequestWithAccessToken("api/users/" + id, 200, updateRequest, loginResponseAccessToken);



    }
}
