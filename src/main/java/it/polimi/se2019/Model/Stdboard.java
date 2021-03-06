package it.polimi.se2019.Model;


/**
 * This class represents the standard game board, and tracks if the player can use enhanced actions, and who took
 * the player's First Blood.
 */
public class Stdboard extends Playerboard{

    /*
     *  ---------------------- FIELDS ----------------------
     */

    /**
     * this Token is the first placed on the Playerboard and Token's colour represents the player who has done it.
     */

    private Token firstBlood;

    /**
     * this int represents the value a Playerboard has when his player is killed.
     */

    private int boardPointValue;

    /**
     * this boolean is 1 if the player has enabled enhancedLoot mode, 0 otherwise.
     */

    private boolean enhancedLoot;

    /**
     * this boolean is 1 if the player has enabled enhancedShoot mode, 0 otherwise.
     */
    private boolean enhancedShoot;

    /*
     *  ---------------------- METHODS ----------------------
     */





    /*
     *  ---------------------- GETTERS
     */


    /**
     * Returns  the token of the player that first attacked this board
     * @return a token containing the attacking player
     */
    public Token getFirstBlood(){return firstBlood;}

    /**
     * Returns the maximum value of a player board in order to calculate every player's corresponding points when the owner
     * of the board dies
     * @return the poins value of the board.
     */
    public int getBoardPointValue(){return boardPointValue;}

    /**
     * Check to verify if a player has access to his enhanced loot action
     * @return true or false if the player can or cannot use enhanced loot
     */
    public boolean hasEnhancedLoot(){return enhancedLoot; }

    /**
     * Check to verify if a player has access to his enhanced shoot action
     * @return true or false if the player can or cannot use enhanced shoot
     */
    public boolean hasEnhancedShoot(){return enhancedShoot;}


    /*
     *  ---------------------- SETTERS
     */


    /**
     * Sets the first blood of a player
     * @param fb is the token of the attacking player who  got the attacked first blood
     */
   public void setFirstBlood(Token fb){this.firstBlood = fb;}


    /**
     * Tracks the value of the redeem of a board, and everytime decreases following the red skull marker rules
     * @param val is the integer corresponding to the maximum value of this board to give to the player that kills
     *            this board's owner.
     */
   public void setBoardPointValue(int val){this.boardPointValue = val;}



    /**
     * Sets the  bVal to true if a player sustained enough damage to be allowed to use the enhanced action
     * @param bVal is the boolean value to be modified if the action is allowed
     */
   public void setEnhancedLoot(boolean bVal){this.enhancedLoot = bVal;}


    /**
     * Sets the  bVal to true if a player sustained enough damage to be allowed to use the enhanced action
     * @param bVal is the boolean value to be modified if the action is allowed
     */
   public void setEnhancedShoot(boolean bVal){this.enhancedShoot = bVal;}



}
