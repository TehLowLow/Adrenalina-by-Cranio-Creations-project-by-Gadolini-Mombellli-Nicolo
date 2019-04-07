package it.polimi.se2019.Controller.Data.EffectBuilders;
import it.polimi.se2019.Model.Effect;
import it.polimi.se2019.Model.Map;
import it.polimi.se2019.Model.Player;
import java.util.ArrayList;



/**
 * Contains the @Override of the methods of Effect.java, and builds the Flamethrower.
 */
public class BasicFlamethrower {

        private Effect basicFlamethrower = new Effect() {

         /**
          * Applies the effect of the Flamethrower to the target (or targets).
          * @param user the Player that wants to apply the effect.
          * @param target the target of the effect. It can be the user itself.
          */

        @Override
        public void applyEffect(Player user, Player target) {


        }

            /**
             * Looks for the target (or targets) of the Flamethrower.
             * @param user the Player thant wants to use the effect.
             * @param map the Map where the targets must be searched.
             * @return
             */

        @Override
        public ArrayList<Player> getTargets(Player user, Map map) {
            return null;
        }
    };
}
