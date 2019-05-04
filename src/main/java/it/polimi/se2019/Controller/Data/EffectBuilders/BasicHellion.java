package it.polimi.se2019.Controller.Data.EffectBuilders;

import it.polimi.se2019.Controller.Adrenalina.Check;
import it.polimi.se2019.Controller.Adrenalina.InputCheck;
import it.polimi.se2019.Model.Cell;
import it.polimi.se2019.Model.Effect;
import it.polimi.se2019.Model.Player;
import it.polimi.se2019.Model.Token;
import it.polimi.se2019.Network.Server;
import it.polimi.se2019.View.Message;

import java.util.ArrayList;

public class BasicHellion extends Effect {


    private ArrayList<Player> possibleTargets;

    @Override
    public void applyEffect(Player user, ArrayList<Player> targets) {

        Player target = targets.get(0);

        Token damage = new Token();

        damage.setChampionName(user.getPlayerboard().getChampionName());

        ArrayList<Token> damages = target.getPlayerboard().getDamage();

        damages.add(damage);

        target.getPlayerboard().setDamage(damages);

        for (Player other : Server.connectedPlayers) {

            if (other.getNickname().equalsIgnoreCase(user.getNickname())) {
                continue;
            }

            if (other.getPosition().equals(target.getPosition())) {

                Token marker = new Token();
                marker.setChampionName(user.getPlayerboard().getChampionName());
                ArrayList<Token> markers = other.getPlayerboard().getMarker();
                markers.add(marker);
                other.getPlayerboard().setMarker(markers);
                Check.limitMarkers(other, user);

            }

        }

    }

    @Override
    public ArrayList<Player> getTargets(Player user) {

        ArrayList<Player> chosenTarget = new ArrayList<>();

        boolean found = false;

        String chosenNickname = null;

        while (!found) {

            chosenNickname = Server.updateWithAnswer(user, Message.scegliBersaglio(possibleTargets));

            if (!InputCheck.nicknameCheck(chosenNickname)) {
                Server.update(user, Message.bersaglioNonValido());
                continue;
            }

            for (Player target : possibleTargets) {

                if (target.getNickname().equalsIgnoreCase(chosenNickname)) {
                    found = true;
                    chosenTarget.add(target);
                    continue;
                }
            }

            Server.update(user, Message.bersaglioNonValido());


        }

        return chosenTarget;
    }

    @Override
    public boolean hasTargets(Player user) {

        ArrayList<Cell> oneMoveAway = Check.reachableCells(user, 1);
        oneMoveAway.add(user.getPosition());

        possibleTargets = new ArrayList<>();

        for (Player target : Server.connectedPlayers) {

            if (target.getNickname().equalsIgnoreCase(user.getNickname())) {
                possibleTargets.add(target);
                continue;
            }

            if (oneMoveAway.contains(target.getPosition())) {
                return true;
            }

        }

        return false;
    }
}
