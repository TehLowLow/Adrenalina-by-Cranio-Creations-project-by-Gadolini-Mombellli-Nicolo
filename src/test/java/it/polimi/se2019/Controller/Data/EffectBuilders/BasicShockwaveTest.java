package it.polimi.se2019.Controller.Data.EffectBuilders;

import it.polimi.se2019.Model.Board;
import it.polimi.se2019.Model.Weapon;
import it.polimi.se2019.Network.Server;
import org.junit.Test;

import static org.junit.Assert.*;

public class BasicShockwaveTest {

    @Test
    public void applyEffect() {
    }

    @Test
    public void getTargets() {
    }

    @Test
    public void hasTargets() {

        ConfigurationTest.createTestConfiguration();
        Server.connectedPlayers.get(1).setPosition(Board.getMap().getWhiteRoom().getCells().get(1));
        Weapon powerglove = new Weapon();
        powerglove.setBaseEffect(new BasicPowerGlove());
        boolean result = powerglove.getBaseEffect().hasTargets(Server.connectedPlayers.get(0));
        assertEquals(result, false);

        Server.connectedPlayers.get(1).setPosition(Board.getMap().getBlueRoom().getCells().get(2));
        result = powerglove.getBaseEffect().hasTargets(Server.connectedPlayers.get(0));
        assertEquals(result, true);

    }
}