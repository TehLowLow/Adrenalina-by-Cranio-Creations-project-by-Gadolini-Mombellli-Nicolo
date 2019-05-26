package it.polimi.se2019.Controller.Adrenalina;

import it.polimi.se2019.Controller.Adrenalina.Exceptions.EmptyDeckException;
import it.polimi.se2019.Controller.Adrenalina.Exceptions.LimitPowerUpException;
import it.polimi.se2019.Controller.Data.EffectBuilders.ConfigurationTest;
import it.polimi.se2019.Controller.Data.PowerUpBuilder.BlueNewtonBuilder;
import it.polimi.se2019.Controller.Data.PowerUpBuilder.RedNewtonBuilder;
import it.polimi.se2019.Controller.Data.PowerUpBuilder.YellowNewtonBuilder;
import it.polimi.se2019.Controller.Setup.BoardSetup;
import it.polimi.se2019.Controller.Setup.LootDeckSetup;
import it.polimi.se2019.Controller.Setup.PowerUpDeckSetup;
import it.polimi.se2019.Controller.Setup.WeaponDeckSetup;
import it.polimi.se2019.Model.*;
import it.polimi.se2019.Network.Server;
import it.polimi.se2019.Network.TestBot;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.Assert.*;

public class InteractionTest {

    @Before
    public void prepareEnvironment() {

        ConfigurationTest.createTestConfiguration();

    }

    @Test
    public void drawPowerUp() {

        PowerUpDeckSetup powerUpDeckSetup = new PowerUpDeckSetup();
        Board.setPowerUpDeck(powerUpDeckSetup.build());
        Player user = Server.connectedPlayers.get(0);
        try {
            Interaction.drawPowerUp(user);
            assertTrue(!Board.getPowerUpDeck().contains(user.getPlayerboard().getPowerups().get(0)));
        }
        catch(Exception e){
            System.out.println("Catturata eccezione non prevista.");
        }

    }

    @Test
    public void drawPowerUpEmptyDeck(){

        Board.setPowerUpDeck(new CopyOnWriteArrayList<Powerup>());
        Player user = Server.connectedPlayers.get(0);
        try {
            Interaction.drawPowerUp(user);
        }
        catch(EmptyDeckException e){
            assertTrue(true);
            return;
        }
        catch (LimitPowerUpException e){

        }

        assertTrue(false);


    }

    @Test
    public void drawPowerUpMoreThanThree(){

        PowerUpDeckSetup powerUpDeckSetup = new PowerUpDeckSetup();
        Board.setPowerUpDeck(powerUpDeckSetup.build());

        Player user = Server.connectedPlayers.get(0);


        try {
            Interaction.drawPowerUp(user);
            Interaction.drawPowerUp(user);
            Interaction.drawPowerUp(user);
            Interaction.drawPowerUp(user);

        }
        catch(LimitPowerUpException e){
            assertTrue(true);
            return;
        }
        catch (EmptyDeckException e){

        }

        assertTrue(false);


    }

    @Test
    public void discardPowerUp() {

        PowerUpDeckSetup powerUpDeckSetup = new PowerUpDeckSetup();
        Board.setPowerUpDeck(powerUpDeckSetup.build());
        Board.setDiscardedPowerUps(new CopyOnWriteArrayList<Powerup>());

        Player user = Server.connectedPlayers.get(0);

        try {
            Interaction.drawPowerUp(user);
        }
        catch(Exception e){

        }

        Interaction.discardPowerUp(user, user.getPlayerboard().getPowerups().get(0));

        assertTrue(user.getPlayerboard().getPowerups().size()==0);
        assertTrue(Board.getDiscardedPowerUps().size()==1);


    }

    @Test
    public void placeWeapons() {

        Interaction.placeWeapons();

        Map map = Board.getMap();

        CopyOnWriteArrayList<Room> spawnRooms = new CopyOnWriteArrayList<Room>();
        CopyOnWriteArrayList<SpawnCell> spawnCells = new CopyOnWriteArrayList<SpawnCell>();
        spawnRooms.add(map.getBlueRoom());
        spawnRooms.add(map.getYellowRoom());
        spawnRooms.add(map.getRedRoom());

        for(Room room : spawnRooms){
            CopyOnWriteArrayList <Cell> cells = room.getCells();
            for(Cell cell : cells){
                if(cell.getName().equals("spawnCell")){
                    spawnCells.add((SpawnCell)cell);
                }
            }
        }

        for(SpawnCell spawnCell : spawnCells){

            assertTrue(spawnCell.getAvailableWeapons().size()==3);

        }


    }


    @Test
    public void payEnoughCubes() {

        BoardSetup boardSetup = new BoardSetup();
        boardSetup.build();

        Rybamount billRybamount = new Rybamount();
        billRybamount.setYellowCubes(1);
        billRybamount.setRedCubes(2);
        billRybamount.setBlueCubes(0);

        Player user = Server.connectedPlayers.get(0);

        user.getPlayerboard().getAmmoCubes().setYellowCubes(3);
        user.getPlayerboard().getAmmoCubes().setRedCubes(2);
        user.getPlayerboard().getAmmoCubes().setBlueCubes(0);

        Interaction.pay(user, billRybamount);

        assertTrue(user.getPlayerboard().getAmmoCubes().getYellow()==2);
        assertTrue(user.getPlayerboard().getAmmoCubes().getRed()==0);
        assertTrue(user.getPlayerboard().getAmmoCubes().getBlue()==0);


    }

