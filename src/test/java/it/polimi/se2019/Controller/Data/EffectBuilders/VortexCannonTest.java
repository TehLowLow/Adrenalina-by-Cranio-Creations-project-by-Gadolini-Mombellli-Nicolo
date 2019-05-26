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

public class VortexCannonTest {

    @Before
    public void preparePlayers(){

        ConfigurationTest.createTestConfiguration();

    }

    @Test
    public void applyEffectJustBase(){


        CopyOnWriteArrayList<String> answers = new CopyOnWriteArrayList<>();

        answers.add("0");
        answers.add("player3");
        answers.add("no");

        TestBot.initAnswers(answers);

        Weapon weapon = new Weapon();
        weapon.setBaseEffect(new VortexCannon());

        Player shooter = Server.connectedPlayers.get(0);
        Player player1 = Server.connectedPlayers.get(1);
        Player player2 = Server.connectedPlayers.get(2);
        Player player3 = Server.connectedPlayers.get(3);
        Player player4 = Server.connectedPlayers.get(4);

        player1.setPosition(Board.getMap().getRedRoom().getCells().get(1));
        player3.setPosition(Board.getMap().getBlueRoom().getCells().get(2));
        player4.setPosition(Board.getMap().getBlueRoom().getCells().get(1));
        player2.setPosition(Board.getMap().getWhiteRoom().getCells().get(0));

        Server.update(shooter, "");

        shooter.getPlayerboard().getAmmoCubes().setRedCubes(3);
        shooter.getPlayerboard().getAmmoCubes().setYellowCubes(3);

        CopyOnWriteArrayList<Player> targets = weapon.getBaseEffect().getTargets(shooter);

        weapon.getBaseEffect().applyEffect(shooter, targets);

        assertTrue(player3.getPosition().equals(Board.getMap().getBlueRoom().getCells().get(1)));
        assertTrue(player3.getPlayerboard().getDamage().size()==2);



    }

    @Test
    public void applyEffectBlackHoleTwoTargets() {

        CopyOnWriteArrayList<String> answers = new CopyOnWriteArrayList<>();

        answers.add("0");
        answers.add("player3");
        answers.add("si");
        answers.add("player1");
        answers.add("si");
        answers.add("player4");

        TestBot.initAnswers(answers);

        Weapon weapon = new Weapon();
        weapon.setBaseEffect(new VortexCannon());

        Player shooter = Server.connectedPlayers.get(0);
        Player player1 = Server.connectedPlayers.get(1);
        Player player2 = Server.connectedPlayers.get(2);
        Player player3 = Server.connectedPlayers.get(3);
        Player player4 = Server.connectedPlayers.get(4);

        player1.setPosition(Board.getMap().getRedRoom().getCells().get(0));
        player3.setPosition(Board.getMap().getBlueRoom().getCells().get(2));
        player4.setPosition(Board.getMap().getBlueRoom().getCells().get(1));
        player2.setPosition(Board.getMap().getWhiteRoom().getCells().get(0));

        Server.update(shooter, "");

        shooter.getPlayerboard().getAmmoCubes().setRedCubes(3);

        CopyOnWriteArrayList<Player> targets = weapon.getBaseEffect().getTargets(shooter);

        weapon.getBaseEffect().applyEffect(shooter, targets);

        assertTrue(player3.getPosition().equals(Board.getMap().getBlueRoom().getCells().get(1)));
        assertTrue(player1.getPosition().equals(Board.getMap().getBlueRoom().getCells().get(1)));
        assertTrue(player4.getPosition().equals(Board.getMap().getBlueRoom().getCells().get(1)));

        assertTrue(player3.getPlayerboard().getDamage().size() == 2);
        assertTrue(player1.getPlayerboard().getDamage().size() == 1);
        assertTrue(player4.getPlayerboard().getDamage().size() == 1);


    }

