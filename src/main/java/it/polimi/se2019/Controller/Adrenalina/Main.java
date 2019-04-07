package it.polimi.se2019.Controller.Adrenalina;
import it.polimi.se2019.Controller.Setup.*;

/**
 * This class starts the application.
 */
public class Main {

    /**
     * The application starts by instantiating a Match.
     * @param args standard parameter for the main method.
     */
    public static void main(String[] args) {

        Match newMatch = new Match();

        BoardSetup boardSetup = new BoardSetup();
        newMatch.setBoard(boardSetup.build());

    }

}
