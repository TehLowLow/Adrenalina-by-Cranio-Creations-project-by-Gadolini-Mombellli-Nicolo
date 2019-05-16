package it.polimi.se2019.Controller.Data.EffectBuilders;

import it.polimi.se2019.Model.Board;
import it.polimi.se2019.Model.Weapon;
import it.polimi.se2019.Network.Server;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BasicFurnaceTest {

    @Before
    public void preparePlayers(){

        ConfigurationTest.createTestConfiguration();

    }

    @Test
    public void applyEffect() {
    }

    @Test
    public void getTargets() {
    }

    @Test
    public void hasTargets() {

        Weapon furnace = new Weapon();
        furnace.setBaseEffect(new BasicFurnace());
        boolean result = furnace.getBaseEffect().hasTargets(Server.connectedPlayers.get(0));
        assertEquals(result, false);

        Server.connectedPlayers.get(1).setPosition(Board.getMap().getRedRoom().getCells().get(0));
        result = furnace.getBaseEffect().hasTargets(Server.connectedPlayers.get(0));
        assertEquals(result, true);

    }
}