package it.polimi.se2019.Controller.Adrenalina;

import it.polimi.se2019.Controller.Data.EffectBuilders.ConfigurationTest;
import it.polimi.se2019.Controller.Data.EffectBuilders.TagBackGrenadeEffect;
import it.polimi.se2019.Controller.Data.EffectBuilders.TeleporterEffect;
import it.polimi.se2019.Controller.Data.PowerUpBuilder.BlueTagbackGrenadeBuilder;
import it.polimi.se2019.Controller.Data.PowerUpBuilder.BlueTeleporterBuilder;
import it.polimi.se2019.Model.Board;
import it.polimi.se2019.Model.Player;
import it.polimi.se2019.Model.Powerup;
import it.polimi.se2019.Model.Token;
import it.polimi.se2019.Network.Server;
import it.polimi.se2019.Network.TestBot;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.Assert.*;

public class TurnTest {

    @Before
    public void prepareEnvironment() {

        ConfigurationTest.createTestConfiguration();

    }

    @Test
    public void standardNoTerminator() {

        CopyOnWriteArrayList<String> answers = new CopyOnWriteArrayList<>();
        answers.add("muovi");
        answers.add("1");
        answers.add("0");
        answers.add("muovi");
        answers.add("1");
        answers.add("0");
        answers.add("0");

        TestBot.initAnswers(answers);





        Turn t = new Turn();

        Player player = Server.connectedPlayers.get(0);

        BlueTagbackGrenadeBuilder tagbackGrenadeBuilder = new BlueTagbackGrenadeBuilder();
        Powerup tagBack = tagbackGrenadeBuilder.build();
        tagBack.setEffect(new TagBackGrenadeEffect());

        player.getPlayerboard().getPowerups().add(tagBack);

        Player killed = Server.connectedPlayers.get(1);

        for (int i=0; i<12; i++) {

            Token token = new Token();
            token.setChampionName("champion0");
            killed.getPlayerboard().getDamage().add(token);
        }

        t.standard(player, false);

        assertTrue(player.getPosition().equals(Board.getMap().getBlueRoom().getCells().get(0)));
        assertTrue(killed.getPlayerboard().getDamage().size()==0);


    }


    @Test
    public void firstSpawnNoTerminator(){

        CopyOnWriteArrayList<String> answers = new CopyOnWriteArrayList<>();
        answers.add("aaa");
        answers.add("3");
        answers.add("1");
        answers.add("no");
        answers.add("muovi");
        answers.add("1");
        answers.add("0");
        answers.add("no");
        answers.add("muovi");
        answers.add("1");
        answers.add("0");
        answers.add("no");
        TestBot.initAnswers(answers);

        Player player = Server.connectedPlayers.get(0);
        BlueTeleporterBuilder teleporterBuilder = new BlueTeleporterBuilder();
        Powerup teleporter = teleporterBuilder.build();
        teleporter.setEffect(new TeleporterEffect());

        player.getPlayerboard().getPowerups().add(teleporter);
        Turn t = new Turn();
        t.first(player);


        assertFalse(player.getPosition().equals(Board.getLimbo()));


    }

    @Test
    public void frenzyNoTerminator(){

        CopyOnWriteArrayList<String> answers = new CopyOnWriteArrayList<>();
        answers.add("muovi");
        answers.add("1");
        answers.add("0");
        answers.add("muovi");
        answers.add("1");
        answers.add("0");
        answers.add("0");

        TestBot.initAnswers(answers);





        Turn t = new Turn();

        Player player = Server.connectedPlayers.get(0);

        BlueTagbackGrenadeBuilder tagbackGrenadeBuilder = new BlueTagbackGrenadeBuilder();
        Powerup tagBack = tagbackGrenadeBuilder.build();
        tagBack.setEffect(new TagBackGrenadeEffect());

        player.getPlayerboard().getPowerups().add(tagBack);

        Player killed = Server.connectedPlayers.get(1);

        for (int i=0; i<12; i++) {

            Token token = new Token();
            token.setChampionName("champion0");
            killed.getPlayerboard().getDamage().add(token);
        }

        t.frenzy(player,false,false);

        assertTrue(player.getPosition().equals(Board.getMap().getBlueRoom().getCells().get(0)));
        assertTrue(killed.getPlayerboard().getDamage().size()==0);


    }

    @Test
    public void firstTerminatorFirstPlayer(){

        Player user = Server.connectedPlayers.get(0);
        Player terminator = Server.connectedPlayers.get(Server.connectedPlayers.size()-1);
        terminator.setNickname("Terminator");

        user.setFirstPlayer(true);

        CopyOnWriteArrayList<String> answers = new CopyOnWriteArrayList<>();
        answers.add("aaa");
        answers.add("3");
        answers.add("rosso");
        answers.add("1");
        answers.add("no");
        answers.add("muovi");
        answers.add("1");
        answers.add("0");
        answers.add("no");
        answers.add("muovi");
        answers.add("1");
        answers.add("0");
        answers.add("no");
        TestBot.initAnswers(answers);

        Player player = Server.connectedPlayers.get(0);

        BlueTeleporterBuilder teleporterBuilder = new BlueTeleporterBuilder();
        Powerup teleporter = teleporterBuilder.build();
        teleporter.setEffect(new TeleporterEffect());

        player.getPlayerboard().getPowerups().add(teleporter);
        Turn t = new Turn();
        t.firstTerminator(player);


        assertFalse(player.getPosition().equals(Board.getLimbo()));
        assertTrue(terminator.getPosition().equals(Board.getMap().getRedRoom().getCells().get(0)));

    }


    @Test
    public void firstTerminatorSecondPlayer(){

        Player user = Server.connectedPlayers.get(0);
        Player terminator = Server.connectedPlayers.get(Server.connectedPlayers.size()-1);
        terminator.setNickname("Terminator");

        user.setFirstPlayer(false);

        CopyOnWriteArrayList<String> answers = new CopyOnWriteArrayList<>();
        answers.add("aaa");
        answers.add("3");
        answers.add("1");
        answers.add("no");
        answers.add("si");
        answers.add("si");
        answers.add("1");
        answers.add("player1");
        answers.add("muovi");
        answers.add("1");
        answers.add("0");
        answers.add("no");
        answers.add("muovi");
        answers.add("1");
        answers.add("0");
        answers.add("no");
        TestBot.initAnswers(answers);

        Player player = Server.connectedPlayers.get(0);
        BlueTeleporterBuilder teleporterBuilder = new BlueTeleporterBuilder();
        Powerup teleporter = teleporterBuilder.build();
        teleporter.setEffect(new TeleporterEffect());

        player.getPlayerboard().getPowerups().add(teleporter);
        Turn t = new Turn();
        t.firstTerminator(player);


        assertFalse(player.getPosition().equals(Board.getLimbo()));
        assertTrue(terminator.getPosition().equals(Board.getMap().getRedRoom().getCells().get(2)));
        assertEquals(1, Server.connectedPlayers.get(1).getPlayerboard().getDamage().size());



    }
}