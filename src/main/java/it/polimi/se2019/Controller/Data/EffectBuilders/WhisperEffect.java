package it.polimi.se2019.Controller.Data.EffectBuilders;

import it.polimi.se2019.Controller.Adrenalina.Check;
import it.polimi.se2019.Controller.Adrenalina.InputCheck;
import it.polimi.se2019.Model.Effect;
import it.polimi.se2019.Model.Player;
import it.polimi.se2019.Model.Token;
import it.polimi.se2019.Network.Server;
import it.polimi.se2019.View.Message;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import static it.polimi.se2019.Network.Server.connectedPlayers;
import static it.polimi.se2019.Network.Server.update;

public class WhisperEffect extends Effect {
    @Override
    public void applyEffect(Player user, ArrayList<Player> targets) {

        if (targets.equals(null)){

            update(user, Message.nessunBersaglio());
            return;

        }

        for (Player target : targets) {

            ArrayList<Token> markers = target.getPlayerboard().getMarker();
            ArrayList<Token> damages = target.getPlayerboard().getDamage();



            Token m1 = new Token();
            Token d1 = new Token();
            Token d2 = new Token();
            Token d3 = new Token();


            markers.add(m1);
            damages.add(d1);
            damages.add(d2);
            damages.add(d3);


            Check.limitMarkers(target, user);


            target.getPlayerboard().setMarker(markers);
            target.getPlayerboard().setDamage(damages);


            update(target, Message.colpito(user));
        }



    }

    @Override
    public ArrayList<Player> getTargets(Player user) {

        if (!hasTargets(user)){

            return null;

        }

        ArrayList<Player> possibleTargets = Check.visiblePlayers(user);

        ArrayList<Player> targets = new ArrayList<>();

        for (Player player:possibleTargets){

            /*
            verifico che non sia nella stessa cella
             */

            if (player.getPosition().equals(user.getPosition())){

                possibleTargets.remove(player);

            }

            /*
            verifico che non sia a distanza unitaria
             */

            if (player.getPosition().getUpConnection().getConnectedCell().equals(user.getPosition())){

                possibleTargets.remove(player);

            }

            if (player.getPosition().getDownConnection().equals(user.getPosition())){

                possibleTargets.remove(player);

            }

            if (player.getPosition().getLeftConnection().equals(user.getPosition())){

                possibleTargets.remove(player);

            }

            if (player.getPosition().getRightConnection().equals(user.getPosition())){

                possibleTargets.remove(player);

            }
        }


        String chosenTarget = Server.updateWithAnswer(user, Message.scegliBersaglio(possibleTargets));

        /*
        controllo validità dell'input dell'utente
         */

        while (!InputCheck.nicknameCheck(chosenTarget)) {

            update(user, Message.bersaglioNonValido());

            chosenTarget = Server.updateWithAnswer(user, Message.scegliBersaglio(possibleTargets));

        }

        boolean valid = false;

        for (Player target:possibleTargets){

            if (target.getNickname().equalsIgnoreCase(chosenTarget)){

                valid = true;

            }

        }



        while (!valid) {

            update(user, Message.bersaglioNonValido());

            chosenTarget = Server.updateWithAnswer(user, Message.scegliBersaglio(possibleTargets));

            for (Player target:targets){

                if (target.getNickname().equalsIgnoreCase(chosenTarget)){

                    valid = true;

                }

            }
        }


        for (Player player : connectedPlayers) {

            if (player.getNickname().equals(chosenTarget)) {

                targets.add(player);

            }

        }

        return targets;

    }

    @Override
    public boolean hasTargets(Player user) {

       ArrayList<Player> visiblePlayers = Check.visiblePlayers(user);

        for(Player player : visiblePlayers){

            /*
            verifico che non sia nella stessa cella
             */

            if (player.getPosition().equals(user.getPosition())){

                visiblePlayers.remove(player);
                continue;
            }

            /*
            verifico che non sia a distanza unitaria
             */

            if (player.getPosition().getUpConnection().getConnectedCell() != null && player.getPosition().getUpConnection().getConnectedCell().equals(user.getPosition())){

                visiblePlayers.remove(player);
                continue;

            }

            if (player.getPosition().getDownConnection().getConnectedCell() != null && player.getPosition().getDownConnection().getConnectedCell().equals(user.getPosition())){

                visiblePlayers.remove(player);
                continue;

            }

            if (player.getPosition().getLeftConnection().getConnectedCell() != null && player.getPosition().getLeftConnection().getConnectedCell().equals(user.getPosition())){

                visiblePlayers.remove(player);
                continue;

            }

            if (player.getPosition().getRightConnection().getConnectedCell() != null && player.getPosition().getRightConnection().getConnectedCell().equals(user.getPosition())){

                visiblePlayers.remove(player);
                continue;

            }

        }

        if(visiblePlayers.isEmpty()){
            return false;
        }

        return true;


    }
}
