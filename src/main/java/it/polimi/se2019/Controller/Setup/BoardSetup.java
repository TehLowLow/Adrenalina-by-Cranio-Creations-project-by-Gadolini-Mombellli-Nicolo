package it.polimi.se2019.Controller.Setup;
import it.polimi.se2019.Model.*;

public class BoardSetup {

    public Board build(){

        Board board = new Board();

        WeaponDeckSetup weaponDeckSetup = new WeaponDeckSetup();
        board.setWeaponDeck(weaponDeckSetup.build());

        Map1Setup map1Setup = new Map1Setup();
        board.setMap(Map1Setup.build());

        LootDeckSetup lootDeckSetup = new LootDeckSetup();
        board.setLootDeck(lootDeckSetup.build());

        PowerUpDeckSetup powerUpDeckSetup = new PowerUpDeckSetup();
        board.setPowerUpDeck(powerUpDeckSetup.build());


        return board;

    }


}
