package it.polimi.se2019.Controller.Data.EffectBuilders;

import it.polimi.se2019.Controller.Data.MapBuilders.Map4Builder;
import it.polimi.se2019.Model.Board;
import it.polimi.se2019.Model.Player;
import it.polimi.se2019.Model.Weapon;
import it.polimi.se2019.Network.Server;
import it.polimi.se2019.Network.TestBot;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.Assert.*;

public class PlasmaGunTest {

    @Before
    public void preparePlayers(){

        ConfigurationTest.createTestConfiguration();

    }

    @Test
    public void applyEffectNoMove(){

        CopyOnWriteArrayList <String> answers = new CopyOnWriteArrayList<>();
        answers.add("no");
        answers.add("player1");
        answers.add("no");
        TestBot.initAnswers(answers);

        Player user = Server.connectedPlayers.get(0);
        Player target = Server.connectedPlayers.get(1);

        CopyOnWriteArrayList <Player> targets = new CopyOnWriteArrayList<>();
        targets.add(target);

        Weapon plasma = new Weapon();
        plasma.setBaseEffect(new PlasmaGun());
        plasma.getBaseEffect().applyEffect(user, targets);

        assertEquals(2, Server.connectedPlayers.get(1).getPlayerboard().getDamage().size());

    }

    @Test
    public void applyEffectMove(){

        CopyOnWriteArrayList <String> answers = new CopyOnWriteArrayList<>();
        answers.add("si");
        answers.add("0");
        answers.add("player1");
        TestBot.initAnswers(answers);

        Player user = Server.connectedPlayers.get(0);
        Player target = Server.connectedPlayers.get(1);

        CopyOnWriteArrayList <Player> targets = new CopyOnWriteArrayList<>();
        targets.add(target);

        Weapon plasma = new Weapon();
        plasma.setBaseEffect(new PlasmaGun());
        plasma.getBaseEffect().applyEffect(user, targets);

        assertEquals(2, Server.connectedPlayers.get(1).getPlayerboard().getDamage().size());


    }

    @Test
    public void applyOptionalEffect(){


        CopyOnWriteArrayList <String> answers = new CopyOnWriteArrayList<>();
        answers.add("no");
        answers.add("player1");
        answers.add("si");
        answers.add("no");
        TestBot.initAnswers(answers);

        Player user = Server.connectedPlayers.get(0);
        Player target = Server.connectedPlayers.get(1);

        user.getPlayerboard().getAmmoCubes().setBlueCubes(1);

        CopyOnWriteArrayList <Player> targets = new CopyOnWriteArrayList<>();
        targets.add(target);

        Weapon plasma = new Weapon();
        plasma.setBaseEffect(new PlasmaGun());
        plasma.getBaseEffect().applyEffect(user, targets);

        assertEquals(3, Server.connectedPlayers.get(1).getPlayerboard().getDamage().size());


    }

    @Test
    public void getTargets(){

        CopyOnWriteArrayList<String> answers = new CopyOnWriteArrayList<>();
        answers.add("player2");
        answers.add("player1");
        TestBot.initAnswers(answers);

        Player user = Server.connectedPlayers.get(0);
        Player player1 = Server.connectedPlayers.get(1);
        Player player2 = Server.connectedPlayers.get(2);

        player1.setPosition(Board.getMap().getBlueRoom().getCells().get(2));
        player2.setPosition(Board.getMap().getYellowRoom().getCells().get(1));

        Weapon plasmagun = new Weapon();
        plasmagun.setBaseEffect(new PlasmaGun());

        plasmagun.getBaseEffect().hasTargets(user);
        CopyOnWriteArrayList <Player> targets = plasmagun.getBaseEffect().getTargets(user);

        assertTrue(!targets.contains(player2));
        assertTrue(targets.contains(player1));
        assertTrue(targets.size()==1);

    }

    @Test
    public void hasTargets() {


        ConfigurationTest.createTestConfiguration();
        Server.connectedPlayers.get(1).setPosition(Board.getMap().getWhiteRoom().getCells().get(1));
        Weapon weapon = new Weapon();
        weapon.setBaseEffect(new PlasmaGun());
        boolean result = weapon.getBaseEffect().hasTargets(Server.connectedPlayers.get(0));
        assertEquals(result, true);

        for(int i = 1; i<5; i++){

            Server.connectedPlayers.get(i).setPosition(Board.getMap().getBlueRoom().getCells().get(2));
        }

        result = weapon.getBaseEffect().hasTargets(Server.connectedPlayers.get(0));
        assertEquals(result, true);

        for(int i = 1; i<5; i++){

            Server.connectedPlayers.get(i).setPosition(Board.getMap().getYellowRoom().getCells().get(1));
        }

        result = weapon.getBaseEffect().hasTargets(Server.connectedPlayers.get(0));
        assertEquals(result, true);


    }


}