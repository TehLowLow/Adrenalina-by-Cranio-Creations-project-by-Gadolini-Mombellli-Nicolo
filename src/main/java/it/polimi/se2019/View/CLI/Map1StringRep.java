package it.polimi.se2019.View.CLI;


import static it.polimi.se2019.View.CLI.Colors.*;
import static it.polimi.se2019.View.CLI.Location.*;

public class Map1StringRep {


    public static String map1() {

        return ANSI_BLUE_BACKGROUND + "________________________________________________________________________________" + ANSI_RESET + "\n" +
                ANSI_BLUE_BACKGROUND + "│" + firstRow(getCell(blueRoom, "lootCell1")) + ". ." + firstRow(getCell(blueRoom, "lootCell2")) + ". ." + firstRow(getCell(blueRoom, "spawnCell")) + "│" + ANSI_RESET + "\n" +
                ANSI_BLUE_BACKGROUND + "│" + secondRow(getCell(blueRoom, "lootCell1")) + ". ." + secondRow(getCell(blueRoom, "lootCell2")) + ". ." + secondRow(getCell(blueRoom, "spawnCell")) + "│" + ANSI_RESET + "\n" +
                ANSI_BLUE_BACKGROUND + "│" + thirdRow(getCell(blueRoom, "lootCell1")) + ". ." + thirdRow(getCell(blueRoom, "lootCell2")) + ". ." + thirdRow(getCell(blueRoom, "spawnCell")) + "│" + ANSI_RESET + "\n" +
                ANSI_BLUE_BACKGROUND + "│" + fourthRow(getCell(blueRoom, "lootCell1")) + ". ." + fourthRow(getCell(blueRoom, "lootCell2")) + ". ." + fourthRow(getCell(blueRoom, "spawnCell")) + "│" + ANSI_RESET + "\n" +
                ANSI_BLUE_BACKGROUND + "│" + fifthRow(getCell(blueRoom, "lootCell1")) + ". ." + fifthRow(getCell(blueRoom, "lootCell2")) + ". ." + fifthRow(getCell(blueRoom, "spawnCell")) + "│" + ANSI_RESET + "\n" +
                ANSI_BLUE_BACKGROUND + "│             loot cell 1. .             loot cell 2. .              spawn cell│" + ANSI_RESET + "\n" +
                ANSI_BLUE_BACKGROUND + "│_________     __________________________________________________     _________│" + ANSI_RESET + "\n"
                + "          │   │                                                  │   │         \n" +

                ANSI_RED_BACKGROUND + "__________│   │__________________________________________________│   │__________" + ANSI_RESET + "  " + ANSI_YELLOW_BACKGROUND + " _________________________" + ANSI_RESET + "\n" +
                ANSI_RED_BACKGROUND + "│" + firstRow(getCell(redRoom, "spawnCell")) + ". ." + firstRow(getCell(redRoom, "lootCell1")) + ". ." + firstRow(getCell(redRoom, "lootCell2")) + "│" + ANSI_RESET + "  " + ANSI_YELLOW_BACKGROUND + "│" + firstRow(getCell(yellowRoom, "lootCell")) + "│" + ANSI_RESET + "\n" +
                ANSI_RED_BACKGROUND + "│" + secondRow(getCell(redRoom, "spawnCell")) + ". ." + secondRow(getCell(redRoom, "lootCell1")) + ". ." + secondRow(getCell(redRoom, "lootCell2")) + "│" + ANSI_RESET + "__" + ANSI_YELLOW_BACKGROUND + "│" + secondRow(getCell(yellowRoom, "lootCell")) + "│" + ANSI_RESET + "\n" +
                ANSI_RED_BACKGROUND + "│" + thirdRow(getCell(redRoom, "spawnCell")) + ". ." + thirdRow(getCell(redRoom, "lootCell1")) + ". ." + thirdRow(getCell(redRoom, "lootCell2")) + " " + ANSI_RESET + "  " + ANSI_YELLOW_BACKGROUND + " " + thirdRow(getCell(yellowRoom, "lootCell")) + "│" + ANSI_RESET + "\n" +
                ANSI_RED_BACKGROUND + "│" + fourthRow(getCell(redRoom, "spawnCell")) + ". ." + fourthRow(getCell(redRoom, "lootCell1")) + ". ." + fourthRow(getCell(redRoom, "lootCell2")) + " " + ANSI_RESET + "__" + ANSI_YELLOW_BACKGROUND + " " + fourthRow(getCell(yellowRoom, "lootCell")) + "│" + ANSI_RESET + "\n" +
                ANSI_RED_BACKGROUND + "│" + fifthRow(getCell(redRoom, "spawnCell")) + ". ." + fifthRow(getCell(redRoom, "lootCell1")) + ". ." + fifthRow(getCell(redRoom, "lootCell2")) + "│" + ANSI_RESET + "  " + ANSI_YELLOW_BACKGROUND + "│" + fifthRow(getCell(yellowRoom, "lootCell")) + "│" + ANSI_RESET + "\n" +
                ANSI_RED_BACKGROUND + "│              spawn cell. .             loot cell 1. .             loot cell 2│" + ANSI_RESET + "  " + ANSI_YELLOW_BACKGROUND + "│               loot cell│" + ANSI_RESET + "\n" +
                ANSI_RED_BACKGROUND + "│____________________________________      ____________________________________│" + ANSI_RESET + "  " + ANSI_YELLOW_BACKGROUND + "│. . . . . . . . . . . . │" + ANSI_RESET + "\n" +


                "                           " + ANSI_RESET + "          │    │                                     " + ANSI_RESET + "  " + ANSI_YELLOW_BACKGROUND + "│                        │" + ANSI_RESET + "\n" +
                "                           " + ANSI_WHITE_BACKGROUND + "__________│    │____________________________________ " + ANSI_RESET + "  " + ANSI_YELLOW_BACKGROUND + "│. . . . . . . . . . . . │" + ANSI_RESET + "\n" +
                "                           " + ANSI_WHITE_BACKGROUND + "│" + firstRow(getCell(whiteRoom, "lootCell1")) + ". ." + firstRow(getCell(whiteRoom, "lootCell2")) + "│" + ANSI_RESET + "  " + ANSI_YELLOW_BACKGROUND + "│" + firstRow(getCell(yellowRoom, "spawnCell")) + "│" + ANSI_RESET + "\n" +
                "                           " + ANSI_WHITE_BACKGROUND + "│" + secondRow(getCell(whiteRoom, "lootCell1")) + ". ." + secondRow(getCell(whiteRoom, "lootCell2")) + "│" + ANSI_RESET + "__" + ANSI_YELLOW_BACKGROUND + "│" + secondRow(getCell(yellowRoom, "spawnCell")) + "│" + ANSI_RESET + "\n" +
                "                           " + ANSI_WHITE_BACKGROUND + "│" + thirdRow(getCell(whiteRoom, "lootCell1")) + ". ." + thirdRow(getCell(whiteRoom, "lootCell2")) + " " + ANSI_RESET + "  " + ANSI_YELLOW_BACKGROUND + " " + thirdRow(getCell(yellowRoom, "spawnCell")) + "│" + ANSI_RESET + "\n" +
                "                           " + ANSI_WHITE_BACKGROUND + "│" + fourthRow(getCell(whiteRoom, "lootCell1")) + ". ." + fourthRow(getCell(whiteRoom, "lootCell2")) + " " + ANSI_RESET + "__" + ANSI_YELLOW_BACKGROUND + " " + fourthRow(getCell(yellowRoom, "spawnCell")) + "│" + ANSI_RESET + "\n" +
                "                           " + ANSI_WHITE_BACKGROUND + "│" + fifthRow(getCell(whiteRoom, "lootCell1")) + ". ." + fifthRow(getCell(whiteRoom, "lootCell2")) + "│" + ANSI_RESET + "  " + ANSI_YELLOW_BACKGROUND + "│" + fifthRow(getCell(yellowRoom, "spawnCell")) + "│" + ANSI_RESET + "\n" +
                "                           " + ANSI_WHITE_BACKGROUND + "│             loot cell 1. .             loot cell 2│" + ANSI_RESET + "  " + ANSI_YELLOW_BACKGROUND + "│              spawn cell│" + ANSI_RESET + "\n" +
                "                           " + ANSI_WHITE_BACKGROUND + "│___________________________________________________│" + ANSI_RESET + "  " + ANSI_YELLOW_BACKGROUND + "│________________________│" + ANSI_RESET + "\n";

    }


}
