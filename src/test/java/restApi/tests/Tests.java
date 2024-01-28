package restApi.tests;

import lombok.val;
import org.testng.Assert;
import org.testng.annotations.Test;
import restApi.EnumGender;
import restApi.EnumUserType;
import restApi.steps.PlayersSteps;

import static restApi.EnumGender.FEMALE;
import static restApi.EnumGender.MALE;
import static restApi.EnumUserType.ADMIN;
import static restApi.EnumUserType.SUPERVISOR;

public class Tests {

    PlayersSteps STEPS = new PlayersSteps();

    @Test()
    public void getAllPlayers() {
        val listPlayers = STEPS.getAllPlayers();
        System.out.println(listPlayers.size());
    }

    @Test()
    public void createPlayer() {
        String randomLogin = "login" + (int) (Math.random() * 100000);

        val newPlayer = STEPS.createPlayer(SUPERVISOR, 44, MALE, randomLogin, "pass55566", ADMIN, "screenNames");
        STEPS.verifyResponseAfterCreate(newPlayer, newPlayer.getId(), 44, MALE, randomLogin, "pass55566", ADMIN, "screenNames");
    }

    @Test()
    public void createPlayer2() {
        String randomLogin = "login" + (int) (Math.random() * 100000);

        val newPlayer = STEPS.createPlayer(SUPERVISOR, 33, FEMALE, randomLogin, "pass5555", ADMIN, "screenNames");
        STEPS.verifyResponseAfterCreate(newPlayer, newPlayer.getId(), randomLogin);
    }

}
