package it.polimi.se2019.Controller.Data.EffectBuilders;
import it.polimi.se2019.Controller.Adrenalina.Check;
import it.polimi.se2019.Controller.Data.EffectBuilders.GeneralMethods.ChoosePlayer;
import it.polimi.se2019.Controller.Data.EffectBuilders.GeneralMethods.Damage;
import it.polimi.se2019.Model.Board;
import it.polimi.se2019.Model.Effect;
import it.polimi.se2019.Model.Player;
import it.polimi.se2019.Network.Server;

import java.util.concurrent.CopyOnWriteArrayList;


/**
 * Contains the @Override of the methods of Effect.java, and builds the Heatseeker.
 */
public class BasicHeatSeeker extends Effect {


    /**
     * Applies the effect of the Heatseeker to the target.
     *
     * @param user    the Player that wants to apply the effect.
     * @param targets the target of the effect. It can be the user itself.
     */

    @Override
    public void applyEffect(Player user, CopyOnWriteArrayList<Player> targets) {


        for (Player target : targets) {

            Damage.giveDamage(3, user, target);

        }


    }

    /**
     * Looks for the target of the Heatseeker.
     *
     * @param user the Player thant wants to use the effect.
     * @return
     */

    @Override
    public CopyOnWriteArrayList<Player> getTargets(Player user) {

        CopyOnWriteArrayList<Player> players = Server.connectedPlayers;
        CopyOnWriteArrayList<Player> possibleTargets = new CopyOnWriteArrayList();
        CopyOnWriteArrayList<Player> visiblePlayers = Check.visiblePlayers(user);
        CopyOnWriteArrayList<Player> chosenTarget = new CopyOnWriteArrayList<>();

        for (Player target : players) {

            if (!visiblePlayers.contains(target) && !target.equals(user)) {
                if (!target.getPosition().equals(Board.getLimbo())) {

                    possibleTargets.add(target);
                }
            }

        }

        chosenTarget.add(ChoosePlayer.one(user, possibleTargets));

        return chosenTarget;


    }


    public boolean hasTargets(Player user) {

        CopyOnWriteArrayList<Player> visiblePlayers = Check.visiblePlayers(user);
        CopyOnWriteArrayList<Player> invisiblePlayers = new CopyOnWriteArrayList<>();

        for (Player player:Server.connectedPlayers){

            if (!player.equals(user) && !visiblePlayers.contains(user) && !player.getPosition().equals(Board.getLimbo())){

                invisiblePlayers.add(player);

            }

        }

        if (invisiblePlayers.isEmpty()) {
                return false;
        }

        return true;

    }

}
