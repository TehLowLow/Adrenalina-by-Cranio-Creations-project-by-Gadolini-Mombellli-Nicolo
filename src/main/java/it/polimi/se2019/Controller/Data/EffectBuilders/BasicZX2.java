package it.polimi.se2019.Controller.Data.EffectBuilders;

import it.polimi.se2019.Controller.Adrenalina.Check;
import it.polimi.se2019.Controller.Data.EffectBuilders.GeneralMethods.ChoosePlayer;
import it.polimi.se2019.Controller.Data.EffectBuilders.GeneralMethods.Damage;
import it.polimi.se2019.Model.Effect;
import it.polimi.se2019.Model.Player;

import java.util.concurrent.CopyOnWriteArrayList;

public class BasicZX2 extends Effect {

    @Override
    public void applyEffect(Player user, CopyOnWriteArrayList<Player> targets) {

        Damage.giveDamage(1, user, targets.get(0));
        Damage.giveMarker(2, user, targets.get(0));

    }

    @Override
    public CopyOnWriteArrayList<Player> getTargets(Player user) {

        CopyOnWriteArrayList<Player> target = new CopyOnWriteArrayList<>();

        CopyOnWriteArrayList<Player> possibleTargets = Check.visiblePlayers(user);

        Player chosen = ChoosePlayer.one(user, possibleTargets);

        target.add(chosen);

        return target;

    }

    @Override
    public boolean hasTargets(Player user) {

        CopyOnWriteArrayList <Player> visible = Check.visiblePlayers(user);
        if(visible.isEmpty()){
            return false;
        }
        return true;
    }
}
