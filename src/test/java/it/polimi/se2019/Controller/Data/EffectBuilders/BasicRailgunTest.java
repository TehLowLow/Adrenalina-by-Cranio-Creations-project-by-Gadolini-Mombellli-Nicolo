package it.polimi.se2019.Controller.Data.EffectBuilders;

import it.polimi.se2019.Model.Board;
import it.polimi.se2019.Model.Player;
import it.polimi.se2019.Model.Weapon;
import it.polimi.se2019.Network.Server;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.Assert.*;

public class BasicRailgunTest {


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


        Weapon railgun = new Weapon();
        railgun.setBaseEffect(new BasicRailgun());

        railgun.getBaseEffect().applyEffect(user, targets);

        assertEquals(3, target.getPlayerboard().getDamage().size());


    }


    @Test
    public void hasTargets() {

        Weapon railgun = new Weapon();
        railgun.setBaseEffect(new BasicRailgun());
        Server.connectedPlayers.get(1).setPosition(Board.getMap().getWhiteRoom().getCells().get(1));
        boolean result =  railgun.getBaseEffect().hasTargets(Server.connectedPlayers.get(0));
        assertTrue(result);

    }
}