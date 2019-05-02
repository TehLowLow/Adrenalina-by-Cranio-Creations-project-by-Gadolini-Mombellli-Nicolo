package it.polimi.se2019.Controller.Data.EffectBuilders;

import it.polimi.se2019.Controller.Adrenalina.Check;
import it.polimi.se2019.Controller.Adrenalina.InputCheck;
import it.polimi.se2019.Model.*;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import it.polimi.se2019.Network.Server;
import it.polimi.se2019.View.*;


public class BasicFlamethrower extends Effect {


    public void applyEffect(Player user, ArrayList<Player> targets) {

        for (Player target : targets) {

            Token damage = new Token();
            damage.setChampionName(user.getPlayerboard().getChampionName());

            ArrayList<Token> damages = target.getPlayerboard().getDamage();

            damages.add(damage);

            target.getPlayerboard().setDamage(damages);

        }

    }


    public ArrayList<Player> getTargets(Player user) {

        ArrayList<Player> possibleTargets = new ArrayList<>();
        ArrayList<Player> chosenTargets = new ArrayList();
        boolean foundFirst = false;

        Cell firstTargetCell = new LootCell();
        String direction = new String();

        while (!foundFirst) {

            direction = Server.updateWithAnswer(user, Message.scegliDirezione());

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

        Check check = new Check();
        ArrayList<Cell> targetCells = check.reachableCells(user, 2);
        CopyOnWriteArrayList<Player> players = Server.connectedPlayers;
        ArrayList<Player> two_step_targets = new ArrayList<>();

        for (Player player : players) {

            if (!player.getNickname().equals(user.getNickname())) {
                if (targetCells.contains(player.getPosition())) {
                    two_step_targets.add(player);
                }
            }

        }

        if (two_step_targets.size() == 0) {
            return false;
        } else {
            return true;
        }
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

    protected static ArrayList<Player> getPlayersInCell(Cell cell) {

        ArrayList<Player> targets = new ArrayList<>();

        for (Player target : Server.connectedPlayers) {

            if (target.getPosition().equals(cell)) {
                targets.add(target);
            }

        }

        return targets;
    }


}