    @Test
    public void applyEffectBlackHoleOneTarget(){

        CopyOnWriteArrayList<String> answers = new CopyOnWriteArrayList<>();

        answers.add("0");
        answers.add("player3");
        answers.add("si");
        answers.add("player1");
        answers.add("no");

        TestBot.initAnswers(answers);

        Weapon weapon = new Weapon();
        weapon.setBaseEffect(new VortexCannon());

        Player shooter = Server.connectedPlayers.get(0);
        Player player1 = Server.connectedPlayers.get(1);
        Player player2 = Server.connectedPlayers.get(2);
        Player player3 = Server.connectedPlayers.get(3);
        Player player4 = Server.connectedPlayers.get(4);

        player1.setPosition(Board.getMap().getRedRoom().getCells().get(0));
        player3.setPosition(Board.getMap().getBlueRoom().getCells().get(2));
        player4.setPosition(Board.getMap().getBlueRoom().getCells().get(1));
        player2.setPosition(Board.getMap().getWhiteRoom().getCells().get(0));

        Server.update(shooter, "");

        shooter.getPlayerboard().getAmmoCubes().setRedCubes(3);

        CopyOnWriteArrayList<Player> targets = weapon.getBaseEffect().getTargets(shooter);

        weapon.getBaseEffect().applyEffect(shooter, targets);

        assertTrue(player3.getPosition().equals(Board.getMap().getBlueRoom().getCells().get(1)));
        assertTrue(player1.getPosition().equals(Board.getMap().getBlueRoom().getCells().get(1)));


        assertTrue(player3.getPlayerboard().getDamage().size()==2);
        assertTrue(player1.getPlayerboard().getDamage().size()==1);


    }

    @Test
    public void getTargets() {

        CopyOnWriteArrayList<String> answers = new CopyOnWriteArrayList<>();

        answers.add("0");
        answers.add("player3");

        TestBot.initAnswers(answers);

        Weapon weapon = new Weapon();
        weapon.setBaseEffect(new VortexCannon());

        Player shooter = Server.connectedPlayers.get(0);
        Player player1 = Server.connectedPlayers.get(1);
        Player player2 = Server.connectedPlayers.get(2);
        Player player3 = Server.connectedPlayers.get(3);
        Player player4 = Server.connectedPlayers.get(4);

        player1.setPosition(Board.getMap().getRedRoom().getCells().get(0));
        player3.setPosition(Board.getMap().getBlueRoom().getCells().get(2));
        player4.setPosition(Board.getMap().getBlueRoom().getCells().get(1));
        player2.setPosition(Board.getMap().getWhiteRoom().getCells().get(0));

        Server.update(shooter, "");

        shooter.getPlayerboard().getAmmoCubes().setRedCubes(3);

        CopyOnWriteArrayList<Player> targets = weapon.getBaseEffect().getTargets(shooter);

        assertTrue(targets.contains(player3));
        assertTrue(targets.size()==1);


    }



    @Test
    public void hasTargets() {

        Weapon weapon = new Weapon();
        weapon.setBaseEffect(new VortexCannon());
        boolean result = weapon.getBaseEffect().hasTargets(Server.connectedPlayers.get(0));
        assertTrue(result);


        for(int i = 1; i<5; i++){

            Server.connectedPlayers.get(i).setPosition(Board.getMap().getWhiteRoom().getCells().get(1));
        }

        result = weapon.getBaseEffect().hasTargets(Server.connectedPlayers.get(0));

        assertFalse(result);



        for(int i = 1; i<5; i++){

            Server.connectedPlayers.get(i).setPosition(Board.getMap().getBlueRoom().getCells().get(2));
        }

        result = weapon.getBaseEffect().hasTargets(Server.connectedPlayers.get(0));
        assertTrue(result);



        for(int i = 1; i<5; i++){

            Server.connectedPlayers.get(i).setPosition(Board.getMap().getWhiteRoom().getCells().get(0));
        }

        result = weapon.getBaseEffect().hasTargets(Server.connectedPlayers.get(0));
        assertTrue(result);

    }
}