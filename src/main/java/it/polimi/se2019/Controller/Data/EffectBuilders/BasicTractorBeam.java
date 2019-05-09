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

import java.util.ArrayList;

public class BasicTractorBeam extends Effect {


    @Override
    public void applyEffect(Player user, ArrayList<Player> targets) {

        ArrayList <Cell> visibleCells = Check.visibleSquares(user);
        ArrayList <Cell> availableCells = new ArrayList<>();

        Player target = targets.get(0);
        ArrayList <Cell> targetCells = Check.reachableCells(target, 2);
        targetCells.add(target.getPosition());

        for(Cell visibleCell : visibleCells){

            for(Cell targetCell : targetCells){

                if(visibleCells.contains(targetCell)){
                    availableCells.add(visibleCell);
                }

            }

        }

        Cell chosenCell = chooseCell(user, availableCells);

        target.setPosition(chosenCell);

        Damage.giveDamage(1, user, target);

        return;


    }


    public Cell chooseCell(Player user, ArrayList <Cell> availableCells){


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

    @Override
    public ArrayList<Player> getTargets(Player user) {

        ArrayList <Cell> visibleCells = Check.visibleSquares(user);

        ArrayList <Player> targets = new ArrayList<>();
        ArrayList <Player> chosenTargets = new ArrayList<>();

        Player fakePlayer = new Player();
        fakePlayer.setPosition(user.getPosition());

        for(Cell visibleCell : visibleCells){

            fakePlayer.setPosition(visibleCell);

            ArrayList <Cell> visibleFakeCells = Check.reachableCells(fakePlayer, 2);

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

    @Override
    public boolean hasTargets(Player user) {

        ArrayList <Cell> visibleCells = Check.visibleSquares(user);

        ArrayList <Player> targets = new ArrayList<>();

        Player fakePlayer = new Player();
        fakePlayer.setPosition(user.getPosition());

        for(Cell visibleCell : visibleCells){

            fakePlayer.setPosition(visibleCell);

            ArrayList <Cell> visibleFakeCells = Check.reachableCells(fakePlayer, 2);
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
