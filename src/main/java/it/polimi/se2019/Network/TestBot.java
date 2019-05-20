package it.polimi.se2019.Network;

import java.util.concurrent.CopyOnWriteArrayList;

public class TestBot {

    private static CopyOnWriteArrayList<String> answers;


    public static void initAnswers(CopyOnWriteArrayList <String> answersToSet){

        answers = answersToSet;

    }

    public static String getAnswer(){

        String answer = answers.get(0);
        answers.remove(answer);
        return answer;

    }


}
