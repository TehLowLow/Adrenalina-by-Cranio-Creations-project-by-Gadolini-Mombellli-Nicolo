package it.polimi.se2019.Controller.Adrenalina;
import it.polimi.se2019.Controller.Setup.*;
import it.polimi.se2019.View.*;

/**
 * This class starts the application.
 */
public class Main {

    /**
     * The application starts by instantiating a Match.
     * @param args standard parameter for the main method.
     */
    public static void main(String[] args) {

        View view = new View();
        view.message.start();
        Match newMatch = new Match();

        BoardSetup boardSetup = new BoardSetup();
        newMatch.setBoard(boardSetup.build());



        newMatch.play();

    }

}
