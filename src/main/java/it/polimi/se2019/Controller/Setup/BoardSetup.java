package it.polimi.se2019.Controller.Setup;
import it.polimi.se2019.Model.*;

public class BoardSetup {

    public Board build(){

        Board board = new Board();

        WeaponDeckSetup weaponDeckSetup = new WeaponDeckSetup();
        MapSetup mapSetup = new MapSetup();
        board.setWeaponDeck(weaponDeckSetup.build());
        return board;

    }


}

