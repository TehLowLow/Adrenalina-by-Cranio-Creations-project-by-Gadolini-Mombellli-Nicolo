package it.polimi.se2019.Controller.Data.EffectBuilders;

import com.sun.corba.se.spi.copyobject.CopyobjectDefaults;
import it.polimi.se2019.Model.Board;
import it.polimi.se2019.Model.Player;
import it.polimi.se2019.Model.Weapon;
import it.polimi.se2019.Network.Server;
import it.polimi.se2019.Network.TestBot;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.Assert.*;

public class PiercingModeTest {

    @Before
    public void preparePlayers() {

        ConfigurationTest.createTestConfiguration();

    }

    @Test
    public void hasTargets() {

        Weapon weapon = new Weapon();
        weapon.setAlternativeEffect(new PiercingMode());
        boolean result = weapon.getAlternativeEffect().hasTargets(Server.connectedPlayers.get(0));
        assertEquals(result, true);

        for (int i = 1; i < 5; i++) {

            Server.connectedPlayers.get(i).setPosition(Board.getMap().getWhiteRoom().getCells().get(1));
        }

        System.out.println(Board.getMap().getWhiteRoom().getCells().get(1).getName());

        result = weapon.getAlternativeEffect().hasTargets(Server.connectedPlayers.get(0));
        assertEquals(result, true);

    }

    @Test
    public void applyEffect() {

        Player shooter = Server.connectedPlayers.get(0);
        Player target1 = Server.connectedPlayers.get(1);
        Player target2 = Server.connectedPlayers.get(2);
        Player target3 = Server.connectedPlayers.get(3);

        shooter.setPosition(Board.getMap().getBlueRoom().getCells().get(2));
        target1.setPosition(Board.getMap().getRedRoom().getCells().get(1));
        target2.setPosition(Board.getMap().getWhiteRoom().getCells().get(0));
        target3.setPosition(Board.getMap().getYellowRoom().getCells().get(0));

        Weapon lasur = new Weapon();
        lasur.setAlternativeEffect(new PiercingMode());

        CopyOnWriteArrayList<Player> targets = new CopyOnWriteArrayList<>();

        targets.add(target1);
        targets.add(target2);

        lasur.getAlternativeEffect().applyEffect(shooter, targets);

        assertEquals(2, target1.getPlayerboard().getDamage().size());
        assertEquals(2, target2.getPlayerboard().getDamage().size());
        assertEquals(0, target3.getPlayerboard().getDamage().size());
    }

    @Test
    public void getTargets() {

        CopyOnWriteArrayList<String> answers = new CopyOnWriteArrayList<>();

        answers.add("basso");
        answers.add("player1");
        answers.add("si");
        answers.add("player2");

        TestBot.initAnswers(answers);

        Player shooter = Server.connectedPlayers.get(0);
        Player target1 = Server.connectedPlayers.get(1);
        Player target2 = Server.connectedPlayers.get(2);
        Player target3 = Server.connectedPlayers.get(3);

        shooter.setPosition(Board.getMap().getBlueRoom().getCells().get(2));
        target1.setPosition(Board.getMap().getRedRoom().getCells().get(1));
        target2.setPosition(Board.getMap().getWhiteRoom().getCells().get(0));
        target3.setPosition(Board.getMap().getYellowRoom().getCells().get(0));

        Weapon lasur = new Weapon();
        lasur.setAlternativeEffect(new PiercingMode());

        CopyOnWriteArrayList<Player> targets = lasur.getAlternativeEffect().getTargets(shooter);

        assertEquals(2, targets.size());
    }
}