package tests.userTests;

import com.github.javafaker.Faker;
import dto.UserCreateRequest;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static tests.BaseTest.postRequest;

public class userRegistration {
    Faker faker = new Faker();
    @Test
    public void userRegisterTest(){
        String userEmail = faker.internet().emailAddress();
        String userPassword = faker.internet().password();
        UserCreateRequest userCreate = new UserCreateRequest(userEmail, userPassword, userPassword, "admin");
        Response response = postRequest("api/auth/register", 201, userCreate);
        String accessToken = response.body().jsonPath().getString("accessToken");
        assertFalse(accessToken.isEmpty());
        String refreshToken = response.body().jsonPath().getString("refreshToken");
        assertFalse(refreshToken.isEmpty());
        String expiration = response.body().jsonPath().getString("expiration");
        assertFalse(expiration.isEmpty());
    }

    @Test
    public void userRegisterInvalidEmailTest(){
        UserCreateRequest userCreate = new UserCreateRequest("donna", "donna999", "donna999", "admin");
        Response response = postRequest("api/auth/register", 400, userCreate);
        String errorMessage = response.body().jsonPath().getString("email");
        assertTrue(errorMessage.contains("Email is not valid."));

    }

    @Test
    public void userRegisterInvalidPasswordTest(){
        UserCreateRequest userCreate = new UserCreateRequest("donna@gmail.com", "donna99", "donna99", "admin");
        Response response = postRequest("api/auth/register", 400, userCreate);
        String errorMessage = response.body().jsonPath().getString("password");
        assertTrue(errorMessage.contains("Password must contain at least 8 characters"));

    }
    @Test
    public void userRegisterIncorrectConfirmPasswordTest(){
        UserCreateRequest userCreate = new UserCreateRequest("donna678@gmail.com", "donna678", "donna679", "admin");
        Response response = postRequest("api/auth/register", 400, userCreate);
        String errorMessage = response.body().jsonPath().getString("message ");
        assertTrue(errorMessage.contains("Password mismatch!"));

    }
}
