package it.polimi.se2019.Model;

/**
 The mortal blow is a track placed on the main board that represents the kills of the game. It can be filled with
 skulls and tokens.
 When all the skull/token are placed on this track, FinalFrenzy mode starts.
 */

public class MortalBlow {


    /*
    *------------------------FIELDS-----------------------------
    * Here there are the possible way to fill the mortal blow
     */

    /**
     * If true, this MortalBlow is still a skull.
     */
    private boolean skull;

    /**
     * If true, this MortalBlow represents an overkill.
     */
    private boolean overkill;

    /**
     * Player who did the MortalBlow.
     */
    private Player killer;


    /*
    -------------------------METHODS----------------------------
     */





    /*
    -------------------------GETTERS----------------------------
     */


    /**
     * Getter of the killer field.
     * @return Player that did the MortalBlow.
     */
    public Player getKiller(){ return killer; }

    /**
     * Checks if the MortalBlow objects still represents a Skull.
     * @return true if the MortalBlow is still a Skull.
     */
    public boolean isSkull() {
        return skull;
    }

    /**
     * Checks if the MortalBlow is an overkill.
     * @return true if the MortalBlow is an overkill.
     */
    public boolean isOverkill() {
        return overkill;
    }



    /*
    ------------------------SETTERS------------------------------
     */


    /**
     * Setter for the skull field
     * @param skull must be true if the MortalBlow must be a skull.
     */
    public void setSkull(boolean skull) { this.skull = skull; }

    /**
     * Setter for the overkill field.
     * @param overkill must be true if the MortalBlow represents an overkill.
     */
    public void setOverkill(boolean overkill) { this.overkill = overkill; }

    /**
     * Setter for the killer field.
     * @param killer Author of the MortalBlow.
     */
    public void setKiller(Player killer) {this.killer = killer; }


}
