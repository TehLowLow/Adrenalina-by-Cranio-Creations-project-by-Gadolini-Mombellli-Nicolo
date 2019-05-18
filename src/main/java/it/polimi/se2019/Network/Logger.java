package it.polimi.se2019.Network;


/**
 * This interface contains the generic methods that a login class needs to implement for login purposes.
 */

public interface Logger {


    /**
     * Asks for the player credentials and validates the informations.
     * @return true if a player is allowed into a game or lobby. False in case of double connection or credentials errors
     * such as typos or username already taken
     */
    int logIn();

    /**
     * Is the actual credential verifier, receives the params from the logIn and checks into the database if there are any matches.
     * @return true if a player is confirmed as new player or as a valid reconnecting.
     */
    boolean checkConnections();


}
