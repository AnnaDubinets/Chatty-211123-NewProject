package tests.userTests;

import com.github.javafaker.Faker;
import dto.UserCreateRequest;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
}
