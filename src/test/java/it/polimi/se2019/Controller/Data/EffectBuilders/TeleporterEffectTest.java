package it.polimi.se2019.Controller.Data.EffectBuilders;

import it.polimi.se2019.Model.Board;
import it.polimi.se2019.Model.Player;
import it.polimi.se2019.Model.Powerup;
import it.polimi.se2019.Network.Server;
import it.polimi.se2019.Network.TestBot;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.Assert.*;

public class TeleporterEffectTest {

    @Before
    public void preparePlayers() {

        ConfigurationTest.createTestConfiguration();

    }


    @Test
    public void applyEffect() {

        CopyOnWriteArrayList<String> answer = new CopyOnWriteArrayList<>();

        answer.add("8");

        TestBot.initAnswers(answer);

        Player shooter = Server.connectedPlayers.get(0);

        CopyOnWriteArrayList<Player> targets = new CopyOnWriteArrayList<>();
        targets.add(Server.connectedPlayers.get(1));

        Powerup teleport = new Powerup();
        teleport.setEffect(new TeleporterEffect());

        teleport.getEffect().applyEffect(shooter, targets);

        assertEquals(Board.getMap().getYellowRoom().getCells().get(0), shooter.getPosition());


    }

}