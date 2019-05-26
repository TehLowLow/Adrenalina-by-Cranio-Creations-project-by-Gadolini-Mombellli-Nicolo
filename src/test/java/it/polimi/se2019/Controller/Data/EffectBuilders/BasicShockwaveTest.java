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

public class BasicShockwaveTest {


    @Before
    public void preparePlayers() {

        ConfigurationTest.createTestConfiguration();

    }


    @Test
    public void applyEffectTest() {

        Player user = Server.connectedPlayers.get(0);
        Player target = Server.connectedPlayers.get(1);
        CopyOnWriteArrayList<Player> targets = new CopyOnWriteArrayList<>();
        targets.add(target);


        Weapon shockwave = new Weapon();
        shockwave.setBaseEffect(new BasicShockwave());
        shockwave.getBaseEffect().applyEffect(user, targets);

        assertEquals(1, target.getPlayerboard().getDamage().size());


    }

    @Test
    public void getTargets() {

        CopyOnWriteArrayList<String> answers = new CopyOnWriteArrayList<>();

        answers.add("player3");
        answers.add("si");
        answers.add("player2");
        answers.add("player4");
        answers.add("si");


        Player user = Server.connectedPlayers.get(0);
        Player player1 = Server.connectedPlayers.get(1);
        Player player2 = Server.connectedPlayers.get(2);
        Player player3 = Server.connectedPlayers.get(3);
        Player player4 = Server.connectedPlayers.get(4);

        player3.setPosition(Board.getMap().getRedRoom().getCells().get(2));
        player4.setPosition(Board.getMap().getBlueRoom().getCells().get(2));
        player2.setPosition(Board.getMap().getRedRoom().getCells().get(0));
        player1.setPosition(Board.getMap().getWhiteRoom().getCells().get(1));

        TestBot.initAnswers(answers);

        Weapon shockwave = new Weapon();
        shockwave.setBaseEffect(new BasicShockwave());

        CopyOnWriteArrayList<Player> targets = shockwave.getBaseEffect().getTargets(user);

        assertTrue(targets.size() == 2);
        assertTrue(targets.contains(player3));
        assertTrue(targets.contains(player4));


    }

    @Test
    public void getOnlyOneTargetTest() {

        CopyOnWriteArrayList<String> answers = new CopyOnWriteArrayList<>();

        answers.add("player3");
        answers.add("no");


        Player user = Server.connectedPlayers.get(0);
        Player player1 = Server.connectedPlayers.get(1);
        Player player2 = Server.connectedPlayers.get(2);
        Player player3 = Server.connectedPlayers.get(3);
        Player player4 = Server.connectedPlayers.get(4);

        player3.setPosition(Board.getMap().getRedRoom().getCells().get(2));
        player4.setPosition(Board.getMap().getBlueRoom().getCells().get(2));
        player2.setPosition(Board.getMap().getRedRoom().getCells().get(0));
        player1.setPosition(Board.getMap().getWhiteRoom().getCells().get(1));

        TestBot.initAnswers(answers);

        Weapon shockwave = new Weapon();
        shockwave.setBaseEffect(new BasicShockwave());

        CopyOnWriteArrayList<Player> targets = shockwave.getBaseEffect().getTargets(user);

        assertTrue(targets.size() == 1);
        assertTrue(targets.contains(player3));


    }

    @Test
    public void hasTargets() {


        Server.connectedPlayers.get(1).setPosition(Board.getMap().getWhiteRoom().getCells().get(1));
        Weapon shockwave = new Weapon();
        shockwave.setBaseEffect(new BasicShockwave());
        boolean result = shockwave.getBaseEffect().hasTargets(Server.connectedPlayers.get(0));
        assertFalse(result);

        Server.connectedPlayers.get(1).setPosition(Board.getMap().getBlueRoom().getCells().get(2));
        result = shockwave.getBaseEffect().hasTargets(Server.connectedPlayers.get(0));
        assertTrue(result);

    }
}