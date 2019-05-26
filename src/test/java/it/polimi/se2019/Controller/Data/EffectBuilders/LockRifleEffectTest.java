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

public class LockRifleEffectTest {

    @Before
    public void preparePlayers(){

        ConfigurationTest.createTestConfiguration();

    }


    @Test
    public void basicPlusOptional(){

        CopyOnWriteArrayList<String> answers = new CopyOnWriteArrayList<>();
        answers.add("player1");
        answers.add("player2");
        answers.add("si");
        answers.add("player2");
        answers.add("player3");

        TestBot.initAnswers(answers);

        Player user = Server.connectedPlayers.get(0);
        Player player1 = Server.connectedPlayers.get(1);
        Player player2 = Server.connectedPlayers.get(2);
        Player player3 = Server.connectedPlayers.get(3);

        CopyOnWriteArrayList <Player> targets = new CopyOnWriteArrayList<>();
        targets.add(player2);

        user.getPlayerboard().getAmmoCubes().setRedCubes(1);
        player1.setPosition(Board.getMap().getYellowRoom().getCells().get(1));
        player2.setPosition(Board.getMap().getBlueRoom().getCells().get(1));

        Weapon lockrifle= new Weapon();
        lockrifle.setBaseEffect(new LockRifleEffect());

        lockrifle.getBaseEffect().applyEffect(user, targets);

        assertEquals(2, player2.getPlayerboard().getDamage().size());
        assertEquals(1, player2.getPlayerboard().getMarker().size());
        assertEquals(1, player3.getPlayerboard().getMarker().size());


    }

    @Test
    public void basicEffect(){

        CopyOnWriteArrayList<String> answers = new CopyOnWriteArrayList<>();
        answers.add("player1");
        answers.add("player2");
        TestBot.initAnswers(answers);

        Player user = Server.connectedPlayers.get(0);
        Player player1 = Server.connectedPlayers.get(1);
        Player player2 = Server.connectedPlayers.get(2);
        CopyOnWriteArrayList <Player> targets = new CopyOnWriteArrayList<>();
        targets.add(player2);

        player1.setPosition(Board.getMap().getYellowRoom().getCells().get(1));
        player2.setPosition(Board.getMap().getBlueRoom().getCells().get(1));

        Weapon lockrifle= new Weapon();
        lockrifle.setBaseEffect(new LockRifleEffect());

        lockrifle.getBaseEffect().applyEffect(user, targets);

        assertEquals(2, player2.getPlayerboard().getDamage().size());
        assertEquals(1, player2.getPlayerboard().getMarker().size());


    }


    @Test
    public void getTargets(){

        CopyOnWriteArrayList<String> answers = new CopyOnWriteArrayList<>();
        answers.add("player2");
        answers.add("player1");
        TestBot.initAnswers(answers);

        Player user = Server.connectedPlayers.get(0);
        Player player1 = Server.connectedPlayers.get(1);
        Player player2 = Server.connectedPlayers.get(2);

        player1.setPosition(Board.getMap().getYellowRoom().getCells().get(1));
        player2.setPosition(Board.getMap().getBlueRoom().getCells().get(1));

        Weapon lockrifle= new Weapon();
        lockrifle.setBaseEffect(new LockRifleEffect());

        CopyOnWriteArrayList <Player> targets = lockrifle.getBaseEffect().getTargets(user);

        assertTrue(targets.contains(player2));
        assertTrue(!targets.contains(player1));

    }

    @Test
    public void hasTargets() {

        Server.connectedPlayers.get(1).setPosition(Board.getMap().getWhiteRoom().getCells().get(1));
        Weapon weapon = new Weapon();
        weapon.setBaseEffect(new LockRifleEffect());
        boolean result = weapon.getBaseEffect().hasTargets(Server.connectedPlayers.get(0));
        assertTrue(result);


        for(int i = 1; i<5; i++){

            Server.connectedPlayers.get(i).setPosition(Board.getMap().getBlueRoom().getCells().get(2));
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