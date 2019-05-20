package it.polimi.se2019.Controller.Data.EffectBuilders;

import it.polimi.se2019.Model.Board;
import it.polimi.se2019.Model.Map;
import it.polimi.se2019.Model.Player;
import it.polimi.se2019.Model.Weapon;
import it.polimi.se2019.Network.Server;
import it.polimi.se2019.Network.TestBot;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.Assert.*;

public class GrenadeLauncherTest {

    @Before
    public void preparePlayers() {

        ConfigurationTest.createTestConfiguration();

    }

    @Test
    public void hasTargets() {

        Server.connectedPlayers.get(1).setPosition(Board.getMap().getWhiteRoom().getCells().get(1));
        Weapon weapon = new Weapon();
        weapon.setBaseEffect(new GrenadeLauncher());
        boolean result = weapon.getBaseEffect().hasTargets(Server.connectedPlayers.get(0));
        assertEquals(result, true);

        for (int i = 1; i < 5; i++) {

            Server.connectedPlayers.get(i).setPosition(Board.getMap().getRedRoom().getCells().get(2));
        }

        result = weapon.getBaseEffect().hasTargets(Server.connectedPlayers.get(0));
        assertEquals(result, true);

        for (int i = 1; i < 5; i++) {

            Server.connectedPlayers.get(i).setPosition(Board.getMap().getYellowRoom().getCells().get(1));
        }

        result = weapon.getBaseEffect().hasTargets(Server.connectedPlayers.get(0));
        assertEquals(result, false);


    }

    @Test
    public void applyBaseEffectNoExtraNoMove() {

        CopyOnWriteArrayList<String> answers = new CopyOnWriteArrayList<>();

        answers.add("base");
        answers.add("no");
        TestBot.initAnswers(answers);

        Player shooter = Server.connectedPlayers.get(0);
        Player target1 = Server.connectedPlayers.get(1);
        Player target2 = Server.connectedPlayers.get(2);

        CopyOnWriteArrayList<Player> targets = new CopyOnWriteArrayList<>();

        targets.add(target1);
        targets.add(target2);

        Weapon gLauncher = new Weapon();
        gLauncher.setBaseEffect(new GrenadeLauncher());
        gLauncher.getBaseEffect().applyEffect(shooter, targets);

        assertEquals(1, targets.get(0).getPlayerboard().getDamage().size());
        assertEquals(0, targets.get(1).getPlayerboard().getDamage().size());

    }

    @Test
    public void applyBaseEffectNoExtraMove() {

        CopyOnWriteArrayList<String> answers = new CopyOnWriteArrayList<>();

        answers.add("base");
        answers.add("si");
        answers.add("sinistra");
        TestBot.initAnswers(answers);

        Player shooter = Server.connectedPlayers.get(0);
        Player target1 = Server.connectedPlayers.get(1);
        Player target2 = Server.connectedPlayers.get(2);

        CopyOnWriteArrayList<Player> targets = new CopyOnWriteArrayList<>();

        targets.add(target1);
        targets.add(target2);

        Weapon gLauncher = new Weapon();
        gLauncher.setBaseEffect(new GrenadeLauncher());
        gLauncher.getBaseEffect().applyEffect(shooter, targets);

        assertEquals(1, targets.get(0).getPlayerboard().getDamage().size());
        assertEquals(0, targets.get(1).getPlayerboard().getDamage().size());
        assertEquals(targets.get(0).getPosition().getName(), Board.getMap().getBlueRoom().getCells().get(2).getName());
    }

    @Test
    public void applyBaseEffectExtraMove() {

        CopyOnWriteArrayList<String> answers = new CopyOnWriteArrayList<>();

        answers.add("base");
        answers.add("si");
        answers.add("sinistra");
        answers.add("si");
        answers.add("0");

        TestBot.initAnswers(answers);

        Player shooter = Server.connectedPlayers.get(0);
        Player target1 = Server.connectedPlayers.get(1);
        Player target2 = Server.connectedPlayers.get(2);

        target2.setPosition(Board.getMap().getBlueRoom().getCells().get(2));

        CopyOnWriteArrayList<Player> targets = new CopyOnWriteArrayList<>();

        targets.add(target1);
        targets.add(target2);

        shooter.getPlayerboard().getAmmoCubes().setRedCubes(1);

        Weapon gLauncher = new Weapon();
        gLauncher.setBaseEffect(new GrenadeLauncher());
        gLauncher.getBaseEffect().applyEffect(shooter, targets);

        assertEquals(2, target1.getPlayerboard().getDamage().size());
        assertEquals(1, target2.getPlayerboard().getDamage().size());

        //Sparo al target 1, lo muovo nella cella di target 2 e sparo la granata extra, cosi t1 ha 2 danni e t2 1
    }

