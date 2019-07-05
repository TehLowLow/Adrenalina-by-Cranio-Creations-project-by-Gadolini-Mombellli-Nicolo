package it.polimi.se2019.Controller.Data.EffectBuilders;

import it.polimi.se2019.Controller.Adrenalina.Check;
import it.polimi.se2019.Controller.Adrenalina.InputCheck;
import it.polimi.se2019.Controller.Data.EffectBuilders.GeneralMethods.ChoosePlayer;
import it.polimi.se2019.Controller.Data.EffectBuilders.GeneralMethods.Damage;
import it.polimi.se2019.Model.Cell;
import it.polimi.se2019.Model.Effect;
import it.polimi.se2019.Model.Player;
import it.polimi.se2019.Network.Server;
import it.polimi.se2019.View.Message;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * basic effect of the tractor beam.
 */

public class BasicTractorBeam extends Effect {

    /**
     *
     * @param user the Player that wants to apply the effect.
     * @param targets the targets of the effect. It can be the user itself.
     */
    @Override
    public void applyEffect(Player user, CopyOnWriteArrayList<Player> targets) {

        CopyOnWriteArrayList <Cell> visibleCells = Check.visibleSquares(user);
        CopyOnWriteArrayList <Cell> availableCells = new CopyOnWriteArrayList<>();

        Player target = targets.get(0);
        CopyOnWriteArrayList <Cell> targetCells = Check.reachableCells(target, 2);

        targetCells.add(target.getPosition());

        for(Cell visible : visibleCells){

            if(targetCells.contains(visible)){
                availableCells.add(visible);
            }

        }

        Cell chosenCell = chooseCell(user, availableCells);

        target.setPosition(chosenCell);

        Damage.giveDamage(1, user, target);

        return;


    }


    /**
     *
     * @param user of the effect.
     * @param availableCells are the cells that are available for the effect.
     * @return the cell selected by the user.
     */

    public Cell chooseCell(Player user, CopyOnWriteArrayList <Cell> availableCells){


        Cell chosenCell = null;

        boolean chosen = false;

        while(!chosen){

            int index = 0;

            String indexCell = Server.updateWithAnswer(user, Message.scegliCella(availableCells));

            try{

                index = InputCheck.numberCheck(indexCell);

                if(index<0 || index>availableCells.size()-1){
                    Server.update(user, Message.inputError());
                    continue;
                }

                chosen = true;

                chosenCell = availableCells.get(index);

            }catch (NumberFormatException e){
                Server.update(user, Message.inputError());
                continue;
            }

        }

        return chosenCell;

    }


    /**
     *
     * @param user the Player that wants to use the effect.
     * @return an array containing the targets of the effect.
     */
    @Override
    public CopyOnWriteArrayList<Player> getTargets(Player user) {

        CopyOnWriteArrayList <Cell> visibleCells = Check.visibleSquares(user);

        CopyOnWriteArrayList <Player> targets = new CopyOnWriteArrayList<>();
        CopyOnWriteArrayList <Player> chosenTargets = new CopyOnWriteArrayList<>();

        Player fakePlayer = new Player();
        fakePlayer.setPosition(user.getPosition());

        for(Cell visibleCell : visibleCells){

            fakePlayer.setPosition(visibleCell);

            CopyOnWriteArrayList <Cell> visibleFakeCells = Check.reachableCells(fakePlayer, 2);

            for(Player target : Server.connectedPlayers){

                if(target.getNickname().equalsIgnoreCase(user.getNickname())){
                    continue;
                }

                if(visibleFakeCells.contains(target.getPosition())){

                    if(targets.contains(target)){
                        continue;
                    }

                    targets.add(target);

                }

            }



        }

        chosenTargets.add(ChoosePlayer.one(user, targets));
        return chosenTargets;
    }


    /**
     *
     * @param user the player who has to use the effect.
     * @return true if the effects has targets to hit.
     */

    @Override
    public boolean hasTargets(Player user) {

        CopyOnWriteArrayList <Cell> visibleCells = Check.visibleSquares(user);

        CopyOnWriteArrayList <Player> targets = new CopyOnWriteArrayList<>();

        Player fakePlayer = new Player();
        fakePlayer.setPosition(user.getPosition());

        for(Cell visibleCell : visibleCells){

            fakePlayer.setPosition(visibleCell);

            CopyOnWriteArrayList <Cell> visibleFakeCells = Check.reachableCells(fakePlayer, 2);
            visibleFakeCells.add(fakePlayer.getPosition());

            for(Player target : Server.connectedPlayers){

                if(target.getNickname().equalsIgnoreCase(user.getNickname())){
                    continue;
                }

                if(visibleFakeCells.contains(target.getPosition())){

                    if(targets.contains(target)){
                        continue;
                    }

                    targets.add(target);

                }

            }



        }

        if(targets.isEmpty()){
            return false;
        }

        return true;
    }



}
