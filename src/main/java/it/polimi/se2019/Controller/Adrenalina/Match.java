package it.polimi.se2019.Controller.Adrenalina;
import it.polimi.se2019.Controller.Setup.MapSetup;
import it.polimi.se2019.Model.*;
import it.polimi.se2019.View.*;

import java.util.ArrayList;
import java.util.Collections;

import it.polimi.se2019.Controller.Adrenalina.InputCheck;

import static it.polimi.se2019.Network.Server.*;


/**
 * This class represents a single match of Adrenalina.
 * Its purpose is to iterate through the players and make them play a turn.
 *
 */
public class Match {


    /*
    * ---------- FIELDS ----------
     */

    /**
     * Turn is a parameter that holds a Turn object.
     */
    private Turn turn;

    /**
     * This is needed to perform various checks.
     */
    private Check check;

    /**
     * ArrayList of players who take part to the match.
     */
    private ArrayList<Player> players;

    /**
     * Board used for the match.
     */
    private Board board;



    /*
    * ---------- METHODS ----------
     */


    /**
     * Constructor for the Match class. It just creates an instance of Turn.
     */
    public Match(){

        this.turn = new Turn();

    }



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

        chooseMap();

    }

    /**
     * Setter for the ArrayList of players that take part at the Match.
     * @param players ArrayList of players.
     */
    public void setPlayers(ArrayList <Player> players){
        this.players = players;
    }

    /**
     * Setter for the board used in the current match.
     * @param board the board used in the current match.
     */
    public void setBoard(Board board){

        this.board = board;

    }

    public void chooseMap(){

        ArrayList<Integer> voti = new ArrayList<>();


        /*
        valore del voto
         */
        int value;

        InputCheck inputCheck = new InputCheck();

        for (Player player:connectedPlayers){

            String chosenMap = updateWithAnswer(player, Message.scegliMappa());

            while (!inputCheck.checkMapInput(chosenMap)){

                update(player, Message.inputError());
                chosenMap = updateWithAnswer(player, Message.scegliMappa());

            }

            value = Integer.parseInt(chosenMap);

            /*
            aumento il contatore alla posizione del voto ricevuto
             */

            voti.set(value, voti.get(value) + 1);




        }

        int max = Collections.max(voti);

        int mapNumber = voti.indexOf(max) + 1;

        MapSetup map1Setup = new MapSetup();
        board.setMap(map1Setup.build(mapNumber));

        for (Player player:connectedPlayers){

            update(player, "La mappa scelta è la numero " + mapNumber);

        }

    }
}