    @Test
    public void applyBaseEffectNoMoveExtraMove() {

        CopyOnWriteArrayList<String> answers = new CopyOnWriteArrayList<>();

        answers.add("base");
        answers.add("no");
        answers.add("si");
        answers.add("0");
        answers.add("si");
        answers.add("sinistra");


        TestBot.initAnswers(answers);

        Player shooter = Server.connectedPlayers.get(0);
        Player target1 = Server.connectedPlayers.get(1);
        Player target2 = Server.connectedPlayers.get(2);

        target2.setPosition(Board.getMap().getBlueRoom().getCells().get(2));

        CopyOnWriteArrayList<Player> targets = new CopyOnWriteArrayList<>();

        targets.add(target1);
        targets.add(target2);

        shooter.getPlayerboard().getAmmoCubes().setRedCubes(1);

        Weapon gLauncher = new Weapon();
        gLauncher.setBaseEffect(new GrenadeLauncher());
        gLauncher.getBaseEffect().applyEffect(shooter, targets);

        assertEquals(2, target1.getPlayerboard().getDamage().size());
        assertEquals(0, target2.getPlayerboard().getDamage().size());
        assertEquals(0, shooter.getPlayerboard().getDamage().size());
        assertEquals(target1.getPosition(), target2.getPosition());
    }

    @Test
    public void applyBaseEffectNoMoveExtraNoMove() {

        CopyOnWriteArrayList<String> answers = new CopyOnWriteArrayList<>();

        answers.add("base");
        answers.add("no");
        answers.add("si");
        answers.add("0");
        answers.add("no");

        TestBot.initAnswers(answers);

        Player shooter = Server.connectedPlayers.get(0);
        Player target1 = Server.connectedPlayers.get(1);
        Player target2 = Server.connectedPlayers.get(2);

        target2.setPosition(Board.getMap().getBlueRoom().getCells().get(2));

        CopyOnWriteArrayList<Player> targets = new CopyOnWriteArrayList<>();

        targets.add(target1);
        targets.add(target2);

        shooter.getPlayerboard().getAmmoCubes().setRedCubes(1);

        Weapon gLauncher = new Weapon();
        gLauncher.setBaseEffect(new GrenadeLauncher());
        gLauncher.getBaseEffect().applyEffect(shooter, targets);

        assertEquals(2, target1.getPlayerboard().getDamage().size());
        assertEquals(0, target2.getPlayerboard().getDamage().size());
        assertEquals(0, shooter.getPlayerboard().getDamage().size());
        assertEquals(target1.getPosition(), shooter.getPosition());
        assertNotEquals(target1.getPosition(), target2.getPosition());

    }


    @Test
    public void applyBaseEffectMultipleDamageNoMove() {

        CopyOnWriteArrayList<String> answers = new CopyOnWriteArrayList<>();

        answers.add("base");
        answers.add("no");
        answers.add("si");
        answers.add("1");
        answers.add("no");

        TestBot.initAnswers(answers);

        Player shooter = Server.connectedPlayers.get(0);
        Player target1 = Server.connectedPlayers.get(1);
        Player target2 = Server.connectedPlayers.get(2);

        target2.setPosition(Board.getMap().getBlueRoom().getCells().get(2));

        CopyOnWriteArrayList<Player> targets = new CopyOnWriteArrayList<>();

        targets.add(target1);
        targets.add(target2);

        shooter.getPlayerboard().getAmmoCubes().setRedCubes(1);

        Weapon gLauncher = new Weapon();
        gLauncher.setBaseEffect(new GrenadeLauncher());
        gLauncher.getBaseEffect().applyEffect(shooter, targets);

        assertEquals(1, target1.getPlayerboard().getDamage().size());
        assertEquals(1, target2.getPlayerboard().getDamage().size());
        assertEquals(0, shooter.getPlayerboard().getDamage().size());



    }

