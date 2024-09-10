package tests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class BaseTest {

    final static String BASE_URI = "http://chatty.telran-edu.de:8989/";

    final static String APP_ID_VALUE = "1fd247ee-cc2e-4df9-b0c5-7fb03b434df9";

    static RequestSpecification specification = new RequestSpecBuilder()
            .setBaseUri(BASE_URI)
            .addHeader("app-id", APP_ID_VALUE)
            .setContentType(ContentType.JSON)
            .build();

    public static Response getRequest(String endpoint, Integer expectedStatusCode){
        Response response = given()
                .spec(specification)
                .when()
                .log().all()
                .get(endpoint)
                .then()
                .log().all()
                .statusCode(expectedStatusCode)
                .extract().response();
        return response;

    }

    public static Response putRequest(String endpoint, Integer expectedStatusCode, Object body){
        Response response = given()
                .spec(specification)
                .body(body)
                .when()
                .log().all()
                .put(endpoint)
                .then()
                .log().all()
                .statusCode(expectedStatusCode)
                .extract().response();
        return response;
    }

    public static Response deleteRequest(String endpoint, Integer expectedStatusCode, Object body){
        Response response = given()
                .spec(specification)
                .body(body)
                .when()
                .log().all()
                .delete(endpoint)
                .then()
                .log().all()
                .statusCode(expectedStatusCode)
                .extract().response();
        return response;
    }

    public static Response postRequest(String endpoint, Integer expectedStatusCode, Object body){
        Response response = given()
                .spec(specification)
                .body(body)
                .when()
                .log().all()
                .post(endpoint)
                .then()
                .log().all()
                .statusCode(expectedStatusCode)
                .extract().response();
        return response;
    }

    public static Response getRequestWithAccessToken(String endpoint, Integer expectedStatusCode, String accessToken){
        Response response = given()
                .spec(specification)
                .header("Authorization", "Bearer " + accessToken)
                .when()
                .log().all()
                .get(endpoint)
                .then()
                .log().all()
                .statusCode(expectedStatusCode)
                .extract().response();
        return response;

    }

    public static Response putRequestWithAccessToken(String endpoint, Integer expectedStatusCode, Object body, String accessToken){
        Response response = given()
                .spec(specification)
                .header("Authorization", "Bearer " + accessToken)
                .body(body)
                .when()
                .log().all()
                .put(endpoint)
                .then()
                .log().all()
                .statusCode(expectedStatusCode)
                .extract().response();
        return response;
    }

    public static Response deleteRequestWithAccessToken(String endpoint, Integer expectedStatusCode, String accessToken){
        Response response = given()
                .spec(specification)
                .header("Authorization", "Bearer " + accessToken)
                .when()
                .log().all()
                .delete(endpoint)
                .then()
                .log().all()
                .statusCode(expectedStatusCode)
                .extract().response();
        return response;
    }

}
