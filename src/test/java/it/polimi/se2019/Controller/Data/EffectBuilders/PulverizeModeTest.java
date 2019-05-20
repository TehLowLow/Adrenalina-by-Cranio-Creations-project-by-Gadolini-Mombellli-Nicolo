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

public class PulverizeModeTest {

    @Before
    public void preparePlayers() {

        ConfigurationTest.createTestConfiguration();

    }

    @Test
    public void hasTargets() {


        Weapon weapon = new Weapon();
        weapon.setAlternativeEffect(new PulverizeMode());
        boolean result = weapon.getAlternativeEffect().hasTargets(Server.connectedPlayers.get(0));
        assertEquals(result, true);

        for (int i = 1; i < 5; i++) {

            Server.connectedPlayers.get(i).setPosition(Board.getMap().getBlueRoom().getCells().get(1));

        }


        result = weapon.getAlternativeEffect().hasTargets(Server.connectedPlayers.get(0));
        assertEquals(result, false);


    }

    @Test
    public void applyEffect() {

        CopyOnWriteArrayList<String> answers = new CopyOnWriteArrayList<>();

        answers.add("player");
        answers.add("5");
        answers.add("player3");
        answers.add("1");
        answers.add("player4");
        answers.add("2");


        TestBot.initAnswers(answers);

        Player shooter = Server.connectedPlayers.get(0);
        Player target1 = Server.connectedPlayers.get(1);
        Player target2 = Server.connectedPlayers.get(2);
        Player target3 = Server.connectedPlayers.get(3);

        shooter.getPlayerboard().getAmmoCubes().setRedCubes(3);

        CopyOnWriteArrayList<Player> targets = new CopyOnWriteArrayList<>();

        targets.add(target1);
        targets.add(target2);
        targets.add(target3);

        Weapon sHammer = new Weapon();
        sHammer.setAlternativeEffect(new PulverizeMode());
        sHammer.getAlternativeEffect().applyEffect(shooter, targets);
        targets.remove(0);
        sHammer.getAlternativeEffect().applyEffect(shooter, targets);
        targets.remove(0);
        sHammer.getAlternativeEffect().applyEffect(shooter, targets);

        assertEquals(3, target1.getPlayerboard().getDamage().size());
        assertEquals(3, target2.getPlayerboard().getDamage().size());
        assertEquals(3, target3.getPlayerboard().getDamage().size());
        assertEquals(target1.getPosition(), Board.getMap().getBlueRoom().getCells().get(0));
        assertEquals(target2.getPosition(), Board.getMap().getRedRoom().getCells().get(2));
        assertEquals(target3.getPosition(), Board.getMap().getBlueRoom().getCells().get(1));

    }

    @Test
    public void getTargets() {

        CopyOnWriteArrayList<String> answers = new CopyOnWriteArrayList<>();

        answers.add("player2");

        TestBot.initAnswers(answers);


        Player shooter = Server.connectedPlayers.get(0);

        Weapon sHammer = new Weapon();

        sHammer.setAlternativeEffect(new PulverizeMode());

        CopyOnWriteArrayList<Player> targets = sHammer.getAlternativeEffect().getTargets(shooter);

        assertEquals(1, targets.size());

    }

}