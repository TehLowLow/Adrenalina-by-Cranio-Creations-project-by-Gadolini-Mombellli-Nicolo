package it.polimi.se2019.Controller.Data.EffectBuilders;

import it.polimi.se2019.Controller.Adrenalina.Check;
import it.polimi.se2019.Controller.Data.EffectBuilders.GeneralMethods.Damage;
import it.polimi.se2019.Model.*;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * This is the effect of the TagBack Grenade.
 */
public class TagBackGrenadeEffect extends Effect {

    /**
     *
     * @param user the Player that wants to apply the effect.
     * @param targets the targets of the effect.
     */

    public void applyEffect(Player user,  CopyOnWriteArrayList <Player> targets){

        if(Check.visiblePlayers(user).contains(targets.get(0))) {
            Damage.giveMarker(1, user, targets.get(0));
        }
    }

    /**
     *
     * @param player is the user of the effect.
     * @return null
     */

    public CopyOnWriteArrayList<Player> getTargets(Player player){

        return null;
    }

    /**
     *
     * @param user the player who has to use the effect.
     * @return false
     */

    public boolean hasTargets(Player user){
        return false;
    }


}
