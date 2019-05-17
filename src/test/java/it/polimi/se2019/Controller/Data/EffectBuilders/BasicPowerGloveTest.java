package it.polimi.se2019.Controller.Data.EffectBuilders;

import it.polimi.se2019.Model.Board;
import it.polimi.se2019.Model.Player;
import it.polimi.se2019.Model.Weapon;
import it.polimi.se2019.Network.Server;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.Assert.*;

public class BasicPowerGloveTest {

    @Before
    public void preparePlayers(){

        ConfigurationTest.createTestConfiguration();

    }

    @Test
    public void applyEffectTest() {

        Player user = Server.connectedPlayers.get(0);
        Player target = Server.connectedPlayers.get(1);
        CopyOnWriteArrayList<Player> targets = new CopyOnWriteArrayList<>();
        targets.add(target);


        Weapon powerglove = new Weapon();
        powerglove.setBaseEffect(new BasicPowerGlove());

        powerglove.getBaseEffect().applyEffect(user, targets);

        assertEquals(1, target.getPlayerboard().getDamage().size());
        assertEquals(2, target.getPlayerboard().getMarker().size());

    }


    @Test
    public void getTargets() {
    }

    @Test
    public void hasTargets() {

        Weapon powerglove = new Weapon();
        powerglove.setBaseEffect(new BasicPowerGlove());
        boolean result = powerglove.getBaseEffect().hasTargets(Server.connectedPlayers.get(0));
        assertEquals(result, false);

        Server.connectedPlayers.get(1).setPosition(Board.getMap().getBlueRoom().getCells().get(2));
        result = powerglove.getBaseEffect().hasTargets(Server.connectedPlayers.get(0));
        assertEquals(result, true);

    }
}