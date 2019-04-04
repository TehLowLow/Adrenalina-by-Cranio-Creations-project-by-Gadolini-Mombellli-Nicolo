package it.polimi.se2019.Controller.Adrenalina;
import it.polimi.se2019.Model.*;
import java.util.ArrayList;


/**
 * This class represents a single match of Adrenalina.
 * Its purpose is to iterate through the players and make them play a turn.
 *
 */
public class Match {

    private ArrayList<Player> players;


    /**
     * When called, this method starts the match.
     * This methods comprehends three cycles. The first one iterates through the ArrayList of players one time
     * and makes them perform the first turn, which requires particular actions, different from the ones of the
     * standard turn.
     * Then it starts iterating through the players and at every step makes them perform a turn. At the end of
     * every step, the methods for replacing picked up loots and weapons on the map are called. This loop
     * goes on until all the Skulls on the Killshot track have been removed. When this happens, the current loop
     * ends and starts the Final Frenzy Mode loop, which iterates one last time through the ArrayList of Players
     * by making them perform their last Final Frenzy turn.
     * After this final loop, the methods for checking the winner are called, and then the match ends.
     */
    public void play(){

    }

    /**
     * Setter for the ArrayList of players that take part at the Match.
     * @param players ArrayList of players.
     */
    public void setPlayers(ArrayList <Player> players){
        this.players = players;
    }
}
