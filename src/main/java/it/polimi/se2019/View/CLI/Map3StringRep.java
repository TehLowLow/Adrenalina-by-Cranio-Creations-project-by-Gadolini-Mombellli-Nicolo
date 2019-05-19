package it.polimi.se2019.View.CLI;




import static it.polimi.se2019.View.CLI.Colors.*;
import static it.polimi.se2019.View.CLI.Location.*;

public class Map3StringRep {


    public static String map3() {
        return


                ANSI_RED_BACKGROUND + " ________________________ " + ANSI_RESET + " " + ANSI_BLUE_BACKGROUND + " ___________________________________________________ " + ANSI_RESET + "\n" +
                ANSI_RED_BACKGROUND + "│" + firstRow(getCell(RedRoom, "LootCell")) + "│" + ANSI_RESET + " " + ANSI_BLUE_BACKGROUND + "│" + firstRow(getCell(BlueRoom, "LootCell")) + ". ." + firstRow(getCell(BlueRoom, "spawncell")) + "│" + ANSI_RESET + "\n" +
                ANSI_RED_BACKGROUND + "│" + secondRow(getCell(RedRoom, "LootCell")) + "│" + ANSI_RESET + "_" + ANSI_BLUE_BACKGROUND + "│" + secondRow(getCell(BlueRoom, "LootCell")) + ". ." + secondRow(getCell(BlueRoom, "spawncell")) + "│" + ANSI_RESET + "\n" +
                ANSI_RED_BACKGROUND + "│" + thirdRow(getCell(RedRoom, "LootCell")) + " " + ANSI_RESET + " " + ANSI_BLUE_BACKGROUND + " " + thirdRow(getCell(BlueRoom, "LootCell")) + ". ." + thirdRow(getCell(BlueRoom, "spawncell")) + "│" + ANSI_RESET + "\n" +
                ANSI_RED_BACKGROUND + "│" + fourthRow(getCell(RedRoom, "LootCell")) + " " + ANSI_RESET + "_" + ANSI_BLUE_BACKGROUND + " " + fourthRow(getCell(BlueRoom, "LootCell")) + ". ." + fourthRow(getCell(BlueRoom, "spawncell")) + "│" + ANSI_RESET + "\n" +
                ANSI_RED_BACKGROUND + "│" + fifthRow(getCell(RedRoom, "LootCell")) + "│" + ANSI_RESET + " " + ANSI_BLUE_BACKGROUND + "│" + fifthRow(getCell(BlueRoom, "LootCell")) + ". ." + fifthRow(getCell(BlueRoom, "spawncell")) + "│" + ANSI_RESET + "\n" +
                ANSI_RED_BACKGROUND + "│              Loot Cell │" + ANSI_RESET + " " + ANSI_BLUE_BACKGROUND + "│              Loot Cell . .             Spawn Cell │" + ANSI_RESET + "\n" +
                ANSI_RED_BACKGROUND + "│. . . . . . . . . . . . │" + ANSI_RESET + " " + ANSI_BLUE_BACKGROUND + "│_________     ______________________     __________│" + ANSI_RESET + "\n" +
                ANSI_RED_BACKGROUND + "│                        │" + ANSI_RESET + "           │   │                      │   │           " + ANSI_RESET + "\n" +
                ANSI_RED_BACKGROUND + "│. . . . . . . . . . . . │" + ANSI_RESET + " " + ANSI_PURPLE_BACKGROUND + " _________│   │______________________│   │__________ " + ANSI_RESET + " " + ANSI_YELLOW_BACKGROUND + " ________________________ " + ANSI_RESET + "\n" +
                ANSI_RED_BACKGROUND + "│" + firstRow(getCell(RedRoom, "spawncell")) + "│" + ANSI_RESET + " " + ANSI_PURPLE_BACKGROUND + "│" + firstRow(getCell(PurpleRoom, "lootcell1")) + ". ." + firstRow(getCell(PurpleRoom, "lootcell2")) + "│" + ANSI_RESET + " " + ANSI_YELLOW_BACKGROUND + "│" + firstRow(getCell(YellowRoom, "lootcell")) + "│" + ANSI_RESET + "\n" +
                ANSI_RED_BACKGROUND + "│" + secondRow(getCell(RedRoom, "spawncell")) + "│" + ANSI_RESET + " " + ANSI_PURPLE_BACKGROUND + "│" + secondRow(getCell(PurpleRoom, "lootcell1")) + ". ." + secondRow(getCell(PurpleRoom, "lootcell2")) + "│" + ANSI_RESET + "_" + ANSI_YELLOW_BACKGROUND + "│" + secondRow(getCell(YellowRoom, "lootcell")) + "│" + ANSI_RESET + "\n" +
                ANSI_RED_BACKGROUND + "│" + thirdRow(getCell(RedRoom, "spawncell")) + "│" + ANSI_RESET + " " + ANSI_PURPLE_BACKGROUND + "│" + thirdRow(getCell(PurpleRoom, "lootcell1")) + ". ." + thirdRow(getCell(PurpleRoom, "lootcell2")) + " " + ANSI_RESET + " " + ANSI_YELLOW_BACKGROUND + " " + thirdRow(getCell(YellowRoom, "lootcell")) + "│" + ANSI_RESET + "\n" +
                ANSI_RED_BACKGROUND + "│" + fourthRow(getCell(RedRoom, "spawncell")) + "│" + ANSI_RESET + " " + ANSI_PURPLE_BACKGROUND + "│" + fourthRow(getCell(PurpleRoom, "lootcell1")) + ". ." + fourthRow(getCell(PurpleRoom, "lootcell2")) + " " + ANSI_RESET + "_" + ANSI_YELLOW_BACKGROUND + " " + fourthRow(getCell(YellowRoom, "lootcell")) + "│" + ANSI_RESET + "\n" +
                ANSI_RED_BACKGROUND + "│" + fifthRow(getCell(RedRoom, "spawncell")) + "│" + ANSI_RESET + " " + ANSI_PURPLE_BACKGROUND + "│" + fifthRow(getCell(PurpleRoom, "lootcell1")) + ". ." + fifthRow(getCell(PurpleRoom, "lootcell2")) + "│" + ANSI_RESET + " " + ANSI_YELLOW_BACKGROUND + "│" + fifthRow(getCell(YellowRoom, "lootcell")) + "│" + ANSI_RESET + "\n" +
                ANSI_RED_BACKGROUND + "│             Spawn Cell │" + ANSI_RESET + " " + ANSI_PURPLE_BACKGROUND + "│            Loot Cell 1 . .            Loot Cell 2 │" + ANSI_RESET + " " + ANSI_YELLOW_BACKGROUND + "│              Loot Cell │" + ANSI_RESET + "\n" +
                ANSI_RED_BACKGROUND + "│__________     _________│" + ANSI_RESET + " " + ANSI_PURPLE_BACKGROUND + "│_________     _____________________________________│" + ANSI_RESET + " " + ANSI_YELLOW_BACKGROUND + "│. . . . . . . . . . . . │" + ANSI_RESET + "\n" +
                ANSI_RESET + "           │   │                     │   │                                       " + ANSI_YELLOW_BACKGROUND + "│                        │" + ANSI_RESET + "\n" +
                ANSI_WHITE_BACKGROUND + " __________│   │_____________________│   │_____________________________________ " + ANSI_RESET + " " + ANSI_YELLOW_BACKGROUND + "│. . . . . . . . . . . . │" + ANSI_RESET + "\n" +
                ANSI_WHITE_BACKGROUND + "│" + firstRow(getCell(WhiteRoom, "lootcell1")) + ". ." + firstRow(getCell(WhiteRoom, "lootcell2")) + ". ." + firstRow(getCell(WhiteRoom, "lootcell3")) + "│" + ANSI_RESET + " " + ANSI_YELLOW_BACKGROUND + "│" + firstRow(getCell(YellowRoom, "spawncell")) + "│" + ANSI_RESET + "\n" +
                ANSI_WHITE_BACKGROUND + "│" + secondRow(getCell(WhiteRoom, "lootcell1")) + ". ." + secondRow(getCell(WhiteRoom, "lootcell2")) + ". ." + secondRow(getCell(WhiteRoom, "lootcell3")) + "│" + ANSI_RESET + "_" + ANSI_YELLOW_BACKGROUND + "│" + secondRow(getCell(YellowRoom, "spawncell")) + "│" + ANSI_RESET + "\n" +
                ANSI_WHITE_BACKGROUND + "│" + thirdRow(getCell(WhiteRoom, "lootcell1")) + ". ." + thirdRow(getCell(WhiteRoom, "lootcell2")) + ". ." + thirdRow(getCell(WhiteRoom, "lootcell3")) + " " + ANSI_RESET + " " + ANSI_YELLOW_BACKGROUND + " " + thirdRow(getCell(YellowRoom, "spawncell")) + "│" + ANSI_RESET + "\n" +
                ANSI_WHITE_BACKGROUND + "│" + fourthRow(getCell(WhiteRoom, "lootcell1")) + ". ." + fourthRow(getCell(WhiteRoom, "lootcell2")) + ". ." + fourthRow(getCell(WhiteRoom, "lootcell3")) + " " + ANSI_RESET + "_" + ANSI_YELLOW_BACKGROUND + " " + fourthRow(getCell(YellowRoom, "spawncell")) + "│" + ANSI_RESET + "\n" +
                ANSI_WHITE_BACKGROUND + "│" + fifthRow(getCell(WhiteRoom, "lootcell1")) + ". ." + fifthRow(getCell(WhiteRoom, "lootcell2")) + ". ." + fifthRow(getCell(WhiteRoom, "lootcell3")) + "│" + ANSI_RESET + " " + ANSI_YELLOW_BACKGROUND + "│" + fifthRow(getCell(YellowRoom, "spawncell")) + "│" + ANSI_RESET + "\n" +
                ANSI_WHITE_BACKGROUND + "│           Loot Cell 1  . .           Loot Cell 2  . .           Loot Cell 3  │" + ANSI_RESET + " " + ANSI_YELLOW_BACKGROUND + "│             Spawn Cell │" + ANSI_RESET + "\n" +
                ANSI_WHITE_BACKGROUND + "│______________________________________________________________________________│" + ANSI_RESET + " " + ANSI_YELLOW_BACKGROUND + "│________________________│" + ANSI_RESET + "\n";

    }
}
