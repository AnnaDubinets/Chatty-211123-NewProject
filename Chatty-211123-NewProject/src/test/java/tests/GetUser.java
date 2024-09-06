package tests;

import dto.UserData;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static tests.BaseTest.getRequest;

public class GetUser {

    @Test
    public void validUserDataTest() {


        Response response = getRequest("api/me", 200);

        UserData user = response.body().jsonPath().getObject("", UserData.class);

        // LoginResponse accessToken = response.body().jsonPath().getObject("", LoginResponse.class);


    }
}
