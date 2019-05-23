package it.polimi.se2019.Controller.Adrenalina;

import it.polimi.se2019.Controller.Data.EffectBuilders.ConfigurationTest;
import it.polimi.se2019.Controller.Data.PowerUpBuilder.BlueTagbackGrenadeBuilder;
import it.polimi.se2019.Controller.Data.PowerUpBuilder.BlueTeleporterBuilder;
import it.polimi.se2019.Controller.Data.WeaponBuilders.BlueWeapons.LockRifleBuilder;
import it.polimi.se2019.Model.Board;
import it.polimi.se2019.Model.Player;
import it.polimi.se2019.Model.Powerup;
import it.polimi.se2019.Model.Weapon;
import it.polimi.se2019.Network.Server;
import it.polimi.se2019.Network.TestBot;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.Assert.assertEquals;

public class ActionTest {

    @Before
    public void prepareEnvironment() {

        ConfigurationTest.createTestConfiguration();

    }

    @Test
    public void perform() {
    }

    @Test
    public void performFrenzy() {
    }

    @Test
    public void reloadNotAffordable() {

        LockRifleBuilder lockRifleBuilder = new LockRifleBuilder();
        Weapon lockrifle = lockRifleBuilder.build();
        lockrifle.setLoaded(false);

        Player player = Server.connectedPlayers.get(0);
        player.getPlayerboard().getWeapons().add(lockrifle);


        Action.reload(player);

        assertEquals(false, player.getPlayerboard().getWeapons().get(0).isLoaded());


    }

    @Test
    public void reloadAffordable(){

        CopyOnWriteArrayList<String> answers = new CopyOnWriteArrayList<>();
        answers.add("si");
        answers.add("distruttore");

        TestBot.initAnswers(answers);


        LockRifleBuilder lockRifleBuilder = new LockRifleBuilder();
        Weapon lockrifle = lockRifleBuilder.build();
        lockrifle.setLoaded(false);

        Player player = Server.connectedPlayers.get(0);
        player.getPlayerboard().getWeapons().add(lockrifle);
        player.getPlayerboard().getAmmoCubes().setBlueCubes(2);


        Action.reload(player);

        assertEquals(true, player.getPlayerboard().getWeapons().get(0).isLoaded());
        assertEquals(0, player.getPlayerboard().getAmmoCubes().getBlue());


    }

    @Test
    public void usePowerUpNotAvailable() {

        BlueTagbackGrenadeBuilder blueTagbackGrenadeBuilder = new BlueTagbackGrenadeBuilder();
        Powerup blueTagbackGrenade = blueTagbackGrenadeBuilder.build();

        Player player = Server.connectedPlayers.get(0);

        player.getPlayerboard().getPowerups().add(blueTagbackGrenade);


        boolean result = Action.usePowerUp(player);

        assertEquals(false, result);


    }

    @Test
    public void usePowerUpAvailable(){

        BlueTeleporterBuilder blueTeleporterBuilder = new BlueTeleporterBuilder();
        Powerup blueTeleporter = blueTeleporterBuilder.build();

        CopyOnWriteArrayList<String> answers = new CopyOnWriteArrayList<>();
        answers.add("b");
        answers.add("si");
        answers.add("Cristiano Ronaldo");
        answers.add("Teletrasporto Blu");
        answers.add("99");
        answers.add("1");

        TestBot.initAnswers(answers);

        Player player = Server.connectedPlayers.get(0);

        player.getPlayerboard().getPowerups().add(blueTeleporter);


        boolean result = Action.usePowerUp(player);

        assertEquals(true, result);
        assertEquals(Board.getMap().getBlueRoom().getCells().get(1), player.getPosition());

    }
}