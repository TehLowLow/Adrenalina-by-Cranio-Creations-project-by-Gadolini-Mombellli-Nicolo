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

public class LongBarrelModeTest {

    @Before
    public void preparePlayers(){

        ConfigurationTest.createTestConfiguration();

    }

    @Test
    public void applyEffect(){

        Player user = Server.connectedPlayers.get(0);
        Player target = Server.connectedPlayers.get(1);
        CopyOnWriteArrayList<Player> targets = new CopyOnWriteArrayList<>();
        targets.add(target);


        Weapon longbarrel = new Weapon();
        longbarrel.setAlternativeEffect(new LongBarrelMode());

        longbarrel.getAlternativeEffect().applyEffect(user, targets);

        assertEquals(2, target.getPlayerboard().getDamage().size());

    }

    @Test
    public void getTargets(){

        CopyOnWriteArrayList<String> answers = new CopyOnWriteArrayList<>();
        answers.add("player1");
        answers.add("player2");
        TestBot.initAnswers(answers);

        Player user = Server.connectedPlayers.get(0);
        Player player1 = Server.connectedPlayers.get(1);
        Player player2 = Server.connectedPlayers.get(2);

        player1.setPosition(Board.getMap().getBlueRoom().getCells().get(1));
        player2.setPosition(Board.getMap().getBlueRoom().getCells().get(2));

        Weapon longbarrel= new Weapon();
        longbarrel.setBaseEffect(new LongBarrelMode());

        CopyOnWriteArrayList <Player> targets = longbarrel.getBaseEffect().getTargets(user);

        assertTrue(targets.contains(player2));
        assertTrue(targets.size()==1);


    }


    @Test
    public void hasTargets() {


        Weapon weapon = new Weapon();
        weapon.setAlternativeEffect(new LongBarrelMode());
        boolean result = weapon.getAlternativeEffect().hasTargets(Server.connectedPlayers.get(0));
        assertEquals(result, false);

        for(int i = 1; i<5; i++){

            Server.connectedPlayers.get(i).setPosition(Board.getMap().getBlueRoom().getCells().get(2));
        }

        result = weapon.getAlternativeEffect().hasTargets(Server.connectedPlayers.get(0));
        assertEquals(result, true);

    }
}