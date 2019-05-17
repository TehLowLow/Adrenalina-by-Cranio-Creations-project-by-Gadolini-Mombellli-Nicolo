package it.polimi.se2019.Controller.Data.EffectBuilders;

import it.polimi.se2019.Model.Board;
import it.polimi.se2019.Model.Player;
import it.polimi.se2019.Model.Weapon;
import it.polimi.se2019.Network.Server;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.Assert.*;

public class BasicFlameThrowerTest {


    @Before
    public void preparePlayers(){

        ConfigurationTest.createTestConfiguration();

    }

    @Test
    public void applyEffectTest(){

        Player user = Server.connectedPlayers.get(0);
        Player target = Server.connectedPlayers.get(1);
        CopyOnWriteArrayList<Player> targets = new CopyOnWriteArrayList<>();
        targets.add(target);


        Weapon flamethrower = new Weapon();
        flamethrower.setBaseEffect(new BasicFlamethrower());

        flamethrower.getBaseEffect().applyEffect(user, targets);

        assertEquals(1, target.getPlayerboard().getDamage().size());


    }

    @Test
    public void hasTargetsTest() {

        Weapon ft = new Weapon();
        ft.setBaseEffect(new BasicFlamethrower());
        Server.connectedPlayers.get(1).setPosition(Board.getMap().getBlueRoom().getCells().get(2));
        boolean result = ft.getBaseEffect().hasTargets(Server.connectedPlayers.get(0));
        assertTrue(result);
    }


}
