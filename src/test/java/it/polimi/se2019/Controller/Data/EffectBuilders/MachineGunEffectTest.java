package it.polimi.se2019.Controller.Data.EffectBuilders;

import it.polimi.se2019.Model.Board;
import it.polimi.se2019.Model.Player;
import it.polimi.se2019.Model.Weapon;
import it.polimi.se2019.Network.Server;
import it.polimi.se2019.Network.TestBot;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.Assert.assertEquals;

public class MachineGunEffectTest {

    @Before
    public void preparePlayers(){

        ConfigurationTest.createTestConfiguration();

    }

    @Test
    public void getTargetsTwoPlayers(){

        CopyOnWriteArrayList<String> answers = new CopyOnWriteArrayList<>();
        answers.add("player2");
        answers.add("si");
        answers.add("player2");
        answers.add("player3");
        TestBot.initAnswers(answers);

        Weapon weapon = new Weapon();
        weapon.setBaseEffect(new MachineGunEffect());
        CopyOnWriteArrayList<Player> result = weapon.getBaseEffect().getTargets(Server.connectedPlayers.get(0));
        assertEquals(result.get(0), Server.connectedPlayers.get(2));
        assertEquals(result.get(1), Server.connectedPlayers.get(3));
        assertEquals(result.size(), 2);
    }


    @Test
    public void getTargetsOneChosenPlayer(){

        /*
        più di uno visibile ma scelgo di colpirne uno
         */

        CopyOnWriteArrayList<String> answers = new CopyOnWriteArrayList<>();
        answers.add("player2");
        answers.add("no");
        TestBot.initAnswers(answers);

        Weapon weapon = new Weapon();
        weapon.setBaseEffect(new MachineGunEffect());
        CopyOnWriteArrayList<Player> result = weapon.getBaseEffect().getTargets(Server.connectedPlayers.get(0));
        assertEquals(result.get(0), Server.connectedPlayers.get(2));
        assertEquals(result.size(), 1);

    }

    @Test
    public void getTargetsOnePossibleTarget(){

        for(int i = 2; i<5; i++){

            Server.connectedPlayers.get(i).setPosition(Board.getMap().getYellowRoom().getCells().get(1));
        }

        CopyOnWriteArrayList<String> answers = new CopyOnWriteArrayList<>();
        answers.add("player1");
        TestBot.initAnswers(answers);


        Weapon weapon = new Weapon();
        weapon.setBaseEffect(new MachineGunEffect());
        CopyOnWriteArrayList<Player> result = weapon.getBaseEffect().getTargets(Server.connectedPlayers.get(0));
        assertEquals(result.get(0), Server.connectedPlayers.get(1));
        assertEquals(result.size(), 1);

    }


    @Test
    public void applyBaseEffectOneTarget(){

        CopyOnWriteArrayList<Player> targets = new CopyOnWriteArrayList<>();
        targets.add(Server.connectedPlayers.get(1));

        Weapon weapon = new Weapon();
        weapon.setBaseEffect(new MachineGunEffect());
        weapon.getBaseEffect().applyEffect(Server.connectedPlayers.get(0), targets);


        assertEquals(Server.connectedPlayers.get(1).getPlayerboard().getDamage().size(), 1);


    }



    @Test
    public void applyBaseEffectTwoTargets(){

        CopyOnWriteArrayList<Player> targets = new CopyOnWriteArrayList<>();
        targets.add(Server.connectedPlayers.get(1));
        targets.add(Server.connectedPlayers.get(2));

        Weapon weapon = new Weapon();
        weapon.setBaseEffect(new MachineGunEffect());
        weapon.getBaseEffect().applyEffect(Server.connectedPlayers.get(0), targets);


        assertEquals(Server.connectedPlayers.get(1).getPlayerboard().getDamage().size(), 1);
        assertEquals(Server.connectedPlayers.get(2).getPlayerboard().getDamage().size(), 1);
    }

