package it.polimi.se2019.Model;
import java.util.ArrayList;

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
    private ArrayList<MortalBlow> mortalBlowTrack;


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
    private Map map;


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
    public ArrayList <MortalBlow> getMortalBlowTrack(){
        /**/
        return this.mortalBlowTrack;
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
    public Map getMap(){
        return this.map;
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


    /*
     *  ---------------------- SETTERS
     */

    /**
     * Sets a Killshot on the Killshot track when performed by a player.
     * @param mortalBlow This contains all the information about the killer, such as
     *                   its identifier and the overkill flag.
     */
    public void setMortalBlow(MortalBlow mortalBlow){
        /**/
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


    /*
     *  ---------------------- SHUFFLING METHODS
     *  Methods that perform a shuffle of a given deck.
     */

    /**
     * Shuffles the deck of ammo tiles.
     */
    public void shuffleLootDeck(){

    }

    /**
     * Shuffles the deck of power ups.
     */
    public void shufflePowerUpDeck(){

    }



}


