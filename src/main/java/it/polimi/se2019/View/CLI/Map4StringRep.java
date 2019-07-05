package it.polimi.se2019.View.CLI;

import it.polimi.se2019.Model.Board;
import it.polimi.se2019.Model.LootCell;
import it.polimi.se2019.Model.Map;
import it.polimi.se2019.Model.Room;

import static it.polimi.se2019.View.CLI.Colors.*;
import static it.polimi.se2019.View.CLI.Location.*;

/**
 * Prints an ascii art of the map4.
 */
public class Map4StringRep {


    /**
     * prints an ascii art of the map4.
     * @return an ascii art of the map4, representing also the players in the cells.
     */
    public static String map4() {

        return
                        ANSI_RED_BACKGROUND + " " + "________________________" + " " + ANSI_RESET + " " + ANSI_BLUE_BACKGROUND + " " + "___________________________________________________" + " " + ANSI_RESET + " " + ANSI_GREEN_BACKGROUND + " " + "________________________" + " " + ANSI_RESET + "\n" +
                        ANSI_RED_BACKGROUND + "│" + firstRow(getCell(redRoom, "LootCell")) + "│" + ANSI_RESET + " " + ANSI_BLUE_BACKGROUND + "│" + firstRow(getCell(blueRoom, "LootCell")) + ". ." + firstRow(getCell(blueRoom, "SpawnCell")) + "│" + ANSI_RESET + " " + ANSI_GREEN_BACKGROUND + "│" + firstRow(getCell(greenRoom, "LootCell")) + "│" + ANSI_RESET + "\n" +
                        ANSI_RED_BACKGROUND + "│" + secondRow(getCell(redRoom, "LootCell")) + "│" + ANSI_RESET + " " + ANSI_BLUE_BACKGROUND + "│" + secondRow(getCell(blueRoom, "LootCell")) + ". ." + secondRow(getCell(blueRoom, "SpawnCell")) + "│" + ANSI_RESET + " " + ANSI_GREEN_BACKGROUND + "│" + secondRow(getCell(greenRoom, "LootCell")) + "│" + ANSI_RESET + "\n" +
                        ANSI_RED_BACKGROUND + "│" + thirdRow(getCell(redRoom, "Lootcell")) + ANSI_RESET + "͞" + "͞" + "͞" + ANSI_BLUE_BACKGROUND + thirdRow(getCell(blueRoom, "LootCell")) + ". ." + thirdRow(getCell(blueRoom, "SpawnCell")) + ANSI_RESET + "͞" + "͞" + "͞" + ANSI_GREEN_BACKGROUND + thirdRow(getCell(greenRoom, "LootCell")) + "│" + ANSI_RESET + "\n" +
                        ANSI_RED_BACKGROUND + "│" + fourthRow(getCell(redRoom, "Lootcell")) + ANSI_RESET + "͟͟͟" + ANSI_BLUE_BACKGROUND + fourthRow(getCell(blueRoom, "LootCell")) + ". ." + fourthRow(getCell(blueRoom, "Spawncell")) + ANSI_RESET + "͟͟͟" + ANSI_GREEN_BACKGROUND + fourthRow(getCell(greenRoom, "LootCell")) + "│" + ANSI_RESET + "\n" +
                        ANSI_RED_BACKGROUND + "│" + fifthRow(getCell(redRoom, "LootCell")) + "│" + ANSI_RESET + " " + ANSI_BLUE_BACKGROUND + "│" + fifthRow(getCell(blueRoom, "LootCell")) + ". ." + fifthRow(getCell(blueRoom, "SpawnCell")) + "│" + ANSI_RESET + " " + ANSI_GREEN_BACKGROUND + "│" + fifthRow(getCell(greenRoom, "LootCell")) + "│" + ANSI_RESET + "\n" +
                        ANSI_RED_BACKGROUND + "│               Loot Cell│" + ANSI_RESET + " " + ANSI_BLUE_BACKGROUND + "│               Loot Cell. .              Spawn Cell│" + ANSI_RESET + " " + ANSI_GREEN_BACKGROUND + "│               Loot Cell│" + ANSI_RESET + "\n" +
                        ANSI_RED_BACKGROUND + "│. . . . . . . . . . . . │" + ANSI_RESET + " " + ANSI_BLUE_BACKGROUND + "│_________       ____________________       ________│" + ANSI_RESET + " " + ANSI_GREEN_BACKGROUND + "│_________       ________│" + ANSI_RESET + "\n" +
                        ANSI_RED_BACKGROUND + "│                        │" + ANSI_RESET + "           │     │                    │     │                    │     │        " + "\n" +
                        ANSI_RED_BACKGROUND + "│. . . . . . . . . . . . │" + ANSI_RESET + " " + ANSI_PURPLE_BACKGROUND + "│͞͞͞͞͞͞͞͞͞" + ANSI_RESET + "       " + ANSI_PURPLE_BACKGROUND + "͞͞͞͞͞͞͞͞│" + ANSI_RESET + " " + ANSI_YELLOW_BACKGROUND + "│͞͞͞͞͞͞͞͞͞" + ANSI_RESET + "       " + ANSI_YELLOW_BACKGROUND + "͞͞͞͞͞͞͞͞͞͞͞͞͞͞͞͞͞͞͞͞" + ANSI_RESET + "       " + ANSI_YELLOW_BACKGROUND + "͞͞͞͞͞͞͞͞│" + ANSI_RESET + "\n" +
                        ANSI_RED_BACKGROUND + "│" + firstRow(getCell(redRoom, "SpawnCell")) + "│" + ANSI_RESET + " " + ANSI_PURPLE_BACKGROUND + "│" + firstRow(getCell(purpleRoom, "LootCell")) + "│" + ANSI_RESET + " " + ANSI_YELLOW_BACKGROUND + "│" + firstRow(getCell(yellowRoom, "LootCell1")) + ". ." + firstRow(getCell(yellowRoom, "Lootcell2")) + "│" + ANSI_RESET + "\n" +
                        ANSI_RED_BACKGROUND + "│" + secondRow(getCell(redRoom, "Spawncell")) + "│" + ANSI_RESET + " " + ANSI_PURPLE_BACKGROUND + "│" + secondRow(getCell(purpleRoom, "LootCell")) + "│" + ANSI_RESET + " " + ANSI_YELLOW_BACKGROUND + "│" + secondRow(getCell(yellowRoom, "LootCell1")) + ". ." + secondRow(getCell(yellowRoom, "LootCell2")) + "│" + ANSI_RESET + "\n" +
                        ANSI_RED_BACKGROUND + "│" + thirdRow(getCell(redRoom, "SpawnCell")) + "│" + ANSI_RESET + " " + ANSI_PURPLE_BACKGROUND + "│" + thirdRow(getCell(purpleRoom, "Lootcell")) + "│" + ANSI_RESET + " " + ANSI_YELLOW_BACKGROUND + "│" + thirdRow(getCell(yellowRoom, "Lootcell1")) + ". ." + thirdRow(getCell(yellowRoom, "Lootcell2")) + "│" + ANSI_RESET + "\n" +
                        ANSI_RED_BACKGROUND + "│" + fourthRow(getCell(redRoom, "SpawnCell")) + "│" + ANSI_RESET + " " + ANSI_PURPLE_BACKGROUND + "│" + fourthRow(getCell(purpleRoom, "LootCell")) + "│" + ANSI_RESET + " " + ANSI_YELLOW_BACKGROUND + "│" + fourthRow(getCell(yellowRoom, "Lootcell1")) + ". ." + fourthRow(getCell(yellowRoom, "Lootcell2")) + "│" + ANSI_RESET + "\n" +
                        ANSI_RED_BACKGROUND + "│" + fifthRow(getCell(redRoom, "Spawncell")) + "│" + ANSI_RESET + " " + ANSI_PURPLE_BACKGROUND + "│" + fifthRow(getCell(purpleRoom, "Lootcell")) + "│" + ANSI_RESET + " " + ANSI_YELLOW_BACKGROUND + "│" + fifthRow(getCell(yellowRoom, "LootCell1")) + ". ." + fifthRow(getCell(yellowRoom, "lootcell2")) + "│" + ANSI_RESET + "\n" +
                        ANSI_RED_BACKGROUND + "│              Spawn Cell│" + ANSI_RESET + " " + ANSI_PURPLE_BACKGROUND + "│               Loot Cell│" + ANSI_RESET + " " + ANSI_YELLOW_BACKGROUND + "│             Loot Cell 1. .             Loot Cell 2│" + ANSI_RESET + "\n" +
                        ANSI_RED_BACKGROUND + "│________       _________│" + ANSI_RESET + " " + ANSI_PURPLE_BACKGROUND + "│_________       ________│" + ANSI_RESET + " " + ANSI_YELLOW_BACKGROUND + "│. . . . . . . . . . . . . . . . . . . . . . . . . .│" + ANSI_RESET + "\n" +
                        "         │     │                     │     │          " + ANSI_YELLOW_BACKGROUND + "│                        . .                        │" + ANSI_RESET + "\n" +
                        ANSI_WHITE_BACKGROUND + "│͞͞͞͞͞͞͞͞      ͞͞͞͞͞͞͞͞͞͞͞͞͞͞͞͞͞͞͞͞͞͞       ͞͞͞͞͞͞͞͞│" + ANSI_RESET + " " + ANSI_YELLOW_BACKGROUND + "│. . . . . . . . . . . . . . . . . . . . . . . . . .│" + ANSI_RESET + "\n" +
                        ANSI_WHITE_BACKGROUND + "│" + firstRow(getCell(whiteRoom, "lootcell1")) + ". ." + firstRow(getCell(whiteRoom, "LootCell2")) + "│" + ANSI_RESET + " " + ANSI_YELLOW_BACKGROUND + "│" + firstRow(getCell(yellowRoom, "lootcell3")) + ". ." + firstRow(getCell(yellowRoom, "Spawncell")) + "│" + ANSI_RESET + "\n" +
                        ANSI_WHITE_BACKGROUND + "│" + secondRow(getCell(whiteRoom, "Lootcell1")) + ". ." + secondRow(getCell(whiteRoom, "Lootcell2")) + ANSI_RESET + "͞͞͞" + ANSI_YELLOW_BACKGROUND + secondRow(getCell(yellowRoom, "Lootcell3")) + ". ." + secondRow(getCell(yellowRoom, "SpawnCell")) + "│" + ANSI_RESET + "\n" +
                        ANSI_WHITE_BACKGROUND + "│" + thirdRow(getCell(whiteRoom, "Lootcell1")) + ". ." + thirdRow(getCell(whiteRoom, "Lootcell2")) + ANSI_RESET + "͟͟͟" + ANSI_YELLOW_BACKGROUND + thirdRow(getCell(yellowRoom, "Lootcell3")) + ". ." + thirdRow(getCell(yellowRoom, "Spawncell")) + "│" + ANSI_RESET + "\n" +
                        ANSI_WHITE_BACKGROUND + "│" + fourthRow(getCell(whiteRoom, "Lootcell1")) + ". ." + fourthRow(getCell(whiteRoom, "Lootcell2")) + "│" + ANSI_RESET + " " + ANSI_YELLOW_BACKGROUND + "│" + fourthRow(getCell(yellowRoom, "lootcell3")) + ". ." + fourthRow(getCell(yellowRoom, "Spawncell")) + "│" + ANSI_RESET + "\n" +
                        ANSI_WHITE_BACKGROUND + "│" + fifthRow(getCell(whiteRoom, "lootcell1")) + ". ." + fifthRow(getCell(whiteRoom, "lootcell2")) + "│" + ANSI_RESET + " " + ANSI_YELLOW_BACKGROUND + "│" + fifthRow(getCell(yellowRoom, "lootcell3")) + ". ." + fifthRow(getCell(yellowRoom, "spawncell")) + "│" + ANSI_RESET + "\n" +
                        ANSI_WHITE_BACKGROUND + "│            Loot Cell 1 . .             Loot Cell 2│" + ANSI_RESET + " " + ANSI_YELLOW_BACKGROUND + "│             Loot Cell 3. .              Spawn Cell│" + ANSI_RESET + "\n" +
                        ANSI_WHITE_BACKGROUND + "│___________________________________________________│" + ANSI_RESET + " " + ANSI_YELLOW_BACKGROUND + "│___________________________________________________│" + ANSI_RESET + "\n";
    }

}
