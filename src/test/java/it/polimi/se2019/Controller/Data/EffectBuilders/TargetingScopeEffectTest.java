package it.polimi.se2019.Controller.Data.EffectBuilders;

import com.sun.corba.se.spi.copyobject.CopyobjectDefaults;
import it.polimi.se2019.Model.Player;
import it.polimi.se2019.Model.Powerup;
import it.polimi.se2019.Network.Server;
import it.polimi.se2019.Network.TestBot;
import org.junit.Before;
import org.junit.Test;
import sun.security.krb5.Config;

import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.Assert.*;

public class TargetingScopeEffectTest {

    @Before
    public void preparePlayers() {

        ConfigurationTest.createTestConfiguration();
    }


    @Test
    public void applyEffect1() {

        CopyOnWriteArrayList<String> answers = new CopyOnWriteArrayList<>();

        answers.add("giallo");
        answers.add("player1");

        TestBot.initAnswers(answers);


        CopyOnWriteArrayList<Player> targets = new CopyOnWriteArrayList<>();

        Player shooter = Server.connectedPlayers.get(0);
        targets.add(Server.connectedPlayers.get(1));

        Powerup targeting = new Powerup();
        targeting.setEffect(new TargetingScopeEffect());

        shooter.getPlayerboard().getAmmoCubes().setYellowCubes(1);

        targeting.getEffect().applyEffect(shooter, targets);

        assertEquals(1, Server.connectedPlayers.get(1).getPlayerboard().getDamage().size());
        assertEquals(0, shooter.getPlayerboard().getAmmoCubes().getYellow());


    }

    @Test
    public void applyEffect2() {

        CopyOnWriteArrayList<String> answers = new CopyOnWriteArrayList<>();

        answers.add("blu");
        answers.add("player1");

        TestBot.initAnswers(answers);


        CopyOnWriteArrayList<Player> targets = new CopyOnWriteArrayList<>();

        Player shooter = Server.connectedPlayers.get(0);
        targets.add(Server.connectedPlayers.get(1));

        Powerup targeting = new Powerup();
        targeting.setEffect(new TargetingScopeEffect());

        shooter.getPlayerboard().getAmmoCubes().setBlueCubes(1);

        targeting.getEffect().applyEffect(shooter, targets);

        assertEquals(1, Server.connectedPlayers.get(1).getPlayerboard().getDamage().size());
        assertEquals(0, shooter.getPlayerboard().getAmmoCubes().getYellow());


    }

    @Test
    public void applyEffect3() {

        CopyOnWriteArrayList<String> answers = new CopyOnWriteArrayList<>();

        answers.add("rosso");
        answers.add("player1");

        TestBot.initAnswers(answers);


        CopyOnWriteArrayList<Player> targets = new CopyOnWriteArrayList<>();

        Player shooter = Server.connectedPlayers.get(0);
        targets.add(Server.connectedPlayers.get(1));

        Powerup targeting = new Powerup();
        targeting.setEffect(new TargetingScopeEffect());

        shooter.getPlayerboard().getAmmoCubes().setRedCubes(1);

        targeting.getEffect().applyEffect(shooter, targets);

        assertEquals(1, Server.connectedPlayers.get(1).getPlayerboard().getDamage().size());
        assertEquals(0, shooter.getPlayerboard().getAmmoCubes().getYellow());


    }


}
