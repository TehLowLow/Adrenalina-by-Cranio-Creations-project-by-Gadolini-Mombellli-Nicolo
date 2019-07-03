package it.polimi.se2019.Controller.Adrenalina;

import it.polimi.se2019.Controller.Data.EffectBuilders.ConfigurationTest;
import it.polimi.se2019.Model.Board;
import it.polimi.se2019.Network.Server;
import it.polimi.se2019.Network.TestBot;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.Assert.*;

public class MatchTest {

    @Before
    public void prepareEnvironment() {

        ConfigurationTest.createTestConfiguration();

    }

    @Test
    public void chooseMap() {
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
    }

    @Test
    public void chooseFirstPlayer() {
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