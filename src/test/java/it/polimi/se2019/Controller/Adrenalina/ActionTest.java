package it.polimi.se2019.Controller.Adrenalina;

import it.polimi.se2019.Controller.Data.EffectBuilders.BasicFlamethrower;
import it.polimi.se2019.Controller.Data.EffectBuilders.ConfigurationTest;
import it.polimi.se2019.Controller.Data.EffectBuilders.LockRifleEffect;
import it.polimi.se2019.Controller.Data.LootBuilders.PrbBuilder;
import it.polimi.se2019.Controller.Data.PowerUpBuilder.BlueTagbackGrenadeBuilder;
import it.polimi.se2019.Controller.Data.PowerUpBuilder.BlueTeleporterBuilder;
import it.polimi.se2019.Controller.Data.WeaponBuilders.BlueWeapons.LockRifleBuilder;
import it.polimi.se2019.Controller.Data.WeaponBuilders.RedWeapons.FlamethrowerBuilder;
import it.polimi.se2019.Model.*;
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
    public void performMove() {

        CopyOnWriteArrayList<String> answers = new CopyOnWriteArrayList<>();
        answers.add("muovi");
        answers.add("3");
        answers.add("0");
        TestBot.initAnswers(answers);

        Player player = Server.connectedPlayers.get(0);
        Action.perform(player);

        assertEquals(Board.getMap().getRedRoom().getCells().get(0), player.getPosition());


    }

    @Test
    public void performPickUpWeapon(){

        CopyOnWriteArrayList<String> answers = new CopyOnWriteArrayList<>();
        answers.add("raccogli");
        answers.add("no");
        answers.add("lanciafiamme");

        TestBot.initAnswers(answers);

        FlamethrowerBuilder flamethrowerBuilder = new FlamethrowerBuilder();
        Weapon flamethrower = flamethrowerBuilder.build();

        Cell spawn = (SpawnCell) Board.getMap().getBlueRoom().getCells().get(0);

        ((SpawnCell)spawn).getAvailableWeapons().add(flamethrower);

        Player player = Server.connectedPlayers.get(0);

        Action.perform(player);

        assertEquals(1, player.getPlayerboard().getWeapons().size());
        assertEquals("Lanciafiamme", player.getPlayerboard().getWeapons().get(0).getName());


    }

    @Test
    public void performPickUpLoot(){

        CopyOnWriteArrayList<String> answers = new CopyOnWriteArrayList<>();
        answers.add("Raccogli");
        answers.add("si");
        answers.add("0");

        TestBot.initAnswers(answers);

        Cell lootCell = (LootCell) Board.getMap().getBlueRoom().getCells().get(2);

        PrbBuilder prbBuilder = new PrbBuilder();
        Loot prb = prbBuilder.build();

        ((LootCell) lootCell).setLoot(prb);


        Player player = Server.connectedPlayers.get(0);

        Action.perform(player);

        assertEquals(1, player.getPlayerboard().getPowerups().size());
        assertEquals(1, player.getPlayerboard().getAmmoCubes().getRed());
        assertEquals(1, player.getPlayerboard().getAmmoCubes().getBlue());
        assertEquals(0, player.getPlayerboard().getAmmoCubes().getYellow());


    }


    @Test
    public void performUnavailableShot(){

        CopyOnWriteArrayList<String> answers = new CopyOnWriteArrayList<>();
        answers.add("Spara");
        answers.add("Muovi");
        answers.add("3");
        answers.add("0");
        TestBot.initAnswers(answers);

        FlamethrowerBuilder flamethrowerBuilder = new FlamethrowerBuilder();
        Weapon flamethrower = flamethrowerBuilder.build();
        flamethrower.setBaseEffect(new BasicFlamethrower());
        flamethrower.setLoaded(false);


        Player player = Server.connectedPlayers.get(0);

        player.getPlayerboard().getWeapons().add(flamethrower);

        Action.perform(player);

        assertEquals(1, player.getPlayerboard().getWeapons().size());
        assertEquals(false, player.getPlayerboard().getWeapons().get(0).isLoaded());
        assertEquals(false, player.getPosition().equals(Board.getMap().getBlueRoom().getCells().get(0)));


    }

    @Test
    public void performAvailableShot(){

        CopyOnWriteArrayList<String> answers = new CopyOnWriteArrayList<>();
        answers.add("spara");
        answers.add("distruttore");
        answers.add("player1");
        TestBot.initAnswers(answers);

        LockRifleBuilder lockRifleBuilder = new LockRifleBuilder();
        Weapon lockrifle = lockRifleBuilder.build();
        lockrifle.setLoaded(true);
        lockrifle.setBaseEffect(new LockRifleEffect());

        Player player = Server.connectedPlayers.get(0);

        player.getPlayerboard().getWeapons().add(lockrifle);

        Action.perform(player);

        assertEquals(false, player.getPlayerboard().getWeapons().get(0).isLoaded());
        assertEquals(2, Server.connectedPlayers.get(1).getPlayerboard().getDamage().size());
        assertEquals(1, Server.connectedPlayers.get(1).getPlayerboard().getMarker().size());


    }

    @Test
    public void performEnhancedPickUp(){

        Player player = Server.connectedPlayers.get(0);
        player.setPosition(Board.getMap().getBlueRoom().getCells().get(1));

        for (int i=0; i<3; i++){

            player.getPlayerboard().getDamage().add(new Token());
            player.getPlayerboard().getDamage().get(i).setChampionName("Violetta");

        }

        CopyOnWriteArrayList<String> answers = new CopyOnWriteArrayList<>();
        answers.add("raccogli");
        answers.add("si");
        answers.add("0");
        answers.add("si");
        answers.add("0");
        answers.add("lanciafiamme");

        TestBot.initAnswers(answers);

        FlamethrowerBuilder flamethrowerBuilder = new FlamethrowerBuilder();
        Weapon flamethrower = flamethrowerBuilder.build();

        Cell spawn = (SpawnCell) Board.getMap().getBlueRoom().getCells().get(0);

        ((SpawnCell) spawn).getAvailableWeapons().add(flamethrower);



        Action.perform(player);

        assertEquals(1, player.getPlayerboard().getWeapons().size());
        assertEquals("Lanciafiamme", player.getPlayerboard().getWeapons().get(0).getName());

    }


    @Test
    public void performEnhancedShot(){

        Player player = Server.connectedPlayers.get(0);
        player.setPosition(Board.getMap().getRedRoom().getCells().get(1));


        for (int i=0; i<6; i++){

            player.getPlayerboard().getDamage().add(new Token());
            player.getPlayerboard().getDamage().get(i).setChampionName("Violetta");

        }


        CopyOnWriteArrayList<String> answers = new CopyOnWriteArrayList<>();
        answers.add("spara");
        answers.add("giu");
        answers.add("stop");
        answers.add("su");
        answers.add("sinistra");
        answers.add("distruttore");
        answers.add("player1");
        TestBot.initAnswers(answers);

        LockRifleBuilder lockRifleBuilder = new LockRifleBuilder();
        Weapon lockrifle = lockRifleBuilder.build();
        lockrifle.setLoaded(true);
        lockrifle.setBaseEffect(new LockRifleEffect());

        player.getPlayerboard().getWeapons().add(lockrifle);

        Action.perform(player);

        assertEquals(false, player.getPlayerboard().getWeapons().get(0).isLoaded());
        assertEquals(2, Server.connectedPlayers.get(1).getPlayerboard().getDamage().size());
        assertEquals(1, Server.connectedPlayers.get(1).getPlayerboard().getMarker().size());





    }


    @Test
    public void performFrenzyMove() {

        CopyOnWriteArrayList<String> answers = new CopyOnWriteArrayList<>();
        answers.add("muovi");
        answers.add("4");
        answers.add("0");
        TestBot.initAnswers(answers);

        Player player = Server.connectedPlayers.get(0);
        Action.performFrenzy(player, false);

        assertEquals(Board.getMap().getWhiteRoom().getCells().get(1), player.getPosition());



    }


    @Test
    public void performFrenzyPickUp(){

        Player player = Server.connectedPlayers.get(0);
        player.setPosition(Board.getMap().getBlueRoom().getCells().get(1));

        for (int i=0; i<3; i++){

            player.getPlayerboard().getDamage().add(new Token());
            player.getPlayerboard().getDamage().get(i).setChampionName("Violetta");

        }

        CopyOnWriteArrayList<String> answers = new CopyOnWriteArrayList<>();
        answers.add("raccogli");
        answers.add("si");
        answers.add("0");
        answers.add("si");
        answers.add("0");
        answers.add("lanciafiamme");

        TestBot.initAnswers(answers);

        FlamethrowerBuilder flamethrowerBuilder = new FlamethrowerBuilder();
        Weapon flamethrower = flamethrowerBuilder.build();

        Cell spawn = (SpawnCell) Board.getMap().getBlueRoom().getCells().get(0);

        ((SpawnCell) spawn).getAvailableWeapons().add(flamethrower);



        Action.performFrenzy(player, false);

        assertEquals(1, player.getPlayerboard().getWeapons().size());
        assertEquals("Lanciafiamme", player.getPlayerboard().getWeapons().get(0).getName());

    }

    @Test
    public void performEnhancedFrenzyPickUp(){

        Player player = Server.connectedPlayers.get(0);
        player.setPosition(Board.getMap().getRedRoom().getCells().get(0));

        CopyOnWriteArrayList<String> answers = new CopyOnWriteArrayList<>();
        answers.add("raccogli");
        answers.add("si");
        answers.add("0");
        answers.add("si");
        answers.add("0");
        answers.add("si");
        answers.add("0");
        answers.add("lanciafiamme");

        TestBot.initAnswers(answers);

        FlamethrowerBuilder flamethrowerBuilder = new FlamethrowerBuilder();
        Weapon flamethrower = flamethrowerBuilder.build();

        Cell spawn = (SpawnCell) Board.getMap().getBlueRoom().getCells().get(0);

        ((SpawnCell) spawn).getAvailableWeapons().add(flamethrower);



        Action.performFrenzy(player, true);

        assertEquals(1, player.getPlayerboard().getWeapons().size());
        assertEquals("Lanciafiamme", player.getPlayerboard().getWeapons().get(0).getName());

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