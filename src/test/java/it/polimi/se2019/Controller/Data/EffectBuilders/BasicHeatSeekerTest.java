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

public class BasicHeatSeekerTest {


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


        Weapon heatseeker = new Weapon();
        heatseeker.setBaseEffect(new BasicHeatSeeker());

        heatseeker.getBaseEffect().applyEffect(user, targets);

        assertEquals(3, target.getPlayerboard().getDamage().size());

    }

    @Test
    public void getTargetsTest(){

        CopyOnWriteArrayList<String> answers = new CopyOnWriteArrayList<>();

        answers.add("player2");
        answers.add("player3");

        Player player3 = Server.connectedPlayers.get(3);
        player3.setPosition(Board.getMap().getYellowRoom().getCells().get(0));

        TestBot.initAnswers(answers);

        Weapon heatseeker = new Weapon();
        heatseeker.setBaseEffect(new BasicHeatSeeker());
        CopyOnWriteArrayList <Player> target = heatseeker.getBaseEffect().getTargets(Server.connectedPlayers.get(0));

        assertTrue(target.size()==1);
        assertTrue(target.contains(player3));

    }

    @Test
    public void hasTargets() {

        Weapon heatseeker = new Weapon();
        heatseeker.setBaseEffect(new BasicHeatSeeker());
        boolean result = heatseeker.getBaseEffect().hasTargets(Server.connectedPlayers.get(0));
        assertFalse(result);

        Server.connectedPlayers.get(2).setPosition(Board.getMap().getWhiteRoom().getCells().get(0));
        result = heatseeker.getBaseEffect().hasTargets(Server.connectedPlayers.get(0));
        assertTrue(result);

    }
}