package it.polimi.se2019.Controller.Data.EffectBuilders;

import it.polimi.se2019.Model.Board;
import it.polimi.se2019.Model.Weapon;
import it.polimi.se2019.Network.Server;
import org.junit.Test;

import static org.junit.Assert.*;

public class BBQModeTest {

    @Test
    public void applyEffect() {
    }

    @Test
    public void getTargets() {
    }

    @Test
    public void hasTargets() {


        ConfigurationTest.createTestConfiguration();
        Server.connectedPlayers.get(1).setPosition(Board.getMap().getBlueRoom().getCells().get(2));

        Weapon weapon = new Weapon();
        weapon.setAlternativeEffect(new BBQMode());
        boolean result = weapon.getAlternativeEffect().hasTargets(Server.connectedPlayers.get(0));
        assertEquals(result, true);

        for(int i = 1; i<5; i++){

            Server.connectedPlayers.get(i).setPosition(Board.getMap().getYellowRoom().getCells().get(1));
        }



        result = weapon.getAlternativeEffect().hasTargets(Server.connectedPlayers.get(0));
        assertEquals(result, false);

    }

}
