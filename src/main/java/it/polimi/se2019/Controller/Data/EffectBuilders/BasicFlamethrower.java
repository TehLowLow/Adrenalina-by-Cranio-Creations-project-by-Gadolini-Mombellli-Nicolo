package it.polimi.se2019.Controller.Data.EffectBuilders;

import it.polimi.se2019.Controller.Adrenalina.Check;
import it.polimi.se2019.Controller.Adrenalina.InputCheck;
import it.polimi.se2019.Model.*;

import java.util.concurrent.CopyOnWriteArrayList;

import it.polimi.se2019.Network.Server;
import it.polimi.se2019.View.*;


public class BasicFlamethrower extends Effect {


    public void applyEffect(Player user, CopyOnWriteArrayList<Player> targets) {

        GrenadeLauncher.giveDamage(user, targets);

    }


    public CopyOnWriteArrayList<Player> getTargets(Player user) {

        CopyOnWriteArrayList<Player> possibleTargets;
        CopyOnWriteArrayList<Player> chosenTargets = new CopyOnWriteArrayList<>();
        boolean foundFirst = false;

        Cell firstTargetCell = new LootCell();
        String direction = new String();

        while (!foundFirst) {

            direction = Server.updateWithAnswer(user, Message.scegliDirezioneSparo());

            if (!InputCheck.directionCheck(direction)) {
                Server.update(user, Message.inputError());
                continue;
            }

            if (!directionFree(user.getPosition(), direction)) {
                Server.update(user, Message.direzioneOstruita());
                continue;
            }

            firstTargetCell = returnConnectedCell(user.getPosition(), direction);

            possibleTargets = getPlayersInCell(firstTargetCell);

            boolean firstTargetFound = false;

            if (possibleTargets.size() == 0) {
                firstTargetFound = true;
            }

            while (!firstTargetFound) {

                String chosenNickname = Server.updateWithAnswer(user, Message.scegliBersaglio(possibleTargets));

                if (!InputCheck.nicknameCheck(chosenNickname)) {
                    Server.update(user, Message.inputError());
                    continue;
                }

                for (Player target : possibleTargets) {

                    if (target.getNickname().equalsIgnoreCase(chosenNickname)) {
                        firstTargetFound = true;
                        chosenTargets.add(target);
                        break;

                    }
                }

                Server.update(user, Message.bersaglioNonValido());


            }


        }

        boolean foundSecond = false;

        while (!foundSecond) {

            if (!directionFree(firstTargetCell, direction)) {
                break;
            }

            Cell secondTargetCell = returnConnectedCell(firstTargetCell, direction);

            possibleTargets = getPlayersInCell(secondTargetCell);

            boolean secondTargetFound = false;

            if (possibleTargets.size() == 0) {
                break;
            }

            while (!secondTargetFound) {

                String chosenNickname = Server.updateWithAnswer(user, Message.scegliBersaglio(possibleTargets));

                if (!InputCheck.nicknameCheck(chosenNickname)) {
                    Server.update(user, Message.inputError());
                    continue;
                }

                for (Player target : possibleTargets) {

                    if (target.getNickname().equalsIgnoreCase(chosenNickname)) {
                        secondTargetFound = true;
                        chosenTargets.add(target);
                        break;
                    }

                }

                Server.update(user, Message.bersaglioNonValido());


            }

        }


        if (chosenTargets.size() == 0) {
            Server.update(user, Message.nessunBersaglio());
            return getTargets(user);
        }

        return chosenTargets;

    }


    public boolean hasTargets(Player user) {

        CopyOnWriteArrayList<Player> players = Server.connectedPlayers;
        CopyOnWriteArrayList<Cell> reachableCardinalCells = getCardinalCells(user);

        for (Player player : players) {

            if (player.equals(user)) {
                continue;
            }

            if (reachableCardinalCells.contains(player.getPosition())) {
                return true;
            }

        }

        return false;


    }


