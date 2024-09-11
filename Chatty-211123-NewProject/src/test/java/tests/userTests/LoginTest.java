package tests.userTests;

import dto.LoginRequest;
import dto.LoginResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tests.BaseTest.postRequest;

public class LoginTest {

    @Test
    public void successLogin(){


        LoginRequest loginRequest = new LoginRequest("johnsmith@gmail.com", "qwerty0707");
        Response response = postRequest("api/auth/login", 200, loginRequest);
        String loginResponseAccessToken = response.body().jsonPath().getString("accessToken"); //забрать все
        assertFalse(loginResponseAccessToken.isEmpty());

//        LoginResponse accessToken = response.body().jsonPath().getObject("",LoginResponse.class);
//        assertFalse(accessToken.getAccessToken().isEmpty());

        LoginResponse refreshToken = response.body().jsonPath().getObject("",LoginResponse.class);
        assertFalse(refreshToken.getRefreshToken().isEmpty());


        LoginResponse expiration = response.body().jsonPath().getObject("",LoginResponse.class);
        assertFalse(expiration.getRefreshToken().isEmpty());

    }

    @Test
    public void withoutEmail() {
        String email = "";
        String password = "qwerty0707";
        // String

        LoginRequest requestBody = new LoginRequest(email, password);
        Response response = postRequest("/api/auth/login", 400, requestBody);

        //String loginResponseAccessToken = response.body().jsonPath().getObject("", LoginResponse.class); //забрать все
        //assertFalse(loginResponseAccessToken.isEmpty());

        List<String> errorMessages = response.body().jsonPath().getList("email", String.class);
        assertTrue(errorMessages.contains("Email cannot be empty"));
        assertTrue(errorMessages.contains("Invalid email format"));

    }

    @Test
    public void invalidPasswordWithOnlyNumbers() {
        String email = "johnsmith@gmail.com";
        String password = "12334";

        LoginRequest requestBody = new LoginRequest(email, password);
        Response response = postRequest("/api/auth/login", 400, requestBody);

        //String loginResponseAccessToken = response.body().jsonPath().getObject("", LoginResponse.class); //забрать все
        //assertFalse(loginResponseAccessToken.isEmpty());

        List<String> errorMessages = response.body().jsonPath().getList("password", String.class);
        assertTrue(errorMessages.contains("Password must contain letters and numbers"));

    }
}

