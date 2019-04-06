package it.polimi.se2019.Controller.Adrenalina;

import it.polimi.se2019.Model.Player;
import it.polimi.se2019.Model.Powerup;
import it.polimi.se2019.View.View;

import java.util.ArrayList;

/**
 * This class represents the player's turn in the match
 */

public class Turn {

    /*
    ------------FIELDS-------------------------
     */

    /**
     * drewPowerUp is used to save the PowerUp that the player has drawn
     */

    private ArrayList<Powerup> drewPowerUp;

    private View view;

    private Action action;

    private Interaction interaction;

    /*
    -----------------METHODS--------------------
     */


    /*
    ------------------GETTERS--------------------
     */

    public ArrayList<Powerup> getDrewPowerUp() {
        return drewPowerUp;
    }

    /*
    -----------------SETTERS-----------------------
     */

    public void setDrewPowerUp(ArrayList<Powerup> drewPowerUp) {
        this.drewPowerUp = drewPowerUp;
    }

    /*
    ----------------OTHER METHODS-------------------
    */


    /**
     *
     * @param time is the max time usable for the turn.
     *             When it's over, the turn is skipped.
     */
    public void timer(int time){}


    /** this method allows the player to play the first turn
     *
     * @param player is the player who has to play the first turn
     */
    public void first(Player player){}


    /**
     * this method allows the player to play a standard turn
     * @param player is the player who has to play the turn
     */
    public void standard(Player player){


    }


    /**
     * allows the player to spawn in the map for the first time
     * @param player is the player that has to spawn
     */

    public void firstSpawn(Player player){}


    /**
     * it allows the player to respawn in the map after his death
     * @param player is the player that has to respawn
     */

    public void respawn(Player player){}


    /**
     * it allows the player to play his FinalFrenzy turn
     * @param player is who has to play the FinalFrenzy turn
     */

    public void frenzy(Player player){}


}