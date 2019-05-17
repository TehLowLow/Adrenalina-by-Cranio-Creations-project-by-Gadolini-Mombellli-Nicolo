package it.polimi.se2019.Controller.Data.EffectBuilders;

import it.polimi.se2019.Model.Board;
import it.polimi.se2019.Model.Player;
import it.polimi.se2019.Model.Weapon;
import it.polimi.se2019.Network.Server;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.Assert.*;

public class BasicTractorBeamTest {


    @Before
    public void preparePlayers(){

        ConfigurationTest.createTestConfiguration();

    }


    @Test
    public void applyEffectDamageOnlyTest() {

       /* Player user = Server.connectedPlayers.get(0);
        Player target = Server.connectedPlayers.get(1);
        CopyOnWriteArrayList<Player> targets = new CopyOnWriteArrayList<>();
        targets.add(target);


        Weapon tractorbeam = new Weapon();
        tractorbeam.setBaseEffect(new BasicTractorBeam());
        try {
            tractorbeam.getBaseEffect().applyEffect(user, targets);
        }catch(NullPointerException e){
            System.out.println("Input utente non rilevato. Non sposto il bersaglio.");
        }

        assertEquals(1, target.getPlayerboard().getDamage().size());*/

    }

    @Test
    public void getTargets() {

    }

    @Test
    public void hasTargets() {


        Server.connectedPlayers.get(1).setPosition(Board.getMap().getWhiteRoom().getCells().get(1));
        Weapon weapon = new Weapon();
        weapon.setBaseEffect(new BasicTractorBeam());
        boolean result = weapon.getBaseEffect().hasTargets(Server.connectedPlayers.get(0));
        assertEquals(result, true);

        for(int i = 1; i<5; i++){

            Server.connectedPlayers.get(i).setPosition(Board.getMap().getYellowRoom().getCells().get(1));
        }

        result = weapon.getBaseEffect().hasTargets(Server.connectedPlayers.get(0));
        assertEquals(result, true);

    }
}