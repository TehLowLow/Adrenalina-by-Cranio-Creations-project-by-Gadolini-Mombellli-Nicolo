package it.polimi.se2019.Controller.Data.EffectBuilders;

import it.polimi.se2019.Controller.Data.MapBuilders.Map1Builder;
import it.polimi.se2019.Controller.Setup.BoardSetup;
import it.polimi.se2019.Model.*;
import it.polimi.se2019.Network.Server;
import org.junit.Test;
import java.util.ArrayList;

public class ConfigurationTest {



    public static void createTestConfiguration(){

        Map1Builder map1Builder = new Map1Builder();
        Board.setMap(map1Builder.build());

        BoardSetup boardSetup = new BoardSetup();
        boardSetup.build();
        Board.setMortalBlowTrack(new ArrayList<MortalBlow>());

        for(int i = 0; i<8; i++){

            MortalBlow mb = new MortalBlow();
            mb.setSkull(true);
            mb.setOverkill(false);
            mb.setKiller(null);
            Board.getMortalBlowTrack().add(mb);

        }

        for(int i = 0; i<5; i++){


          Player player = new Player();
          player.setPosition(Board.getMap().getBlueRoom().getCells().get(0));
          player.setNickname("player" + i);
          player.setPlayerboard(new Playerboard());
          Playerboard playerboard = player.getPlayerboard();
          playerboard.setDamage(new ArrayList<Token>());
          playerboard.setMarker(new ArrayList<Token>());
          playerboard.setPowerups(new ArrayList<Powerup>());
          playerboard.setWeapons(new ArrayList<Weapon>());
          playerboard.setAmmoCubes(new Rybamount());
          playerboard.setChampionName("champion" + 1);
          playerboard.setFrenzyboard(false);
          ArrayList <Integer> pbvalue = new ArrayList<>();
          pbvalue.add(8);
          pbvalue.add(6);
          pbvalue.add(4);
          pbvalue.add(2);
          pbvalue.add(1);
          pbvalue.add(1);
          playerboard.setPlayerboardValue(pbvalue);
          Server.connectedPlayers.add(player);

      }



    }


}
