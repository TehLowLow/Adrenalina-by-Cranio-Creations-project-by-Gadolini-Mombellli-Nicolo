package it.polimi.se2019.Controller.Data.EffectBuilders;

import it.polimi.se2019.Controller.Adrenalina.Check;
import it.polimi.se2019.Controller.Adrenalina.InputCheck;
import it.polimi.se2019.Controller.Data.EffectBuilders.GeneralMethods.ChoosePlayer;
import it.polimi.se2019.Model.Cell;
import it.polimi.se2019.Model.Connection;
import it.polimi.se2019.Model.Effect;
import it.polimi.se2019.Model.Player;
import it.polimi.se2019.Network.Server;
import it.polimi.se2019.View.Message;

import java.util.ArrayList;

public class Newton extends Effect {

    @Override
    public void applyEffect(Player user, ArrayList<Player> targets) {

        ArrayList <Cell> reachableCells = getDirectionCells(user, targets.get(0));
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

    private ArrayList <Cell> getDirectionCells(Player user, Player target){

        ArrayList <Cell> reachableCells = Check.reachableCells(target, 1);
        ArrayList <Cell> targettableCells = new ArrayList<>();

        boolean chosen = false;

        while(!chosen) {

            String direction = Server.updateWithAnswer(user, Message.spostaBersaglio(user, reachableCells));

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

                targettableCells.add(toVerify);

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

                targettableCells.add(toVerify);

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

                targettableCells.add(toVerify);
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

                targettableCells.add(toVerify);

            }


        }

        return targettableCells;


    }

    @Override
    public ArrayList<Player> getTargets(Player user) {

        ArrayList <Player> possibleTargets = new ArrayList<>();

        for(Player target : Server.connectedPlayers){

            if(target.getNickname().equalsIgnoreCase(user.getNickname())){
                continue;
            }

            possibleTargets.add(target);



        }

        ArrayList <Player> target = new ArrayList<>();

        target.add(ChoosePlayer.one(user, possibleTargets));

        return null;
    }

    @Override
    public boolean hasTargets(Player user) {
        return true;
    }
}
