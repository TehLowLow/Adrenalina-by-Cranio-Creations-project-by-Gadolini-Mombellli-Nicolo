package it.polimi.se2019.Controller.Data.EffectBuilders;

import it.polimi.se2019.Controller.Adrenalina.Check;
import it.polimi.se2019.Controller.Data.EffectBuilders.GeneralMethods.Damage;
import it.polimi.se2019.Model.*;

import java.util.ArrayList;

/**
 * This is the effect of the TagBack Grenade.
 */
public class TagBackGrenadeEffect extends Effect {

    public void applyEffect(Player user,  ArrayList <Player> targets){

        if(Check.visiblePlayers(user).contains(targets.get(0))) {
            Damage.giveMarker(1, user, targets.get(0));
        }
    }

    public ArrayList<Player> getTargets(Player player){

        return null;
    }

    public boolean hasTargets(Player user){
        return false;
    }


}
