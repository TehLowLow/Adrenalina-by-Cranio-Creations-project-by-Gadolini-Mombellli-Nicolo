package it.polimi.se2019.Controller.Data.EffectBuilders;

import it.polimi.se2019.Model.Board;
import it.polimi.se2019.Model.Player;
import it.polimi.se2019.Model.Weapon;
import it.polimi.se2019.Network.Server;
import it.polimi.se2019.Network.TestBot;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.Assert.*;

public class BasicElectroschytheTest {


    @Before
    public void preparePlayers(){

        ConfigurationTest.createTestConfiguration();

    }

    @Test
    public void getTargetsTest(){

        Weapon electro = new Weapon();
        electro.setBaseEffect(new BasicElectroschythe());

        Player user = Server.connectedPlayers.get(0);
        Player target1 = Server.connectedPlayers.get(1);
        Player target2 = Server.connectedPlayers.get(2);
        Player target3 = Server.connectedPlayers.get(3);
        Player target4 = Server.connectedPlayers.get(4);


        target1.setPosition(Board.getMap().getBlueRoom().getCells().get(2));
        target2.setPosition(Board.getMap().getRedRoom().getCells().get(0));

        CopyOnWriteArrayList <Player> targets = electro.getBaseEffect().getTargets(user);

        assertEquals(2, targets.size());
        assertTrue(targets.contains(target3));
        assertTrue(targets.contains(target4));

    }

    @Test
    public void applyEffectTest(){

        Player user = Server.connectedPlayers.get(0);
        Player target = Server.connectedPlayers.get(1);
        CopyOnWriteArrayList <Player> targets = new CopyOnWriteArrayList<>();
        targets.add(target);


        Weapon electroschyte = new Weapon();
        electroschyte.setBaseEffect(new BasicElectroschythe());

        electroschyte.getBaseEffect().applyEffect(user, targets);

        assertEquals(1, target.getPlayerboard().getDamage().size());



    }

    @Test
    public void hasTargetsTest(){

        Weapon electroschyte = new Weapon();
        electroschyte.setBaseEffect(new BasicElectroschythe());
        boolean result = electroschyte.getBaseEffect().hasTargets(Server.connectedPlayers.get(0));
        assertTrue(result);

    }




}