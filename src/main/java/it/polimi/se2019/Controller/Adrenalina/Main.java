package it.polimi.se2019.Controller.Adrenalina;
import it.polimi.se2019.Controller.Setup.*;


public class Main {

    public static void main(String[] args) {

        Match newMatch = new Match();

        BoardSetup boardSetup = new BoardSetup();
        newMatch.setBoard(boardSetup.build());



    }

}