    @Test
    public void applyFocusShotOneTarget(){

        CopyOnWriteArrayList<Player> targets = new CopyOnWriteArrayList<>();
        targets.add(Server.connectedPlayers.get(1));

        CopyOnWriteArrayList<String> answers = new CopyOnWriteArrayList<>();
        answers.add("si");
        answers.add("player1");
        TestBot.initAnswers(answers);

        Server.connectedPlayers.get(0).getPlayerboard().getAmmoCubes().setYellowCubes(1);

        Weapon weapon = new Weapon();
        weapon.setBaseEffect(new MachineGunEffect());
        weapon.getBaseEffect().applyEffect(Server.connectedPlayers.get(0), targets);


        assertEquals(Server.connectedPlayers.get(1).getPlayerboard().getDamage().size(), 2);

        assertEquals(Server.connectedPlayers.get(0).getPlayerboard().getAmmoCubes().getYellow(), 0);


    }

    @Test
    public void applyFocusShotTwoTargets(){

        CopyOnWriteArrayList<Player> targets = new CopyOnWriteArrayList<>();
        targets.add(Server.connectedPlayers.get(1));
        targets.add(Server.connectedPlayers.get(2));

        CopyOnWriteArrayList<String> answers = new CopyOnWriteArrayList<>();
        answers.add("si");
        answers.add("player1");
        TestBot.initAnswers(answers);

        Server.connectedPlayers.get(0).getPlayerboard().getAmmoCubes().setYellowCubes(1);

        Weapon weapon = new Weapon();
        weapon.setBaseEffect(new MachineGunEffect());
        weapon.getBaseEffect().applyEffect(Server.connectedPlayers.get(0), targets);


        assertEquals(Server.connectedPlayers.get(1).getPlayerboard().getDamage().size(), 2);
        assertEquals(Server.connectedPlayers.get(2).getPlayerboard().getDamage().size(), 1);

        assertEquals(Server.connectedPlayers.get(0).getPlayerboard().getAmmoCubes().getYellow(), 0);


    }

    @Test
    public void applyTurretTripodAfterFocusShotOnlyOneBasicTarget(){


        CopyOnWriteArrayList<Player> targets = new CopyOnWriteArrayList<>();
        targets.add(Server.connectedPlayers.get(1));

        CopyOnWriteArrayList<String> answers = new CopyOnWriteArrayList<>();
        answers.add("si");
        answers.add("player1");
        answers.add("si");
        answers.add("player2");
        TestBot.initAnswers(answers);

        Server.connectedPlayers.get(0).getPlayerboard().getAmmoCubes().setYellowCubes(1);
        Server.connectedPlayers.get(0).getPlayerboard().getAmmoCubes().setBlueCubes(1);

        Weapon weapon = new Weapon();
        weapon.setBaseEffect(new MachineGunEffect());
        weapon.getBaseEffect().applyEffect(Server.connectedPlayers.get(0), targets);


        assertEquals(Server.connectedPlayers.get(1).getPlayerboard().getDamage().size(), 2);
        assertEquals(Server.connectedPlayers.get(2).getPlayerboard().getDamage().size(), 1);

        assertEquals(Server.connectedPlayers.get(0).getPlayerboard().getAmmoCubes().getYellow(), 0);
        assertEquals(Server.connectedPlayers.get(0).getPlayerboard().getAmmoCubes().getBlue(), 0);


    }

    @Test
    public void applyTurretTripodAfterFocusShotOneBasicTargetNoOtherTargets(){

        for(int i = 2; i<5; i++){

            Server.connectedPlayers.get(i).setPosition(Board.getMap().getYellowRoom().getCells().get(1));
        }

        CopyOnWriteArrayList<Player> targets = new CopyOnWriteArrayList<>();
        targets.add(Server.connectedPlayers.get(1));

        CopyOnWriteArrayList<String> answers = new CopyOnWriteArrayList<>();
        answers.add("si");
        answers.add("player1");
        TestBot.initAnswers(answers);

        Server.connectedPlayers.get(0).getPlayerboard().getAmmoCubes().setYellowCubes(1);
        Server.connectedPlayers.get(0).getPlayerboard().getAmmoCubes().setBlueCubes(1);

        Weapon weapon = new Weapon();
        weapon.setBaseEffect(new MachineGunEffect());
        weapon.getBaseEffect().applyEffect(Server.connectedPlayers.get(0), targets);


        assertEquals(Server.connectedPlayers.get(1).getPlayerboard().getDamage().size(), 2);

        assertEquals(Server.connectedPlayers.get(0).getPlayerboard().getAmmoCubes().getYellow(), 0);
        assertEquals(Server.connectedPlayers.get(0).getPlayerboard().getAmmoCubes().getBlue(), 1);

    }

