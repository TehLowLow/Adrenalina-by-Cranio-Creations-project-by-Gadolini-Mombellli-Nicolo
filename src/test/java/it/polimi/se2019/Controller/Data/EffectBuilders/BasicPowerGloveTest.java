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

public class BasicPowerGloveTest {

    @Before
    public void preparePlayers(){

        ConfigurationTest.createTestConfiguration();

    }

    @Test
    public void applyEffectTest() {

        Player user = Server.connectedPlayers.get(0);
        Player target = Server.connectedPlayers.get(1);
        CopyOnWriteArrayList<Player> targets = new CopyOnWriteArrayList<>();
        targets.add(target);


        Weapon powerglove = new Weapon();
        powerglove.setBaseEffect(new BasicPowerGlove());

        powerglove.getBaseEffect().applyEffect(user, targets);

        assertEquals(1, target.getPlayerboard().getDamage().size());
        assertEquals(2, target.getPlayerboard().getMarker().size());

    }


    @Test
    public void getTargets() {

        CopyOnWriteArrayList<String> answers = new CopyOnWriteArrayList<>();

        answers.add("player2");
        answers.add("player1");
        answers.add("player3");

        Player user = Server.connectedPlayers.get(0);
        Player player1 = Server.connectedPlayers.get(1);
        Player player2 = Server.connectedPlayers.get(2);
        Player player3 = Server.connectedPlayers.get(3);
        Player player4 = Server.connectedPlayers.get(4);

        player3.setPosition(Board.getMap().getRedRoom().getCells().get(2));
        player4.setPosition(Board.getMap().getBlueRoom().getCells().get(2));
        player2.setPosition(Board.getMap().getRedRoom().getCells().get(0));
        player1.setPosition(Board.getMap().getYellowRoom().getCells().get(0));

        TestBot.initAnswers(answers);

        Weapon powerglove = new Weapon();
        powerglove.setBaseEffect(new BasicPowerGlove());

        CopyOnWriteArrayList <Player> targets = powerglove.getBaseEffect().getTargets(user);

        assertTrue(targets.size()==1 && targets.contains(player3));

    }

    @Test
    public void hasTargets() {

        Weapon powerglove = new Weapon();
        powerglove.setBaseEffect(new BasicPowerGlove());
        boolean result = powerglove.getBaseEffect().hasTargets(Server.connectedPlayers.get(0));
        assertFalse(result);

        Server.connectedPlayers.get(1).setPosition(Board.getMap().getBlueRoom().getCells().get(2));
        result = powerglove.getBaseEffect().hasTargets(Server.connectedPlayers.get(0));
        assertTrue(result);

    }
}