    @Test
    public void applyBaseEffectMultipleDamageNoMoveExtraMove(){

        CopyOnWriteArrayList<String> answers = new CopyOnWriteArrayList<>();

        answers.add("base");
        answers.add("no");
        answers.add("si");
        answers.add("1");
        answers.add("si");
        answers.add("basso");

        TestBot.initAnswers(answers);

        Player shooter = Server.connectedPlayers.get(0);
        Player target1 = Server.connectedPlayers.get(1);
        Player target2 = Server.connectedPlayers.get(2);

        target2.setPosition(Board.getMap().getBlueRoom().getCells().get(2));

        CopyOnWriteArrayList<Player> targets = new CopyOnWriteArrayList<>();

        targets.add(target1);
        targets.add(target2);

        shooter.getPlayerboard().getAmmoCubes().setRedCubes(1);

        Weapon gLauncher = new Weapon();
        gLauncher.setBaseEffect(new GrenadeLauncher());
        gLauncher.getBaseEffect().applyEffect(shooter, targets);

        assertEquals(1, target1.getPlayerboard().getDamage().size());
        assertEquals(1, target2.getPlayerboard().getDamage().size());
        assertEquals(0, shooter.getPlayerboard().getDamage().size());
        assertEquals(Board.getMap().getRedRoom().getCells().get(2), target1.getPosition());
        assertEquals(Board.getMap().getBlueRoom().getCells().get(2), target2.getPosition());
    }

    @Test
    public void applyBaseEffectMultipleDamageMoveExtra(){

        CopyOnWriteArrayList<String> answers = new CopyOnWriteArrayList<>();

        answers.add("base");
        answers.add("si");
        answers.add("basso");
        answers.add("si");
        answers.add("1");

        TestBot.initAnswers(answers);

        Player shooter = Server.connectedPlayers.get(0);
        Player target1 = Server.connectedPlayers.get(1);
        Player target2 = Server.connectedPlayers.get(2);

        target2.setPosition(Board.getMap().getBlueRoom().getCells().get(2));

        CopyOnWriteArrayList<Player> targets = new CopyOnWriteArrayList<>();

        targets.add(target1);
        targets.add(target2);

        shooter.getPlayerboard().getAmmoCubes().setRedCubes(1);

        Weapon gLauncher = new Weapon();
        gLauncher.setBaseEffect(new GrenadeLauncher());
        gLauncher.getBaseEffect().applyEffect(shooter, targets);

        assertEquals(1, target1.getPlayerboard().getDamage().size());
        assertEquals(1, target2.getPlayerboard().getDamage().size());
        assertEquals(0, shooter.getPlayerboard().getDamage().size());
        assertEquals(Board.getMap().getRedRoom().getCells().get(2), target1.getPosition());
        assertEquals(Board.getMap().getBlueRoom().getCells().get(2), target2.getPosition());
    }





    @Test
    public void getTargets() {

        CopyOnWriteArrayList<String> answers = new CopyOnWriteArrayList<>();

        answers.add("player2");

        TestBot.initAnswers(answers);

        Player shooter = Server.connectedPlayers.get(0);
        Player target1 = Server.connectedPlayers.get(1);
        Player target2 = Server.connectedPlayers.get(2);

        target2.setPosition(Board.getMap().getBlueRoom().getCells().get(2));

        CopyOnWriteArrayList<Player> targets;

        shooter.getPlayerboard().getAmmoCubes().setRedCubes(1);

        Weapon gLauncher = new Weapon();
        gLauncher.setBaseEffect(new GrenadeLauncher());

        targets = gLauncher.getBaseEffect().getTargets(shooter);


        assertEquals(1, targets.size());
    }

    /*
    Flow Graph for GLauncher

                                  |--------- End   --->  applyBaseEffectNoExtraMove()
                                  |
            |---------Move--------|--------- Extra --->  applyBaseEffectExtraMove()
            |
    shoot---|
            |                     |--------- End   --->  applyBaseEffectNoExtraNoMove()
            |                     |
            |----------NoMove-----|--------- Extra--------|--------Move    --->  applyBaseEffectNoMoveExtraMove()
                                                          |
                                                          |--------NoMove  --->  applyBaseEffectNoMoveExtraNoMove()






      //Gli ultimi tre metodi verificano le stesse condizioni di branching ma senza effettuare due volte il danno al target 1
     */


}