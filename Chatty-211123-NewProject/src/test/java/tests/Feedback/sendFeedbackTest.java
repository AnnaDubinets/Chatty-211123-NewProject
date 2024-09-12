package tests.Feedback;

import dto.FeedbackRequest;
import dto.LoginRequest;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static tests.BaseTest.*;

public class sendFeedbackTest {
    @Test
    public void contactUsTest() {
        LoginRequest loginRequest = new LoginRequest("johnsmith@gmail.com", "qwerty0707");
        Response response = postRequest("api/auth/login", 200, loginRequest);
        String accessToken = response.body().jsonPath().getString("accessToken");
        FeedbackRequest feedbackRequest = new FeedbackRequest("Harvey", "johnsmith@gmail.com", "great site");
        Response response1 = postRequestWithAccessToken("api/feedback", 200, feedbackRequest, accessToken);
    }
}
