package it.polimi.se2019.Model;

/**
 * This class represents an ammo tile.
 */

public class Loot {

    /**
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

    /**
     * ---------------------- GETTERS
     */

    public Rybamount getRewardValue() {
        return rewardValue;
    }


    /**
     *
     * @return true if the ammo tile contains also a power up.
     */
    public boolean hasPowerUp() {
        return powerUp;
    }

    /**
     * ---------------------- SETTERS
     */

    public void setRewardValue(Rybamount rewardValue) {
        this.rewardValue = rewardValue;
    }

    public void setPowerUp(boolean powerUp) {
        this.powerUp = powerUp;
    }

}
