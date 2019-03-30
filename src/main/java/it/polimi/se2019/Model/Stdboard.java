package it.polimi.se2019.Model;

public class Stdboard extends Board {

    /**
     * This class represents the standard game board, and tracks if the player can use enhanced actions, and who took
     * the player's First Blood
     */

   private Token firstBlood;
   private int boardPointValue;
   private boolean enhancedLoot;
   private boolean enhancedShoot;

    /**
     * Sets the first blood of a player
     * @param fb is the token of the attacking player who  got the attacked first blood
     */
   public void setFirstBlood(Token fb){this.firstBlood = fb;}

    /**
     * Returns  the token of the player that first attacked this board
     * @return a token containing the attacking player
     */
   public Token getFirstBlood(){return firstBlood;}

    /**
     * Tracks the value of the redeem of a board, and everytime decreases following the red skull marker rules
     * @param val is the integer corresponding to the maximum value of this board to give to the player that kills
     *            this board's owner.
     */
   public void setBoardPointValue(int val){this.boardPointValue = val;}

    /**
     * Return the maximum value of a player board in order to calculate every player's corresponding points when the owner
     * of the board dies
     * @return the poins value of the board.
     */
   public int getBoardPointValue(){return boardPointValue;}

    /**
     * Check to verify if a player has access to his enhanced loot action
     * @return true or false if the player can or cannot use enhanced loot
     */
   public boolean hasEnhancedLoot(){return enhancedLoot;}

    /**
     * Sets the  bVal to true if a player sustained enough damage to be allowed to use the enhanced action
     * @param bVal is the boolean value to be modified if the action is allowed
     */
   public void setEnhancedLoot(boolean bVal){this.enhancedLoot = bVal;}

    /**
     * Check to verify if a player has access to his enhanced shoot action
     * @return true or false if the player can or cannot use enhanced shoot
     */
   public boolean hasEnhancedShoot(){return enhancedShoot;}

    /**
     * Sets the  bVal to true if a player sustained enough damage to be allowed to use the enhanced action
     * @param bVal is the boolean value to be modified if the action is allowed
     */
   public void setEnhancedShoot(boolean bVal){this.enhancedShoot = bVal;}



}
