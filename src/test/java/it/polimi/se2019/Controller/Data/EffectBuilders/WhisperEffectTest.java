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

public class WhisperEffectTest {

    @Before
    public void preparePlayers(){

        ConfigurationTest.createTestConfiguration();

    }

    @Test
    public void applyEffectTest(){

        CopyOnWriteArrayList <Player> targets = new CopyOnWriteArrayList<>();
        Player user = Server.connectedPlayers.get(0);
        Player player3 = Server.connectedPlayers.get(3);


        targets.add(player3);


        Weapon weapon = new Weapon();
        weapon.setBaseEffect(new WhisperEffect());

        weapon.getBaseEffect().applyEffect(user, targets);

        assertTrue(player3.getPlayerboard().getDamage().size()==3);
        assertTrue(player3.getPlayerboard().getMarker().size()==1);

    }

    @Test
    public void getTargetsTest(){

        CopyOnWriteArrayList<String> answers = new CopyOnWriteArrayList<>();

        answers.add("player2");
        answers.add("player4");
        answers.add("player1");
        answers.add("player3");

        Player user = Server.connectedPlayers.get(0);
        Player player1 = Server.connectedPlayers.get(1);
        Player player2 = Server.connectedPlayers.get(2);
        Player player3 = Server.connectedPlayers.get(3);
        Player player4 = Server.connectedPlayers.get(4);

        player1.setPosition(Board.getMap().getBlueRoom().getCells().get(2));
        player2.setPosition(Board.getMap().getRedRoom().getCells().get(2));
        player3.setPosition(Board.getMap().getRedRoom().getCells().get(0));

        TestBot.initAnswers(answers);

        Weapon weapon = new Weapon();
        weapon.setBaseEffect(new WhisperEffect());

        CopyOnWriteArrayList <Player> targets = weapon.getBaseEffect().getTargets(user);

        assertTrue(targets.size()==1);
        assertTrue(targets.contains(player3));

    }

    @Test
    public void hasTargets() {


        Weapon weapon = new Weapon();
        weapon.setBaseEffect(new WhisperEffect());
        boolean result = weapon.getBaseEffect().hasTargets(Server.connectedPlayers.get(0));

        assertFalse(result);

        for(int i = 1; i<5; i++){

            Server.connectedPlayers.get(i).setPosition(Board.getMap().getBlueRoom().getCells().get(2));
        }

        result = weapon.getBaseEffect().hasTargets(Server.connectedPlayers.get(0));

        assertFalse(result);

        for(int i = 1; i<5; i++){

            Server.connectedPlayers.get(i).setPosition(Board.getMap().getBlueRoom().getCells().get(1));
        }

        result = weapon.getBaseEffect().hasTargets(Server.connectedPlayers.get(0));
        assertTrue(result);


        for(int i = 1; i<5; i++){

            Server.connectedPlayers.get(i).setPosition(Board.getMap().getYellowRoom().getCells().get(1));
        }

        result = weapon.getBaseEffect().hasTargets(Server.connectedPlayers.get(0));

        assertFalse(result);



    }
}