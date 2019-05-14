package it.polimi.se2019.Controller.Data.EffectBuilders;

import it.polimi.se2019.Model.Board;
import it.polimi.se2019.Model.Weapon;
import it.polimi.se2019.Network.Server;
import org.junit.Test;

import static org.junit.Assert.*;

public class BasicHellionTest {

    @Test
    public void applyEffect() {
    }

    @Test
    public void getTargets() {
    }

    @Test
    public void hasTargets() {

        ConfigurationTest.createTestConfiguration();
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