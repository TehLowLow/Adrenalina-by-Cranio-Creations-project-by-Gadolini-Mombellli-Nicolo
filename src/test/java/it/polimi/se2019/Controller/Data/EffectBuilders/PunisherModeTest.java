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

public class PunisherModeTest {

    @Before
    public void preparePlayers() {

        ConfigurationTest.createTestConfiguration();

    }

    @Test
    public void hasTargets() {


        Weapon weapon = new Weapon();
        weapon.setAlternativeEffect(new PunisherMode());
        boolean result = weapon.getAlternativeEffect().hasTargets(Server.connectedPlayers.get(0));
        assertTrue(result);


        for (int i = 1; i < 5; i++) {

            Server.connectedPlayers.get(i).setPosition(Board.getMap().getWhiteRoom().getCells().get(1));
        }

        System.out.println(Board.getMap().getWhiteRoom().getCells().get(1).getName());

        result = weapon.getAlternativeEffect().hasTargets(Server.connectedPlayers.get(0));

        assertFalse(result);


    }

    @Test
    public void applyEffect() {

        CopyOnWriteArrayList<String> answers = new CopyOnWriteArrayList<>();

        answers.add("player1");
        answers.add("player2");
        answers.add("player3");

        TestBot.initAnswers(answers);

        Weapon tractorBeam = new Weapon();
        tractorBeam.setAlternativeEffect(new PunisherMode());

        Player shooter = Server.connectedPlayers.get(0);
        Player target0 = Server.connectedPlayers.get(1);
        Player target1 = Server.connectedPlayers.get(2);
        Player target2 = Server.connectedPlayers.get(3);

        target1.setPosition(Board.getMap().getRedRoom().getCells().get(2));
        target2.setPosition(Board.getMap().getWhiteRoom().getCells().get(0));

        shooter.getPlayerboard().getAmmoCubes().setRedCubes(3);
        shooter.getPlayerboard().getAmmoCubes().setYellowCubes(3);

        CopyOnWriteArrayList<Player> targets = new CopyOnWriteArrayList<>();

        targets.add(target0);
        targets.add(target1);
        targets.add(target2);

        tractorBeam.getAlternativeEffect().applyEffect(shooter, targets);
        targets.remove(0);
        tractorBeam.getAlternativeEffect().applyEffect(shooter,targets);
        targets.remove(0);
        tractorBeam.getAlternativeEffect().applyEffect(shooter,targets);

        assertEquals(3,target0.getPlayerboard().getDamage().size());
        assertEquals(3,target1.getPlayerboard().getDamage().size());
        assertEquals(3,target2.getPlayerboard().getDamage().size());


        assertEquals(target0.getPosition(), Board.getMap().getBlueRoom().getCells().get(0));
        assertEquals(target1.getPosition(), Board.getMap().getBlueRoom().getCells().get(0));
        assertEquals(target2.getPosition(), Board.getMap().getBlueRoom().getCells().get(0));


    }

    @Test
    public void getTargets() {

        CopyOnWriteArrayList<String> answers = new CopyOnWriteArrayList<>();

        answers.add("player1");
        answers.add("player2");
        answers.add("player3");

        TestBot.initAnswers(answers);

        CopyOnWriteArrayList<Player> targets = new CopyOnWriteArrayList<>();

        Weapon tractorBeam = new Weapon();
        tractorBeam.setAlternativeEffect(new PunisherMode());

        Player shooter = Server.connectedPlayers.get(0);
        Player target0 = Server.connectedPlayers.get(1);
        Player target1 = Server.connectedPlayers.get(2);
        Player target2 = Server.connectedPlayers.get(3);

        target1.setPosition(Board.getMap().getRedRoom().getCells().get(2));
        target2.setPosition(Board.getMap().getWhiteRoom().getCells().get(0));

        targets.add(tractorBeam.getAlternativeEffect().getTargets(shooter).get(0));
        targets.add(tractorBeam.getAlternativeEffect().getTargets(shooter).get(0));
        targets.add(tractorBeam.getAlternativeEffect().getTargets(shooter).get(0));

        assertEquals(3, targets.size());
    }
}