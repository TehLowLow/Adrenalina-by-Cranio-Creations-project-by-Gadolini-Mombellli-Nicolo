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

public class NanoTracerModeTest {

    @Before
    public void preparePlayers(){

        ConfigurationTest.createTestConfiguration();

    }

    @Test
    public void applyEffectTest(){

        Player user = Server.connectedPlayers.get(0);
        Player target = Server.connectedPlayers.get(1);
        CopyOnWriteArrayList<Player> targets = new CopyOnWriteArrayList<>();
        targets.add(target);


        Weapon nanotracer = new Weapon();
        nanotracer.setAlternativeEffect(new NanoTracerMode());

        nanotracer.getAlternativeEffect().applyEffect(user, targets);

        assertEquals(1, target.getPlayerboard().getDamage().size());

        for(Player hit : Server.connectedPlayers){

            if(hit.getPosition().equals(target.getPosition())){

                if(hit.equals(user)){
                    assertEquals(0, user.getPlayerboard().getMarker().size());
                    continue;
                }

                assertEquals(2, hit.getPlayerboard().getMarker().size());

            }
        }

    }


    @Test
    public void getTargets(){

        CopyOnWriteArrayList<String> answers = new CopyOnWriteArrayList<>();
        answers.add("player2");
        answers.add("player1");
        TestBot.initAnswers(answers);

        Player user = Server.connectedPlayers.get(0);
        Player player1 = Server.connectedPlayers.get(1);
        Player player2 = Server.connectedPlayers.get(2);

        player1.setPosition(Board.getMap().getBlueRoom().getCells().get(2));
        player2.setPosition(Board.getMap().getBlueRoom().getCells().get(1));

        Weapon nanotracer= new Weapon();
        nanotracer.setBaseEffect(new NanoTracerMode());

        CopyOnWriteArrayList <Player> targets = nanotracer.getBaseEffect().getTargets(user);

        assertTrue(!targets.contains(player2));
        assertTrue(targets.contains(player1));
        assertTrue(targets.size()==1);


    }



    public void hasTargets() {


        Weapon weapon = new Weapon();
        weapon.setAlternativeEffect(new NanoTracerMode());
        boolean result = weapon.getAlternativeEffect().hasTargets(Server.connectedPlayers.get(0));
        assertTrue(result);


        for(int i = 1; i<5; i++){

            Server.connectedPlayers.get(i).setPosition(Board.getMap().getBlueRoom().getCells().get(1));
        }

        result = weapon.getAlternativeEffect().hasTargets(Server.connectedPlayers.get(0));

        assertFalse(result);

    }
}