    @Test
    public void payWithForcedPowerup(){

        CopyOnWriteArrayList<String> answers = new CopyOnWriteArrayList<>();

        answers.add("0");

        TestBot.initAnswers(answers);

        BoardSetup boardSetup = new BoardSetup();
        boardSetup.build();

        Rybamount billRybamount = new Rybamount();
        billRybamount.setYellowCubes(1);
        billRybamount.setRedCubes(2);
        billRybamount.setBlueCubes(1);

        Player user = Server.connectedPlayers.get(0);

        user.getPlayerboard().getAmmoCubes().setYellowCubes(3);
        user.getPlayerboard().getAmmoCubes().setRedCubes(2);
        user.getPlayerboard().getAmmoCubes().setBlueCubes(0);

        BlueNewtonBuilder blueNewtonBuilder = new BlueNewtonBuilder();
        Powerup blueNewton = blueNewtonBuilder.build();

        user.getPlayerboard().getPowerups().add(blueNewton);

        Interaction.pay(user, billRybamount);

        assertTrue(user.getPlayerboard().getAmmoCubes().getYellow()==2);
        assertTrue(user.getPlayerboard().getAmmoCubes().getRed()==0);
        assertTrue(user.getPlayerboard().getAmmoCubes().getBlue()==0);


    }

    @Test
    public void payWithTwoPowerups(){

        CopyOnWriteArrayList<String> answers = new CopyOnWriteArrayList<>();

        answers.add("si");
        answers.add("0");
        answers.add("si");
        answers.add("0");
        answers.add("no");

        TestBot.initAnswers(answers);

        BoardSetup boardSetup = new BoardSetup();
        boardSetup.build();

        Rybamount billRybamount = new Rybamount();
        billRybamount.setYellowCubes(1);
        billRybamount.setRedCubes(2);
        billRybamount.setBlueCubes(1);

        Player user = Server.connectedPlayers.get(0);

        user.getPlayerboard().getAmmoCubes().setYellowCubes(3);
        user.getPlayerboard().getAmmoCubes().setRedCubes(2);
        user.getPlayerboard().getAmmoCubes().setBlueCubes(1);

        BlueNewtonBuilder blueNewtonBuilder = new BlueNewtonBuilder();
        RedNewtonBuilder redNewtonBuilder = new RedNewtonBuilder();
        YellowNewtonBuilder yellowNewtonBuilder = new YellowNewtonBuilder();

        Powerup blueNewton = blueNewtonBuilder.build();
        Powerup yellowNewton = yellowNewtonBuilder.build();
        Powerup redNewton = redNewtonBuilder.build();


        user.getPlayerboard().getPowerups().add(yellowNewton);
        user.getPlayerboard().getPowerups().add(redNewton);
        user.getPlayerboard().getPowerups().add(blueNewton);

        Interaction.pay(user, billRybamount);

        assertTrue(user.getPlayerboard().getAmmoCubes().getYellow()==3);
        assertTrue(user.getPlayerboard().getAmmoCubes().getRed()==1);
        assertTrue(user.getPlayerboard().getAmmoCubes().getBlue()==0);


    }

    @Test
    public void payWithOneForcedAndOneChosen(){

        CopyOnWriteArrayList<String> answers = new CopyOnWriteArrayList<>();

        answers.add("0");
        answers.add("si");
        answers.add("0");
        answers.add("no");

        TestBot.initAnswers(answers);

        BoardSetup boardSetup = new BoardSetup();
        boardSetup.build();

        Rybamount billRybamount = new Rybamount();
        billRybamount.setYellowCubes(1);
        billRybamount.setRedCubes(2);
        billRybamount.setBlueCubes(1);

        Player user = Server.connectedPlayers.get(0);

        user.getPlayerboard().getAmmoCubes().setYellowCubes(0);
        user.getPlayerboard().getAmmoCubes().setRedCubes(2);
        user.getPlayerboard().getAmmoCubes().setBlueCubes(1);

        BlueNewtonBuilder blueNewtonBuilder = new BlueNewtonBuilder();
        RedNewtonBuilder redNewtonBuilder = new RedNewtonBuilder();
        YellowNewtonBuilder yellowNewtonBuilder = new YellowNewtonBuilder();

        Powerup blueNewton = blueNewtonBuilder.build();
        Powerup yellowNewton = yellowNewtonBuilder.build();
        Powerup redNewton = redNewtonBuilder.build();


        user.getPlayerboard().getPowerups().add(yellowNewton);
        user.getPlayerboard().getPowerups().add(redNewton);
        user.getPlayerboard().getPowerups().add(blueNewton);

        Interaction.pay(user, billRybamount);

        assertTrue(user.getPlayerboard().getAmmoCubes().getYellow()==0);
        assertTrue(user.getPlayerboard().getAmmoCubes().getRed()==1);
        assertTrue(user.getPlayerboard().getAmmoCubes().getBlue()==0);

        assertTrue(user.getPlayerboard().getPowerups().size()==1);
        assertTrue(user.getPlayerboard().getPowerups().contains(blueNewton));

    }


    @Test
    public void recoverLoots() {

        LootDeckSetup lootDeckSetup = new LootDeckSetup();
        CopyOnWriteArrayList<Loot> discardLootDeck = lootDeckSetup.build();
        Board.setDiscardedLoot(discardLootDeck);
        Interaction.recoverLoots();

        assertTrue(Board.getDiscardedLoot().size()==0);
        assertTrue(Board.getLootDeck().containsAll(discardLootDeck));

    }

    @Test
    public void recoverPowerUps(){

        PowerUpDeckSetup powerUpDeckSetup = new PowerUpDeckSetup();
        CopyOnWriteArrayList<Powerup> discardPUDeck = powerUpDeckSetup.build();
        Board.setDiscardedPowerUps(discardPUDeck);
        Interaction.recoverPowerUps();

        assertTrue(Board.getDiscardedPowerUps().size()==0);
        assertTrue(Board.getPowerUpDeck().containsAll(discardPUDeck));


    }




}