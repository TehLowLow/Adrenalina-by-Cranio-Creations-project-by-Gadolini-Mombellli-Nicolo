package it.polimi.se2019.Controller.Data.EffectBuilders;

import it.polimi.se2019.Controller.Adrenalina.Check;
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

public class RocketFistMode extends Effect {

    String direction;

    @Override
    public void applyEffect(Player user, CopyOnWriteArrayList<Player> targets) {

        Player target = targets.get(0);

        Damage.giveDamage(1, user, target);

        if(user.getPosition().getUpConnection().getConnectedCell().equals(target.getPosition())){
            direction = "up";
        }

        if(user.getPosition().getDownConnection().getConnectedCell().equals(target.getPosition())){
            direction = "down";
        }

        if(user.getPosition().getLeftConnection().getConnectedCell().equals(target.getPosition())){
            direction = "left";
        }

        if(user.getPosition().getRightConnection().getConnectedCell().equals(target.getPosition())){
            direction = "right";
        }

        user.setPosition(target.getPosition());

        boolean move = chooseMove(user);

        if(move){
            applyMoveEffect(user);
        }

    }


    private void applyMoveEffect(Player user){

        if(direction.equalsIgnoreCase("up")){
            user.setPosition(user.getPosition().getUpConnection().getConnectedCell());
        }

        if(direction.equalsIgnoreCase("down")){
            user.setPosition(user.getPosition().getDownConnection().getConnectedCell());
        }

        if(direction.equalsIgnoreCase("left")){
            user.setPosition(user.getPosition().getLeftConnection().getConnectedCell());
        }

        if(direction.equalsIgnoreCase("right")){
            user.setPosition(user.getPosition().getRightConnection().getConnectedCell());
        }

        if(hasTargets(user)) {

            boolean choseAttack = false;
            boolean attack = false;

            while(!choseAttack){

                String answer = Server.updateWithAnswer(user, Message.scegliAltroBersaglio());
                if(!InputCheck.correctYesNo(answer)){
                    Server.update(user, Message.inputError());
                    continue;
                }

                if(InputCheck.yesOrNo(answer)){
                    attack = true;
                    choseAttack = true;
                    continue;
                }
            }

            if(attack) {

                CopyOnWriteArrayList<Player> targets = getTargets(user);
                Damage.giveDamage(2, user, targets.get(0));

            }

        }


    }

    private boolean chooseMove(Player user){

        boolean canMove = false;

        if(direction.equalsIgnoreCase("up")){
            if(user.getPosition().getUpConnection().getType().equals(Connection.DOOR) || user.getPosition().getUpConnection().getType().equals(Connection.FREE)){
                canMove = true;
            }
        }

        if(direction.equalsIgnoreCase("down")){
            if(user.getPosition().getDownConnection().getType().equals(Connection.DOOR) || user.getPosition().getDownConnection().getType().equals(Connection.FREE)){
                canMove = true;
            }
        }

        if(direction.equalsIgnoreCase("left")){
            if(user.getPosition().getLeftConnection().getType().equals(Connection.DOOR) || user.getPosition().getLeftConnection().getType().equals(Connection.FREE)){
                canMove = true;
            }
        }

        if(direction.equalsIgnoreCase("right")){
            if(user.getPosition().getRightConnection().getType().equals(Connection.DOOR) || user.getPosition().getRightConnection().getType().equals(Connection.FREE)){
                canMove = true;
            }
        }

        boolean chosen = false;

        if(canMove){

            while(!chosen){

                String answer = Server.updateWithAnswer(user, Message.vuoiSpostartiAncora());

                if(!InputCheck.correctYesNo(answer)){

                    Server.update(user, Message.inputError());
                    continue;
                }

                chosen = true;

                if(InputCheck.yesOrNo(answer)){
                    return true;
                }

            }

        }

        return false;

    }

    @Override
    public CopyOnWriteArrayList<Player> getTargets(Player user) {

        CopyOnWriteArrayList <Player> chosenTarget = new CopyOnWriteArrayList<>();
        CopyOnWriteArrayList <Player> possibleTargets = new CopyOnWriteArrayList<>();

        CopyOnWriteArrayList <Cell> cells = Check.reachableCells(user, 1);

        for(Player target : Server.connectedPlayers){

            if(target.getNickname().equalsIgnoreCase(user.getNickname())){
                continue;
            }

            if(cells.contains(target.getPosition())){
                possibleTargets.add(target);
            }
        }

        chosenTarget.add(ChoosePlayer.one(user, possibleTargets));

        return chosenTarget;
    }


    @Override
    public boolean hasTargets(Player user) {

        CopyOnWriteArrayList <Cell> cells = Check.reachableCells(user, 1);

        for(Player target : Server.connectedPlayers){

            if(target.getNickname().equalsIgnoreCase(user.getNickname())){
                continue;
            }

            if(cells.contains(target.getPosition())){
                return true;
            }
        }

        return false;
    }

}
