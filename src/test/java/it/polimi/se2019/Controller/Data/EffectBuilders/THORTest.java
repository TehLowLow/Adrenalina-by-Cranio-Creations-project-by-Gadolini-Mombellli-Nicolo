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

public class THORTest {

    @Before
    public void preparePlayers(){

        ConfigurationTest.createTestConfiguration();

    }

    @Test
    public void applyEffectOnlyBasic() {

        CopyOnWriteArrayList<String> answers = new CopyOnWriteArrayList<>();

        answers.add("no");

        TestBot.initAnswers(answers);

        Player user = Server.connectedPlayers.get(0);
        Player player1 = Server.connectedPlayers.get(1);
        Player player2 = Server.connectedPlayers.get(2);
        Player player3 = Server.connectedPlayers.get(3);
        Player player4 = Server.connectedPlayers.get(4);

        user.getPlayerboard().getAmmoCubes().setBlueCubes(1);

        player1.setPosition(Board.getMap().getRedRoom().getCells().get(2));
        player2.setPosition(Board.getMap().getRedRoom().getCells().get(1));
        player3.setPosition(Board.getMap().getWhiteRoom().getCells().get(0));
        player4.setPosition(Board.getMap().getRedRoom().getCells().get(0));

        CopyOnWriteArrayList<Player> targets = new CopyOnWriteArrayList<>();

        targets.add(player1);

        Weapon thor = new Weapon();
        thor.setBaseEffect(new THOR());
        thor.getBaseEffect().applyEffect(user, targets);

        assertEquals(2, player1.getPlayerboard().getDamage().size());

        for(Player player : Server.connectedPlayers){

            if(!player.equals(user) && !player.equals(player1)){

                assertEquals(0, player.getPlayerboard().getDamage().size());

            }
        }


    }

    @Test
    public void applyEffectChainReaction(){

        CopyOnWriteArrayList<String> answers = new CopyOnWriteArrayList<>();

        answers.add("si");
        answers.add("player2");
        answers.add("no");

        TestBot.initAnswers(answers);

        Player user = Server.connectedPlayers.get(0);
        Player player1 = Server.connectedPlayers.get(1);
        Player player2 = Server.connectedPlayers.get(2);
        Player player3 = Server.connectedPlayers.get(3);
        Player player4 = Server.connectedPlayers.get(4);

        user.getPlayerboard().getAmmoCubes().setBlueCubes(1);
        player1.setPosition(Board.getMap().getRedRoom().getCells().get(2));
        player2.setPosition(Board.getMap().getRedRoom().getCells().get(1));
        player3.setPosition(Board.getMap().getWhiteRoom().getCells().get(0));
        player4.setPosition(Board.getMap().getRedRoom().getCells().get(0));

        CopyOnWriteArrayList<Player> targets = new CopyOnWriteArrayList<>();

        targets.add(player1);

        Weapon thor = new Weapon();
        thor.setBaseEffect(new THOR());
        thor.getBaseEffect().applyEffect(user, targets);

        assertEquals(2, player1.getPlayerboard().getDamage().size());
        assertEquals(1, player2.getPlayerboard().getDamage().size());

        for(Player player : Server.connectedPlayers){

            if(!player.equals(user) && !player.equals(player1) && !player.equals(player2)){

                assertEquals(0, player.getPlayerboard().getDamage().size());

            }
        }


    }

