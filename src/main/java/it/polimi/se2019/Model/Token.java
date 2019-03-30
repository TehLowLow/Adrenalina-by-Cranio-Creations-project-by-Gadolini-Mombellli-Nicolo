package it.polimi.se2019.Model;

public class Token {

    /**
     * This class stores the name of the champion that is linked to all the markers he places during the game.
     */

    private String championName;

    /**
     * This method reads the name applied on a token, to trace its owner
     * @return the name of the owner of the token
     */
    public String getChampionName(){return championName;}
}
