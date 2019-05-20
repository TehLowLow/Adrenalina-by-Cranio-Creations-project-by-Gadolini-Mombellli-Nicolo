package it.polimi.se2019.Controller.Data.EffectBuilders;

import it.polimi.se2019.Model.Board;
import it.polimi.se2019.Model.Player;
import it.polimi.se2019.Model.Powerup;
import it.polimi.se2019.Model.Weapon;
import it.polimi.se2019.Network.Server;
import it.polimi.se2019.Network.TestBot;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.Assert.*;

public class NewtonTest {

    @Before
    public void preparePlayers(){

        ConfigurationTest.createTestConfiguration();

    }

    @Test
    public void applyEffect(){

        CopyOnWriteArrayList<String> answers = new CopyOnWriteArrayList<>();
        answers.add("sinistra");
        answers.add("1");
        TestBot.initAnswers(answers);

        Player user = Server.connectedPlayers.get(0);
        Player player1 = Server.connectedPlayers.get(1);
        CopyOnWriteArrayList <Player> targets = new CopyOnWriteArrayList<>();
        targets.add(player1);

        player1.setPosition(Board.getMap().getRedRoom().getCells().get(2));


       Powerup newton = new Powerup();
       newton.setEffect(new Newton());
       newton.getEffect().applyEffect(user, targets);
       assertTrue(player1.getPosition().equals(Board.getMap().getRedRoom().getCells().get(0)));


    }


    @Test
    public void getTargets(){

        CopyOnWriteArrayList<String> answers = new CopyOnWriteArrayList<>();
        answers.add("player1");
        TestBot.initAnswers(answers);

        Player user = Server.connectedPlayers.get(0);
        Player player1 = Server.connectedPlayers.get(1);
        Player player2 = Server.connectedPlayers.get(2);

        player1.setPosition(Board.getMap().getYellowRoom().getCells().get(1));
        player2.setPosition(Board.getMap().getBlueRoom().getCells().get(1));

        Powerup newton = new Powerup();
        newton.setEffect(new Newton());

        CopyOnWriteArrayList <Player> targets = newton.getEffect().getTargets(user);

        assertTrue(targets.contains(player1));
        assertTrue(targets.size()==1);

    }

    @Test
    public void hasTargets() {

        //Non serve testare questo metodo poiché ritorna sempre true.

    }
}