package it.polimi.se2019.Model;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.Collections;

/**
 * Represents the game board.
 */


public class Board implements java.io.Serializable{

    /*
     * ----------------------FIELDS----------------------
     * --------------------------------------------------
     *
     * Each one represents one element on the game board, such as the map, the piles of cards
     * and the Killshot track.
     *
     */


    /**
     * Represents the Killshot track as an CopyOnWriteArrayList of Killshots.
     *
     */
    private static CopyOnWriteArrayList<MortalBlow> mortalBlowTrack;


    /**
     * It's the value of the mortalBlowTrack. It's used at the end of the game to assign the points.
     */

    private static CopyOnWriteArrayList<Integer> mortalBlowTrackValue;


    /**
     * Keeps track of the player that did a double kill.
     */
    private static Token doubleKill;


    /**
     * Represents the pile of power ups as an CopyOnWriteArrayList.
     */
    private static CopyOnWriteArrayList <Powerup> powerUpDeck;


    /**
     * Represents the pile of discarded power ups as an CopyOnWriteArrayList
     */
    private static CopyOnWriteArrayList <Powerup> discardedPowerUps;


    /**
     * Represents the pile of ammo tiles as an CopyOnWriteArrayList.
     */
    private static CopyOnWriteArrayList <Loot> lootDeck;


    /**
     * Represents the pile of used ammo tiles as an CopyOnWriteArrayList.
     */
    private static CopyOnWriteArrayList  <Loot> discardedLoot;


    /**
     * Represents the pile of weapons as an CopyOnWriteArrayList.
     */
    private static CopyOnWriteArrayList <Weapon> weaponDeck;

    /**
     * Represents the map.
     */
    private static Map map;


    private static Cell limbo;

    /*
     * ----------------------METHODS----------------------
     * ----------------------------------------------------
     *
     */

    /*
     * ---------------------- GETTERS
     */

    /**
     * Gets the Killshot track.
     * @return the CopyOnWriteArrayList of the Killshot track.
     */
    public static CopyOnWriteArrayList <MortalBlow> getMortalBlowTrack(){
        /**/
        return mortalBlowTrack;
    }

    /**
     * Gets the token of the player that did a double kill.
     * @return the Token on the field doubleKill.
     */
    public static Token getDoubleKill(){
        return doubleKill;
    }

    /**
     * Gets the map of the board.
     * @return reference to the map.
     */
    public static Map getMap(){
        return map;
    }

    /**
     * Returns a reference to the power up deck.
     * @return the CopyOnWriteArrayList that represents the power up deck.
     */
    public static CopyOnWriteArrayList <Powerup> getPowerUpDeck(){
        return powerUpDeck;
    }


    /**
     * Returns a reference to the ammo tiles deck.
     * @return the CopyOnWriteArrayList that represents the ammo tiles deck.
     */
    public static CopyOnWriteArrayList <Loot> getLootDeck(){
        return lootDeck;
    }


    /**
     * Returns a reference to the weapon deck.
     * @return the CopyOnWriteArrayList that represents the weapon deck.
     */
    public static CopyOnWriteArrayList <Weapon> getWeaponDeck(){
        return weaponDeck;
    }

    /**
     * Returns a reference to the discarded loot deck.
     * @return the CopyOnWriteArrayList that represents the pile of discarded loots.
     */
    public static CopyOnWriteArrayList <Loot> getDiscardedLoot(){
        return discardedLoot;
    }

    /**
     * Returns a reference to the discarded power ups deck.
     * @return the CopyOnWriteArrayList that represents the pile of discarded loots.
     */
    public static CopyOnWriteArrayList <Powerup> getDiscardedPowerUps(){
        return discardedPowerUps;
    }


    /**
     * Returns the limbo cell
     * @return the limbo cell.
     */
    public static Cell getLimbo() {

        return limbo;
    }


    /**
     * Getter for mortalBlowTrack value
     * @return an CopyOnWriteArrayList containing the points related to the mortalBlow track
     */

