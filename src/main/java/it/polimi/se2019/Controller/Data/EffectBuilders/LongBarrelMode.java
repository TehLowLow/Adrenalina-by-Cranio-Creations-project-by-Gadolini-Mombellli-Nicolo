package it.polimi.se2019.Controller.Data.EffectBuilders;

import it.polimi.se2019.Controller.Adrenalina.Check;
import it.polimi.se2019.Controller.Data.EffectBuilders.GeneralMethods.ChoosePlayer;
import it.polimi.se2019.Controller.Data.EffectBuilders.GeneralMethods.Damage;
import it.polimi.se2019.Model.Cell;
import it.polimi.se2019.Model.Effect;
import it.polimi.se2019.Model.Player;
import it.polimi.se2019.Network.Server;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * alternative effect of the shotgun
 */

public class LongBarrelMode extends Effect {


    /**
     *
     * @param user the Player that wants to apply the effect.
     * @param targets the targets of the effect.
     */
    @Override
    public void applyEffect(Player user, CopyOnWriteArrayList<Player> targets) {

        Damage.giveDamage(2, user, targets.get(0));

    }


    /**
     *
     * @param user the Player that wants to use the effect.
     * @return the array of the effect targets.
     */
    @Override
    public CopyOnWriteArrayList<Player> getTargets(Player user) {

        CopyOnWriteArrayList <Cell> reachables = Check.reachableCells(user, 1);
        CopyOnWriteArrayList<Player> possibleTargets = new CopyOnWriteArrayList<>();
        CopyOnWriteArrayList<Player> chosenTarget = new CopyOnWriteArrayList<>();

        for(Player target : Server.connectedPlayers){
            if(target.getNickname().equalsIgnoreCase(user.getNickname())){
                continue;
            }

            if(reachables.contains(target.getPosition())){
                possibleTargets.add(target);
            }
        }

        chosenTarget.add(ChoosePlayer.one(user, possibleTargets));

        return chosenTarget;
    }


    /**
     *
     * @param user the player who has to use the effect.
     * @return true if the effect has targets to shot.
     */

    @Override
    public boolean hasTargets(Player user) {

        CopyOnWriteArrayList <Cell> reachables = Check.reachableCells(user, 1);

        for(Player target : Server.connectedPlayers){
            if(target.getNickname().equalsIgnoreCase(user.getNickname())){
                continue;
            }

            if(reachables.contains(target.getPosition())){
                return true;
            }
        }

        return false;
    }
}
