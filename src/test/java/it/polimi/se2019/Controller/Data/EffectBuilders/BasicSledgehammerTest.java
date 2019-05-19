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

public class BasicSledgehammerTest {


    @Before
    public void preparePlayers(){

        ConfigurationTest.createTestConfiguration();

    }


    @Test
    public void applyEffectTest() {

        Player user = Server.connectedPlayers.get(0);
        Player target = Server.connectedPlayers.get(1);
        CopyOnWriteArrayList<Player> targets = new CopyOnWriteArrayList<>();
        targets.add(target);


        Weapon sledgehammer = new Weapon();
        sledgehammer.setBaseEffect(new BasicSledgehammer());
        sledgehammer.getBaseEffect().applyEffect(user, targets);

        assertEquals(2, target.getPlayerboard().getDamage().size());

    }


    //Testa che vengano scelti i bersagli nel proprio quadrato
    @Test
    public void getTargets() {

        CopyOnWriteArrayList <String> answers = new CopyOnWriteArrayList<>();
        answers.add("player2");
        TestBot.initAnswers(answers);

        Player user = Server.connectedPlayers.get(0);
        Player player2 = Server.connectedPlayers.get(2);

        Weapon sledgehammer = new Weapon();
        sledgehammer.setBaseEffect(new BasicSledgehammer());


        CopyOnWriteArrayList <Player> targets = sledgehammer.getBaseEffect().getTargets(user);

        assertEquals(player2, targets.get(0));

        answers.add("player3");
        answers.add("player1");

        Player player1 = Server.connectedPlayers.get(1);

        Server.connectedPlayers.get(3).setPosition(Board.getMap().getWhiteRoom().getCells().get(0));
        targets = sledgehammer.getBaseEffect().getTargets(user);
        assertEquals(player1, targets.get(0));

    }

    @Test
    public void hasTargets() {


        Server.connectedPlayers.get(1).setPosition(Board.getMap().getWhiteRoom().getCells().get(1));
        Weapon sledgehammer = new Weapon();
        sledgehammer.setBaseEffect(new BasicSledgehammer());
        boolean result = sledgehammer.getBaseEffect().hasTargets(Server.connectedPlayers.get(0));
        assertEquals(result, true);

        for(int i = 1; i<5; i++){

            Server.connectedPlayers.get(i).setPosition(Board.getMap().getBlueRoom().getCells().get(2));
        }

        result = sledgehammer.getBaseEffect().hasTargets(Server.connectedPlayers.get(0));
        assertEquals(result, false);

    }
}