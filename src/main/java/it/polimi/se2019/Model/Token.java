package it.polimi.se2019.Model;

/**
 * This class stores the name of the champion that is linked to all the markers he places during the game.
 */
public class Token {

    /*
     *  ---------------------- FIELDS ----------------------
     */

    /**
     * Name of a champion
     */
    private String championName;

    /*
     *  ---------------------- METHODS ----------------------
     */


    /*
     *  ---------------------- GETTERS
     */

    /**
     * This method reads the name applied on a token, to trace its owner.
     * @return the name of the owner of the token.
     */
    public String getChampionName(){return championName;}

    /*
     * ---------------------- SETTERS
     */

    /**
     * Sets the name of the champion that owns the token
     * @param championName is the name of the owner
     */
    public void setChampionName(String championName) {
        this.championName = championName;
    }

}
