package it.polimi.se2019.Controller.Data.EffectBuilders;

import it.polimi.se2019.Controller.Data.MapBuilders.Map1Builder;
import it.polimi.se2019.Controller.Setup.BoardSetup;
import it.polimi.se2019.Model.*;
import it.polimi.se2019.Network.Server;

import java.util.concurrent.CopyOnWriteArrayList;

public class ConfigurationTest {



    public static void createTestConfiguration() {

        Server.connectedPlayers = new CopyOnWriteArrayList<>();
        Map1Builder map1Builder = new Map1Builder();
        Board.setMap(map1Builder.build());

        BoardSetup boardSetup = new BoardSetup();
        boardSetup.build();
        Board.setMortalBlowTrack(new CopyOnWriteArrayList<MortalBlow>());
        CopyOnWriteArrayList<Integer> mbTrackValue = new CopyOnWriteArrayList<>();
        mbTrackValue.add(8);
        mbTrackValue.add(6);
        mbTrackValue.add(4);
        mbTrackValue.add(2);
        mbTrackValue.add(1);
        mbTrackValue.add(1);

        Board.setMortalBlowTrackValue(mbTrackValue);

        for (int i = 0; i < 8; i++) {

            MortalBlow mb = new MortalBlow();
            mb.setSkull(true);
            mb.setOverkill(false);
            mb.setKiller(null);
            Board.getMortalBlowTrack().add(mb);

        }

        for (int i = 0; i < 5; i++) {


            Player player = new Player();
            player.setPosition(Board.getMap().getBlueRoom().getCells().get(0));
            player.setNickname("player" + i);
            player.setConnectionTech("testEnvironment");
            player.setPlayerboard(new Playerboard());
            Playerboard playerboard = player.getPlayerboard();
            playerboard.setDamage(new CopyOnWriteArrayList<Token>());
            playerboard.setMarker(new CopyOnWriteArrayList<Token>());
            playerboard.setPowerups(new CopyOnWriteArrayList<Powerup>());
            playerboard.setWeapons(new CopyOnWriteArrayList<Weapon>());
            playerboard.setAmmoCubes(new Rybamount());
            playerboard.getAmmoCubes().setBlueCubes(0);
            playerboard.getAmmoCubes().setRedCubes(0);
            playerboard.getAmmoCubes().setYellowCubes(0);
            playerboard.setChampionName("champion" + i);
            playerboard.setFrenzyboard(false);
            CopyOnWriteArrayList<Integer> pbvalue = new CopyOnWriteArrayList<>();
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
