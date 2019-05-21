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

public class RocketLauncherTest {

    @Before
    public void preparePlayers() {

        ConfigurationTest.createTestConfiguration();

    }

    @Test
    public void hasTargets() {


        Server.connectedPlayers.get(1).setPosition(Board.getMap().getWhiteRoom().getCells().get(1));
        Weapon weapon = new Weapon();
        weapon.setBaseEffect(new RocketLauncher());
        boolean result = weapon.getBaseEffect().hasTargets(Server.connectedPlayers.get(0));
        assertEquals(result, false);

        for (int i = 1; i < 5; i++) {

            Server.connectedPlayers.get(i).setPosition(Board.getMap().getBlueRoom().getCells().get(2));
        }

        result = weapon.getBaseEffect().hasTargets(Server.connectedPlayers.get(0));
        assertEquals(result, true);

        for (int i = 1; i < 5; i++) {

            Server.connectedPlayers.get(i).setPosition(Board.getMap().getYellowRoom().getCells().get(1));
        }

        result = weapon.getBaseEffect().hasTargets(Server.connectedPlayers.get(0));
        assertEquals(result, false);

        Server.connectedPlayers.get(0).getPlayerboard().getAmmoCubes().setBlueCubes(1);
        result = weapon.getBaseEffect().hasTargets(Server.connectedPlayers.get(0));
        assertEquals(result, true);


    }

    @Test
    public void applyEffect() {

        CopyOnWriteArrayList<String> answers = new CopyOnWriteArrayList<>();

        answers.add("si");
        answers.add("no");
        answers.add("si");
        answers.add("sinistra");
        answers.add("4");

        //---------------

        answers.add("si");
        answers.add("sinistra");
        answers.add("si");


        TestBot.initAnswers(answers);

        Player shooter = Server.connectedPlayers.get(0);
        Player target1 = Server.connectedPlayers.get(1);
        Player target2 = Server.connectedPlayers.get(2);
        Player target3 = Server.connectedPlayers.get(3);

        CopyOnWriteArrayList<Player> targets = new CopyOnWriteArrayList<>();
        targets.add(target1);
        targets.add(target2);
        targets.add(target3);

        Weapon launcher = new Weapon();
        launcher.setBaseEffect(new RocketLauncher());

        //Mi muovo dopo

        target1.setPosition(Board.getMap().getBlueRoom().getCells().get(2));
        target2.setPosition(Board.getMap().getBlueRoom().getCells().get(2));
        target3.setPosition(Board.getMap().getBlueRoom().getCells().get(2));

        shooter.getPlayerboard().getAmmoCubes().setBlueCubes(1);
        launcher.getBaseEffect().applyEffect(shooter, targets);

        assertEquals(2, target1.getPlayerboard().getDamage().size());
        assertEquals(target1.getPosition(), Board.getMap().getBlueRoom().getCells().get(1));
        assertEquals(0, shooter.getPlayerboard().getAmmoCubes().getBlue());

        //------------------------------------------------------------------------------------

        target1.setPosition(Board.getMap().getBlueRoom().getCells().get(2));
        target2.setPosition(Board.getMap().getBlueRoom().getCells().get(2));
        target3.setPosition(Board.getMap().getBlueRoom().getCells().get(2));

        shooter.setPosition(Board.getMap().getBlueRoom().getCells().get(0));

        target1.getPlayerboard().getDamage().remove(0);
        target1.getPlayerboard().getDamage().remove(0);

        shooter.getPlayerboard().getAmmoCubes().setYellowCubes(1);
        launcher.getBaseEffect().applyEffect(shooter, targets);

        assertEquals(3, target1.getPlayerboard().getDamage().size());
        assertEquals(1, target2.getPlayerboard().getDamage().size());
        assertEquals(1, target3.getPlayerboard().getDamage().size());



    }

    @Test
    public void getTargets() {

        CopyOnWriteArrayList<String> answers = new CopyOnWriteArrayList<>();

        answers.add("player1");


        TestBot.initAnswers(answers);

        Player shooter = Server.connectedPlayers.get(0);
        Player target1 = Server.connectedPlayers.get(1);
        Player target2 = Server.connectedPlayers.get(2);
        Player target3 = Server.connectedPlayers.get(3);

        CopyOnWriteArrayList<Player> targets;

        Weapon launcher = new Weapon();
        launcher.setBaseEffect(new RocketLauncher());

        //Beccare i target nella cella adiacente alla mia

        target1.setPosition(Board.getMap().getBlueRoom().getCells().get(2));
        target2.setPosition(Board.getMap().getBlueRoom().getCells().get(2));
        target3.setPosition(Board.getMap().getBlueRoom().getCells().get(2));

        targets = launcher.getBaseEffect().getTargets(shooter);

        assertEquals(1, targets.size());


    }
}