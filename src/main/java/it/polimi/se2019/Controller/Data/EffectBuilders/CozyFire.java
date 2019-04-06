package it.polimi.se2019.Controller.Data.EffectBuilders;

import it.polimi.se2019.Model.Effect;
import it.polimi.se2019.Model.Map;
import it.polimi.se2019.Model.Player;

import java.util.ArrayList;

/*Alternative effect for the Furnace*/



public class CozyFire {

    private Effect cozyFire = new Effect() {
        @Override
        public void applyEffect(Player user, Player target) {

        }

        @Override
        public ArrayList<Player> getTargets(Player user, Map map) {
            return null;
        }
    };




}
