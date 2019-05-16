package it.polimi.se2019.Controller.Data.EffectBuilders;

import it.polimi.se2019.Controller.Adrenalina.Check;
import it.polimi.se2019.Controller.Adrenalina.InputCheck;
import it.polimi.se2019.Model.Cell;
import it.polimi.se2019.Model.Effect;
import it.polimi.se2019.Model.Player;
import it.polimi.se2019.Model.Token;
import it.polimi.se2019.Network.Server;
import it.polimi.se2019.View.Message;

import java.util.concurrent.CopyOnWriteArrayList;

public class NanoTracerMode extends Effect {


    private CopyOnWriteArrayList<Player> possibleTargets;

    @Override
    public void applyEffect(Player user, CopyOnWriteArrayList<Player> targets) {

        Player target = targets.get(0);

        Token damage = new Token();

        damage.setChampionName(user.getPlayerboard().getChampionName());

        CopyOnWriteArrayList<Token> damages = target.getPlayerboard().getDamage();

        damages.add(damage);

        target.getPlayerboard().setDamage(damages);

        for (Player other : Server.connectedPlayers) {

            if (other.getNickname().equalsIgnoreCase(user.getNickname())) {
                continue;
            }

            if (other.getPosition().equals(target.getPosition())) {

                Token marker1 = new Token();
                Token marker2 = new Token();

                marker1.setChampionName(user.getPlayerboard().getChampionName());
                marker2.setChampionName(user.getPlayerboard().getChampionName());

                CopyOnWriteArrayList<Token> markers = other.getPlayerboard().getMarker();

                markers.add(marker1);
                markers.add(marker2);

                other.getPlayerboard().setMarker(markers);
                Check.limitMarkers(other, user);

            }

        }

    }

    @Override
    public CopyOnWriteArrayList<Player> getTargets(Player user) {

        CopyOnWriteArrayList<Player> chosenTarget = new CopyOnWriteArrayList<>();

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

        CopyOnWriteArrayList<Cell> oneMoveAway = Check.reachableCells(user, 1);
        oneMoveAway.add(user.getPosition());

        possibleTargets = new CopyOnWriteArrayList<>();

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
