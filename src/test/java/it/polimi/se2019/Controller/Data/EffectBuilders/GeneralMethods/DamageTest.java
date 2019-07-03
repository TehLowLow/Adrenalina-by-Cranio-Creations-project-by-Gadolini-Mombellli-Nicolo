package it.polimi.se2019.Controller.Data.EffectBuilders.GeneralMethods;

import it.polimi.se2019.Controller.Data.EffectBuilders.ConfigurationTest;
import it.polimi.se2019.Controller.Data.EffectBuilders.TagBackGrenadeEffect;
import it.polimi.se2019.Controller.Data.EffectBuilders.TargetingScopeEffect;
import it.polimi.se2019.Controller.Data.PowerUpBuilder.BlueTagbackGrenadeBuilder;
import it.polimi.se2019.Controller.Data.PowerUpBuilder.BlueTargetingScopeBuilder;
import it.polimi.se2019.Model.Player;
import it.polimi.se2019.Model.Powerup;
import it.polimi.se2019.Model.Rybamount;
import it.polimi.se2019.Model.Token;
import it.polimi.se2019.Network.Server;
import it.polimi.se2019.Network.TestBot;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.Assert.assertEquals;

public class DamageTest {

    @Before
    public void prepareEnvironment() {

        ConfigurationTest.createTestConfiguration();

    }



    @Test
    public void giveDamageWithMarker() {

        Player user = Server.connectedPlayers.get(0);
        Player target = Server.connectedPlayers.get(1);
        Token token = new Token();
        token.setChampionName("champion0");
        target.getPlayerboard().getMarker().add(token);
        Damage.giveDamage(1, user, target);

        assertEquals(2, target.getPlayerboard().getDamage().size());

    }

    @Test
    public void giveDamageWithTargetingScope(){

        CopyOnWriteArrayList<String> answers = new CopyOnWriteArrayList<>();
        answers.add("aaa");
        answers.add("si");
        answers.add("b");
        answers.add("Mirino blu");
        answers.add("rosso");
        answers.add("bb");
        answers.add("player1");
        TestBot.initAnswers(answers);

        Player user = Server.connectedPlayers.get(0);
        Player target = Server.connectedPlayers.get(1);

        Rybamount rybamount = new Rybamount();
        rybamount.setYellowCubes(0);
        rybamount.setBlueCubes(0);
        rybamount.setRedCubes(1);

        user.getPlayerboard().setAmmoCubes(rybamount);

        BlueTargetingScopeBuilder targetingScopeBuilder = new BlueTargetingScopeBuilder();
        Powerup targetingScope = targetingScopeBuilder.build();
        targetingScope.setEffect(new TargetingScopeEffect());

        user.getPlayerboard().getPowerups().add(targetingScope);

        Damage.giveDamage(1, user, target);

        assertEquals(2, target.getPlayerboard().getDamage().size());

    }


    @Test
    public void giveDamageWithTagbackGrenade(){


        CopyOnWriteArrayList<String> answers = new CopyOnWriteArrayList<>();
        answers.add("aaa");
        answers.add("si");
        answers.add("b");
        answers.add("granata venom blu");
        answers.add("rosso");
        answers.add("bb");
        answers.add("player0");
        TestBot.initAnswers(answers);

        Player user = Server.connectedPlayers.get(0);
        Player target = Server.connectedPlayers.get(1);

        Rybamount rybamount = new Rybamount();
        rybamount.setYellowCubes(0);
        rybamount.setBlueCubes(0);
        rybamount.setRedCubes(1);

        target.getPlayerboard().setAmmoCubes(rybamount);

        BlueTagbackGrenadeBuilder blueTagbackGrenadeBuilder = new BlueTagbackGrenadeBuilder();
        Powerup tagBack = blueTagbackGrenadeBuilder.build();
        tagBack.setEffect(new TagBackGrenadeEffect());

        target.getPlayerboard().getPowerups().add(tagBack);

        Damage.giveDamage(1, user, target);

        assertEquals(1, user.getPlayerboard().getMarker().size());
        assertEquals("champion1", user.getPlayerboard().getMarker().get(0).getChampionName());


    }

    @Test
    public void giveMarker() {
    }
}