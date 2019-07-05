package it.polimi.se2019.Controller.Data.EffectBuilders;

import it.polimi.se2019.Controller.Adrenalina.Check;
import it.polimi.se2019.Controller.Data.EffectBuilders.GeneralMethods.Damage;
import it.polimi.se2019.Model.Cell;
import it.polimi.se2019.Model.Effect;
import it.polimi.se2019.Model.Player;
import it.polimi.se2019.Network.Server;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * alternative effect of the shockWave
 */

public class TsunamiMode extends Effect {

    /**
     *
     * @param user the Player that wants to apply the effect.
     * @param targets the targets of the effect.
     */

    @Override
    public void applyEffect(Player user, CopyOnWriteArrayList<Player> targets) {

        for (Player target : targets) {
            Damage.giveDamage(1, user, target);
        }


    }

    /**
     *
     * @param user the Player that wants to use the effect.
     * @return the targets of the tsunami mode
     */

    @Override
    public CopyOnWriteArrayList<Player> getTargets(Player user) {

        CopyOnWriteArrayList<Cell> reachableCells = Check.reachableCells(user, 1);
        CopyOnWriteArrayList<Player> targets = new CopyOnWriteArrayList<>();

        for (Player target : Server.connectedPlayers) {

            if (target.getNickname().equalsIgnoreCase(user.getNickname())) {
                continue;
            }

            if (reachableCells.contains(target.getPosition())) {
                targets.add(target);
            }
        }

        return targets;
    }


    /**
     *
     * @param user the player who has to use the effect.
     * @return true if the user can hit some players.
     */

    @Override
    public boolean hasTargets(Player user) {

        CopyOnWriteArrayList<Cell> reachableCells = Check.reachableCells(user, 1);

        for (Player target : Server.connectedPlayers) {

            if (target.getNickname().equalsIgnoreCase(user.getNickname())) {
                continue;
            }

            if (reachableCells.contains(target.getPosition())) {
                return true;
            }
        }

        return false;

    }
}

