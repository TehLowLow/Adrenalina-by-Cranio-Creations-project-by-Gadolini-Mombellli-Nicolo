package it.polimi.se2019.Controller.Data.EffectBuilders;

import it.polimi.se2019.Controller.Adrenalina.Check;
import it.polimi.se2019.Controller.Data.EffectBuilders.GeneralMethods.ChoosePlayer;
import it.polimi.se2019.Controller.Data.EffectBuilders.GeneralMethods.Damage;
import it.polimi.se2019.Model.Effect;
import it.polimi.se2019.Model.Player;
import it.polimi.se2019.Network.Server;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * basic effect of the hellion
 */

public class BasicHellion extends Effect {


    /**
     *
     * @param user the Player that wants to apply the effect.
     * @param targets the targets of the effect.
     */
    @Override
    public void applyEffect(Player user, CopyOnWriteArrayList<Player> targets) {

        Player target = targets.get(0);

        Damage.giveDamage(1, user, target);

        for (Player other : Server.connectedPlayers) {

            if (other.getNickname().equalsIgnoreCase(user.getNickname())) {
                continue;
            }

            if (other.getPosition().equals(target.getPosition())) {

                Damage.giveMarker(1, user, other);

            }

        }

    }

    /**
     *
     * @param user the Player thant wants to use the effect.
     * @return the targets to shot.
     */
    @Override
    public CopyOnWriteArrayList<Player> getTargets(Player user) {

        CopyOnWriteArrayList<Player> chosenTarget = new CopyOnWriteArrayList<>();

        CopyOnWriteArrayList<Player> visiblePlayers = Check.visiblePlayers(user);

        for (Player player:visiblePlayers){

            if (player.getPosition().equals(user.getPosition())){

                visiblePlayers.remove(player);

            }

        }

        chosenTarget.add(ChoosePlayer.one(user, visiblePlayers));

        return chosenTarget;
    }


    /**
     *
     * @param user the player who has to use the effect.
     * @return true if the weapon has targets to shot.
     */
    @Override
    public boolean hasTargets(Player user) {


        CopyOnWriteArrayList<Player> visiblePlayers = Check.visiblePlayers(user);

        for (Player player:visiblePlayers){

            if (player.getPosition().equals(user.getPosition())){

                visiblePlayers.remove(player);

            }

        }

        if (!visiblePlayers.isEmpty()){

            return true;

        }

        return false;
    }


}
