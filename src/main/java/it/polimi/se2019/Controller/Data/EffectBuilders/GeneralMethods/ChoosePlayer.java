package it.polimi.se2019.Controller.Data.EffectBuilders.GeneralMethods;

import it.polimi.se2019.Controller.Adrenalina.InputCheck;
import it.polimi.se2019.Model.Player;
import it.polimi.se2019.Network.Server;
import it.polimi.se2019.View.Message;

import java.util.concurrent.CopyOnWriteArrayList;

public class ChoosePlayer {

    public static Player one(Player user, CopyOnWriteArrayList<Player> possibleTargets){

        boolean hasChosen = false;
        Player target = new Player();

        while(!hasChosen){

            String targetNickname = Server.updateWithAnswer(user, Message.scegliBersaglio(possibleTargets));

            if(!InputCheck.nicknameCheck(targetNickname)){
                Server.update(user,Message.inputError());
            }

            for(Player possibleTarget : possibleTargets){

                if(possibleTarget.getNickname().equalsIgnoreCase(targetNickname)){
                    target = possibleTarget;
                    hasChosen = true;
                }
            }

            if(!hasChosen){

                Server.update(user, Message.bersaglioNonValido());
                continue;
            }



        }

        return target;
    }

}
