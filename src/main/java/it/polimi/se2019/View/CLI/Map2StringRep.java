package it.polimi.se2019.View.CLI;



import static it.polimi.se2019.View.CLI.Colors.*;
import static it.polimi.se2019.View.CLI.Location.*;
public class Map2StringRep {


    static String map2() {


        return ANSI_BLUE_BACKGROUND + " ______________________________________________________________________________ " + ANSI_RESET + "  " + ANSI_GREEN_BACKGROUND + "_________________________ " + ANSI_RESET + "\n" +
                ANSI_BLUE_BACKGROUND + "│" + firstRow(getCell(blueRoom, "lootCell1")) + ". ." + firstRow(getCell(blueRoom, "lootCell2")) + ". ." + firstRow(getCell(blueRoom, "spawnCell")) + "│" + ANSI_RESET + "  " + ANSI_GREEN_BACKGROUND + "│" + firstRow(getCell(greenRoom, "lootCell")) + "│" + ANSI_RESET + "\n" +
                ANSI_BLUE_BACKGROUND + "│" + secondRow(getCell(blueRoom, "lootCell1")) + ". ." + secondRow(getCell(blueRoom, "lootCell2")) + ". ." + secondRow(getCell(blueRoom, "spawnCell")) + "│" + ANSI_RESET + "__" + ANSI_GREEN_BACKGROUND + "│" + secondRow(getCell(greenRoom, "lootCell")) + "│" + ANSI_RESET + "\n" +
                ANSI_BLUE_BACKGROUND + "│" + thirdRow(getCell(blueRoom, "lootCell1")) + ". ." + thirdRow(getCell(blueRoom, "lootCell2")) + ". ." + thirdRow(getCell(blueRoom, "spawnCell")) + " " + ANSI_RESET + "  " + ANSI_GREEN_BACKGROUND + " " + thirdRow(getCell(greenRoom, "lootCell")) + "│" + ANSI_RESET + "\n" +
                ANSI_BLUE_BACKGROUND + "│" + fourthRow(getCell(blueRoom, "lootCell1")) + ". ." + fourthRow(getCell(blueRoom, "lootCell2")) + ". ." + fourthRow(getCell(blueRoom, "spawnCell")) + " " + ANSI_RESET + "__" + ANSI_GREEN_BACKGROUND + " " + fourthRow(getCell(greenRoom, "lootCell")) + "│" + ANSI_RESET + "\n" +
                ANSI_BLUE_BACKGROUND + "│" + fifthRow(getCell(blueRoom, "lootCell1")) + ". ." + fifthRow(getCell(blueRoom, "lootCell2")) + ". ." + fifthRow(getCell(blueRoom, "spawnCell")) + "│" + ANSI_RESET + "  " + ANSI_GREEN_BACKGROUND + "│" + fifthRow(getCell(greenRoom, "lootCell")) + "│" + ANSI_RESET + "\n" +
                ANSI_BLUE_BACKGROUND + "│            loot cell 1 . .            loot cell 2 . .              spawn cell│" + ANSI_RESET + "  " + ANSI_GREEN_BACKGROUND + "│               loot cell│" + ANSI_RESET + "\n" +
                ANSI_BLUE_BACKGROUND + "│_______        ______________________________________________        _________│" + ANSI_RESET + "  " + ANSI_GREEN_BACKGROUND + "│_______         ________│" + ANSI_RESET + "\n" +
                "        │      │                                              │      │                    │       │         \n" +
                ANSI_RED_BACKGROUND + " _______│      │____________________________________ " + ANSI_RESET + "  " + ANSI_YELLOW_BACKGROUND + " ______│      │____________________│       │________ " + ANSI_RESET + "\n" +
                ANSI_RED_BACKGROUND + "│" + firstRow(getCell(redRoom, "spawnCell")) + ". ." + firstRow(getCell(redRoom, "lootCell")) + "│" + ANSI_RESET + "  " + ANSI_YELLOW_BACKGROUND + "│" + firstRow(getCell(yellowRoom, "lootCell1")) + ". ." + firstRow(getCell(yellowRoom, "lootCell2")) + "│" + ANSI_RESET + "\n" +
                ANSI_RED_BACKGROUND + "│" + secondRow(getCell(redRoom, "spawnCell")) + ". ." + secondRow(getCell(redRoom, "lootCell")) + "│" + ANSI_RESET + "  " + ANSI_YELLOW_BACKGROUND + "│" + secondRow(getCell(yellowRoom, "lootCell1")) + ". ." + secondRow(getCell(yellowRoom, "lootCell2")) + "│" + ANSI_RESET + "\n" +
                ANSI_RED_BACKGROUND + "│" + thirdRow(getCell(redRoom, "spawnCell")) + ". ." + thirdRow(getCell(redRoom, "lootCell")) + "│" + ANSI_RESET + "  " + ANSI_YELLOW_BACKGROUND + "│" + thirdRow(getCell(yellowRoom, "lootCell1")) + ". ." + thirdRow(getCell(yellowRoom, "lootCell2")) + "│" + ANSI_RESET + "\n" +
                ANSI_RED_BACKGROUND + "│" + fourthRow(getCell(redRoom, "spawnCell")) + ". ." + fourthRow(getCell(redRoom, "lootCell")) + "│" + ANSI_RESET + "  " + ANSI_YELLOW_BACKGROUND + "│" + fourthRow(getCell(yellowRoom, "lootCell1")) + ". ." + fourthRow(getCell(yellowRoom, "lootCell2")) + "│" + ANSI_RESET + "\n" +
                ANSI_RED_BACKGROUND + "│" + fifthRow(getCell(redRoom, "spawnCell")) + ". ." + fifthRow(getCell(redRoom, "lootCell")) + "│" + ANSI_RESET + "  " + ANSI_YELLOW_BACKGROUND + "│" + fifthRow(getCell(yellowRoom, "lootCell1")) + ". ." + fifthRow(getCell(yellowRoom, "lootCell2")) + "│" + ANSI_RESET + "\n" +
                ANSI_RED_BACKGROUND + "│              spawn cell. .               loot cell│" + ANSI_RESET + "  " + ANSI_YELLOW_BACKGROUND + "│             loot cell 1. .             loot cell 2│" + ANSI_RESET + "\n" +
                ANSI_RED_BACKGROUND + "│__________________________________        _________│" + ANSI_RESET + "  " + ANSI_YELLOW_BACKGROUND + "│ . . . . . . . . . . . .   . . . . . . . . . . . . │" + ANSI_RESET + "\n" +
                "                                   │      │            " + ANSI_YELLOW_BACKGROUND + "│                                                   │" + ANSI_RESET + "\n" +

                "                           " + ANSI_WHITE_BACKGROUND + "________│      │_________ " + ANSI_RESET + "  " + ANSI_YELLOW_BACKGROUND + "│ . . . . . . . . . . . .   . . . . . . . . . . . . │" + ANSI_RESET + "\n" +
                "                           " + ANSI_WHITE_BACKGROUND + "│" + firstRow(getCell(whiteRoom, "lootCell")) + "│" + ANSI_RESET + "  " + ANSI_YELLOW_BACKGROUND + "│" + firstRow(getCell(yellowRoom, "lootCell3")) + ". ." + firstRow(getCell(yellowRoom, "spawnCell")) + "│" + ANSI_RESET + "\n" +
                "                           " + ANSI_WHITE_BACKGROUND + "│" + secondRow(getCell(whiteRoom, "lootCell")) + "│" + ANSI_RESET + "__" + ANSI_YELLOW_BACKGROUND + "│" + secondRow(getCell(yellowRoom, "lootCell3")) + ". ." + secondRow(getCell(yellowRoom, "spawnCell")) + "│" + ANSI_RESET + "\n" +
                "                           " + ANSI_WHITE_BACKGROUND + "│" + thirdRow(getCell(whiteRoom, "lootCell")) + " " + ANSI_RESET + "  " + ANSI_YELLOW_BACKGROUND + " " + thirdRow(getCell(yellowRoom, "lootCell3")) + ". ." + thirdRow(getCell(yellowRoom, "spawnCell")) + "│" + ANSI_RESET + "\n" +
                "                           " + ANSI_WHITE_BACKGROUND + "│" + fourthRow(getCell(whiteRoom, "lootCell")) + " " + ANSI_RESET + "__" + ANSI_YELLOW_BACKGROUND + " " + fourthRow(getCell(yellowRoom, "lootCell3")) + ". ." + fourthRow(getCell(yellowRoom, "spawnCell")) + "│" + ANSI_RESET + "\n" +
                "                           " + ANSI_WHITE_BACKGROUND + "│" + fifthRow(getCell(whiteRoom, "lootCell")) + "│" + ANSI_RESET + "  " + ANSI_YELLOW_BACKGROUND + "│" + fifthRow(getCell(yellowRoom, "lootCell3")) + ". ." + fifthRow(getCell(yellowRoom, "spawnCell")) + "│" + ANSI_RESET + "\n" +
                "                           " + ANSI_WHITE_BACKGROUND + "│               loot cell│" + ANSI_RESET + "  " + ANSI_YELLOW_BACKGROUND + "│             loot cell 3. .              spawn cell│" + ANSI_RESET + "\n" +

                "                           " + ANSI_WHITE_BACKGROUND + "│________________________│" + ANSI_RESET + "  " + ANSI_YELLOW_BACKGROUND + "│___________________________________________________│" + ANSI_RESET + "\n";
    }
}
