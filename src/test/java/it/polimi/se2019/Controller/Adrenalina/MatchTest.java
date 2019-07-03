package it.polimi.se2019.Controller.Adrenalina;

import it.polimi.se2019.Controller.Data.EffectBuilders.ConfigurationTest;
import it.polimi.se2019.Model.Board;
import it.polimi.se2019.Network.Server;
import it.polimi.se2019.Network.TestBot;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CopyOnWriteArrayList;

import static it.polimi.se2019.Network.Server.connectedPlayers;
import static org.junit.Assert.*;

public class MatchTest {


    @Before
    public void prepareEnvironment() {

        ConfigurationTest.createTestConfiguration();

    }


    @Test
    public void chooseMap() {

        CopyOnWriteArrayList<String> answers = new CopyOnWriteArrayList<>();

        answers.add("1");
        answers.add("2");
        answers.add("2");
        answers.add("3");
        answers.add("4");

        TestBot.initAnswers(answers);

        Match match = new Match();
        match.chooseMap();

        assertEquals((long) 2, (long) Board.getMap().getMapID());


    }

    @Test
    public void chooseSkulls() {

        CopyOnWriteArrayList<String> answers = new CopyOnWriteArrayList<>();
        answers.add("5");
        answers.add("6");
        answers.add("6");
        answers.add("5");
        answers.add("4");
        answers.add("5");

        TestBot.initAnswers(answers);

        Match match = new Match();
        match.chooseSkulls();

        assertEquals(6, Board.getMortalBlowTrack().size());


    }

    @Test
    public void chooseChampion() {


        CopyOnWriteArrayList<String> answers = new CopyOnWriteArrayList<>();

        answers.add("Sprog");
        answers.add("Dozer");
        answers.add("Violetta");
        answers.add("Banshee");
        answers.add(":D-strutt-or3");

        answers.add("Sprog");
        answers.add("Dozer");
        answers.add("Violetta");
        answers.add("Banshee");
        answers.add(":D-strutt-or3");

        TestBot.initAnswers(answers);


        Match match = new Match();
        match.chooseChampion(false);


        assertEquals("Sprog", connectedPlayers.get(0).getPlayerboard().getChampionName());
        assertEquals("Dozer", connectedPlayers.get(1).getPlayerboard().getChampionName());
        assertEquals("Violetta", connectedPlayers.get(2).getPlayerboard().getChampionName());
        assertEquals("Banshee", connectedPlayers.get(3).getPlayerboard().getChampionName());
        assertEquals(":D-strutt-or3", connectedPlayers.get(4).getPlayerboard().getChampionName());


        connectedPlayers.get(0).setFirstPlayer(true);
        connectedPlayers.get(4).setNickname("Terminator");

        match.chooseChampion(true);


        assertEquals("Sprog", connectedPlayers.get(0).getPlayerboard().getChampionName());
        assertEquals("Dozer", connectedPlayers.get(1).getPlayerboard().getChampionName());
        assertEquals("Violetta", connectedPlayers.get(2).getPlayerboard().getChampionName());
        assertEquals("Banshee", connectedPlayers.get(3).getPlayerboard().getChampionName());
        assertEquals(":D-strutt-or3", connectedPlayers.get(4).getPlayerboard().getChampionName());


    }


    @Test
    public void chooseMode() {

        CopyOnWriteArrayList<String> answers = new CopyOnWriteArrayList<>();
        answers.add("terminator");
        answers.add("classica");
        answers.add("terminator");
        answers.add("terminator");

        TestBot.initAnswers(answers);


        boolean result = Match.chooseMode();

        assertFalse(result);


        Server.connectedPlayers.remove(0);

        result = Match.chooseMode();

        assertTrue(result);

    }
}