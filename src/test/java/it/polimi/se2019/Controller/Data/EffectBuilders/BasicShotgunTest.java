package it.polimi.se2019.Controller.Data.EffectBuilders;

import it.polimi.se2019.Model.Player;
import it.polimi.se2019.Model.Weapon;
import it.polimi.se2019.Network.Server;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.Assert.*;

public class BasicShotgunTest {


    @Before
    public void preparePlayers(){

        ConfigurationTest.createTestConfiguration();

    }

    @Test
    public void applyEffectDamageOnlyTest() {

        Player user = Server.connectedPlayers.get(0);
        Player target = Server.connectedPlayers.get(1);
        CopyOnWriteArrayList<Player> targets = new CopyOnWriteArrayList<>();
        targets.add(target);


        Weapon shotgun = new Weapon();
        shotgun.setBaseEffect(new BasicShotgun());

        try{
            shotgun.getBaseEffect().applyEffect(user, targets);
        }catch (NullPointerException e){
            System.out.println("Input utente non rilevato. Non verrà spostato il bersaglio.");
        }

        assertEquals(3, target.getPlayerboard().getDamage().size());


    }


    @Test
    public void hasTargets() {

        Weapon shot = new Weapon();
        shot.setBaseEffect(new BasicShotgun());

        boolean result = shot.getBaseEffect().hasTargets(Server.connectedPlayers.get(0));
        assertTrue(result);
    }
}