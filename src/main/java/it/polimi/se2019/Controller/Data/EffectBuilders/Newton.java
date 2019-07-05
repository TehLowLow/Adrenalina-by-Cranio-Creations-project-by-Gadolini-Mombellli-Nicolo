package it.polimi.se2019.Controller.Data.EffectBuilders;

import it.polimi.se2019.Controller.Adrenalina.Check;
import it.polimi.se2019.Controller.Adrenalina.InputCheck;
import it.polimi.se2019.Controller.Data.EffectBuilders.GeneralMethods.ChoosePlayer;
import it.polimi.se2019.Model.*;
import it.polimi.se2019.Network.Server;
import it.polimi.se2019.View.Message;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Effect of the newton Powerup
 */

public class Newton extends Effect {


    /**
     *
     * @param user the Player that wants to apply the effect.
     * @param targets the targets of the effect.
     */
    @Override
    public void applyEffect(Player user, CopyOnWriteArrayList<Player> targets) {

        CopyOnWriteArrayList <Cell> reachableCells = getDirectionCells(user, targets.get(0));
        Cell chosenCell = null;

        boolean chosen = false;

        while(!chosen){

            int index = 0;

            String indexCell = Server.updateWithAnswer(user, Message.scegliCella(reachableCells));

            try{

                index = InputCheck.numberCheck(indexCell);

                if(index<0 || index>reachableCells.size()-1){
                    Server.update(user, Message.inputError());
                    continue;
                }

                chosen = true;

                chosenCell = reachableCells.get(index);

            }catch (NumberFormatException e){
                Server.update(user, Message.inputError());
                continue;
            }

        }

        targets.get(0).setPosition(chosenCell);


    }

    /**
     *
     * @param user of the powerup
     * @param target is who has to be moved.
     * @return the cells in the selected direction.
     */

    private CopyOnWriteArrayList <Cell> getDirectionCells(Player user, Player target){

        CopyOnWriteArrayList <Cell> reachableCells = Check.reachableCells(target, 1);
        CopyOnWriteArrayList <Cell> targettableCells = new CopyOnWriteArrayList<>();

        boolean chosen = false;

        while(!chosen) {

            String direction = Server.updateWithAnswer(user, Message.spostaBersaglio(target, reachableCells));

            if(!InputCheck.directionCheck(direction)){
                Server.update(user, Message.inputError());
                continue;
            }


            if(direction.equalsIgnoreCase("alto")){

                if(target.getPosition().getUpConnection().getType().equals(Connection.EDGE) || target.getPosition().getUpConnection().getType().equals(Connection.WALL)){
                    Server.update(user, Message.inputError());
                    continue;
                }

                chosen = true;
                targettableCells.add(target.getPosition().getUpConnection().getConnectedCell());

                Cell toVerify = target.getPosition().getUpConnection().getConnectedCell();

                if(toVerify.getUpConnection().getType().equals(Connection.EDGE) || toVerify.getUpConnection().getType().equals(Connection.WALL)){
                    continue;
                }

                targettableCells.add(toVerify.getUpConnection().getConnectedCell());

            }

            if(direction.equalsIgnoreCase("basso")){

                if(target.getPosition().getDownConnection().getType().equals(Connection.EDGE) || target.getPosition().getDownConnection().getType().equals(Connection.WALL)){
                    Server.update(user, Message.inputError());
                    continue;
                }

                chosen = true;
                targettableCells.add(target.getPosition().getDownConnection().getConnectedCell());

                Cell toVerify = target.getPosition().getDownConnection().getConnectedCell();

                if(toVerify.getDownConnection().getType().equals(Connection.EDGE) || toVerify.getDownConnection().getType().equals(Connection.WALL)){
                    continue;
                }

                targettableCells.add(toVerify.getDownConnection().getConnectedCell());

            }

            if(direction.equalsIgnoreCase("sinistra")){

                if(target.getPosition().getLeftConnection().getType().equals(Connection.EDGE) || target.getPosition().getLeftConnection().getType().equals(Connection.WALL)){
                    Server.update(user, Message.inputError());
                    continue;
                }

                chosen = true;
                targettableCells.add(target.getPosition().getLeftConnection().getConnectedCell());

                Cell toVerify = target.getPosition().getLeftConnection().getConnectedCell();

                if(toVerify.getLeftConnection().getType().equals(Connection.EDGE) || toVerify.getLeftConnection().getType().equals(Connection.WALL)){
                    continue;
                }

                targettableCells.add(toVerify.getLeftConnection().getConnectedCell());
            }

            if(direction.equalsIgnoreCase("destra")){

                if(target.getPosition().getRightConnection().getType().equals(Connection.EDGE) || target.getPosition().getRightConnection().getType().equals(Connection.WALL)){
                    Server.update(user, Message.inputError());
                    continue;
                }

                chosen = true;
                targettableCells.add(target.getPosition().getRightConnection().getConnectedCell());

                Cell toVerify = target.getPosition().getRightConnection().getConnectedCell();

                if(toVerify.getRightConnection().getType().equals(Connection.EDGE) || toVerify.getRightConnection().getType().equals(Connection.WALL)){

                    continue;
                }

                targettableCells.add(toVerify.getRightConnection().getConnectedCell());

            }


        }

        return targettableCells;


    }

    /**
     *
     * @param user the Player that wants to use the effect.
     * @return the targets of the effect.
     */

    @Override
    public CopyOnWriteArrayList<Player> getTargets(Player user) {

        CopyOnWriteArrayList <Player> possibleTargets = new CopyOnWriteArrayList<>();

        for(Player target : Server.connectedPlayers){

            if(target.getNickname().equalsIgnoreCase(user.getNickname())){
                continue;
            }

            if (target.getPosition().equals(Board.getLimbo())){
                continue;
            }

            possibleTargets.add(target);

        }

        CopyOnWriteArrayList <Player> target = new CopyOnWriteArrayList<>();

        target.add(ChoosePlayer.one(user, possibleTargets));

        return target;
    }


    /**
     *
     * @param user the player who has to use the effect.
     * @return true
     */

    @Override
    public boolean hasTargets(Player user) {
        return true;
    }
}
