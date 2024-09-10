package tests;

import com.github.javafaker.Faker;
import dto.LoginRequest;
import dto.UpdateUserRequest;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static tests.BaseTest.*;

public class UpdateUserTest {
    Faker faker = new Faker();

    @Test
    public void updateUserInfo() {
        //4. Change Email

        //1. Create new user

//        String userEmail = faker.internet().emailAddress();
//        String userFirstName = faker.name().firstName();
//        String userLastName = faker.name().lastName();
//        String gender = "male";
//        String tittle = "mr";
//        String phone = "+49123456";


        String email = "johnsmith@gmail.com";
        String password = "qwerty0707";
        LoginRequest requestBody = new LoginRequest(email, password);
        Response response = postRequest("/api/auth/login", 200, requestBody);
        String loginResponseAccessToken = response.body().jsonPath().getString("accessToken");
        assertFalse(loginResponseAccessToken.isEmpty());

        UpdateUserRequest updateRequest = new UpdateUserRequest();
        updateRequest.setName("Harvey");
        updateRequest.setSurname("Specter");
        updateRequest.setPhone("+1234567890");
       // updateRequest.setBirthdate("1982-05-01T00:00:00.000Z");
        updateRequest.setGender("MALE");

        Response response1 = getRequestWithAccessToken("api/me", 200, loginResponseAccessToken);
        String id = response1.body().jsonPath().getString("id");
        Response updateResponse = putRequestWithAccessToken("api/users/" + id, 200, updateRequest, loginResponseAccessToken);

        assertEquals("Harvey", updateResponse.body().jsonPath().getString("name"));
        assertEquals("Specter", updateResponse.body().jsonPath().getString("surname"));
        //assertEquals("1992-01-01T00:00:00.000Z", updateResponse.body().jsonPath().getString("birthdate"));
        assertEquals("+1234567890", updateResponse.body().jsonPath().getString("phone"));
        assertEquals("MALE", updateResponse.body().jsonPath().getString("gender"));
    }
}



