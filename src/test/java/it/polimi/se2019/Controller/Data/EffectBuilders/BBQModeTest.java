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

public class BBQModeTest {

    @Before
    public void preparePlayers(){

        ConfigurationTest.createTestConfiguration();

    }

    @Test
    public void applyEffect() {


        Player user = Server.connectedPlayers.get(0);
        Player target = Server.connectedPlayers.get(1);
        Player target2 = Server.connectedPlayers.get(2);
        Player target3 = Server.connectedPlayers.get(3);
        Player target4 = Server.connectedPlayers.get(4);

        target.setPosition(Board.getMap().getBlueRoom().getCells().get(2));
        target3.setPosition(Board.getMap().getBlueRoom().getCells().get(2));
        target2.setPosition(Board.getMap().getBlueRoom().getCells().get(1));
        target4.setPosition(Board.getMap().getBlueRoom().getCells().get(1));


        CopyOnWriteArrayList<Player> targets = new CopyOnWriteArrayList<>();
        targets.add(target);
        targets.add(target2);
        targets.add(target3);
        targets.add(target4);

        Weapon sledgehammer = new Weapon();
        sledgehammer.setAlternativeEffect(new BBQMode());
        sledgehammer.getAlternativeEffect().applyEffect(user, targets);

        assertEquals(2, target.getPlayerboard().getDamage().size());
        assertEquals(2, target3.getPlayerboard().getDamage().size());
        assertEquals(1, target4.getPlayerboard().getDamage().size());
        assertEquals(1, target2.getPlayerboard().getDamage().size());


    }

    @Test
    public void getTargets() {

        CopyOnWriteArrayList <String> answers = new CopyOnWriteArrayList<>();

        answers.add("alto");
        answers.add("sinistra");

        TestBot.initAnswers(answers);

        Player user = Server.connectedPlayers.get(0);

        Player target1 = Server.connectedPlayers.get(1);
        Player target2 = Server.connectedPlayers.get(2);
        Player target3 = Server.connectedPlayers.get(3);
        Player target4 = Server.connectedPlayers.get(4);

        target1.setPosition(Board.getMap().getBlueRoom().getCells().get(2));
        target2.setPosition(Board.getMap().getBlueRoom().getCells().get(2));
        target3.setPosition(Board.getMap().getBlueRoom().getCells().get(1));
        target4.setPosition(Board.getMap().getYellowRoom().getCells().get(1));

        Weapon bbq = new Weapon();
        bbq.setBaseEffect(new BBQMode());


        CopyOnWriteArrayList <Player> targets = bbq.getBaseEffect().getTargets(user);

        assertTrue(targets.contains(target1));
        assertTrue(targets.contains(target2));
        assertTrue(targets.contains(target3));
        assertTrue(!targets.contains(target4));



    }

    @Test
    public void hasTargets() {


        Server.connectedPlayers.get(1).setPosition(Board.getMap().getBlueRoom().getCells().get(2));

        Weapon weapon = new Weapon();
        weapon.setAlternativeEffect(new BBQMode());
        boolean result = weapon.getAlternativeEffect().hasTargets(Server.connectedPlayers.get(0));
        assertEquals(result, true);

        for(int i = 1; i<5; i++){

            Server.connectedPlayers.get(i).setPosition(Board.getMap().getYellowRoom().getCells().get(1));
        }

        result = weapon.getAlternativeEffect().hasTargets(Server.connectedPlayers.get(0));
        //TODO sistemare BBQ effect assertEquals(result, false);

    }

}
