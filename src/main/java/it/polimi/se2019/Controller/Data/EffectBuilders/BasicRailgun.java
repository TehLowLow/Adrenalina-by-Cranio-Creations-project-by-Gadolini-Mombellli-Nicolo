package it.polimi.se2019.Controller.Data.EffectBuilders;

import it.polimi.se2019.Controller.Adrenalina.InputCheck;
import it.polimi.se2019.Controller.Data.EffectBuilders.GeneralMethods.ChoosePlayer;
import it.polimi.se2019.Controller.Data.EffectBuilders.GeneralMethods.Damage;
import it.polimi.se2019.Model.Cell;
import it.polimi.se2019.Model.Effect;
import it.polimi.se2019.Model.Player;
import it.polimi.se2019.Network.Server;
import it.polimi.se2019.View.Message;

import java.util.ArrayList;

public class BasicRailgun extends Effect {

    @Override
    public void applyEffect(Player user, ArrayList<Player> targets) {

        for(Player target : targets){

            Damage.giveDamage(3, user, target);
        }


    }

    @Override
    public ArrayList<Player> getTargets(Player user) {

        ArrayList <Player> chosenTargets= new ArrayList<>();

        ArrayList <Player> possibleTargets= new ArrayList<>();


        boolean chosenDirection = false;
        String direction;

        while(!chosenDirection){

            direction = Server.updateWithAnswer(user, Message.scegliDirezioneSparo());

            if(!InputCheck.directionCheck(direction)){
                Server.update(user, Message.inputError());
                continue;
            }

            ArrayList <Cell> targetCells = getCellsDirection(user, direction);


            for(Player target : Server.connectedPlayers){
                if(target.getNickname().equalsIgnoreCase(user.getNickname())){
                    continue;
                }

                if(targetCells.contains(target.getPosition())){
                    possibleTargets.add(target);
                }
            }

            if(possibleTargets.isEmpty()){
                Server.update(user, Message.direzioneSenzaBersagli());
                continue;
            }

            chosenDirection = true;

        }

        chosenTargets.add(ChoosePlayer.one(user, possibleTargets));
        return chosenTargets;
    }

    @Override
    public boolean hasTargets(Player user) {

        ArrayList <Cell> targetCells = cardinalCells(user);

        for(Player target : Server.connectedPlayers){
            if(target.getNickname().equalsIgnoreCase(user.getNickname())){
                continue;
            }

            if(targetCells.contains(target.getPosition())){
                return true;
            }
        }

        return false;
    }

    private ArrayList <Cell> getCellsDirection(Player user, String direction){

        ArrayList <Cell> targetCells = new ArrayList<>();

        targetCells.add(user.getPosition());

        if(direction.equalsIgnoreCase("alto")){

            Cell cell = user.getPosition();

            while(cell!=null){

                cell = user.getPosition().getUpConnection().getConnectedCell();
                targetCells.add(cell);

            }

        }

        if(direction.equalsIgnoreCase("basso")){

            Cell cell = user.getPosition();

            while(cell!=null){

                cell = user.getPosition().getDownConnection().getConnectedCell();
                targetCells.add(cell);

            }

        }

        if(direction.equalsIgnoreCase("sinistra")){

            Cell cell = user.getPosition();

            while(cell!=null){

                cell = user.getPosition().getLeftConnection().getConnectedCell();
                targetCells.add(cell);

            }

        }

        if(direction.equalsIgnoreCase("destra")){

            Cell cell = user.getPosition();

            while(cell!=null){

                cell = user.getPosition().getRightConnection().getConnectedCell();
                targetCells.add(cell);

            }

        }

        return targetCells;

    }

    private ArrayList <Cell> cardinalCells(Player user){

        ArrayList <Cell> targetCells = new ArrayList<>();

        targetCells.add(user.getPosition());

        Cell cell = user.getPosition();

        while(cell!=null){

            cell = user.getPosition().getUpConnection().getConnectedCell();
            targetCells.add(cell);

        }

        while(cell!=null){

            cell = user.getPosition().getDownConnection().getConnectedCell();
            targetCells.add(cell);

        }

        while(cell!=null){

            cell = user.getPosition().getLeftConnection().getConnectedCell();
            targetCells.add(cell);

        }

        while(cell!=null){

            cell = user.getPosition().getRightConnection().getConnectedCell();
            targetCells.add(cell);

        }

        return targetCells;

    }


}
