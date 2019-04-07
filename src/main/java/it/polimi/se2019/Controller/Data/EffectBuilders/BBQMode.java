package it.polimi.se2019.Controller.Data.EffectBuilders;
import it.polimi.se2019.Model.Effect;
import it.polimi.se2019.Model.Map;
import it.polimi.se2019.Model.Player;
import java.util.ArrayList;


/**
 * Alternative effect for the Flamethrower.
 */
public class BBQMode {


    public Effect bbqMode = new Effect() {

        /**
         * Applies the alternative effect of the Flamethrower.
         * @param user the Player that wants to apply the effect.
         * @param target the target of the effect. It can be the user itself.
         */

        @Override
        public void applyEffect(Player user, Player target) {

        }

        /**
         * Looks for the target (or targets) of the alternative effect
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


//TODO: controllare e decidere regole visibilità del costruttore, nelle basic è private.