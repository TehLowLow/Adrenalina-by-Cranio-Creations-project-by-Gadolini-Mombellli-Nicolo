package it.polimi.se2019.Controller.Data.EffectBuilders;

import it.polimi.se2019.Model.*;

import java.util.ArrayList;

/**
 * This is the effect of the TagBack Grenade.
 */
public class TagBackGrenadeEffect extends Effect {

    public void applyEffect(Player user, Player target){

        //Token appartenente all'utilizzatore della granata venom
        Token new_token = new Token();
        new_token.setChampionName(user.getPlayerboard().getChampionName());

        //Assegno il token alla playerboard del giocatore bersagliato.
        target.getPlayerboard().getMarker().add(new_token);

    }

    public ArrayList<Player> getTargets(Player player, Map map){


        Cell current_cell = player.getPosition();


        return new ArrayList<Player>();
    }

}