    @Test
    public void applyEffectHighVoltage(){

        CopyOnWriteArrayList<String> answers = new CopyOnWriteArrayList<>();

        answers.add("si");
        answers.add("player2");
        answers.add("si");
        answers.add("player3");

        TestBot.initAnswers(answers);

        Player user = Server.connectedPlayers.get(0);
        Player player1 = Server.connectedPlayers.get(1);
        Player player2 = Server.connectedPlayers.get(2);
        Player player3 = Server.connectedPlayers.get(3);
        Player player4 = Server.connectedPlayers.get(4);

        user.getPlayerboard().getAmmoCubes().setBlueCubes(2);
        player1.setPosition(Board.getMap().getRedRoom().getCells().get(2));
        player2.setPosition(Board.getMap().getRedRoom().getCells().get(1));
        player3.setPosition(Board.getMap().getWhiteRoom().getCells().get(0));
        player4.setPosition(Board.getMap().getRedRoom().getCells().get(0));

        CopyOnWriteArrayList<Player> targets = new CopyOnWriteArrayList<>();

        targets.add(player1);

        Weapon thor = new Weapon();
        thor.setBaseEffect(new THOR());
        thor.getBaseEffect().applyEffect(user, targets);

        assertEquals(2, player1.getPlayerboard().getDamage().size());
        assertEquals(1, player2.getPlayerboard().getDamage().size());
        assertEquals(2, player3.getPlayerboard().getDamage().size());

        for(Player player : Server.connectedPlayers){

            if(!player.equals(user) && !player.equals(player1) && !player.equals(player2) && !player.equals(player3)){

                assertEquals(0, player.getPlayerboard().getDamage().size());

            }
        }



    }

    @Test
    public void refuseApplyHighVoltage(){

        CopyOnWriteArrayList<String> answers = new CopyOnWriteArrayList<>();

        answers.add("si");
        answers.add("player2");
        answers.add("no");

        TestBot.initAnswers(answers);

        Player user = Server.connectedPlayers.get(0);
        Player player1 = Server.connectedPlayers.get(1);
        Player player2 = Server.connectedPlayers.get(2);
        Player player3 = Server.connectedPlayers.get(3);
        Player player4 = Server.connectedPlayers.get(4);

        user.getPlayerboard().getAmmoCubes().setBlueCubes(2);
        player1.setPosition(Board.getMap().getRedRoom().getCells().get(2));
        player2.setPosition(Board.getMap().getRedRoom().getCells().get(1));
        player3.setPosition(Board.getMap().getWhiteRoom().getCells().get(0));
        player4.setPosition(Board.getMap().getRedRoom().getCells().get(0));

        CopyOnWriteArrayList<Player> targets = new CopyOnWriteArrayList<>();

        targets.add(player1);

        Weapon thor = new Weapon();
        thor.setBaseEffect(new THOR());
        thor.getBaseEffect().applyEffect(user, targets);

        assertEquals(2, player1.getPlayerboard().getDamage().size());
        assertEquals(1, player2.getPlayerboard().getDamage().size());

        for(Player player : Server.connectedPlayers){

            if(!player.equals(user) && !player.equals(player1) && !player.equals(player2)){

                assertEquals(0, player.getPlayerboard().getDamage().size());

            }
        }


    }

    @Test
    public void getTargets() {

        CopyOnWriteArrayList<String> answers = new CopyOnWriteArrayList<>();

        answers.add("player2");

        TestBot.initAnswers(answers);

        Weapon weapon = new Weapon();
        weapon.setBaseEffect(new THOR());

        Server.connectedPlayers.get(1).setPosition(Board.getMap().getBlueRoom().getCells().get(1));
        Server.connectedPlayers.get(2).setPosition(Board.getMap().getRedRoom().getCells().get(2));
        Server.connectedPlayers.get(3).setPosition(Board.getMap().getWhiteRoom().getCells().get(0));
        Server.connectedPlayers.get(4).setPosition(Board.getMap().getRedRoom().getCells().get(0));


        CopyOnWriteArrayList <Player> target = weapon.getBaseEffect().getTargets(Server.connectedPlayers.get(0));

        assertTrue(target.contains(Server.connectedPlayers.get(2)));
        assertTrue(target.size()==1);



    }


    @Test
    public void hasTargets() {

        Weapon weapon = new Weapon();
        weapon.setBaseEffect(new THOR());
        boolean result = weapon.getBaseEffect().hasTargets(Server.connectedPlayers.get(0));
        assertTrue(result);



        for(int i = 1; i<5; i++){

            Server.connectedPlayers.get(i).setPosition(Board.getMap().getWhiteRoom().getCells().get(1));
        }

        result = weapon.getBaseEffect().hasTargets(Server.connectedPlayers.get(0));

        assertFalse(result);

    }
}