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

public class RocketFistModeTest {

    @Before
    public void preparePlayers(){

        ConfigurationTest.createTestConfiguration();

    }

    @Test
    public void hasTargets() {


        Weapon weapon = new Weapon();
        weapon.setAlternativeEffect(new RocketFistMode());
        boolean result = weapon.getAlternativeEffect().hasTargets(Server.connectedPlayers.get(0));
        assertEquals(result, false);

        for(int i = 1; i<5; i++){

            Server.connectedPlayers.get(i).setPosition(Board.getMap().getBlueRoom().getCells().get(2));
        }

        System.out.println(Board.getMap().getWhiteRoom().getCells().get(1).getName());

        result = weapon.getAlternativeEffect().hasTargets(Server.connectedPlayers.get(0));
        assertEquals(result, true);

    }

    @Test
    public void applyEffect() {
    }

    @Test
    public void getTargets() {

        CopyOnWriteArrayList<String> answers = new CopyOnWriteArrayList<>();

        answers.add("player2");
        answers.add("player3");

        TestBot.initAnswers(answers);

        Player shooter = Server.connectedPlayers.get(0);
        Player target1 = Server.connectedPlayers.get(1);
        Player target2 = Server.connectedPlayers.get(2);

        Weapon glove = new Weapon();

        glove.setAlternativeEffect(new RocketFistMode());

        target1.setPosition(Board.getMap().getBlueRoom().getCells().get(1));
        target2.setPosition(Board.getMap().getBlueRoom().getCells().get(2));

        CopyOnWriteArrayList<Player> targets = glove.getAlternativeEffect().getTargets(shooter);
        assertEquals(1, targets.size());
        shooter.setPosition(Board.getMap().getBlueRoom().getCells().get(2));
        targets = glove.getAlternativeEffect().getTargets(shooter);
        assertEquals(1, targets.size());
    }

}