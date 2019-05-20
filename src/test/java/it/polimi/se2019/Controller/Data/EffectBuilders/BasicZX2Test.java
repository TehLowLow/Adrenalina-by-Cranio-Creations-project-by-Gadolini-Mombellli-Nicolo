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

public class BasicZX2Test {

    @Before
    public void preparePlayers(){

        ConfigurationTest.createTestConfiguration();

    }

    @Test
    public void applyEffect() {


        Player user = Server.connectedPlayers.get(0);
        Player target = Server.connectedPlayers.get(1);
        CopyOnWriteArrayList<Player> targets = new CopyOnWriteArrayList<>();
        targets.add(target);


        Weapon zx2 = new Weapon();
        zx2.setBaseEffect(new BasicZX2());
        zx2.getBaseEffect().applyEffect(user, targets);

        assertEquals(1, target.getPlayerboard().getDamage().size());
        assertEquals(2, target.getPlayerboard().getMarker().size());

    }

    @Test
    public void getTargets() {

        CopyOnWriteArrayList <String> answers = new CopyOnWriteArrayList<>();
        answers.add("player2");
        answers.add("player1");
        TestBot.initAnswers(answers);

        Player user = Server.connectedPlayers.get(0);
        Player player1 = Server.connectedPlayers.get(1);
        Player player2 = Server.connectedPlayers.get(2);

        player1.setPosition(Board.getMap().getBlueRoom().getCells().get(1));
        player2.setPosition(Board.getMap().getYellowRoom().getCells().get(0));

        Weapon zx2= new Weapon();
        zx2.setBaseEffect(new BasicZX2());

        CopyOnWriteArrayList <Player> targets = zx2.getBaseEffect().getTargets(user);

        assertTrue(targets.contains(player1));
        assertTrue(!targets.contains(player2));


    }

    @Test
    public void hasTargets() {



        Server.connectedPlayers.get(1).setPosition(Board.getMap().getWhiteRoom().getCells().get(1));
        Weapon weapon = new Weapon();
        weapon.setBaseEffect(new BasicZX2());
        boolean result = weapon.getBaseEffect().hasTargets(Server.connectedPlayers.get(0));
        assertEquals(result, true);

        for(int i = 1; i<5; i++){

            Server.connectedPlayers.get(i).setPosition(Board.getMap().getWhiteRoom().getCells().get(1));
        }

        result = weapon.getBaseEffect().hasTargets(Server.connectedPlayers.get(0));
        assertEquals(result, false);

    }

}
