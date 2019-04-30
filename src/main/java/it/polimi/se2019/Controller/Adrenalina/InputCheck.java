package it.polimi.se2019.Controller.Adrenalina;

import it.polimi.se2019.Controller.Data.RoomBuilders.Colour;
import it.polimi.se2019.Model.Player;
import it.polimi.se2019.Network.Server;

/**
 * This class analyzes the input strings provided by the user.
 */
public class InputCheck {


    /*
     * ---------- METHODS -----------
     */

    /**
     * This method analyzes the input string typed by the user and determines what kind of standard action
     * must be performed.
     * @param input The input string typed by the user (for example, "shot")
     * @return -1 if the input string is not valid
     *          0 if the player has chosen "shot"
     *          1 if the player has chosen "move"
     *          2 if the player has chosen "pick up"
     */
    public int standardAction(String input){

        return -1;
    }

    /**
     * This method analyzes the input string typed by the user and determines what kind of enhanced action
     * must be performed.
     * @param input The input string typed by the user (for example, "shot")
     * @return -1 if the input string is not valid
     *          0 if the player has chosen "shot"
     *          1 if the player has chosen "move"
     *          2 if the player has chosen "pick up"
     */
    public int enhancedAction(String input){

        return -1;
    }

    /**
     * This method analyzes the input string typed by the user and determines what kind of frenzy action
     * must be performed.
     * @param input The input string typed by the user (for example, "shot")
     * @return -1 if the input string is not valid
     *          0 if the player has chosen "shot"
     *          1 if the player has chosen "move"
     *          2 if the player has chosen "pick up"
     */
    public int frenzyAction(String input){

        return -1;
    }

    /**
     * This method analyzes the input string typed by the user and determines what kind of frenzy enhanced action
     * must be performed.
     * @param input The input string typed by the user (for example, "shot")
     * @return -1 if the input string is not valid
     *          0 if the player has chosen "shot"
     *          1 if the player has chosen "move"
     *          2 if the player has chosen "pick up"
     */
    public int frenzyEnhancedAction(String input){

        return -1;
    }

    public static boolean checkMapInput(String input){
        if (input.equals("1") || input.equals("2") || input.equals("3") || input.equals("4")){
            return true;
        }

        else {
            return false;
        }
    }

    public static boolean directionCheck (String input){

        if(input.equalsIgnoreCase("alto") || input.equalsIgnoreCase("basso") || input.equalsIgnoreCase("sinistra") || input.equalsIgnoreCase("destra"))
        {
            return true;
        }
        return false;
    }

    public static boolean nicknameCheck(String input){

        for(Player player : Server.connectedPlayers){

            if(player.getNickname().equalsIgnoreCase(input)){
                return true;
            }

        }

        return false;
    }

    /*
    Questo metodo controlla se l'utente ha digitato una stringa valida e ritorna l'intero corrispondente al colore.
    Se la stringa è invalida, ritorna -1.
     */
    public static int colourCheck(String input){

        if(input.equalsIgnoreCase("blu")){
            return Colour.BLUE;
        }

        if(input.equalsIgnoreCase("giallo")){
            return Colour.YELLOW;
        }

        if(input.equalsIgnoreCase("rosso")){
            return Colour.RED;
        }

        if(input.equalsIgnoreCase("bianco")){
            return Colour.WHITE;
        }

        if(input.equalsIgnoreCase("viola")){
            return Colour.PURPLE;
        }
        if(input.equalsIgnoreCase("verde")){
            return Colour.GREEN;
        }

        return -1;
    }

}
