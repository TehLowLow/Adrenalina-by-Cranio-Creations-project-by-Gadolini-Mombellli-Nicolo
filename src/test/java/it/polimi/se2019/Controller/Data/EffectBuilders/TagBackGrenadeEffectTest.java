package it.polimi.se2019.Controller.Data.EffectBuilders;

import it.polimi.se2019.Model.Player;
import it.polimi.se2019.Model.Powerup;
import it.polimi.se2019.Network.Server;
import it.polimi.se2019.Network.TestBot;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.Assert.*;

public class TagBackGrenadeEffectTest {

    @Before
    public void preparePlayers(){

        ConfigurationTest.createTestConfiguration();

    }

    @Test
    public void applyEffect() {

        Player shooter = Server.connectedPlayers.get(0);
        Player target1 = Server.connectedPlayers.get(1);
        CopyOnWriteArrayList<Player> targets = new CopyOnWriteArrayList<>();

        Powerup tbgr = new Powerup();
        tbgr.setEffect(new TagBackGrenadeEffect());

        targets.add(target1);

        tbgr.getEffect().applyEffect(shooter, targets);

        assertEquals(1, target1.getPlayerboard().getMarker().size());
    }

}