    public static CopyOnWriteArrayList<Integer> getMortalBlowTrackValue(){
        return mortalBlowTrackValue;
    }

    /*
     *  ---------------------- SETTERS
     */

    /**
     * Sets the limbo cell.
     * @param limbo limbo cell.
     */
    public static void setLimbo(Cell limbo) {

        limbo.setLeftConnection(new Connection());
        limbo.getLeftConnection().setType(Connection.EDGE);

        limbo.setRightConnection(new Connection());
        limbo.getRightConnection().setType(Connection.EDGE);

        limbo.setUpConnection(new Connection());
        limbo.getUpConnection().setType(Connection.EDGE);

        limbo.setDownConnection(new Connection());
        limbo.getDownConnection().setType(Connection.EDGE);

        Board.limbo = limbo;
    }
    /**
     * Setter method for mortal blow track
     * @param mBTrack is the track of the mortal blow, that contains all the information about the killer, such as
     *                   its identifier and the overkill flag.
     */
    public static void setMortalBlowTrack(CopyOnWriteArrayList<MortalBlow> mBTrack){
        mortalBlowTrack = mBTrack;
    }


    /**
     * Sets a map on the board.
     * @param newmap the map that must be set.
     */
    public static void setMap(Map newmap){
        map = newmap;
    }
    /**
     * Sets the token to keep track of the player that did a double kill.
     * @param doubleKillAuthor Token of the author of the double kill.
     */
    public void setDoubleKill(Token doubleKillAuthor) {
        this.doubleKill = doubleKillAuthor;
    }


    /**
     * This methods sets the power up deck.
     * @param powUpDeck reference to the deck that must be
     *                    placed on the game board.
     */
    public static void setPowerUpDeck(CopyOnWriteArrayList<Powerup> powUpDeck) {
        powerUpDeck = powUpDeck;
    }


    /**
     * This method sets the deck of ammo tiles.
     * @param newlootDeck reference to the CopyOnWriteArrayList of ammo tiles.
     */
    public static void setLootDeck(CopyOnWriteArrayList<Loot> newlootDeck) {
        lootDeck = newlootDeck;
    }

    /**
     * This method sets the deck of discarded ammo tiles.
     * @param ndiscardedLoot reference to the CopyOnWriteArrayList of discarded ammo tiles.
     */
    public static void setDiscardedLoot(CopyOnWriteArrayList <Loot> ndiscardedLoot){

        discardedLoot = ndiscardedLoot;

    }

    /**
     * This method sets the deck of discarded ammo tiles.
     * @param newDiscardedPowerUps reference to the CopyOnWriteArrayList of discarded ammo tiles.
     */
    public static void setDiscardedPowerUps(CopyOnWriteArrayList <Powerup> newDiscardedPowerUps){

        discardedPowerUps = newDiscardedPowerUps;

    }

    /**
     * This method sets the deck of weapons.
     * @param wDeck reference to the CopyOnWriteArrayList of weapons.
     */
    public static void setWeaponDeck(CopyOnWriteArrayList <Weapon> wDeck){
        weaponDeck = wDeck;
    }

    public void setMortalBlowTrackValue(CopyOnWriteArrayList<Integer> mortalBlowTrackValue) {
        this.mortalBlowTrackValue = mortalBlowTrackValue;
    }

    /*
     *  ---------------------- SHUFFLING METHODS
     *  Methods that perform a shuffle of a given deck.
     */

    /**
     * Shuffles the deck of ammo tiles.
     */
    public static void shuffleLootDeck(){

        Collections.shuffle(lootDeck);

    }

    /**
     * Shuffles the deck of power ups.
     */
    public static void shufflePowerUpDeck(){

        Collections.shuffle(powerUpDeck);

    }

    /**
     * Shuffles the weapon deck
     */

    public static void shuffleWeaponDeck(){
        Collections.shuffle(weaponDeck);
    }



}


