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

public class BasicHellionTest {


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


        Weapon hellion = new Weapon();
        hellion.setBaseEffect(new BasicHellion());

        hellion.getBaseEffect().applyEffect(user, targets);

        assertEquals(1, target.getPlayerboard().getDamage().size());

        for(Player hit : Server.connectedPlayers){

            if(hit.equals(user)){
                continue;
            }

            if(hit.getPosition().equals(target)){

                assertEquals(1, hit.getPlayerboard().getMarker());

            }

        }

    }

    @Test
    public void getTargets() {

        CopyOnWriteArrayList<String> answers = new CopyOnWriteArrayList<>();
        answers.add("player2");
        answers.add("player1");
        TestBot.initAnswers(answers);

        Player user = Server.connectedPlayers.get(0);
        Player player1 = Server.connectedPlayers.get(1);
        Player player2 = Server.connectedPlayers.get(2);

        player1.setPosition(Board.getMap().getBlueRoom().getCells().get(2));
        player2.setPosition(Board.getMap().getBlueRoom().getCells().get(1));

        Weapon hellion = new Weapon();
        hellion.setBaseEffect(new BasicHellion());

        hellion.getBaseEffect().hasTargets(user);
        CopyOnWriteArrayList <Player> targets = hellion.getBaseEffect().getTargets(user);

        assertTrue(!targets.contains(player2));
        assertTrue(targets.contains(player1));
        assertTrue(targets.size()==1);

    }

    @Test
    public void hasTargets() {


        Weapon hellion = new Weapon();
        hellion.setBaseEffect(new BasicHellion());
        boolean result = hellion.getBaseEffect().hasTargets(Server.connectedPlayers.get(0));
        assertEquals(result, true);

        for(int i = 1; i<5; i++) {

            Server.connectedPlayers.get(i).setPosition(Board.getMap().getRedRoom().getCells().get(0));

        }
        result = hellion.getBaseEffect().hasTargets(Server.connectedPlayers.get(0));
        assertEquals(result, false);
    }
}