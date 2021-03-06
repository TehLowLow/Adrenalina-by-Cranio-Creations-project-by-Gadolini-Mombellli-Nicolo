package it.polimi.se2019.Model;

/**
 * This class is used to represent the players.
 */
public class Player {

    /*
    --------------------FIELDS-----------------------
    Here there is the player's nickname, his position and his score.
    Furthermore there is the playerboard assigned to the player.
     */

    /**
     * Every player is identified by their chosen nickname, saved as a String.
     */
    private String nickname;

    /**
     * Every player has a playerboard, represented by this field.
     */
    private Playerboard playerboard;

    /**
     * This field is true if the player is the first player.
     */
    private boolean firstPlayer = false;

    /**
     * Cell where the player currently is.
     */
    private Cell position;

    /**
     * Score of the player.
     */
    private int score;

    /**
     * If it's true, the player is connected to the server and ready to be assigned to a match.
     */

    private boolean connectionAlive;


    /**
     * It describes the type of connection.
     */
    private String connectionTech;


    /**
     * The port on which the network will find the remote player.
     */
    private int PORT;

    /**
     * This is the name of the RMI registry or the IP address of the client who has this player.
     */
    private String address;

    /*
    -----------------------METHODS------------------------------
     */




    /*
    ----------------------GETTERS------------------------------
     */


    /**
     * Getter for the nickname field.
     *
     * @return the nickname of the Player.
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * Getter for the playerboard field.
     *
     * @return Playerboard of the player.
     */
    public Playerboard getPlayerboard() {
        return playerboard;
    }

    /**
     * Getter of the position of the player.
     *
     * @return the Cell where the player currently is.
     */
    public Cell getPosition() {
        return position;
    }

    /**
     * Checks if the player is the First Player.
     *
     * @return true if the player is the First Player.
     */
    public boolean isFirstPlayer() {
        return firstPlayer;
    }

    /**
     * Getter for the score field.
     *
     * @return the score of the player.
     */
    public int getScore() {
        return score;
    }


    /**
     * Getter for the conectionAlive field.
     *
     * @return true if the player is connnected.
     */
    public boolean isConnected() {
        return connectionAlive;
    }


    /**
     *
     * @return the connection used by the player.
     */
    public String getConnectionTech() {
        return connectionTech;
    }

    /**
     *
     * @return the player's local network port.
     */
    public int getPORT() {
        return PORT;
    }

/*
    ------------------------SETTERS----------------------------
     */


    /**
     * Setter for the nickname field.
     *
     * @param nickname Nickname of the Player.
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * Setter for the Playerboard field.
     *
     * @param playerboard the playerbord that must be assigned to the Player.
     */
    public void setPlayerboard(Playerboard playerboard) {
        this.playerboard = playerboard;
    }

    /**
     * Setter for the position field.
     *
     * @param position the Cell where the player currently is.
     */
    public void setPosition(Cell position) {
        this.position = position;
    }


    /**
     * Sets the score of the player.
     *
     * @param score Score that must be set.
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Sets the first player.
     *
     * @param firstPlayer sets the first player boolean
     */
    public void setFirstPlayer(boolean firstPlayer) {
        this.firstPlayer = firstPlayer;
    }

    /**
     * Setter for the connectionAlive field.
     *
     * @param connectionAlive sets the status of the connection
     */
    public void setConnectionAlive(boolean connectionAlive) {
        this.connectionAlive = connectionAlive;
    }

    /**
     * Sets the technology used by the player for connecting to the game.
     * @param connectionTech is the connection used by the player.
     */
    public void setConnectionTech(String connectionTech) {
        this.connectionTech = connectionTech;
    }

    /**
     * Sets the player's pc port
     * @param PORT is the port.
     */
    public void setPORT(int PORT) {
        this.PORT = PORT;
    }

}
