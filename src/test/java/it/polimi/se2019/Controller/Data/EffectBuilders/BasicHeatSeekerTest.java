package it.polimi.se2019.Controller.Data.EffectBuilders;

import it.polimi.se2019.Model.Board;
import it.polimi.se2019.Model.Weapon;
import it.polimi.se2019.Network.Server;
import org.junit.Test;

import static org.junit.Assert.*;

public class BasicHeatSeekerTest {

    @Test
    public void hasTargets() {

        ConfigurationTest.createTestConfiguration();
        Weapon heatseeker = new Weapon();
        heatseeker.setBaseEffect(new BasicHeatSeeker());
        boolean result = heatseeker.getBaseEffect().hasTargets(Server.connectedPlayers.get(0));
        assertEquals(result, false);

        Server.connectedPlayers.get(2).setPosition(Board.getMap().getWhiteRoom().getCells().get(0));
        result = heatseeker.getBaseEffect().hasTargets(Server.connectedPlayers.get(0));
        assertTrue(result);

    }
}