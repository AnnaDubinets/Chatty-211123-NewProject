package tests;

import dto.LoginRequest;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static tests.BaseTest.*;

public class DeleteUserTest {
    @Test
    public void deleteUserTest() {
        String email = "johnsmith@gmail.com";
        String password = "qwerty0707";
        LoginRequest requestBody = new LoginRequest(email, password);
        Response response = postRequest("/api/auth/login", 200, requestBody);
        String loginResponseAccessToken = response.body().jsonPath().getString("accessToken");
        assertFalse(loginResponseAccessToken.isEmpty());


        Response response1 = getRequestWithAccessToken("api/me", 200, "accessToken");
        String id = response1.body().jsonPath().getString("id");
        Response deleteResponse = deleteRequestWithAccessToken("api/users/" + id, 403, "accessToken" );

        String errorMessage = deleteResponse.body().jsonPath().getString("message");

        assertEquals("You don't have permission to delete users", errorMessage);

    }
}
