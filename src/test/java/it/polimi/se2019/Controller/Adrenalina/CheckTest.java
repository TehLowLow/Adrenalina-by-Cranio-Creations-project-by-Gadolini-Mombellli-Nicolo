package it.polimi.se2019.Controller.Adrenalina;

import it.polimi.se2019.Controller.Data.EffectBuilders.ConfigurationTest;
import it.polimi.se2019.Model.Board;
import it.polimi.se2019.Model.Player;
import it.polimi.se2019.Model.Token;
import it.polimi.se2019.Network.Server;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CheckTest {

    @Before
    public void prepareEnvironment() {

        ConfigurationTest.createTestConfiguration();

    }


    /*
    DEATH TESTS
     */


    @Test
    public void deathOverkilled() {

        Player damaged = Server.connectedPlayers.get(0);
        for (int i = 0; i < 12; i++) {

            damaged.getPlayerboard().getDamage().add(new Token());

        }
        int result = Check.death(damaged);
        assertEquals(2, result);

    }


    @Test
    public void deathKilled() {

        Player damaged = Server.connectedPlayers.get(0);
        for (int i = 0; i < 11; i++) {

            damaged.getPlayerboard().getDamage().add(new Token());

        }
        int result = Check.death(damaged);
        assertEquals(1, result);


    }

    @Test
    public void deathNotKilled() {

        Player damaged = Server.connectedPlayers.get(0);
        for (int i = 0; i < 10; i++) {

            damaged.getPlayerboard().getDamage().add(new Token());

        }
        int result = Check.death(damaged);
        assertEquals(0, result);

    }

    /*
    RESOLVE BOARD TESTS
     */


    @Test
    public void resolveBoardWithoutTie() {

        Player killed = Server.connectedPlayers.get(4);

        for (int i = 0; i < 11; i++) {

            Token damage = new Token();

            killed.getPlayerboard().getDamage().add(damage);

            if (i < 6) {

                killed.getPlayerboard().getDamage().get(i).setChampionName("champion0");

            }

            if (i >= 6 && i < 9) {

                killed.getPlayerboard().getDamage().get(i).setChampionName("champion1");

            }

            if (i >= 9) {

                killed.getPlayerboard().getDamage().get(i).setChampionName("champion2");

            }

        }

        Check.resolveBoard(killed, false);

        assertEquals(9, Server.connectedPlayers.get(0).getScore());
        assertEquals(6, Server.connectedPlayers.get(1).getScore());
        assertEquals(4, Server.connectedPlayers.get(2).getScore());
        assertEquals(0, Server.connectedPlayers.get(4).getPlayerboard().getDamage().size());
        assertEquals((Integer) 6, Server.connectedPlayers.get(4).getPlayerboard().getPlayerboardValue().get(0));
        assertEquals(8, Board.getMortalBlowTrack().size());
        assertEquals(Server.connectedPlayers.get(2), Board.getMortalBlowTrack().get(0).getKiller());
        assertEquals(false, Board.getMortalBlowTrack().get(0).isSkull());
        assertEquals(false, Board.getMortalBlowTrack().get(0).isOverkill());


    }


    @Test

    public void resolveBoardWithTie(){


        Player killed = Server.connectedPlayers.get(4);

        for (int i = 0; i < 12; i++) {

            Token damage = new Token();

            killed.getPlayerboard().getDamage().add(damage);

            if (i < 4) {

                killed.getPlayerboard().getDamage().get(i).setChampionName("champion0");

            }

            if (i >= 4 && i < 10) {

                killed.getPlayerboard().getDamage().get(i).setChampionName("champion1");

            }

            if (i >= 10) {

                killed.getPlayerboard().getDamage().get(i).setChampionName("champion2");

            }

        }

        Check.resolveBoard(killed, true);

        assertEquals(9, Server.connectedPlayers.get(0).getScore());
        assertEquals(6, Server.connectedPlayers.get(1).getScore());
        assertEquals(4, Server.connectedPlayers.get(2).getScore());
        assertEquals(0, Server.connectedPlayers.get(4).getPlayerboard().getDamage().size());
        assertEquals((Integer) 6, Server.connectedPlayers.get(4).getPlayerboard().getPlayerboardValue().get(0));
        assertEquals(8, Board.getMortalBlowTrack().size());
        assertEquals(Server.connectedPlayers.get(2), Board.getMortalBlowTrack().get(0).getKiller());
        assertEquals(false, Board.getMortalBlowTrack().get(0).isSkull());
        assertEquals(true, Board.getMortalBlowTrack().get(0).isOverkill());


    }




    @Test
    public void markers() {
    }

    @Test
    public void affordable() {
    }
}