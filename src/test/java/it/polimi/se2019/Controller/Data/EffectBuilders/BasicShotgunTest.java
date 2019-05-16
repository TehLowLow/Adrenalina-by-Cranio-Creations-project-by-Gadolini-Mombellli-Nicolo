package it.polimi.se2019.Controller.Data.EffectBuilders;

import it.polimi.se2019.Model.Weapon;
import it.polimi.se2019.Network.Server;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BasicShotgunTest {


    @Before
    public void preparePlayers(){

        ConfigurationTest.createTestConfiguration();

    }


    @Test
    public void hasTargets() {

        Weapon shot = new Weapon();
        shot.setBaseEffect(new BasicShotgun());

        boolean result = shot.getBaseEffect().hasTargets(Server.connectedPlayers.get(0));
        assertTrue(result);
    }
}