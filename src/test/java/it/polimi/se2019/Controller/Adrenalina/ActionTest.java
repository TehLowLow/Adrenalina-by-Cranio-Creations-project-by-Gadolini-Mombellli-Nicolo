package it.polimi.se2019.Controller.Adrenalina;

import it.polimi.se2019.Controller.Data.EffectBuilders.*;
import it.polimi.se2019.Controller.Data.LootBuilders.PrbBuilder;
import it.polimi.se2019.Controller.Data.PowerUpBuilder.BlueTagbackGrenadeBuilder;
import it.polimi.se2019.Controller.Data.PowerUpBuilder.BlueTeleporterBuilder;
import it.polimi.se2019.Controller.Data.WeaponBuilders.BlueWeapons.LockRifleBuilder;
import it.polimi.se2019.Controller.Data.WeaponBuilders.RedWeapons.FlamethrowerBuilder;
import it.polimi.se2019.Controller.Data.WeaponBuilders.RedWeapons.HeatseekerBuilder;
import it.polimi.se2019.Controller.Data.WeaponBuilders.YellowWeapons.ShotgunBuilder;
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
    public void performPickUpWeapon() {

        CopyOnWriteArrayList<String> answers = new CopyOnWriteArrayList<>();
        answers.add("raccogli");
        answers.add("no");
        answers.add("m");
        answers.add("lanciafiamme");
        answers.add("aa");
        answers.add("Distruttore");

        TestBot.initAnswers(answers);

        FlamethrowerBuilder flamethrowerBuilder = new FlamethrowerBuilder();
        Weapon flamethrower = flamethrowerBuilder.build();

        Cell spawn = (SpawnCell) Board.getMap().getBlueRoom().getCells().get(0);

        ((SpawnCell) spawn).getAvailableWeapons().add(flamethrower);

        Player player = Server.connectedPlayers.get(0);

        LockRifleBuilder lockRifleBuilder1 = new LockRifleBuilder();
        Weapon lockrifle1 = lockRifleBuilder1.build();
        lockrifle1.setLoaded(false);
        lockrifle1.setBaseEffect(new LockRifleEffect());
        player.getPlayerboard().getWeapons().add(lockrifle1);


        LockRifleBuilder lockRifleBuilder2 = new LockRifleBuilder();
        Weapon lockrifle2 = lockRifleBuilder2.build();
        lockrifle2.setLoaded(false);
        lockrifle2.setBaseEffect(new LockRifleEffect());
        player.getPlayerboard().getWeapons().add(lockrifle2);


        LockRifleBuilder lockRifleBuilder3 = new LockRifleBuilder();
        Weapon lockrifle3 = lockRifleBuilder3.build();
        lockrifle3.setLoaded(false);
        lockrifle3.setBaseEffect(new LockRifleEffect());
        player.getPlayerboard().getWeapons().add(lockrifle3);


        Action.perform(player);

        assertEquals(1, player.getPlayerboard().getWeapons().size());
        assertEquals("Lanciafiamme", player.getPlayerboard().getWeapons().get(0).getName());


    }

    @Test
    public void performPickUpLoot() {

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
    public void performUnavailableShot() {

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
    public void performAvailableShot() {

        CopyOnWriteArrayList<String> answers = new CopyOnWriteArrayList<>();
        answers.add("spara");
        answers.add("a");
        answers.add("distruttore");
        answers.add("b");
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
    public void shotWithOnlyBase(){

        CopyOnWriteArrayList<String> answers = new CopyOnWriteArrayList<>();
        answers.add("m");
        answers.add("spara");
        answers.add("w");
        answers.add("Distruttore");
        answers.add("Razzo termico");
        answers.add("t");
        answers.add("player1");

        TestBot.initAnswers(answers);


        HeatseekerBuilder heatseekerBuilder = new HeatseekerBuilder();
        Weapon heatseeker = heatseekerBuilder.build();
        heatseeker.setLoaded(true);
        heatseeker.setBaseEffect(new BasicHeatSeeker());

        Player user = Server.connectedPlayers.get(0);

        user.getPlayerboard().getWeapons().add(heatseeker);



        LockRifleBuilder lockRifleBuilder = new LockRifleBuilder();
        Weapon lockrifle = lockRifleBuilder.build();
        lockrifle.setLoaded(false);
        lockrifle.setBaseEffect(new LockRifleEffect());



        user.getPlayerboard().getWeapons().add(lockrifle);



        user.setPosition(Board.getMap().getYellowRoom().getCells().get(0));

        Action.perform(user);

        assertEquals(3, Server.connectedPlayers.get(1).getPlayerboard().getDamage().size());

    }

    @Test
    public void performEnhancedPickUp() {

        Player player = Server.connectedPlayers.get(0);
        player.setPosition(Board.getMap().getBlueRoom().getCells().get(1));

        for (int i = 0; i < 3; i++) {

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
    public void performEnhancedShot() {

        Player player = Server.connectedPlayers.get(0);
        player.setPosition(Board.getMap().getRedRoom().getCells().get(1));


        for (int i = 0; i < 6; i++) {

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
    public void performFrenzyPickUp() {

        Player player = Server.connectedPlayers.get(0);
        player.setPosition(Board.getMap().getBlueRoom().getCells().get(1));

        for (int i = 0; i < 3; i++) {

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
    public void performEnhancedFrenzyPickUp() {

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
    public void performFrenzyShot() {

        Player player = Server.connectedPlayers.get(0);
        player.setPosition(Board.getMap().getRedRoom().getCells().get(1));


        CopyOnWriteArrayList<String> answers = new CopyOnWriteArrayList<>();
        answers.add("spara");
        answers.add("giu");
        answers.add("stop");
        answers.add("su");
        answers.add("sinistra");
        answers.add("Siuuuuuu!");
        answers.add("distruttore");
        answers.add("distruttore");
        answers.add("player1");
        TestBot.initAnswers(answers);

        LockRifleBuilder lockRifleBuilder = new LockRifleBuilder();
        Weapon lockrifle = lockRifleBuilder.build();
        lockrifle.setLoaded(false);
        lockrifle.setBaseEffect(new LockRifleEffect());


        player.getPlayerboard().getWeapons().add(lockrifle);

        player.getPlayerboard().getAmmoCubes().setBlueCubes(2);

        Action.performFrenzy(player, false);

        assertEquals(false, player.getPlayerboard().getWeapons().get(0).isLoaded());
        assertEquals(2, Server.connectedPlayers.get(1).getPlayerboard().getDamage().size());
        assertEquals(1, Server.connectedPlayers.get(1).getPlayerboard().getMarker().size());
        assertEquals(0, player.getPlayerboard().getAmmoCubes().getBlue());


    }


    @Test
    public void performEnhancedFrenzyShot() {

        Player player = Server.connectedPlayers.get(0);
        player.setPosition(Board.getMap().getWhiteRoom().getCells().get(0));


        CopyOnWriteArrayList<String> answers = new CopyOnWriteArrayList<>();
        answers.add("spara");
        answers.add("giu");
        answers.add("stop");
        answers.add("destra");
        answers.add("su");
        answers.add("giu");
        answers.add("sinistra");
        answers.add("Gigi Buffon");
        answers.add("distruttore");
        answers.add("distruttore");
        answers.add("player1");
        TestBot.initAnswers(answers);

        LockRifleBuilder lockRifleBuilder = new LockRifleBuilder();
        Weapon lockrifle = lockRifleBuilder.build();
        lockrifle.setLoaded(false);
        lockrifle.setBaseEffect(new LockRifleEffect());


        player.getPlayerboard().getWeapons().add(lockrifle);

        player.getPlayerboard().getAmmoCubes().setBlueCubes(2);

        Action.performFrenzy(player, true);

        assertEquals(false, player.getPlayerboard().getWeapons().get(0).isLoaded());
        assertEquals(2, Server.connectedPlayers.get(1).getPlayerboard().getDamage().size());
        assertEquals(1, Server.connectedPlayers.get(1).getPlayerboard().getMarker().size());
        assertEquals(0, player.getPlayerboard().getAmmoCubes().getBlue());


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
    public void reloadAffordable() {

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
    public void usePowerUpAvailable() {

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

    @Test
    public void performBasicAndAlternative() {

        //longbarrel basicshotgun


        ShotgunBuilder sb = new ShotgunBuilder();
        Weapon shotgun = sb.build();
        shotgun.setLoaded(true);
        shotgun.setBaseEffect(new BasicShotgun());
        shotgun.setAlternativeEffect(new LongBarrelMode());

        CopyOnWriteArrayList<Weapon> wp = new CopyOnWriteArrayList<>();

        wp.add(shotgun);


        CopyOnWriteArrayList<String> answers = new CopyOnWriteArrayList<>();
        answers.add("spara");
        answers.add("Fucile a pompa");
        answers.add("base");
        answers.add("player1");
        answers.add("si");
        answers.add("sinistra");
        answers.add("spara");
        answers.add("Fucile a pompa");
        answers.add("alternativo");
        answers.add("player1");

        TestBot.initAnswers(answers);

        Player shooter = Server.connectedPlayers.get(0);
        Player target = Server.connectedPlayers.get(1);

        shooter.getPlayerboard().setWeapons(wp);

        Action.perform(shooter);

        shotgun.setLoaded(true);

        Action.perform(shooter);

        assertEquals(5, target.getPlayerboard().getDamage().size());
        assertEquals(Board.getMap().getBlueRoom().getCells().get(2), target.getPosition());


    }

}


