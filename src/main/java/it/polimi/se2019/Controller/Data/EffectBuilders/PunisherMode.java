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
 * alternative effect for the tractor beam
 */

public class PunisherMode extends Effect {


    /**
     *
     * @param user the Player that wants to apply the effect.
     * @param targets the targets of the effect.
     */


    @Override
    public void applyEffect(Player user, CopyOnWriteArrayList<Player> targets) {

        Player target = targets.get(0);

        Damage.giveDamage(3, user, target);

        target.setPosition(user.getPosition());

        return;


    }


    /**
     *
     * @param user the Player that wants to use the effect.
     * @return the array of the targets that will be hit with the punisher mode.
     */

    @Override
    public CopyOnWriteArrayList<Player> getTargets(Player user) {

        CopyOnWriteArrayList <Cell> reachableCells = Check.reachableCells(user, 3);
        reachableCells.add(user.getPosition());
        CopyOnWriteArrayList <Player> possibleTargets = new CopyOnWriteArrayList<>();
        CopyOnWriteArrayList <Player> targets = new CopyOnWriteArrayList<>();

        for(Player target : Server.connectedPlayers){

            if(target.getNickname().equalsIgnoreCase(user.getNickname())){
                continue;
            }

            if(reachableCells.contains(target.getPosition())){
                possibleTargets.add(target);
            }

        }

        targets.add(ChoosePlayer.one(user, possibleTargets));
        return targets;
    }

    /**
     *
     * @param user the player who has to use the effect.
     * @return true if the user can shot someone.
     */

    @Override
    public boolean hasTargets(Player user) {

        CopyOnWriteArrayList <Cell> reachableCells = Check.reachableCells(user, 2);
        reachableCells.add(user.getPosition());
        CopyOnWriteArrayList <Player> targets = new CopyOnWriteArrayList<>();

        for(Player target : Server.connectedPlayers){

            if(target.getNickname().equalsIgnoreCase(user.getNickname())){
                continue;
            }

            if(reachableCells.contains(target.getPosition())){
                targets.add(target);
            }

        }


        if(targets.isEmpty()) {
            return false;
        }
        return true;
    }
}
