package it.polimi.se2019.Network;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * this class is used for messaging simulation and automation between server and player during JUnit tests where human interaction is not required.
 */

public class TestBot {

    /**
     * This array contains all the messages needed to interact with the server inside JUnit tests.
     */
    private static CopyOnWriteArrayList<String> answers;

    /**
     * Adds all the answers inside the test into the TestBot answers array.
     * @param answersToSet sets the answers for testbot
     */

    public static void initAnswers(CopyOnWriteArrayList <String> answersToSet){

        answers = answersToSet;

    }

    /**
     * Pops an answer each time a client-server interaction is needed.
     * @return a single answer for each updatewithanswer that is encountered during an automatic test.
     */

    public static String getAnswer(){

        String answer = answers.get(0);
        answers.remove(answer);
        return answer;

    }


}
