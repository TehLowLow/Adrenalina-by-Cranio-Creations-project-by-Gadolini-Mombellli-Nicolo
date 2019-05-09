package it.polimi.se2019.Model;
import java.util.ArrayList;
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
     * Represents the Killshot track as an ArrayList of Killshots.
     *
     */
    private static ArrayList<MortalBlow> mortalBlowTrack;


    /**
     * It's the value of the mortalBlowTrack. It's used at the end of the game to assign the points.
     */

    private ArrayList<Integer> mortalBlowTrackValue;


    /**
     * Keeps track of the player that did a double kill.
     */
    private Token doubleKill;


    /**
     * Represents the pile of power ups as an ArrayList.
     */
    private ArrayList <Powerup> powerUpDeck;


    /**
     * Represents the pile of discarded power ups as an ArrayList
     */
    private ArrayList <Powerup> discardedPowerUps;


    /**
     * Represents the pile of ammo tiles as an ArrayList.
     */
    private ArrayList <Loot> lootDeck;


    /**
     * Represents the pile of used ammo tiles as an ArrayList.
     */
    private ArrayList  <Loot> discardedLoot;


    /**
     * Represents the pile of weapons as an ArrayList.
     */
    private ArrayList <Weapon> weaponDeck;

    /**
     * Represents the map.
     */
    private static Map map;


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
     * @return the ArrayList of the Killshot track.
     */
    public static ArrayList <MortalBlow> getMortalBlowTrack(){
        /**/
        return mortalBlowTrack;
    }

    /**
     * Gets the token of the player that did a double kill.
     * @return the Token on the field doubleKill.
     */
    public Token getDoubleKill(){
        return this.doubleKill;
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
     * @return the ArrayList that represents the power up deck.
     */
    public ArrayList <Powerup> getPowerUpDeck(){
        return this.powerUpDeck;
    }


    /**
     * Returns a reference to the ammo tiles deck.
     * @return the ArrayList that represents the ammo tiles deck.
     */
    public ArrayList <Loot> getLootDeck(){
        return this.lootDeck;
    }


    /**
     * Returns a reference to the weapon deck.
     * @return the ArrayList that represents the weapon deck.
     */
    public ArrayList <Weapon> getWeaponDeck(){
        return this.weaponDeck;
    }

    /**
     * Returns a reference to the discarded loot deck.
     * @return the ArrayList that represents the pile of discarded loots.
     */
    public ArrayList <Loot> getDiscardedLoot(){
        return discardedLoot;
    }

    /**
     * Returns a reference to the discarded power ups deck.
     * @return the ArrayList that represents the pile of discarded loots.
     */
    public ArrayList <Powerup> getDiscardedPowerUps(){
        return discardedPowerUps;
    }


    /**
     * Getter for mortalBlowTrack value
     * @return an Arraylist containing the points related to the mortalBlow track
     */

    public ArrayList<Integer> getMortalBlowTrackValue(){
        return mortalBlowTrackValue;
    }

    /*
     *  ---------------------- SETTERS
     */

    /**
     * Setter method for mortal blow track
     * @param mBTrack is the track of the mortal blow, that contains all the information about the killer, such as
     *                   its identifier and the overkill flag.
     */
    public static void setMortalBlowTrack(ArrayList<MortalBlow> mBTrack){
        mortalBlowTrack = mBTrack;
    }


    /**
     * Sets a map on the board.
     * @param map the map that must be set.
     */
    public void setMap(Map map){
        this.map = map;
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
     * @param powerUpDeck reference to the deck that must be
     *                    placed on the game board.
     */
    public void setPowerUpDeck(ArrayList<Powerup> powerUpDeck) {
        this.powerUpDeck = powerUpDeck;
    }


    /**
     * This method sets the deck of ammo tiles.
     * @param lootDeck reference to the Arraylist of ammo tiles.
     */
    public void setLootDeck(ArrayList<Loot> lootDeck) {
        this.lootDeck = lootDeck;
    }

    /**
     * This method sets the deck of discarded ammo tiles.
     * @param discardedLoot reference to the ArrayList of discarded ammo tiles.
     */
    public void setDiscardedLoot(ArrayList <Loot> discardedLoot){

    }

    /**
     * This method sets the deck of discarded ammo tiles.
     * @param discardedPowerUps reference to the ArrayList of discarded ammo tiles.
     */
    public void setDiscardedPowerUps(ArrayList <Powerup> discardedPowerUps){

    }

    /**
     * This method sets the deck of weapons.
     * @param weaponDeck reference to the Arraylist of weapons.
     */
    public void setWeaponDeck(ArrayList <Weapon> weaponDeck){
        this.weaponDeck = weaponDeck;
    }

    public void setMortalBlowTrackValue(ArrayList<Integer> mortalBlowTrackValue) {
        this.mortalBlowTrackValue = mortalBlowTrackValue;
    }

    /*
     *  ---------------------- SHUFFLING METHODS
     *  Methods that perform a shuffle of a given deck.
     */

    /**
     * Shuffles the deck of ammo tiles.
     */
    public void shuffleLootDeck(){

        Collections.shuffle(lootDeck);

    }

    /**
     * Shuffles the deck of power ups.
     */
    public void shufflePowerUpDeck(){

        Collections.shuffle(powerUpDeck);

    }

    /**
     * Shuffles the weapon deck
     */

    public void shuffleWeaponDeck(){
        Collections.shuffle(weaponDeck);
    }



}


