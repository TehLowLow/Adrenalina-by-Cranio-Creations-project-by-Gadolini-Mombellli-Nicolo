package it.polimi.se2019.Model;

import java.util.concurrent.CopyOnWriteArrayList;


/**
 * This class represents an effect of a weapon or a power up.
 */

public abstract class Effect {

    /*
     * ---------------------- FIELDS  ----------------------
     * They represent the cost on red/blue/yellow ammo cubes, the name of the effect and its description.
     */

    /**
     * The cost of the effect of the card, either basic, alternative or optional.
     */
    private Rybamount cost;

    /**
     * The name of the specific effect.
     */
    private String name;

    /**
     * The description of the specific effect.
     */
    private String description;

    /*
     * ---------------------- METHODS  ----------------------
     */

    /*
     * ---------------------- GETTERS
     */

    /**
     * @return the cost of the specific effect.
     */
    public Rybamount getCost() {
        return cost;
    }

    /**
     * @return the name of the specific effect.
     */
    public String getName() {
        return name;
    }

    /**
     * @return the description of the specific effect.
     */
    public String getDescription() {
        return description;
    }

    /*
     * ---------------------- SETTERS
     */

    /**
     * Sets the cost of a specified effect.
     *
     * @param cost is the cost of the effect
     */
    public void setCost(Rybamount cost) {
        this.cost = cost;
    }

    /**
     * Sets the name of the effect
     *
     * @param name is the name of the effect
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the description of an effect
     *
     * @param description is the description of the effect
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /*
     * ---------------------- APPLY EFFECT
     * This method calls the algorithm that applies the specific effect.
     */


    /**
     * This is the algorithm of the specific effect. It is an abstract method because it must be defined
     * by the class that implements Effect.
     *
     * @param user    the Player that wants to apply the effect.
     * @param targets the targets of the effect. It can be the user itself.
     */
    public abstract void applyEffect(Player user, CopyOnWriteArrayList<Player> targets);

    /**
     * This returns the possible targets of an effect.
     *
     * @param user the Player thant wants to use the effect.
     * @return an CopyOnWriteArrayList of possible targets.
     */
    public abstract CopyOnWriteArrayList<Player> getTargets(Player user);

    /**
     * @param user is the player that will check for targets.
     * @return true if the player has one or more reachables targets.
     */
    public abstract boolean hasTargets(Player user);


}

