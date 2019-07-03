package it.polimi.se2019.Controller.Data.EffectBuilders;

import it.polimi.se2019.Model.Board;
import it.polimi.se2019.Model.Player;
import it.polimi.se2019.Model.Weapon;
import it.polimi.se2019.Network.Server;
import it.polimi.se2019.Network.TestBot;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.Assert.*;

public class BasicFlameThrowerTest {


    @Before
    public void preparePlayers() {

        ConfigurationTest.createTestConfiguration();

    }

    @Test
    public void getTargetsTest() {

        CopyOnWriteArrayList<String> answers = new CopyOnWriteArrayList<>();

        answers.add("sinistra");
        answers.add("player1");


        answers.add("basso");
        answers.add("player2");


        answers.add("alto");
        answers.add("player3");


        answers.add("destra");
        answers.add("player4");


        TestBot.initAnswers(answers);

        Player user = Server.connectedPlayers.get(0);
        Player target1 = Server.connectedPlayers.get(1);
        Player target2 = Server.connectedPlayers.get(2);
        Player target3 = Server.connectedPlayers.get(3);
        Player target4 = Server.connectedPlayers.get(4);

        user.setPosition(Board.getMap().getBlueRoom().getCells().get(0));
        target1.setPosition(Board.getMap().getBlueRoom().getCells().get(2));
        target2.setPosition(Board.getMap().getRedRoom().getCells().get(2));
        target3.setPosition(Board.getMap().getRedRoom().getCells().get(1));
        target4.setPosition(Board.getMap().getWhiteRoom().getCells().get(1));


        Weapon flamethrower = new Weapon();
        flamethrower.setBaseEffect(new BasicFlamethrower());

        CopyOnWriteArrayList<Player> targets = flamethrower.getBaseEffect().getTargets(user);

        assertTrue(targets.contains(target1));

        targets = flamethrower.getBaseEffect().getTargets(user);

        assertTrue(targets.contains(target2));

        user.setPosition(Board.getMap().getWhiteRoom().getCells().get(0));

        targets = flamethrower.getBaseEffect().getTargets(user);

        assertTrue(targets.contains(target3));

        targets = flamethrower.getBaseEffect().getTargets(user);

        assertTrue(targets.contains(target4));

    }

    @Test
    public void applyEffectTest() {

        Player user = Server.connectedPlayers.get(0);
        Player target = Server.connectedPlayers.get(1);
        CopyOnWriteArrayList<Player> targets = new CopyOnWriteArrayList<>();
        targets.add(target);


        Weapon flamethrower = new Weapon();
        flamethrower.setBaseEffect(new BasicFlamethrower());

        flamethrower.getBaseEffect().applyEffect(user, targets);

        assertEquals(1, target.getPlayerboard().getDamage().size());


    }

    @Test
    public void hasTargetsTest() {

        Weapon ft = new Weapon();
        ft.setBaseEffect(new BasicFlamethrower());
        Server.connectedPlayers.get(1).setPosition(Board.getMap().getBlueRoom().getCells().get(2));
        boolean result = ft.getBaseEffect().hasTargets(Server.connectedPlayers.get(0));
        assertTrue(result);
    }


}
