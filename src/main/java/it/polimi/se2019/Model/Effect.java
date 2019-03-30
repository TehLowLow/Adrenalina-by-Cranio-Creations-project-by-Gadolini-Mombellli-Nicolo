package it.polimi.se2019.Model;

/**
 * This class represents an effect of a weapon or a power up.
 */

public abstract class Effect {

    /**
     * ---------------------- FIELDS  ----------------------
     * They represent the cost on red/blue/yellow ammo cubes, the name of the effect and its description.
     */

    private Rybamount cost;
    private String name;
    private String description;

    /**
     * ---------------------- METHODS  ----------------------
     */

    /**
     * ---------------------- GETTERS
     */
    public Rybamount getCost() {
        return cost;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    /**
     * ---------------------- SETTERS
     */

    public void setCost(Rybamount cost) {
        this.cost = cost;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * ---------------------- APPLY EFFECT
     * This method calls the algorithm that applies the specific effect.
     */


    /**
     * This is the algorithm of the specific effect. It is an abstract method because it must be defined
     * by the class that implements Effect.
     * @param user the Player that wants to apply the effect.
     * @param target the target of the effect. It can be the user itself.
     */
    public abstract void applyEffect(Player user, Player target);

}
