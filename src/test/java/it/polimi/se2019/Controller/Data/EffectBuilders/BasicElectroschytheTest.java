package it.polimi.se2019.Controller.Data.EffectBuilders;

import it.polimi.se2019.Model.Weapon;
import it.polimi.se2019.Network.Server;
import org.junit.Test;

import static org.junit.Assert.*;

public class BasicElectroschytheTest {


    @Test
    public void hasTargetsTest(){

        ConfigurationTest.createTestConfiguration();
        Weapon electroschyte = new Weapon();
        electroschyte.setBaseEffect(new BasicElectroschythe());
        boolean result = electroschyte.getBaseEffect().hasTargets(Server.connectedPlayers.get(0));
        assertEquals(result, true);

    }


}