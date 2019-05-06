package it.polimi.se2019.Controller.Data.EffectBuilders;

import it.polimi.se2019.Controller.Adrenalina.Check;
import it.polimi.se2019.Controller.Adrenalina.InputCheck;
import it.polimi.se2019.Model.*;
import it.polimi.se2019.Network.Server;
import it.polimi.se2019.View.Message;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;


/**
 * Alternative effect for the Flamethrower.
 */
public class BBQMode extends Effect {


    /**
     * Applies the alternative effect of the Flamethrower.
     *
     * @param user    the Player that wants to apply the effect.
     * @param targets the target of the effect. It can be the user itself.
     */

    @Override
    public void applyEffect(Player user, ArrayList<Player> targets) {

        ArrayList<Cell> doubleDamageCells = Check.reachableCells(user, 1);

        for (Player target : targets) {

            Token d = new Token();
            d.setChampionName(user.getPlayerboard().getChampionName());
            ArrayList<Token> damages = target.getPlayerboard().getDamage();
            damages.add(d);


            if (doubleDamageCells.contains(target.getPosition())) {

                Token d1 = new Token();
                d1.setChampionName(user.getPlayerboard().getChampionName());
                damages.add(d1);
            }

            target.getPlayerboard().setDamage(damages);

        }


    }

    public ArrayList<Player> getTargets(Player user) {

        ArrayList<Player> possibleTargets = new ArrayList<>();
        boolean foundFirst = false;

        Cell firstTargetCell = new LootCell();
        String direction = new String();

        while (!foundFirst) {

            direction = Server.updateWithAnswer(user, Message.scegliDirezioneSparo());

            if (!InputCheck.directionCheck(direction)) {
                Server.update(user, Message.inputError());
                continue;
            }

            if (!BasicFlamethrower.directionFree(user.getPosition(), direction)) {
                Server.update(user, Message.direzioneOstruita());
                continue;
            }

            foundFirst = true;
            firstTargetCell = BasicFlamethrower.returnConnectedCell(user.getPosition(), direction);

            possibleTargets = BasicFlamethrower.getPlayersInCell(firstTargetCell);


        }

        boolean foundSecond = false;

        while (!foundSecond) {

            if (!BasicFlamethrower.directionFree(firstTargetCell, direction)) {
                break;
            }

            Cell secondTargetCell = BasicFlamethrower.returnConnectedCell(firstTargetCell, direction);

            possibleTargets.addAll(BasicFlamethrower.getPlayersInCell(secondTargetCell));

            foundSecond = true;


        }


        if (possibleTargets.size() == 0) {
            Server.update(user, Message.nessunBersaglio());
            return getTargets(user);
        }

        return possibleTargets;

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


}

