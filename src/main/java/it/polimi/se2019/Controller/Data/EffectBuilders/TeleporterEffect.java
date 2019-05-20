package it.polimi.se2019.Controller.Data.EffectBuilders;

import it.polimi.se2019.Controller.Adrenalina.InputCheck;
import it.polimi.se2019.Model.*;
import it.polimi.se2019.View.Message;

import java.util.concurrent.CopyOnWriteArrayList;

import static it.polimi.se2019.Network.Server.update;
import static it.polimi.se2019.Network.Server.updateWithAnswer;

public class TeleporterEffect extends Effect {
    @Override
    public void applyEffect(Player user, CopyOnWriteArrayList<Player> targets) {

        chooseCell(user);

    }

    @Override
    public CopyOnWriteArrayList<Player> getTargets(Player user) {
        return null;
    }

    @Override
    public boolean hasTargets(Player user) {
        return false;
    }

    public void chooseCell(Player user) {

        Map map = Board.getMap();

        CopyOnWriteArrayList<Cell> cells = new CopyOnWriteArrayList<>();


        if (map.getBlueRoom() != null) {
            cells.addAll(map.getBlueRoom().getCells());
        }
        if (map.getGreenRoom() != null) {
            cells.addAll(map.getGreenRoom().getCells());
        }
        if (map.getPurpleRoom() != null) {
            cells.addAll(map.getPurpleRoom().getCells());
        }
        if (map.getRedRoom() != null) {
            cells.addAll(map.getRedRoom().getCells());
        }
        if (map.getWhiteRoom() != null) {
            cells.addAll(map.getWhiteRoom().getCells());
        }
        if (map.getYellowRoom() != null) {
            cells.addAll(map.getYellowRoom().getCells());
        }



        boolean cellaValida = false;

        String scelta = updateWithAnswer(user, Message.scegliCella(cells));
        int cellNumber;

        while (!cellaValida) {

            try {
                cellNumber = InputCheck.numberCheck(scelta);
            } catch (NumberFormatException e) {
                update(user, Message.inputError());
                scelta = updateWithAnswer(user, Message.scegliCella(cells));
                continue;
            }


            if (cellNumber >= 0 && cellNumber < cells.size()) {

                user.setPosition(cells.get(cellNumber));
                update(user , Message.movedTo());
                cellaValida = true;

            } else {
                update(user, Message.inputError());
            }

            if (!cellaValida){
                scelta = updateWithAnswer(user, Message.scegliCella(cells));
            }


        }


    }
}
