package it.polimi.se2019.Controller.Data.EffectBuilders;

import it.polimi.se2019.Controller.Adrenalina.Check;
import it.polimi.se2019.Controller.Adrenalina.InputCheck;
import it.polimi.se2019.Model.Effect;
import it.polimi.se2019.Model.Player;
import it.polimi.se2019.Model.Token;
import it.polimi.se2019.Network.Server;
import it.polimi.se2019.View.Message;

import java.util.ArrayList;

import static it.polimi.se2019.Network.Server.connectedPlayers;
import static it.polimi.se2019.Network.Server.update;

public class BasicLockRifle extends Effect {




    @Override
    public void applyEffect(Player user, ArrayList<Player> targets) {

        for (Player target:targets){

            ArrayList<Token> damages = target.getPlayerboard().getDamage();
            ArrayList<Token> markers = target.getPlayerboard().getMarker();

            Token d1 = new Token();
            Token d2 = new Token();
            Token m1 = new Token();


            damages.add(d1);
            damages.add(d2);
            markers.add(m1);

            Check.limitMarkers(target, user);

            target.getPlayerboard().setDamage(damages);
            target.getPlayerboard().setMarker(markers);

            update(target, Message.colpito(user));

        }

    }

    @Override
    public ArrayList<Player> getTargets(Player user) {

        ArrayList<Player> targets = new ArrayList<>();

        ArrayList<Player> possibleTargets = new ArrayList<>();

        possibleTargets = Check.visiblePlayers(user);

        String chosenTarget = Server.updateWithAnswer(user, Message.scegliBersaglio(possibleTargets));

        /*
        controllo validità dell'input dell'utente
         */

        while (!InputCheck.nicknameCheck(chosenTarget)){

            update(user, Message.bersaglioNonValido());

            chosenTarget = Server.updateWithAnswer(user, Message.scegliBersaglio(possibleTargets));

        }

        while(!possibleTargets.contains(chosenTarget)){

            update(user, Message.bersaglioNonValido());

            chosenTarget = Server.updateWithAnswer(user, Message.scegliBersaglio(possibleTargets));


        }


        for (Player player: connectedPlayers){

            if(player.getNickname().equals(chosenTarget)){

                targets.add(player);

            }

        }

    return targets;


    }

    @Override
    public boolean hasTargets(Player user) {

        if (Check.visiblePlayers(user).size() != 0){
            return true;
        }

        return false;
    }
}
