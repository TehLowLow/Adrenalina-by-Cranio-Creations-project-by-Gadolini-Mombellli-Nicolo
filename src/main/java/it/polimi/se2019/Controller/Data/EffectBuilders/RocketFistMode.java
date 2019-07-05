package it.polimi.se2019.Controller.Data.EffectBuilders;

import it.polimi.se2019.Controller.Adrenalina.InputCheck;
import it.polimi.se2019.Controller.Data.EffectBuilders.GeneralMethods.ChoosePlayer;
import it.polimi.se2019.Controller.Data.EffectBuilders.GeneralMethods.Damage;
import it.polimi.se2019.Model.Cell;
import it.polimi.se2019.Model.Connection;
import it.polimi.se2019.Model.Effect;
import it.polimi.se2019.Model.Player;
import it.polimi.se2019.Network.Server;
import it.polimi.se2019.View.Message;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * alternative effect for the power glove
 */

public class RocketFistMode extends Effect {

    /**
     *
     * @param user the Player that wants to apply the effect.
     * @param targets the targets of the effect.
     */

    @Override
    public void applyEffect(Player user, CopyOnWriteArrayList<Player> targets) {

        if(targets.size()==0){
            return;
        }

       for(Player target : targets){
           Damage.giveDamage(2, user, target);
       }

    }


    /**
     *
     * @param user the Player that wants to use the effect.
     * @return the targets of the effect.
     */

    @Override
    public CopyOnWriteArrayList<Player> getTargets(Player user) {


        CopyOnWriteArrayList<Player> targets = new CopyOnWriteArrayList<>();


        //Devo scegliere una direzione, farlo muovere in quella direzione ed eventualmente fargli scegliere i bersagli da colpire.

        CopyOnWriteArrayList<Cell> cardinalCells = BasicFlamethrower.getCardinalCells(user);

        boolean chosen = false;
        String direction = new String();

        while (!chosen) {

            direction = Server.updateWithAnswer(user, Message.scegliCellaFurnace(cardinalCells, user));

            if (!InputCheck.directionCheck(direction)) {
                Server.update(user, Message.inputError());
                continue;
            }

            chosen = true;

        }

        Cell next = null;

        if (direction.equals("alto")) {

            user.setPosition(user.getPosition().getUpConnection().getConnectedCell());
            Server.update(user, Message.movedTo());
            if(user.getPosition().getUpConnection().getType().equals(Connection.DOOR) || user.getPosition().getUpConnection().getType().equals(Connection.FREE)){
               next = user.getPosition().getUpConnection().getConnectedCell();
           }
           
        }

        if (direction.equals("basso")) {

            user.setPosition(user.getPosition().getDownConnection().getConnectedCell());
            Server.update(user, Message.movedTo());
            if(user.getPosition().getDownConnection().getType().equals(Connection.DOOR) || user.getPosition().getDownConnection().getType().equals(Connection.FREE)){
                next = user.getPosition().getDownConnection().getConnectedCell();
            }
        }

        if (direction.equals("sinistra")) {

            user.setPosition(user.getPosition().getLeftConnection().getConnectedCell());
            Server.update(user, Message.movedTo());
            if(user.getPosition().getLeftConnection().getType().equals(Connection.DOOR) || user.getPosition().getLeftConnection().getType().equals(Connection.FREE)){
                next = user.getPosition().getLeftConnection().getConnectedCell();
            }

        }

        if (direction.equals("destra")) {

            user.setPosition(user.getPosition().getRightConnection().getConnectedCell());
            Server.update(user, Message.movedTo());
            if(user.getPosition().getRightConnection().getType().equals(Connection.DOOR) || user.getPosition().getRightConnection().getType().equals(Connection.FREE)){
                next = user.getPosition().getRightConnection().getConnectedCell();
            }

        }

        //Qui codice per scegliere se colpire qualcuno.

        Player target = selectTarget(user);
        if(target!=null){
            targets.add(target);
        }

        chosen = false;

        String answer = new String();

        //Se next non è null, posso spostarmi ancora se voglio.
        if (next != null) {

            while (!chosen) {

                answer = Server.updateWithAnswer(user, Message.vuoiSpostartiAncora());

                if (!InputCheck.correctYesNo(answer)) {
                    Server.update(user, Message.inputError());
                    continue;
                }

                chosen = true;

            }


            if (InputCheck.yesOrNo(answer)) {

                user.setPosition(next);
                Server.update(user, Message.movedTo());

                target = selectTarget(user);
                if(target!=null){
                    targets.add(target);

                }

            }

        }

        return targets;

    }


    /**
     *
     * @param user of the effect
     * @return the target
     */
    private static Player selectTarget(Player user){

        CopyOnWriteArrayList<Player> possibleTargets = new CopyOnWriteArrayList<>();

        boolean chosen = false;
        String answer = new String();


        for (Player player : Server.connectedPlayers) {
            if (player.equals(user)) {
                continue;
            }

            if (player.getPosition().equals(user.getPosition())) {
                possibleTargets.add(player);
            }
        }

        if(possibleTargets.size()==0){
            return null;
        }


        while (!chosen) {

            answer = Server.updateWithAnswer(user, Message.vuoiColpire());

            if (!InputCheck.correctYesNo(answer)) {
                Server.update(user, Message.inputError());
                continue;
            }

            chosen = true;

        }


        if (InputCheck.yesOrNo(answer)) {

            return ChoosePlayer.one(user, possibleTargets);

        }

        return null;

    }

    /**
     *
     * @param user the player who has to use the effect.
     * @return true
     */

    @Override
    public boolean hasTargets(Player user) {

        //Al massimo si muove e basta e non colpisce nessuno.
       return true;

    }

}
