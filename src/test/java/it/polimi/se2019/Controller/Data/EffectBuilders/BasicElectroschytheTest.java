package it.polimi.se2019.Controller.Data.EffectBuilders;

import it.polimi.se2019.Model.Player;
import it.polimi.se2019.Model.Weapon;
import it.polimi.se2019.Network.Server;
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