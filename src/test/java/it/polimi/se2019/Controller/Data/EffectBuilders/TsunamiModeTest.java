package it.polimi.se2019.Controller.Data.EffectBuilders;

import it.polimi.se2019.Model.Board;
import it.polimi.se2019.Model.Player;
import it.polimi.se2019.Model.Weapon;
import it.polimi.se2019.Network.Server;
import it.polimi.se2019.Network.TestBot;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.Assert.*;

public class TsunamiModeTest {

    @Before
    public void preparePlayers(){

        ConfigurationTest.createTestConfiguration();

    }

    @Test
    public void applyEffect(){

        Player shooter = Server.connectedPlayers.get(0);

        Weapon tsunami = new Weapon();
        tsunami.setAlternativeEffect(new TsunamiMode());

        CopyOnWriteArrayList<Player> targets = new CopyOnWriteArrayList<>();

        targets.add(Server.connectedPlayers.get(1));
        targets.add(Server.connectedPlayers.get(2));
        targets.add(Server.connectedPlayers.get(3));


        tsunami.getAlternativeEffect().applyEffect(shooter, targets);

        assertEquals(1, Server.connectedPlayers.get(1).getPlayerboard().getDamage().size());
        assertEquals(1, Server.connectedPlayers.get(2).getPlayerboard().getDamage().size());
        assertEquals(1, Server.connectedPlayers.get(3).getPlayerboard().getDamage().size());


    }


    @Test
    public void getTargets(){

        Weapon weapon = new Weapon();
        weapon.setAlternativeEffect(new TsunamiMode());

        Player shooter = Server.connectedPlayers.get(0);
        Player player1 = Server.connectedPlayers.get(1);
        Player player2 = Server.connectedPlayers.get(2);
        Player player3 = Server.connectedPlayers.get(3);
        Player player4 = Server.connectedPlayers.get(4);

        player1.setPosition(Board.getMap().getRedRoom().getCells().get(2));
        player3.setPosition(Board.getMap().getBlueRoom().getCells().get(2));
        player4.setPosition(Board.getMap().getBlueRoom().getCells().get(1));
        player2.setPosition(Board.getMap().getWhiteRoom().getCells().get(0));

        Server.update(shooter, "");

        shooter.getPlayerboard().getAmmoCubes().setRedCubes(3);
        shooter.getPlayerboard().getAmmoCubes().setYellowCubes(3);

        CopyOnWriteArrayList<Player> targets = weapon.getAlternativeEffect().getTargets(shooter);

        assertTrue(targets.contains(player1));
        assertTrue(targets.contains(player3));
        assertTrue(targets.size()==2);

    }

    @Test
    public void hasTargets() {

        Weapon weapon = new Weapon();
        weapon.setAlternativeEffect(new TsunamiMode());
        boolean result = weapon.getAlternativeEffect().hasTargets(Server.connectedPlayers.get(0));
        assertEquals(result, false);

        for(int i = 1; i<5; i++){

            Server.connectedPlayers.get(i).setPosition(Board.getMap().getBlueRoom().getCells().get(2));
        }


        result = weapon.getAlternativeEffect().hasTargets(Server.connectedPlayers.get(0));
        assertEquals(result, true);

        for(int i = 1; i<5; i++){

            Server.connectedPlayers.get(i).setPosition(Board.getMap().getBlueRoom().getCells().get(1));
        }

        result = weapon.getAlternativeEffect().hasTargets(Server.connectedPlayers.get(0));
        assertEquals(result, false);


    }
}