    @Test
    public void applyTurretTripodAfterFocusShotTwoBasicTargets(){

        CopyOnWriteArrayList<Player> targets = new CopyOnWriteArrayList<>();
        targets.add(Server.connectedPlayers.get(1));
        targets.add(Server.connectedPlayers.get(2));

        CopyOnWriteArrayList<String> answers = new CopyOnWriteArrayList<>();
        answers.add("si");
        answers.add("player1");
        answers.add("si");
        answers.add("2");
        answers.add("player2");
        answers.add("player3");

        TestBot.initAnswers(answers);

        Server.connectedPlayers.get(0).getPlayerboard().getAmmoCubes().setYellowCubes(1);
        Server.connectedPlayers.get(0).getPlayerboard().getAmmoCubes().setBlueCubes(1);

        Weapon weapon = new Weapon();
        weapon.setBaseEffect(new MachineGunEffect());
        weapon.getBaseEffect().applyEffect(Server.connectedPlayers.get(0), targets);


        assertEquals(Server.connectedPlayers.get(1).getPlayerboard().getDamage().size(), 2);
        assertEquals(Server.connectedPlayers.get(2).getPlayerboard().getDamage().size(), 2);
        assertEquals(Server.connectedPlayers.get(3).getPlayerboard().getDamage().size(), 1);


        assertEquals(Server.connectedPlayers.get(0).getPlayerboard().getAmmoCubes().getYellow(), 0);
        assertEquals(Server.connectedPlayers.get(0).getPlayerboard().getAmmoCubes().getBlue(), 0);


    }



    @Test
    public void applyTurretTripodWithoutFocusShotOneBasicTarget(){

        CopyOnWriteArrayList<Player> targets = new CopyOnWriteArrayList<>();
        targets.add(Server.connectedPlayers.get(1));

        CopyOnWriteArrayList<String> answers = new CopyOnWriteArrayList<>();
        answers.add("no");
        answers.add("si");
        answers.add("2");
        answers.add("player1");
        answers.add("player2");
        TestBot.initAnswers(answers);

        Server.connectedPlayers.get(0).getPlayerboard().getAmmoCubes().setBlueCubes(1);
        Server.connectedPlayers.get(0).getPlayerboard().getAmmoCubes().setYellowCubes(1);

        Weapon weapon = new Weapon();
        weapon.setBaseEffect(new MachineGunEffect());
        weapon.getBaseEffect().applyEffect(Server.connectedPlayers.get(0), targets);


        assertEquals(Server.connectedPlayers.get(1).getPlayerboard().getDamage().size(), 2);

        assertEquals(Server.connectedPlayers.get(0).getPlayerboard().getAmmoCubes().getBlue(), 0);
        assertEquals(Server.connectedPlayers.get(0).getPlayerboard().getAmmoCubes().getYellow(), 1);

    }



    @Test
    public void hasTargets() {


        Server.connectedPlayers.get(1).setPosition(Board.getMap().getWhiteRoom().getCells().get(1));
        Weapon weapon = new Weapon();
        weapon.setBaseEffect(new MachineGunEffect());
        boolean result = weapon.getBaseEffect().hasTargets(Server.connectedPlayers.get(0));
        assertEquals(result, true);

        for(int i = 1; i<5; i++){

            Server.connectedPlayers.get(i).setPosition(Board.getMap().getYellowRoom().getCells().get(1));
        }

        result = weapon.getBaseEffect().hasTargets(Server.connectedPlayers.get(0));
        assertEquals(result, false);

    }


}
