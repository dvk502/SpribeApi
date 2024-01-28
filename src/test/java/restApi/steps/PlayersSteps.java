package restApi.steps;

import groovyjarjarantlr4.v4.parse.GrammarTreeVisitor;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import restApi.EnumGender;
import restApi.EnumUserType;
import restApi.model.PlayerCreateResponseDto;
import restApi.model.PlayersItemDto;
import restApi.service.PlayersService;
import org.apache.log4j.Logger;

import java.util.List;

public class PlayersSteps {

    protected static final Logger log = Logger.getLogger(PlayersSteps.class);
    private final PlayersService service = new PlayersService();
//    protected static final Logger log = Logger.getLogger(GetAllPlayersSteps.class);

    public List<PlayersItemDto> getAllPlayers() {
        Response response = service.getAllPlayers();

        return response.then()
                .extract().body()
                .jsonPath().getList("players", PlayersItemDto.class);
    }

    @Step ("Create player")
    public PlayerCreateResponseDto createPlayer(EnumUserType userType, int age, EnumGender gender, String login, String password , EnumUserType role, String screenName) {
        Response response = service.createPlayer(userType, age, gender, login, password,role, screenName);

        log.info("Create player" + String.format("Age: %s gender: %s login:%s password:%s role:%s screen name:%s", age, gender, login, password, role.getName(), screenName)) ;

        return  response.then()
                .extract().body()
                .jsonPath().getObject("", PlayerCreateResponseDto.class);
    }

    @Step ("Verify response after create player")
    public  void verifyResponseAfterCreate (PlayerCreateResponseDto actualResponse, int id_player,int age, EnumGender gender, String login, String password ,EnumUserType role, String screenName){
        PlayerCreateResponseDto expectedResponse = new PlayerCreateResponseDto();
        expectedResponse.setId(id_player);
        expectedResponse.setLogin(login);
        expectedResponse.setPassword(password);
        expectedResponse.setScreenName(screenName);
        expectedResponse.setGender(gender.getGender());
        expectedResponse.setAge(age);
        expectedResponse.setRole(role.getName());
        log.info("Verify response after create player");
        Assertions.assertThat(actualResponse).isEqualTo(expectedResponse);
    }

    @Step ("Verify response after create player")
    public  void verifyResponseAfterCreate (PlayerCreateResponseDto actualResponse, int id_player,String login){
        PlayerCreateResponseDto expectedResponse = new PlayerCreateResponseDto();
        expectedResponse.setId(id_player);
        expectedResponse.setLogin(login);

        log.info("Verify response after create player");
        Assertions.assertThat(actualResponse).isEqualTo(expectedResponse);
    }

}