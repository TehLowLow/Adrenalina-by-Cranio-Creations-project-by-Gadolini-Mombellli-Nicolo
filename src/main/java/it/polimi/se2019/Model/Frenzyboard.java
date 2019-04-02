package it.polimi.se2019.Model;

/**
 * This class represents the flipped Playerboard, i.e the one used on the Final Frenzy.
 * It does not have the enhanced loot and shoot operations, and the first blood token.
 */
public class Frenzyboard extends Playerboard {

    /*
     * ---------------------- FIELDS ----------------------
     */

    /**
     * Represents the value of the Playerboard.
     */
    private int boardValue;

    /*
     *  ---------------------- GETTERS
     */

    /**
     * Returns the value of the playerboard during the turn
     * @return the board value
     */
    public int getBoardValue() {
        return boardValue;
    }

    /*
     *  ---------------------- SETTERS
     */

    /**
     * Sets the Playerboard value during the Frenzy Mode
     * @param boardValue is the new frenzy vaue of a player's Board.
     */
    public void setBoardValue(int boardValue) {
        this.boardValue = boardValue;
    }


}
