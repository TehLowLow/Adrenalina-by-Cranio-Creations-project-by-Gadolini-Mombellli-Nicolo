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

        Weapon glove = new Weapon();
        glove.setAlternativeEffect(new RocketFistMode());

        assertTrue(glove.getAlternativeEffect().hasTargets(Server.connectedPlayers.get(0)));

    }

    @Test
    public void applyEffect() {

        CopyOnWriteArrayList<Player> targets = new CopyOnWriteArrayList<>();

        Player shooter = Server.connectedPlayers.get(0);
        Player target1 = Server.connectedPlayers.get(1);
        Player target2 = Server.connectedPlayers.get(2);
        targets.add(target1);
        targets.add(target2);

        Weapon glove = new Weapon();
        glove.setAlternativeEffect(new RocketFistMode());

        glove.getAlternativeEffect().applyEffect(shooter, targets);

       for(Player target : targets) {
           assertEquals(2, target.getPlayerboard().getDamage().size());
       }
    }



    @Test
    public void getTargets() {

        CopyOnWriteArrayList<String> answers = new CopyOnWriteArrayList<>();

        answers.add("sinistra");
        answers.add("si");
        answers.add("player2");
        answers.add("si");
        answers.add("si");
        answers.add("player1");


        TestBot.initAnswers(answers);

        Player shooter = Server.connectedPlayers.get(0);
        Player target1 = Server.connectedPlayers.get(1);
        Player target2 = Server.connectedPlayers.get(2);

        Weapon glove = new Weapon();

        glove.setAlternativeEffect(new RocketFistMode());

        target1.setPosition(Board.getMap().getBlueRoom().getCells().get(1));
        target2.setPosition(Board.getMap().getBlueRoom().getCells().get(2));

        CopyOnWriteArrayList<Player> targets = glove.getAlternativeEffect().getTargets(shooter);
        assertEquals(2, targets.size());

    }

    @Test
    public void getTargetsNoSecondMove(){

        CopyOnWriteArrayList<String> answers = new CopyOnWriteArrayList<>();

        answers.add("sinistra");
        answers.add("si");
        answers.add("player2");
        answers.add("no");


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


    }

    @Test
    public void justMoveTest(){

        CopyOnWriteArrayList<String> answers = new CopyOnWriteArrayList<>();

        answers.add("basso");


        TestBot.initAnswers(answers);

        Player shooter = Server.connectedPlayers.get(0);
        Player target1 = Server.connectedPlayers.get(1);
        Player target2 = Server.connectedPlayers.get(2);

        Weapon glove = new Weapon();

        glove.setAlternativeEffect(new RocketFistMode());

        target1.setPosition(Board.getMap().getBlueRoom().getCells().get(1));
        target2.setPosition(Board.getMap().getBlueRoom().getCells().get(2));

        CopyOnWriteArrayList<Player> targets = glove.getAlternativeEffect().getTargets(shooter);

        assertEquals(Board.getMap().getRedRoom().getCells().get(2), shooter.getPosition());

    }

}