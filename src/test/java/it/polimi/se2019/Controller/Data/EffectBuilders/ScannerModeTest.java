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

public class ScannerModeTest {

    @Before
    public void preparePlayers() {

        ConfigurationTest.createTestConfiguration();

    }

    @Test
    public void hasTargets() {

        Server.connectedPlayers.get(1).setPosition(Board.getMap().getBlueRoom().getCells().get(2));

        Weapon weapon = new Weapon();
        weapon.setAlternativeEffect(new ScannerMode());
        boolean result = weapon.getAlternativeEffect().hasTargets(Server.connectedPlayers.get(0));
        assertTrue(result);

        for (int i = 1; i < 5; i++) {

            Server.connectedPlayers.get(i).setPosition(Board.getMap().getYellowRoom().getCells().get(1));
        }

        result = weapon.getAlternativeEffect().hasTargets(Server.connectedPlayers.get(0));
        assertFalse(result);

    }


    @Test
    public void applyEffect() {







    }

    @Test
    public void getTargets() {


        CopyOnWriteArrayList<String> answers = new CopyOnWriteArrayList<>();

        answers.add("player1");
        answers.add("si");
        answers.add("player2");
        answers.add("si");
        answers.add("player3");
        answers.add("no");

        TestBot.initAnswers(answers);

        Player shooter = Server.connectedPlayers.get(0);
        Player target1 = Server.connectedPlayers.get(1);
        Player target2 = Server.connectedPlayers.get(2);
        Player target3 = Server.connectedPlayers.get(3);


        Weapon zx2 = new Weapon();

        zx2.setAlternativeEffect(new ScannerMode());

        shooter.setPosition(Board.getMap().getRedRoom().getCells().get(2));
        target1.setPosition(Board.getMap().getYellowRoom().getCells().get(1));
        target2.setPosition(Board.getMap().getRedRoom().getCells().get(2));
        target3.setPosition(Board.getMap().getBlueRoom().getCells().get(2));


        CopyOnWriteArrayList<Player> targets = zx2.getAlternativeEffect().getTargets(shooter);

        assertEquals(3, targets.size());


        //TODO get targets sembra avere un bug





    }


}