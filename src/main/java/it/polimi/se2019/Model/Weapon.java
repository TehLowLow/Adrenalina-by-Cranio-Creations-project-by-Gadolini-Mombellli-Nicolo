package it.polimi.se2019.Model;
import java.util.ArrayList;

/**
 * This class stores all the information related to every weapon card, such as price, reload cost and
 * optional/alternative effects and their related costs.
 */

public class Weapon {

    /*
     *  ---------------------- FIELDS ----------------------
     */

    private String name;
    private Rybamount rechargeCost;
    private Rybamount price;
    private String description;
    private boolean loaded;
    private Effect baseEffect;
    private Effect alternativeEffect;
    private ArrayList <Effect> optionalEffect;

    /*
     *  ---------------------- METHODS ----------------------
     */



    /*
     *  ---------------------- GETTERS
     */


    /**
     * This method reads the name of the card and returns it to the caller
     * @return the name of the weapon
     */
    public String getName(){return name;}


    /**
     * This methods reads the cost of reloading the weapon, and returns it to the caller
     * @return is the cost for reloading
     */
    public Rybamount getRechargeCost(){return rechargeCost;}


    /**
     * Returns the description of a card
     * @return a string containing the description
     */
    public String getDescription(){return description;}


    /**
     * Returns the status of the weapon, loaded or unloaded
     * @return true if the weapon can be used
     */
    public boolean isLoaded(){return loaded;}


    /**
     * Returns the name of the basic effect of the card
     * @return an Effect object containing all the effects
     */
    public Effect getBaseEffect(){return baseEffect;}


    /**
     * Reads the alternative effect and returns it to the caller
     * @return an Effect obj
     */
    public Effect getAlternativeEffect(){return alternativeEffect;}


    /**
     * Returns all the Effect obj that a card owns
     * @return an arraylist of effects
     */
    public ArrayList<Effect> getOptionalEffect(){return optionalEffect;}



    /*
     *  ---------------------- SETTERS
     */


    /**
     * This method sets the name of the object card loading the card name from memory
     * @param wName is the name of the weapon
     */
    public void setName(String wName){}



    /**
     * This method loads the cost of recharging the weapon from file
     * @param rCost is the cost of the reload
     */
    public void setRechargeCost(Rybamount rCost){}



    /**
     * This method reads the description of the weapon from file and sets it
     * @param desc is the description of the card
     */
    public void setDescription(String desc){}



    /**
     * Reads the card and sets the proper basic effect to be used when activating the card
     * @param effc is the name of the basic effect
     */
    public void setBaseEffect(Effect effc){}


    /**
     * Reads the alternative effect of the card and sets it as an usable effect isntead of the basic one
     * @param altEffc is an obj. containing the alternative effect to be used.
     */
    public void setAlternativeEffect(Effect altEffc){}


    /**
     * Sets an array of optional effect based  on the lists of opt effects the card can do.
     * @param optEffc is the ArrayList containing all the opt effects.
     */
    public void setOptionalEffect(ArrayList<Effect> optEffc){}

    /**
     * This method is responsible for reloading the weapon, changing the status of the boolean variable loaded, leaving
     * to the controller the task of  checking if the player can afford the reload and updating the player amount of ammo.
     */

    public void setLoaded(boolean loaded){
        this.loaded = loaded;
    }




/*
 *  TODO: modificato identificatore per reload(), da bool a void.
 *  TODO: spelling check per errori nei commenti e nelle descrizioni dei metodi
 *  TODO: check dell uml, la classe stdboard ha degli errori nelle specifiche dei metodi da correggere
 *  TODO: creare constructor per Stdboard
 */



}
