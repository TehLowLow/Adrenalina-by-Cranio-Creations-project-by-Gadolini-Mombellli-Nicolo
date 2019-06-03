package it.polimi.se2019.Model;

/**
 * This class represents an ammo tile.
 */

public class Loot {

    /*
     *  ---------------------- FIELDS ----------------------
     */

    /**
     * The amount of red/blue/yellow ammo cubes given by the ammo tile.
     */
   private Rybamount rewardValue;


    /**
     * If an ammo tile contains a power up, this must be set to true.
     */
   private boolean powerUp;

   private String name;

    /*
     * ---------------------- GETTERS
     */

    /**
     * Return the reward that a player is given from a loot card
     * @return
     */
    public Rybamount getRewardValue() {
        return rewardValue;
    }

    public String getName(){

        return this.name;

    }


    /**
     *Check if a player has any powerup in hand
     * @return true if the ammo tile contains also a power up.
     */
    public boolean hasPowerUp() {
        return powerUp;
    }

    /*
     * ---------------------- SETTERS
     */

    /**
     * Sets the reward given by a loot card
     * @param rewardValue is the list of rewards, that can either be multiple ammos or a powerup
     */
    public void setRewardValue(Rybamount rewardValue) {
        this.rewardValue = rewardValue;
    }

    /**
     * Sets the powerup to true if the loot card rewards a player with it
     * @param powerUp
     */
    public void setPowerUp(boolean powerUp) {
        this.powerUp = powerUp;
    }

    public void setName(String name){

        this.name = name;

    }

}
