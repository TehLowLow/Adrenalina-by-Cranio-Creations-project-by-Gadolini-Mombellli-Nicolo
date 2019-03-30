package it.polimi.se2019.Model;

/**
 * This class represents the flipped Playerboard, i.e the one used on the Final Frenzy.
 * It does not have the enhanced loot and shoot operations, and the first blood token.
 */
public class Frenzyboard extends Playerboard {

    /**
     * ---------------------- FIELDS ----------------------
     */

    /**
     * Represents the value of the board.
     */

    private int boardValue;

    /**
     *  ---------------------- GETTERS
     */

    public int getBoardValue() {
        return boardValue;
    }

    /**
     *  ---------------------- SETTERS
     */

    public void setBoardValue(int boardValue) {
        this.boardValue = boardValue;
    }


}
