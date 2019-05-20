package it.polimi.se2019.Controller.Data.EffectBuilders;

import it.polimi.se2019.Controller.Adrenalina.Check;
import it.polimi.se2019.Controller.Adrenalina.InputCheck;
import it.polimi.se2019.Controller.Data.EffectBuilders.GeneralMethods.Damage;
import it.polimi.se2019.Model.*;
import it.polimi.se2019.Network.Server;
import it.polimi.se2019.View.Message;

import java.util.concurrent.CopyOnWriteArrayList;

public class NanoTracerMode extends Effect {


    private CopyOnWriteArrayList<Player> possibleTargets;

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

    @Override
    public CopyOnWriteArrayList<Player> getTargets(Player user) {

        //Same targets as the Basic Hellion
        BasicHellion effect = new BasicHellion();
        return effect.getTargets(user);
    }

    @Override
    public boolean hasTargets(Player user) {

        //Same targets as the Basic Hellion.
        BasicHellion effect = new BasicHellion();
        return effect.hasTargets(user);
    }
}
