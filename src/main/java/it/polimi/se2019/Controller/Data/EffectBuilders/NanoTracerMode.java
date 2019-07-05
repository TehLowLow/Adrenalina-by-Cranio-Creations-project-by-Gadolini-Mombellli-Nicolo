package it.polimi.se2019.Controller.Data.EffectBuilders;

import it.polimi.se2019.Controller.Data.EffectBuilders.GeneralMethods.Damage;
import it.polimi.se2019.Model.Effect;
import it.polimi.se2019.Model.Player;
import it.polimi.se2019.Network.Server;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * alternative mode for the hellion
 */


public class NanoTracerMode extends Effect {


    /**
     *
     * @param user the Player that wants to apply the effect.
     * @param targets the targets of the effect.
     */

    @Override
    public void applyEffect(Player user, CopyOnWriteArrayList<Player> targets) {

        BasicHellion effect = new BasicHellion();
        effect.applyEffect(user, targets);

        Player target = targets.get(0);


        for(Player hit : Server.connectedPlayers){

            if(hit.getPosition().equals(target.getPosition())){
                if(!hit.equals(user)){
                    Damage.giveMarker(1, user, hit);
                }
            }
        }



    }

    /**
     *
     * @param user the Player that wants to use the effect.
     * @return the targets of the nano tracer mode
     */

    @Override
    public CopyOnWriteArrayList<Player> getTargets(Player user) {

        //Same targets as the Basic Hellion
        BasicHellion effect = new BasicHellion();
        return effect.getTargets(user);
    }

    /**
     *
     * @param user the player who has to use the effect.
     * @return true if the user has someone to hit.
     */
    @Override
    public boolean hasTargets(Player user) {

        //Same targets as the Basic Hellion.
        BasicHellion effect = new BasicHellion();
        return effect.hasTargets(user);
    }
}
