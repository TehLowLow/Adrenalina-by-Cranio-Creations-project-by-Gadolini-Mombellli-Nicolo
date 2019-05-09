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

    /**
     * Name of the weapon.
     */
    private String name;

    /**
     * Recharge cost of the weapon. It does not include the price of the weapon.
     */
    private Rybamount rechargeCost;
    /**
     * Price of the weapon.
     */
    private Rybamount price;

    /**
     * If true, the weapon is loaded.
     */
    private boolean loaded;
    /**
     * Base effect of the weapon.
     */
    private Effect baseEffect;
    /**
     * Alternative effect of the weapon. This represents the different fire modes.
     */
    private Effect alternativeEffect;
    /**
     * Optional effects of the weapon.
     */
    private ArrayList<Effect> optionalEffect;

    private Rybamount alternativeEffectCost;

    /*
     *  ---------------------- METHODS ----------------------
     */



    /*
     *  ---------------------- GETTERS
     */


    /**
     * This method reads the name of the card and returns it to the caller
     *
     * @return the name of the weapon
     */
    public String getName() {
        return name;
    }


    /**
     * This method reads the cost of reloading the weapon, and returns it to the caller
     *
     * @return is the cost for reloading
     */
    public Rybamount getRechargeCost() {
        return rechargeCost;
    }


    /**
     * This method reads the price, in terms of rybamount, of the weapon and returns it.
     *
     * @return the price of the weapon
     */

    public Rybamount getPrice() {
        return price;
    }


    /**
     * Returns the status of the weapon, loaded or unloaded
     *
     * @return true if the weapon can be used
     */
    public boolean isLoaded() {
        return loaded;
    }


    /**
     * Returns the name of the basic effect of the card
     *
     * @return an Effect object containing all the effects
     */
    public Effect getBaseEffect() {
        return baseEffect;
    }


    /**
     * Reads the alternative effect and returns it to the caller
     *
     * @return an Effect obj
     */
    public Effect getAlternativeEffect() {
        return alternativeEffect;
    }


    /**
     * Returns all the Effect obj that a card owns
     *
     * @return an arraylist of effects
     */
    public ArrayList<Effect> getOptionalEffect() {
        return optionalEffect;
    }

    public Rybamount getAlternativeEffectCost(){

        return this.alternativeEffectCost;

    }


    /*
     *  ---------------------- SETTERS
     */


    /**
     * This method sets the name of the object card loading the card name from memory
     *
     * @param wName is the name of the weapon
     */
    public void setName(String wName) {

        this.name = wName;

    }


    /**
     * This method loads the cost of recharging the weapon from file
     *
     * @param rCost is the cost of the reload
     */
    public void setRechargeCost(Rybamount rCost) {

        this.rechargeCost = rCost;

    }


    /**
     * This method sets the price of the weapon
     *
     * @param price is the price of the weapon in terms of rybamount
     */
    public void setPrice(Rybamount price) {

        this.price = price;

    }


    /**
     * Reads the card and sets the proper basic effect to be used when activating the card
     *
     * @param effc is the name of the basic effect
     */
    public void setBaseEffect(Effect effc) {

        this.baseEffect = effc;

    }


    /**
     * Reads the alternative effect of the card and sets it as an usable effect isntead of the basic one
     *
     * @param altEffc is an obj. containing the alternative effect to be used.
     */
    public void setAlternativeEffect(Effect altEffc) {

        this.alternativeEffect = altEffc;

    }

    /**
     * This method is responsible for reloading the weapon, changing the status of the boolean variable loaded, leaving
     * to the controller the task of  checking if the player can afford the reload and updating the player amount of ammo.
     */

    public void setLoaded(boolean loaded) {
        this.loaded = loaded;
    }

    public void setAlternativeCost(Rybamount aCost) {

        this.alternativeEffectCost = aCost;

    }

}
