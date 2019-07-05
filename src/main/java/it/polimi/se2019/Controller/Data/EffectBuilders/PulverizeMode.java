package it.polimi.se2019.Controller.Data.EffectBuilders;

import it.polimi.se2019.Controller.Adrenalina.Check;
import it.polimi.se2019.Controller.Adrenalina.InputCheck;
import it.polimi.se2019.Controller.Data.EffectBuilders.GeneralMethods.Damage;
import it.polimi.se2019.Model.Cell;
import it.polimi.se2019.Model.Effect;
import it.polimi.se2019.Model.Player;
import it.polimi.se2019.Network.Server;
import it.polimi.se2019.View.Message;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * alternative effect for the sledgehammer
 */

public class PulverizeMode extends Effect {

    /**
     *
     * @param user the Player that wants to apply the effect.
     * @param targets the targets of the effect.
     */

    @Override
    public void applyEffect(Player user, CopyOnWriteArrayList<Player> targets) {

        Player target = targets.get(0);

        Damage.giveDamage(3, user, target);
        moveTarget(target, user);

    }

    /**
     *
     * @param user the Player that wants to use the effect.
     * @return the targets of the sledgehammer.
     */

    @Override
    public CopyOnWriteArrayList<Player> getTargets(Player user) {

        BasicSledgehammer effect = new BasicSledgehammer();
        return effect.getTargets(user);
    }

    /**
     *
     * @param user the player who has to use the effect.
     * @return true if the user has some targets to shot.
     */

    @Override
    public boolean hasTargets(Player user) {

        BasicSledgehammer effect = new BasicSledgehammer();
        return effect.hasTargets(user);

    }

    /**
     * is responsible of the move of the target
     * @param target to move
     * @param user of the effect
     */

    public void moveTarget(Player target, Player user){

        CopyOnWriteArrayList <Cell> reachableCells = Check.reachableCells(target, 2);
        reachableCells.add(target.getPosition());

        boolean chosen = false;
        int index = 0;

        while(!chosen){

            String indexCell = Server.updateWithAnswer(user, Message.scegliCella(reachableCells));

            try{
                index = InputCheck.numberCheck(indexCell);

                if(index<0 || index>reachableCells.size()-1){
                    Server.update(user, Message.inputError());
                    continue;
                }

                chosen = true;

                target.setPosition(reachableCells.get(index));


            }catch(NumberFormatException e){
                Server.update(user, Message.inputError());
                continue;
            }

        }

    }



}
