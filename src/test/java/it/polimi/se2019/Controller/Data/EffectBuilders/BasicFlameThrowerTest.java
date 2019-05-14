package it.polimi.se2019.Controller.Data.EffectBuilders;

import it.polimi.se2019.Model.Board;
import it.polimi.se2019.Model.Weapon;
import it.polimi.se2019.Network.Server;
import org.junit.Test;

import static org.junit.Assert.*;

public class BasicFlameThrowerTest {

    @Test
    public void hasTargetsTest() {

        ConfigurationTest.createTestConfiguration();
        Weapon ft = new Weapon();
        ft.setBaseEffect(new BasicFlamethrower());
        Server.connectedPlayers.get(1).setPosition(Board.getMap().getBlueRoom().getCells().get(1));
        boolean result = ft.getBaseEffect().hasTargets(Server.connectedPlayers.get(0));

        assertTrue(result);
    }


}
