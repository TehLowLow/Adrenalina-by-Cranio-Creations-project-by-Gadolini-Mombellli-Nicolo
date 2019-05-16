package it.polimi.se2019.Controller.Data.EffectBuilders;

import it.polimi.se2019.Model.Weapon;
import it.polimi.se2019.Network.Server;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BasicElectroschytheTest {


    @Before
    public void preparePlayers(){

        ConfigurationTest.createTestConfiguration();

    }

    @Test
    public void hasTargetsTest(){

        Weapon electroschyte = new Weapon();
        electroschyte.setBaseEffect(new BasicElectroschythe());
        boolean result = electroschyte.getBaseEffect().hasTargets(Server.connectedPlayers.get(0));
        assertEquals(result, true);

    }




}