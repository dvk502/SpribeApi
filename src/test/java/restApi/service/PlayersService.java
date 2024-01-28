package restApi.service;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import restApi.EnumGender;
import restApi.EnumUserType;
import restApi.endpoint.Endpoints;

import java.util.Map;

public class PlayersService {

    private final String BASE_URL = "http://3.68.165.45";

    private RequestSpecification getDefaultRequestSpecification() {
        // Creating request specification using given()
        RequestSpecification requestSpecification = RestAssured.given();
        // Setting Base URI
        requestSpecification.baseUri(BASE_URL);
        // Specify to log all data from response in console
//        requestSpecification.filters(new ResponseLoggingFilter());
//        requestSpecification.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

        return requestSpecification;
    }

    public Response getAllPlayers() {

        return getDefaultRequestSpecification()
                .when()
                .get(Endpoints.GET_ALL_PLAYERS)
                .then()
                .statusCode(200)
                .extract().response();
    }

    public Response createPlayer(EnumUserType userType, int age, EnumGender gender, String login, String password, EnumUserType role, String screenName) {

        return getDefaultRequestSpecification()
                .when()
                .queryParam("age", age)
                .queryParam("gender", gender.getGender())
                .queryParam("login", login)
                .queryParam("password", password)
                .queryParam("role", role.getName())
                .queryParam("screenName", screenName)
                .get(Endpoints.CREATE + userType.getName())
                .then()
                .statusCode(200)
                .extract().response();
    }

    public Response deletePlayer(int playerId, EnumUserType editor) {

        return getDefaultRequestSpecification()
                .when()
                .body(Map.of("playerId", playerId))
                .contentType("application/json")
                .delete(Endpoints.DELETE + editor.getName())
                .then()
                .statusCode(200)
                .extract().response();
    }

    public Response getByPlayerId(int playerId) {

        return getDefaultRequestSpecification()
                .when()
                .body(Map.of("playerId", playerId))
                .contentType("application/json")
                .post(Endpoints.GET_PLAYER_BY_ID)
                .then()
                .statusCode(200)
                .extract().response();
    }

    public Response verifyEmptyPlayerId(int playerId) {

        return getDefaultRequestSpecification()
                .when()
                .body(Map.of("playerId", playerId))
                .contentType("application/json")
                .post(Endpoints.GET_PLAYER_BY_ID)
                .then()
                .statusCode(404)
                .extract().response();
    }

}