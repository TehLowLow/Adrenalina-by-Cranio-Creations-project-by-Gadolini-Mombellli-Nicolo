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
 * basic effect of the powerglove.
 */


public class BasicPowerGlove extends Effect {


    /**
     *
     * @param user the Player that wants to apply the effect.
     * @param targets the targets of the effect.
     */
    @Override
    public void applyEffect(Player user, CopyOnWriteArrayList<Player> targets) {

        Player target = targets.get(0);

        Damage.giveDamage(1, user, target);
        Damage.giveMarker(2, user, target);
        user.setPosition(target.getPosition());

    }

    /**
     *
     * @param user the Player that wants to use the effect.
     * @return the targets that will be shot.
     */
    @Override
    public CopyOnWriteArrayList<Player> getTargets(Player user) {

        CopyOnWriteArrayList <Player> chosenTarget = new CopyOnWriteArrayList<>();
        CopyOnWriteArrayList <Player> possibleTargets = new CopyOnWriteArrayList<>();

        CopyOnWriteArrayList <Cell> cells = Check.reachableCells(user, 1);

        for(Player target : Server.connectedPlayers){

            if(target.getNickname().equalsIgnoreCase(user.getNickname())){
                continue;
            }

            if(cells.contains(target.getPosition())){
                possibleTargets.add(target);
            }
        }

        chosenTarget.add(ChoosePlayer.one(user, possibleTargets));

        return chosenTarget;
    }

    /**
     *
     * @param user the player who has to use the effect.
     * @return true if the power glove has targets to hit.
     */
    @Override
    public boolean hasTargets(Player user) {

        CopyOnWriteArrayList <Cell> cells = Check.reachableCells(user, 1);

        for(Player target : Server.connectedPlayers){

            if(target.getNickname().equalsIgnoreCase(user.getNickname())){
                continue;
            }

            if(cells.contains(target.getPosition())){
                return true;
            }
        }

        return false;
    }
}
