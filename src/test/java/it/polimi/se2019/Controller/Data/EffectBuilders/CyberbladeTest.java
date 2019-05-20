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

public class CyberbladeTest {

    @Before
    public void preparePlayers(){

        ConfigurationTest.createTestConfiguration();

    }

    @Test
    public void applyEffectJustBasic() {

        CopyOnWriteArrayList <String> answers = new CopyOnWriteArrayList<>();
        answers.add("no");
        answers.add("player1");
        answers.add("no");
        TestBot.initAnswers(answers);

        Player user = Server.connectedPlayers.get(0);



        Weapon cyber = new Weapon();
        cyber.setBaseEffect(new Cyberblade());
        cyber.getBaseEffect().applyEffect(user, null);

        assertEquals(2, Server.connectedPlayers.get(1).getPlayerboard().getDamage().size());



    }

    @Test
    public void applyEffectWithMoveBefore(){

        CopyOnWriteArrayList <String> answers = new CopyOnWriteArrayList<>();
        answers.add("si");
        answers.add("sinistra");
        answers.add("player1");

        TestBot.initAnswers(answers);

        Player user = Server.connectedPlayers.get(0);
        Player target = Server.connectedPlayers.get(1);
        target.setPosition(Board.getMap().getBlueRoom().getCells().get(2));

        Weapon cyber = new Weapon();
        cyber.setBaseEffect(new Cyberblade());
        cyber.getBaseEffect().applyEffect(user, null);

        assertEquals(2, target.getPlayerboard().getDamage().size());
        assertTrue(user.getPosition().equals(target.getPosition()));


    }

    @Test
    public void applyEffectWithMoveAfter(){

        CopyOnWriteArrayList <String> answers = new CopyOnWriteArrayList<>();
        answers.add("no");
        answers.add("player1");
        answers.add("si");
        answers.add("sinistra");

        TestBot.initAnswers(answers);

        Player user = Server.connectedPlayers.get(0);
        Player target = Server.connectedPlayers.get(1);

        Weapon cyber = new Weapon();
        cyber.setBaseEffect(new Cyberblade());
        cyber.getBaseEffect().applyEffect(user, null);

        assertEquals(2, target.getPlayerboard().getDamage().size());
        assertTrue(user.getPosition().equals(Board.getMap().getBlueRoom().getCells().get(2)));

    }

    @Test
    public void applyOptionalWithMoveBefore(){

        CopyOnWriteArrayList <String> answers = new CopyOnWriteArrayList<>();
        answers.add("no");
        answers.add("player2");
        answers.add("si");
        answers.add("sinistra");
        answers.add("si");
        answers.add("player2");
        answers.add("player1");


        TestBot.initAnswers(answers);

        Player user = Server.connectedPlayers.get(0);
        Player target1 = Server.connectedPlayers.get(1);
        Player target2 = Server.connectedPlayers.get(2);

        user.getPlayerboard().getAmmoCubes().setYellowCubes(1);
        target1.setPosition(Board.getMap().getBlueRoom().getCells().get(2));

        Weapon cyber = new Weapon();
        cyber.setBaseEffect(new Cyberblade());
        cyber.getBaseEffect().applyEffect(user, null);

        assertEquals(2, target1.getPlayerboard().getDamage().size());
        assertEquals(2, target2.getPlayerboard().getDamage().size());
        assertTrue(user.getPosition().equals(Board.getMap().getBlueRoom().getCells().get(2)));

    }

    @Test
    public void applyOptionalWithMoveAfter(){

        CopyOnWriteArrayList <String> answers = new CopyOnWriteArrayList<>();
        answers.add("no");
        answers.add("player2");
        answers.add("no");
        answers.add("si");
        answers.add("player2");
        answers.add("player1");
        answers.add("si");
        answers.add("sinistra");


        TestBot.initAnswers(answers);

        Player user = Server.connectedPlayers.get(0);
        Player target1 = Server.connectedPlayers.get(1);
        Player target2 = Server.connectedPlayers.get(2);

        user.getPlayerboard().getAmmoCubes().setYellowCubes(1);
        target1.setPosition(Board.getMap().getBlueRoom().getCells().get(0));

        Weapon cyber = new Weapon();
        cyber.setBaseEffect(new Cyberblade());
        cyber.getBaseEffect().applyEffect(user, null);

        assertEquals(2, target1.getPlayerboard().getDamage().size());
        assertEquals(2, target2.getPlayerboard().getDamage().size());
        assertTrue(user.getPosition().equals(Board.getMap().getBlueRoom().getCells().get(2)));

    }

    @Test
    public void getTargets() {




    }

    @Test
    public void hasTargets() {


        Server.connectedPlayers.get(1).setPosition(Board.getMap().getWhiteRoom().getCells().get(1));
        Weapon weapon = new Weapon();
        weapon.setBaseEffect(new Cyberblade());
        boolean result = weapon.getBaseEffect().hasTargets(Server.connectedPlayers.get(0));
        assertEquals(result, true);

        for(int i = 1; i<5; i++){

            Server.connectedPlayers.get(i).setPosition(Board.getMap().getBlueRoom().getCells().get(2));
        }

        result = weapon.getBaseEffect().hasTargets(Server.connectedPlayers.get(0));
        assertEquals(result, true);

        for(int i = 1; i<5; i++){

            Server.connectedPlayers.get(i).setPosition(Board.getMap().getBlueRoom().getCells().get(1));
        }

        result = weapon.getBaseEffect().hasTargets(Server.connectedPlayers.get(0));
        assertEquals(result, false);

    }
}