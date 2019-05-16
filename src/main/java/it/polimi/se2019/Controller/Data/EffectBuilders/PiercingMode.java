package it.polimi.se2019.Controller.Data.EffectBuilders;

import it.polimi.se2019.Controller.Adrenalina.InputCheck;
import it.polimi.se2019.Controller.Data.EffectBuilders.GeneralMethods.ChoosePlayer;
import it.polimi.se2019.Controller.Data.EffectBuilders.GeneralMethods.Damage;
import it.polimi.se2019.Model.Cell;
import it.polimi.se2019.Model.Effect;
import it.polimi.se2019.Model.Player;
import it.polimi.se2019.Network.Server;
import it.polimi.se2019.View.Message;

import java.util.concurrent.CopyOnWriteArrayList;

public class PiercingMode extends Effect {


    @Override
    public void applyEffect(Player user, CopyOnWriteArrayList<Player> targets) {

        for(Player target : targets){

            Damage.giveDamage(2, user, target);
        }


    }

    @Override
    public CopyOnWriteArrayList<Player> getTargets(Player user) {

        CopyOnWriteArrayList <Player> possibleTargets= new CopyOnWriteArrayList<>();
        CopyOnWriteArrayList <Player> chosenTargets = new CopyOnWriteArrayList<>();

        boolean chosenDirection = false;
        String direction = new String();

        while(!chosenDirection){

            direction = Server.updateWithAnswer(user, Message.scegliDirezioneSparo());

            if(!InputCheck.directionCheck(direction)){
                Server.update(user, Message.inputError());
                continue;
            }

            CopyOnWriteArrayList <Cell> targetCells = getCellsDirection(user, direction);


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

            chosenTargets = choseTargets(user, possibleTargets);

            chosenDirection = true;

        }

        return chosenTargets;
    }


    public CopyOnWriteArrayList<Player> choseTargets(Player user, CopyOnWriteArrayList <Player> possibleTargets){

        CopyOnWriteArrayList <Player> targets = new CopyOnWriteArrayList<>();

        Player chosenPlayer = ChoosePlayer.one(user, possibleTargets);
        possibleTargets.remove(chosenPlayer);
        targets.add(chosenPlayer);

        boolean chosen = false;

        while(!chosen){

                String answer = Server.updateWithAnswer(user, Message.scegliAltroBersaglio());

                if(!InputCheck.correctYesNo(answer)){
                    Server.update(user, Message.inputError());
                    continue;
                }

                if(InputCheck.yesOrNo(answer)){

                    chosen = true;
                    chosenPlayer = ChoosePlayer.one(user, possibleTargets);
                    possibleTargets.remove(chosenPlayer);
                    targets.add(chosenPlayer);

                }

            }

        return targets;

    }

    @Override
    public boolean hasTargets(Player user) {

        CopyOnWriteArrayList <Cell> targetCells = cardinalCells(user);

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

    private CopyOnWriteArrayList <Cell> getCellsDirection(Player user, String direction){

        CopyOnWriteArrayList <Cell> targetCells = new CopyOnWriteArrayList<>();

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

    private CopyOnWriteArrayList <Cell> cardinalCells(Player user){

        CopyOnWriteArrayList <Cell> targetCells = new CopyOnWriteArrayList<>();

        targetCells.add(user.getPosition());

        Cell cell = user.getPosition();

        while(cell!=null){

            cell = cell.getUpConnection().getConnectedCell();
            targetCells.add(cell);

        }

        cell = user.getPosition();

        while(cell!=null){

            cell = cell.getDownConnection().getConnectedCell();
            targetCells.add(cell);

        }

        cell = user.getPosition();

        while(cell!=null){

            cell = cell.getLeftConnection().getConnectedCell();
            targetCells.add(cell);

        }

        cell = user.getPosition();

        while(cell!=null){

            cell = cell.getRightConnection().getConnectedCell();
            targetCells.add(cell);

        }

        return targetCells;

    }


}
