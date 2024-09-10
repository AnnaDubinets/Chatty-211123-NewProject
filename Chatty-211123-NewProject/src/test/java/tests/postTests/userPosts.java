package tests.postTests;

import dto.LoginRequest;
import dto.UpdatePost;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static tests.BaseTest.*;

public class userPosts {

    @Test
    public void getPostByUserTest(){
        LoginRequest loginRequest = new LoginRequest("johnsmith@gmail.com", "qwerty0707");
        Response response = postRequest("api/auth/login", 200, loginRequest);
        String accessToken = response.body().jsonPath().getString("accessToken");
        // System.out.println(accessToken);
        assertFalse(accessToken.isEmpty());

        Response response1 = getRequestWithAccessToken("api/me", 200, accessToken);
        String id = response1.body().jsonPath().getString("id");

        Response response2 = getRequestWithAccessToken("api/users/" + id + "/posts?skip=0&limit=10", 200, accessToken);

    }

    @Test
    public void getPostByPostIdTest(){
        LoginRequest loginRequest = new LoginRequest("johnsmith@gmail.com", "qwerty0707");
        Response response = postRequest("api/auth/login", 200, loginRequest);
        String accessToken = response.body().jsonPath().getString("accessToken");
        Response response1 = getRequestWithAccessToken("api/me", 200, accessToken);
        String id = response1.body().jsonPath().getString("id");

        Response response2 = getRequestWithAccessToken("api/users/" + id + "/posts?skip=0&limit=10", 200, accessToken);
        List<String> postsId = response2.body().jsonPath().getList("id", String.class);
        System.out.println(postsId);

        String firstPostId = postsId.get(0); //we got the first posts id in list
        Response response3 = getRequestWithAccessToken("api/posts/" + firstPostId, 200, accessToken);

    }

    @Test
    public void updatePostByPostId(){
        LoginRequest loginRequest = new LoginRequest("johnsmith@gmail.com", "qwerty0707");
        Response response = postRequest("api/auth/login", 200, loginRequest);
        String accessToken = response.body().jsonPath().getString("accessToken");
        Response response1 = getRequestWithAccessToken("api/me", 200, accessToken);
        String id = response1.body().jsonPath().getString("id");

        Response response2 = getRequestWithAccessToken("api/users/" + id + "/posts?skip=0&limit=10", 200, accessToken);
        List<String> postsId = response2.body().jsonPath().getList("id", String.class);
        System.out.println(postsId);

        String firstPostId = postsId.get(0); //we got the first posts id in list

        UpdatePost updatePost = new UpdatePost();
        Response response3 = putRequestWithAccessToken("api/posts/" + firstPostId, 200,updatePost, accessToken);
        updatePost.setTitle("New title");
        updatePost.setDescription("Nice");
        updatePost.setBody("New text");

        assertEquals("New title", response3.getBody().jsonPath().getString("title"));
        assertEquals("Nice", response3.getBody().jsonPath().getString("description"));
        assertEquals("New text", response3.getBody().jsonPath().getString("body"));



    }

}
