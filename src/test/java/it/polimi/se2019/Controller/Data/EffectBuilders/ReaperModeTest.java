package it.polimi.se2019.Controller.Data.EffectBuilders;

import com.sun.corba.se.spi.copyobject.CopyobjectDefaults;
import it.polimi.se2019.Model.Board;
import it.polimi.se2019.Model.Player;
import it.polimi.se2019.Model.Weapon;
import it.polimi.se2019.Network.Server;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.Assert.*;

public class ReaperModeTest {


    @Before
    public void preparePlayers() {

        ConfigurationTest.createTestConfiguration();

    }

    @Test
    public void hasTargets() {

        Weapon weapon = new Weapon();
        weapon.setAlternativeEffect(new ReaperMode());
        boolean result = weapon.getAlternativeEffect().hasTargets(Server.connectedPlayers.get(0));
        assertTrue(result);

        for (int i = 1; i < 5; i++) {

            Server.connectedPlayers.get(i).setPosition(Board.getMap().getWhiteRoom().getCells().get(1));
        }

        System.out.println(Board.getMap().getWhiteRoom().getCells().get(1).getName());

        result = weapon.getAlternativeEffect().hasTargets(Server.connectedPlayers.get(0));

        assertFalse(result);


    }

    @Test
    public void applyEffect() {

        Player shooter = Server.connectedPlayers.get(0);

        shooter.getPlayerboard().getAmmoCubes().setRedCubes(1);
        shooter.getPlayerboard().getAmmoCubes().setBlueCubes(1);

        Weapon scythe = new Weapon();
        scythe.setAlternativeEffect(new ReaperMode());

        CopyOnWriteArrayList<Player> targets = new CopyOnWriteArrayList<>();

        targets.add(Server.connectedPlayers.get(1));
        targets.add(Server.connectedPlayers.get(2));
        targets.add(Server.connectedPlayers.get(3));
        targets.add(Server.connectedPlayers.get(4));

        scythe.getAlternativeEffect().applyEffect(shooter, targets);

        assertEquals(2, Server.connectedPlayers.get(1).getPlayerboard().getDamage().size());
        assertEquals(2, Server.connectedPlayers.get(2).getPlayerboard().getDamage().size());
        assertEquals(2, Server.connectedPlayers.get(3).getPlayerboard().getDamage().size());
        assertEquals(2, Server.connectedPlayers.get(4).getPlayerboard().getDamage().size());
        assertEquals(0, Server.connectedPlayers.get(0).getPlayerboard().getDamage().size());
    }

    @Test
    public void getTargets() {

        Player shooter = Server.connectedPlayers.get(0);

        Weapon scythe = new Weapon();
        scythe.setAlternativeEffect(new ReaperMode());

        CopyOnWriteArrayList<Player> targets;

        targets = scythe.getAlternativeEffect().getTargets(shooter);

        assertEquals(4, targets.size());
    }
}