package it.polimi.se2019.Controller.Data.EffectBuilders;

import it.polimi.se2019.Model.Board;
import it.polimi.se2019.Model.Weapon;
import it.polimi.se2019.Network.Server;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MachineGunEffectTest {

    @Before
    public void preparePlayers(){

        ConfigurationTest.createTestConfiguration();

    }

    @Test
    public void hasTargets() {


        Server.connectedPlayers.get(1).setPosition(Board.getMap().getWhiteRoom().getCells().get(1));
        Weapon weapon = new Weapon();
        weapon.setBaseEffect(new MachineGunEffect());
        boolean result = weapon.getBaseEffect().hasTargets(Server.connectedPlayers.get(0));
        assertEquals(result, true);

        for(int i = 1; i<5; i++){

            Server.connectedPlayers.get(i).setPosition(Board.getMap().getYellowRoom().getCells().get(1));
        }

        result = weapon.getBaseEffect().hasTargets(Server.connectedPlayers.get(0));
        assertEquals(result, false);

    }


}
