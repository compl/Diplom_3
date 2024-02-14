package ru.practicum.yandex.api;

import io.restassured.http.ContentType;
import java.net.HttpURLConnection;

import static io.restassured.RestAssured.given;

public class UserClient {
    public static final String BASE_URI = "https://stellarburgers.nomoreparties.site";
    private static final String BASE_PATH = "/api/auth";
    private static final String USER_PATH = "/user";
    private static final String REGISTER_PATH = "/register";

    public void delete(String accessToken) {
        given()
                .baseUri(BASE_URI)
                .basePath(BASE_PATH)
                .header("Authorization", accessToken)
                .delete(USER_PATH)
                .then().log().all()
                .statusCode(HttpURLConnection.HTTP_ACCEPTED);
    }

    public void createNewUser(User user) {
        given()
                .baseUri(BASE_URI)
                .basePath(BASE_PATH)
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post(REGISTER_PATH)
                .then().log().all()
                .statusCode(HttpURLConnection.HTTP_OK);
    }
}
