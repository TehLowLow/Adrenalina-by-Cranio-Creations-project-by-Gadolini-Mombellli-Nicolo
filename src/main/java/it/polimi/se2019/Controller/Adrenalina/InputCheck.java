package it.polimi.se2019.Controller.Adrenalina;

import it.polimi.se2019.Controller.Data.RoomBuilders.Colour;
import it.polimi.se2019.Model.Player;
import it.polimi.se2019.Model.Weapon;
import it.polimi.se2019.Network.Server;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

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

    /*
    Questo metodo controlla se l'utente ha digitato un numero valido.
     */
    public static int numberCheck(String input) throws NumberFormatException{

        int result = Integer.parseInt(input);
        return result;
    }


    /*
    serve nella scelta del numero di teschi da mettere nel tracciato colpo mortale
     */

    public static boolean checkSkullsInput(String input){
        if (input.equals("5") || input.equals("6") || input.equals("7") || input.equals("8")){
            return true;
        }

        else {
            return false;
        }
    }


    public static boolean yesOrNo(String input){

        if (input.equalsIgnoreCase("si")){
            return true;
        }

        if(input.equalsIgnoreCase("no")){
            return false;
        }

        else {
            return false;
        }
    }

    public static boolean correctYesNo(String input){

        if(input.equalsIgnoreCase("si") || input.equalsIgnoreCase("no")){
            return false;
        }

        return false;

    }

    public static boolean baseEffectGrenadeLauncher(String input){

        if(input.equalsIgnoreCase("base") || input.equalsIgnoreCase("extra") ){
            return true;
        }

        return false;

    }

    public static boolean availablePlayer(String nickname, ArrayList <Player> players){

        for(Player player : players){

            if(player.getNickname().equalsIgnoreCase(nickname))
                return true;

        }

        return false;


    }

    /*
    SCELTA BERSAGLI PER TURRET TRIPOD (MACHINE GUN)
     */

    public static boolean chooseTurretTripodTarget(String input){

        if (input.equalsIgnoreCase("0") || input.equalsIgnoreCase("1") || input.equalsIgnoreCase("2")){

            return true;

        }

        return false;

    }

    /*
    SCELTA CAMPIONE
     */

    public static boolean chooseChampion(String input, CopyOnWriteArrayList<String> champions){

        for (String champion:champions){

            if (input.equalsIgnoreCase(champion)){
                return true;
            }

        }

        return false;

    }



    /*
    SCELTA ARMA
     */

    public static boolean correctWeapon(String input, ArrayList<Weapon> weapons){

        for (Weapon weapon:weapons){

            if (input.equalsIgnoreCase(weapon.getName())){

                return true;

            }

        }

        return false;

    }


    /*
    SCELTA EFFETTO BASE O ALTERNATIVO
     */

    public static boolean correctBasicOrAlternative(String input){

        if (input.equalsIgnoreCase("base") || input.equalsIgnoreCase("alternativo")){

            return true;
        }

        return false;
    }

    public static boolean correctAction(String action){

        if(action.equalsIgnoreCase("muovi") || action.equalsIgnoreCase("spara") || action.equalsIgnoreCase("raccogli")){
            return true;
        }

        return false;

    }

    public static boolean correctPowerUp(String powerup){

        if(powerup.equalsIgnoreCase("Granata venom") || powerup.equalsIgnoreCase("Mirino") ||powerup.equalsIgnoreCase("Raggio cinetico") || powerup.equalsIgnoreCase("Teletrasporto")){
            return true;
        }

        return false;

    }

    /*
    ENHANCED SHOT SCELTA MOVIMENTO
     */

    public static boolean correctMoveEnhancedShot(String input){

        if (input.equalsIgnoreCase("stop") || input.equalsIgnoreCase("su") || input.equalsIgnoreCase("giu") || input.equalsIgnoreCase("destra") || input.equalsIgnoreCase("sinistra")){

            return true;

        }

        return false;

    }

}
