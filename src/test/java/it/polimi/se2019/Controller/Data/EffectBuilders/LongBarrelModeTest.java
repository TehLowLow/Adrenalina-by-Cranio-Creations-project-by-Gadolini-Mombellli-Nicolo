package it.polimi.se2019.Controller.Data.EffectBuilders;

import it.polimi.se2019.Model.Board;
import it.polimi.se2019.Model.Weapon;
import it.polimi.se2019.Network.Server;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LongBarrelModeTest {

    @Before
    public void preparePlayers(){

        ConfigurationTest.createTestConfiguration();

    }

    @Test
    public void hasTargets() {


        Weapon weapon = new Weapon();
        weapon.setAlternativeEffect(new LongBarrelMode());
        boolean result = weapon.getAlternativeEffect().hasTargets(Server.connectedPlayers.get(0));
        assertEquals(result, false);

        for(int i = 1; i<5; i++){

            Server.connectedPlayers.get(i).setPosition(Board.getMap().getBlueRoom().getCells().get(2));
        }

        result = weapon.getAlternativeEffect().hasTargets(Server.connectedPlayers.get(0));
        assertEquals(result, true);

    }
}