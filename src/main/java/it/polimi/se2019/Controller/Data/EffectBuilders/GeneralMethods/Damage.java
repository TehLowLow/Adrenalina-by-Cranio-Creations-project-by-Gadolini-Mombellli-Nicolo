package it.polimi.se2019.Controller.Data.EffectBuilders.GeneralMethods;

import it.polimi.se2019.Model.Player;
import it.polimi.se2019.Model.Token;
import it.polimi.se2019.View.Message;

import java.util.ArrayList;

import static it.polimi.se2019.Network.Server.update;

public class Damage {

    private static void giveOneDamage(Player user, Player target){

        Token damage = new Token();
        damage.setChampionName(user.getPlayerboard().getChampionName());
        ArrayList <Token> damages = target.getPlayerboard().getDamage();
        damages.add(damage);
        target.getPlayerboard().setDamage(damages);
    }

    public static void giveDamage(int damageAmount, Player user, Player target){

        for(int i=0; i<damageAmount; i++){

            giveOneDamage(user, target);

        }

        update(target, Message.colpito(user));

    }

    private static void giveOneMarker(Player user, Player target){

        Token marker = new Token();
        marker.setChampionName(user.getPlayerboard().getChampionName());
        ArrayList <Token> markers = target.getPlayerboard().getMarker();
        markers.add(marker);
        target.getPlayerboard().setMarker(markers);

    }

    public static void giveMarker(int markerAmount, Player user, Player target){

        for(int i=0; i<markerAmount; i++){

            giveOneMarker(user, target);

        }

        update(target, Message.colpito(user));

    }



}
