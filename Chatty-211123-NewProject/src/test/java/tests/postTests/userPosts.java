package tests.postTests;

import dto.LoginRequest;
import dto.PostData;
import dto.UpdatePost;
import dto.UpdateUserRequest;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.stream.DoubleStream.builder;
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

        UpdatePost updatePost = new UpdatePost().builder()
                .title("Harvey")
                .description("Spector")
                .body("new body")
                .build();

        Response response3 = putRequestWithAccessToken("api/posts/" + firstPostId, 200,updatePost, accessToken);
//        updatePost.setTitle("New title");
//        updatePost.setDescription("Nice");
//        updatePost.setBody("New text");

        assertEquals("Harvey", response3.getBody().jsonPath().getString("title"));
        assertEquals("Spector", response3.getBody().jsonPath().getString("description"));
        assertEquals("new body", response3.getBody().jsonPath().getString("body"));


    }

    @Test
    public void allUsersPostsTest(){
        LoginRequest loginRequest = new LoginRequest("johnsmith@gmail.com", "qwerty0707");
        Response response = postRequest("api/auth/login", 200, loginRequest);
        String accessToken = response.body().jsonPath().getString("accessToken");
        Response response1 = getRequestWithAccessToken("api/posts?skip=0&limit=10", 200, accessToken);

    }

    @Test
    public void draftPostsCheckTest(){
        LoginRequest loginRequest = new LoginRequest("johnsmith@gmail.com", "qwerty0707");
        Response response = postRequest("api/auth/login", 200, loginRequest);
        String accessToken = response.body().jsonPath().getString("accessToken");
        Response response1 = getRequestWithAccessToken("api/posts/drafts", 200, accessToken);
    }

    @Test
    public void createPostTest(){
        LoginRequest loginRequest = new LoginRequest("johnsmith@gmail.com", "qwerty0707");
        Response response = postRequest("api/auth/login", 200, loginRequest);
        String accessToken = response.body().jsonPath().getString("accessToken");
        PostData postData = new PostData("New post", "My job", "NY");
        Response response1 = postRequestWithAccessToken("api/posts", 200, postData, accessToken);//STATUS CODE!!!
        String postId = response1.body().jsonPath().getString("id");
        System.out.println(postId);

    }

    @Test
    public void deletePostTest(){
        LoginRequest loginRequest = new LoginRequest("johnsmith@gmail.com", "qwerty0707");
        Response response = postRequest("api/auth/login", 200, loginRequest);
        String accessToken = response.body().jsonPath().getString("accessToken");
        String postId = "4d4124f1-af8d-4f3e-beb3-3b74f6fcc959";//take the postId from the previous test(create test)
        Response response1 = deleteRequestWithAccessToken("api/posts/" + postId, 200,accessToken );//STATUS CODE!!!
    }

}
