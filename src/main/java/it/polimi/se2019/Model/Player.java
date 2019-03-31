package it.polimi.se2019.Model;

/**
 * This class is used to represent the players.
 */
public class Player {

    /**
    --------------------FIELDS-----------------------
    Here there is the player's nickname, his position and his score.
    Furthermore there is the playerboard assigned to the player.
     */

    private String nickname;

    private Playerboard playerboard;

    /**
     * This field is true if the player is the first player.
     */
    private boolean firstPlayer;

    private Cell position;

    private int score;

    /**
    -----------------------METHODS------------------------------
     */




    /**
    ----------------------GETTERS------------------------------
     */


    public String getNickname() {return nickname;}

    public Playerboard getPlayerboard() {return playerboard;}

    public Cell getPosition() {return position;}

    public boolean isFirstPlayer(){return firstPlayer;}

    public int getScore(){return score;}




    /**
    ------------------------SETTERS----------------------------
     */


    public void setNickname(String nickname){this.nickname = nickname;}

    public void setPlayerboard(Playerboard playerboard){this.playerboard = playerboard;}

    public void setPosition(Cell position){this.position = position;}

    public void setFirstPlayer(){this.firstPlayer = true;}

    public void setScore(int score){this.score = score;}

}
