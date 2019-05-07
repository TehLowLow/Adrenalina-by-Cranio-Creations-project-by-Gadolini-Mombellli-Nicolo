package it.polimi.se2019.Controller.Data.EffectBuilders;

import it.polimi.se2019.Model.*;
import it.polimi.se2019.View.Message;

import java.util.ArrayList;

import static it.polimi.se2019.Network.Server.update;
import static it.polimi.se2019.Network.Server.updateWithAnswer;

public class TeleporterEffect extends Effect {
    @Override
    public void applyEffect(Player user, ArrayList<Player> targets) {

        chooseCell(user);

    }

    @Override
    public ArrayList<Player> getTargets(Player user) {
        return null;
    }

    @Override
    public boolean hasTargets(Player user) {
        return false;
    }

    public void chooseCell(Player user) {

        Map map = Board.getMap();

        ArrayList<Cell> cells = new ArrayList<>();


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

        update(user, "In quale cella vuoi teletrasportarti?");
        update(user, "Scegli tra:");

        for (Cell cell : cells) {

            update(user, cell.getName());

        }


        boolean cellaValida = false;

        String scelta = updateWithAnswer(user, "Seleziona una delle celle elencate");


        for (Cell cell : cells) {

            if (cell.getName().equalsIgnoreCase(scelta)) {

                cellaValida = true;
                user.setPosition(cell);
                break;

            }

        }

        while (!cellaValida) {

            update(user, Message.inputError());
            scelta = updateWithAnswer(user, "Seleziona una delle celle elencate");


            for (Cell cell : cells) {

                if (cell.getName().equalsIgnoreCase(scelta)) {

                    cellaValida = true;
                    user.setPosition(cell);
                    break;

                }

            }


        }


    }
}