    protected static boolean directionFree(Cell cell, String direction) {

        if (direction.equalsIgnoreCase("alto")) {
            if (cell.getUpConnection().getType().equalsIgnoreCase(Connection.DOOR) || cell.getUpConnection().getType().equalsIgnoreCase(Connection.FREE)) {
                return true;
            }
        }

        if (direction.equalsIgnoreCase("basso")) {
            if (cell.getDownConnection().getType().equalsIgnoreCase(Connection.DOOR) || cell.getDownConnection().getType().equalsIgnoreCase(Connection.FREE)) {
                return true;
            }
        }

        if (direction.equalsIgnoreCase("sinistra")) {
            if (cell.getLeftConnection().getType().equalsIgnoreCase(Connection.DOOR) || cell.getLeftConnection().getType().equalsIgnoreCase(Connection.FREE)) {
                return true;
            }
        }

        if (direction.equalsIgnoreCase("destra")) {
            if (cell.getRightConnection().getType().equalsIgnoreCase(Connection.DOOR) || cell.getRightConnection().getType().equalsIgnoreCase(Connection.FREE)) {
                return true;
            }
        }

        return false;

    }

    protected static Cell returnConnectedCell(Cell cell, String direction) {

        if (direction.equalsIgnoreCase("alto")) {
            return cell.getUpConnection().getConnectedCell();
        }

        if (direction.equalsIgnoreCase("basso")) {
            return cell.getDownConnection().getConnectedCell();
        }

        if (direction.equalsIgnoreCase("sinistra")) {
            return cell.getLeftConnection().getConnectedCell();
        }

        if (direction.equalsIgnoreCase("destra")) {
            return cell.getRightConnection().getConnectedCell();
        }

        return cell;
    }

    protected static CopyOnWriteArrayList<Player> getPlayersInCell(Cell cell) {

        CopyOnWriteArrayList<Player> targets = new CopyOnWriteArrayList<>();

        for (Player target : Server.connectedPlayers) {

            if (target.getPosition().equals(cell)) {
                targets.add(target);
            }

        }

        return targets;
    }

    protected static CopyOnWriteArrayList <Cell> getCardinalCells(Player user){


        CopyOnWriteArrayList<Cell> reachableCardinalCells = new CopyOnWriteArrayList<>();
        Cell position = user.getPosition();
        
            
        Cell upCell = null;
        Cell downCell = null;
        Cell leftCell = null;
        Cell rightCell = null;


        if(position.getUpConnection().getType().equals(Connection.FREE) || position.getUpConnection().getType().equals(Connection.DOOR)){
                    upCell = position.getUpConnection().getConnectedCell();
                    reachableCardinalCells.add(upCell);
                    
                    if(upCell.getUpConnection().getType().equals(Connection.FREE) || position.getUpConnection().getType().equals(Connection.DOOR)){
                        reachableCardinalCells.add(upCell.getUpConnection().getConnectedCell());
                    }
                
            }

        if(position.getDownConnection().getType().equals(Connection.FREE) || position.getDownConnection().getType().equals(Connection.DOOR)){
            downCell = position.getDownConnection().getConnectedCell();
            reachableCardinalCells.add(downCell);

            if(downCell.getDownConnection().getType().equals(Connection.FREE) || position.getDownConnection().getType().equals(Connection.DOOR)){
                reachableCardinalCells.add(downCell.getDownConnection().getConnectedCell());
            }

        }

        if(position.getLeftConnection().getType().equals(Connection.FREE) || position.getLeftConnection().getType().equals(Connection.DOOR)){
            leftCell = position.getLeftConnection().getConnectedCell();
            reachableCardinalCells.add(leftCell);

            if(leftCell.getLeftConnection().getType().equals(Connection.FREE) || position.getLeftConnection().getType().equals(Connection.DOOR)){
                reachableCardinalCells.add(leftCell.getLeftConnection().getConnectedCell());
            }

        }

        if(position.getRightConnection().getType().equals(Connection.FREE) || position.getRightConnection().getType().equals(Connection.DOOR)){
            rightCell = position.getRightConnection().getConnectedCell();
            reachableCardinalCells.add(rightCell);

            if(rightCell.getRightConnection().getType().equals(Connection.FREE) || position.getRightConnection().getType().equals(Connection.DOOR)){
                reachableCardinalCells.add(rightCell.getRightConnection().getConnectedCell());
            }

        }
            

        return reachableCardinalCells;

    }

}
