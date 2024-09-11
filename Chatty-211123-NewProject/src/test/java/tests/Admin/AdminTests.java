package tests.Admin;


import dto.LoginRequest;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static tests.BaseTest.*;

public class AdminTests {
    @Test
    public void getAllUsersTest() {

        LoginRequest loginRequest = new LoginRequest("donna666@gmail.com","idonna666");
        Response response = postRequest("api/auth/login", 200, loginRequest);
        String accessToken = response.body().jsonPath().getString("accessToken");
        Response response1 = getRequestWithAccessToken("api/users?email=donna666@gmail.com&skip=0&limit=10", 200, accessToken);

    }
}