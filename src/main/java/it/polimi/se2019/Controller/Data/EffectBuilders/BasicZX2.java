package it.polimi.se2019.Controller.Data.EffectBuilders;

import it.polimi.se2019.Controller.Adrenalina.Check;
import it.polimi.se2019.Controller.Data.EffectBuilders.GeneralMethods.ChoosePlayer;
import it.polimi.se2019.Controller.Data.EffectBuilders.GeneralMethods.Damage;
import it.polimi.se2019.Model.Effect;
import it.polimi.se2019.Model.Player;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * basic effect of the zx-2
 */

public class BasicZX2 extends Effect {

    /**
     *
     * @param user the Player that wants to apply the effect.
     * @param targets the targets of the effect.
     */
    @Override
    public void applyEffect(Player user, CopyOnWriteArrayList<Player> targets) {

        Damage.giveDamage(1, user, targets.get(0));
        Damage.giveMarker(2, user, targets.get(0));

    }


    /**
     *
     * @param user the Player that wants to use the effect.
     * @return an array containing the targets to hit.
     */

    @Override
    public CopyOnWriteArrayList<Player> getTargets(Player user) {

        CopyOnWriteArrayList<Player> target = new CopyOnWriteArrayList<>();

        CopyOnWriteArrayList<Player> possibleTargets = Check.visiblePlayers(user);

        Player chosen = ChoosePlayer.one(user, possibleTargets);

        target.add(chosen);

        return target;

    }

    /**
     *
     * @param user the player who has to use the effect.
     * @return true if the effect has targets to hit, false otherwise.
     */

    @Override
    public boolean hasTargets(Player user) {

        CopyOnWriteArrayList <Player> visible = Check.visiblePlayers(user);
        if(visible.isEmpty()){
            return false;
        }
        return true;
    }
}
