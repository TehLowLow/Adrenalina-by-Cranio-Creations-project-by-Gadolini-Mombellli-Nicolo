package it.polimi.se2019.Controller.Data.EffectBuilders;
import it.polimi.se2019.Controller.Data.EffectBuilders.GeneralMethods.ChoosePlayer;
import it.polimi.se2019.Controller.Data.EffectBuilders.GeneralMethods.Damage;
import it.polimi.se2019.Model.Effect;
import it.polimi.se2019.Model.Player;
import it.polimi.se2019.Network.Server;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * basic effect of the sledgehammer.
 */

public class BasicSledgehammer extends Effect {

    /**
     *
     * @param user the Player that wants to apply the effect.
     * @param targets the targets of the effect.
     */
    @Override
    public void applyEffect(Player user, CopyOnWriteArrayList<Player> targets) {

        Player target = targets.get(0);

        Damage.giveDamage(2, user, target);

    }


    /**
     *
     * @param user the Player that wants to use the effect.
     * @return an array containing the targets that will be hit by the user.
     */
    @Override
    public CopyOnWriteArrayList<Player> getTargets(Player user) {

        CopyOnWriteArrayList <Player> targets = new CopyOnWriteArrayList<Player>();
        CopyOnWriteArrayList <Player> chosenTarget = new CopyOnWriteArrayList<Player>();

        for(Player target : Server.connectedPlayers){
            if(target.getNickname().equalsIgnoreCase(user.getNickname())){
                continue;
            }

            if(target.getPosition().equals(user.getPosition())){
                targets.add(target);
            }
        }

       chosenTarget.add(ChoosePlayer.one(user, targets));
        return chosenTarget;
    }


    /**
     *
     * @param user the player who has to use the effect.
     * @return true if the effect has targets to hit.
     */
    @Override
    public boolean hasTargets(Player user) {

        for(Player target : Server.connectedPlayers){
            if(target.getNickname().equalsIgnoreCase(user.getNickname())){
                continue;
            }

            if(target.getPosition().equals(user.getPosition())){
                return true;
            }
        }

        return false;
    }

}
