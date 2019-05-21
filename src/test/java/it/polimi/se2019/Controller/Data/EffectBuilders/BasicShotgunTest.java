package it.polimi.se2019.Controller.Data.EffectBuilders;

import it.polimi.se2019.Model.Board;
import it.polimi.se2019.Model.Cell;
import it.polimi.se2019.Model.Player;
import it.polimi.se2019.Model.Weapon;
import it.polimi.se2019.Network.Server;
import it.polimi.se2019.Network.TestBot;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.Assert.*;

public class BasicShotgunTest {


    @Before
    public void preparePlayers(){

        ConfigurationTest.createTestConfiguration();

    }

    @Test
    public void applyEffectDamageOnlyTest() {

        CopyOnWriteArrayList <String> answers = new CopyOnWriteArrayList<>();
        answers.add("no");
        TestBot.initAnswers(answers);

        Player user = Server.connectedPlayers.get(0);
        Player target = Server.connectedPlayers.get(1);
        CopyOnWriteArrayList<Player> targets = new CopyOnWriteArrayList<>();
        targets.add(target);

        Weapon shotgun = new Weapon();
        shotgun.setBaseEffect(new BasicShotgun());
        shotgun.getBaseEffect().applyEffect(user, targets);


        assertEquals(3, target.getPlayerboard().getDamage().size());


    }

    @Test
    public void applyEffectWithMoveTest(){


        CopyOnWriteArrayList <String> answers = new CopyOnWriteArrayList<>();
        answers.add("si");
        answers.add("sinistra");
        TestBot.initAnswers(answers);

        Player user = Server.connectedPlayers.get(0);
        Player target = Server.connectedPlayers.get(1);
        Cell expectedPosition = target.getPosition().getLeftConnection().getConnectedCell();
        CopyOnWriteArrayList<Player> targets = new CopyOnWriteArrayList<>();
        targets.add(target);


        Weapon shotgun = new Weapon();
        shotgun.setBaseEffect(new BasicShotgun());

        shotgun.getBaseEffect().applyEffect(user, targets);


        assertEquals(3, target.getPlayerboard().getDamage().size());
        assertEquals(expectedPosition, target.getPosition());


    }

    @Test
    public void getTargetsTest(){


        CopyOnWriteArrayList <String> answers = new CopyOnWriteArrayList<>();
        answers.add("player2");
        TestBot.initAnswers(answers);

        Player user = Server.connectedPlayers.get(0);
        Player player2 = Server.connectedPlayers.get(2);

        Weapon shotgun = new Weapon();
        shotgun.setBaseEffect(new BasicShotgun());


        CopyOnWriteArrayList <Player> targets = shotgun.getBaseEffect().getTargets(user);

        assertEquals(player2, targets.get(0));


    }




    @Test
    public void hasTargets() {

        Weapon shot = new Weapon();
        shot.setBaseEffect(new BasicShotgun());

        boolean result = shot.getBaseEffect().hasTargets(Server.connectedPlayers.get(0));
        assertTrue(result);
    }
}