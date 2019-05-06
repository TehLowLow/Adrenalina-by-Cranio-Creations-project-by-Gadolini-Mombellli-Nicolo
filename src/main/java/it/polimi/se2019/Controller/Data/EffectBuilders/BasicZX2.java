package it.polimi.se2019.Controller.Data.EffectBuilders;

import it.polimi.se2019.Controller.Adrenalina.Check;
import it.polimi.se2019.Controller.Data.EffectBuilders.GeneralMethods.ChoosePlayer;
import it.polimi.se2019.Controller.Data.EffectBuilders.GeneralMethods.Damage;
import it.polimi.se2019.Model.Effect;
import it.polimi.se2019.Model.Player;

import java.util.ArrayList;

public class BasicZX2 extends Effect {

    @Override
    public void applyEffect(Player user, ArrayList<Player> targets) {

        Damage.giveDamage(1, user, targets.get(0));
        Damage.giveMarker(2, user, targets.get(0));

    }

    @Override
    public ArrayList<Player> getTargets(Player user) {

        ArrayList<Player> target = new ArrayList<>();

        ArrayList<Player> possibleTargets = Check.visiblePlayers(user);

        Player chosen = ChoosePlayer.one(user, possibleTargets);

        target.add(chosen);

        return target;

    }

    @Override
    public boolean hasTargets(Player user) {

        ArrayList <Player> visible = Check.visiblePlayers(user);
        if(visible.isEmpty()){
            return false;
        }
        return true;
    }
}
