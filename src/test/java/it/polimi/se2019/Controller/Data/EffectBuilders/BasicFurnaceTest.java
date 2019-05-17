package it.polimi.se2019.Controller.Data.EffectBuilders;

import it.polimi.se2019.Model.Board;
import it.polimi.se2019.Model.Player;
import it.polimi.se2019.Model.Weapon;
import it.polimi.se2019.Network.Server;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.Assert.*;

public class BasicFurnaceTest {

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


        Weapon furnace = new Weapon();
        furnace.setBaseEffect(new BasicFurnace());

        furnace.getBaseEffect().applyEffect(user, targets);

        assertEquals(1, target.getPlayerboard().getDamage().size());

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