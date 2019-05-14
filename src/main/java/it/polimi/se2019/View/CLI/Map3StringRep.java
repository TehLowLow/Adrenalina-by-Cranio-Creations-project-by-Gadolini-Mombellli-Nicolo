package it.polimi.se2019.View.CLI;

import it.polimi.se2019.Model.Board;
import it.polimi.se2019.Model.Map;
import it.polimi.se2019.Model.Room;

import static it.polimi.se2019.View.CLI.Location.*;

public class Map3StringRep {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[41m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[43m";
    public static final String ANSI_BLUE ="\u001B[44m";
    public static final String ANSI_PURPLE = "\u001B[45m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_BOLD = "\033[0;1m";

    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND =  "\u001B[31m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[33m";
    public static final String ANSI_BLUE_BACKGROUND =  "\u001B[34m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[35m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[30m";


    static Map map = Board.getMap();
    static Room blueRoom = map.getBlueRoom();
    static Room yellowRoom = map.getYellowRoom();
    static Room redRoom = map.getRedRoom();
    static Room whiteRoom = map.getWhiteRoom();
    static Room purpleRoom = map.getPurpleRoom();



    public static String map3 =

            ANSI_RED_BACKGROUND + " ________________________ "                                   + ANSI_RESET + " " + ANSI_BLUE_BACKGROUND + " ___________________________________________________ " +                                                                ANSI_RESET + "\n" +
            ANSI_RED_BACKGROUND + "│" + firstRow(getCell(redRoom, "LootCell"))   + "│"   +  ANSI_RESET + " " + ANSI_BLUE_BACKGROUND + "│"  + firstRow(getCell(blueRoom, "LootCell"))   + ". ." + firstRow(getCell(blueRoom, "spawncell")) + "│" + ANSI_RESET + "\n" +
            ANSI_RED_BACKGROUND + "│" + secondRow(getCell(redRoom, "LootCell"))  + "│"   +  ANSI_RESET + "_" + ANSI_BLUE_BACKGROUND + "│"  + secondRow(getCell(blueRoom, "LootCell")) + ". ." + secondRow(getCell(blueRoom, "spawncell")) + "│" + ANSI_RESET + "\n" +
            ANSI_RED_BACKGROUND + "│" + thirdRow(getCell(redRoom, "LootCell"))   + " "   +  ANSI_RESET + " " + ANSI_BLUE_BACKGROUND + " "  + thirdRow(getCell(blueRoom, "LootCell"))  + ". ." + thirdRow(getCell(blueRoom, "spawncell"))  + "│" + ANSI_RESET + "\n" +
            ANSI_RED_BACKGROUND + "│" + fourthRow(getCell(redRoom, "LootCell"))  + " "   +  ANSI_RESET + "_" + ANSI_BLUE_BACKGROUND + " "  + fourthRow(getCell(blueRoom, "LootCell")) + ". ." + fourthRow(getCell(blueRoom, "spawncell")) + "│" + ANSI_RESET + "\n" +
            ANSI_RED_BACKGROUND + "│" + fifthRow(getCell(redRoom, "LootCell"))   + "│"   +  ANSI_RESET + " " + ANSI_BLUE_BACKGROUND + "│"  + fifthRow(getCell(blueRoom, "LootCell"))  + ". ." + fifthRow(getCell(blueRoom, "spawncell"))  + "│" + ANSI_RESET + "\n" +
            ANSI_RED_BACKGROUND + "│              Loot Cell │"                                   + ANSI_RESET + " " + ANSI_BLUE_BACKGROUND +  "│              Loot Cell . .             Spawn Cell │"                                                                 + ANSI_RESET + "\n" +
            ANSI_RED_BACKGROUND + "│. . . . . . . . . . . . │"                                   + ANSI_RESET + " " + ANSI_BLUE_BACKGROUND +  "│_________     ______________________     __________│"                                                                 + ANSI_RESET + "\n" +
            ANSI_RED_BACKGROUND + "│                        │"                                   + ANSI_RESET +                               "           │   │                      │   │           "                                                                + ANSI_RESET + "\n" +
            ANSI_RED_BACKGROUND + "│. . . . . . . . . . . . │"                                   + ANSI_RESET + " " + ANSI_PURPLE_BACKGROUND + " _________│   │______________________│   │__________ " + ANSI_RESET + " " + ANSI_YELLOW_BACKGROUND  + " ________________________ " + ANSI_RESET + "\n" +
            ANSI_RED_BACKGROUND + "│" + firstRow(getCell(redRoom, "spawncell")) +  "│"   +  ANSI_RESET + " " + ANSI_PURPLE_BACKGROUND + "│" + firstRow(getCell(purpleRoom, "lootcell1")) + ". ." + firstRow(getCell(purpleRoom, "lootcell2")) +  "│" + ANSI_RESET + " " +    ANSI_YELLOW_BACKGROUND + "│" + firstRow(getCell(yellowRoom, "lootcell")) + "│" + ANSI_RESET + "\n" +
            ANSI_RED_BACKGROUND + "│" + secondRow(getCell(redRoom, "spawncell")) + "│"   +  ANSI_RESET + " " + ANSI_PURPLE_BACKGROUND + "│" + secondRow(getCell(purpleRoom, "lootcell1"))+ ". ." + secondRow(getCell(purpleRoom, "lootcell2")) + "│" + ANSI_RESET + "_" +    ANSI_YELLOW_BACKGROUND + "│" + secondRow(getCell(yellowRoom, "lootcell"))+ "│" + ANSI_RESET + "\n" +
            ANSI_RED_BACKGROUND + "│" + thirdRow(getCell(redRoom, "spawncell")) +  "│"   +  ANSI_RESET + " " + ANSI_PURPLE_BACKGROUND + "│" + thirdRow(getCell(purpleRoom, "lootcell1")) + ". ." + thirdRow(getCell(purpleRoom, "lootcell2")) +  " " + ANSI_RESET + " " +    ANSI_YELLOW_BACKGROUND + " " + thirdRow(getCell(yellowRoom, "lootcell")) + "│" + ANSI_RESET + "\n" +
            ANSI_RED_BACKGROUND + "│" + fourthRow(getCell(redRoom, "spawncell")) + "│"   +  ANSI_RESET + " " + ANSI_PURPLE_BACKGROUND + "│" + fourthRow(getCell(purpleRoom, "lootcell1"))+ ". ." + fourthRow(getCell(purpleRoom, "lootcell2")) + " " + ANSI_RESET + "_" +    ANSI_YELLOW_BACKGROUND + " " + fourthRow(getCell(yellowRoom, "lootcell"))+ "│" + ANSI_RESET + "\n" +
            ANSI_RED_BACKGROUND + "│" + fifthRow(getCell(redRoom, "spawncell")) +  "│"   +  ANSI_RESET + " " + ANSI_PURPLE_BACKGROUND + "│" + fifthRow(getCell(purpleRoom, "lootcell1")) + ". ." + fifthRow(getCell(purpleRoom, "lootcell2")) +  "│" + ANSI_RESET + " " +    ANSI_YELLOW_BACKGROUND + "│" + fifthRow(getCell(yellowRoom, "lootcell")) + "│" + ANSI_RESET + "\n" +
            ANSI_RED_BACKGROUND + "│             Spawn Cell │"                                   + ANSI_RESET + " " + ANSI_PURPLE_BACKGROUND + "│            Loot Cell 1 . .            Loot Cell 2 │" + ANSI_RESET + " " + ANSI_YELLOW_BACKGROUND + "│              Loot Cell │" + ANSI_RESET + "\n" +
            ANSI_RED_BACKGROUND + "│__________     _________│"                                   + ANSI_RESET + " " + ANSI_PURPLE_BACKGROUND + "│_________     _____________________________________│" + ANSI_RESET + " " + ANSI_YELLOW_BACKGROUND + "│. . . . . . . . . . . . │" + ANSI_RESET + "\n" +
            ANSI_RESET +          "           │   │                     │   │                                       " +                                                                                                     ANSI_YELLOW_BACKGROUND + "│                        │" + ANSI_RESET + "\n" +
            ANSI_WHITE_BACKGROUND+" __________│   │_____________________│   │_____________________________________ "   + ANSI_RESET + " "                                                                                  + ANSI_YELLOW_BACKGROUND + "│. . . . . . . . . . . . │" + ANSI_RESET + "\n" +
            ANSI_WHITE_BACKGROUND+"│" + firstRow(getCell(whiteRoom, "lootcell1")) +  ". ." + firstRow(getCell(whiteRoom, "lootcell2")) +  ". ." + firstRow(getCell(whiteRoom, "lootcell3")) +  "│" + ANSI_RESET + " " + ANSI_YELLOW_BACKGROUND + "│" + firstRow(getCell(yellowRoom, "spawncell")) +  "│" + ANSI_RESET + "\n" +
            ANSI_WHITE_BACKGROUND+"│" + secondRow(getCell(whiteRoom, "lootcell1")) + ". ." + secondRow(getCell(whiteRoom, "lootcell2")) + ". ." + secondRow(getCell(whiteRoom, "lootcell3")) + "│" + ANSI_RESET + "_" + ANSI_YELLOW_BACKGROUND + "│" + secondRow(getCell(yellowRoom, "spawncell")) + "│" + ANSI_RESET + "\n" +
            ANSI_WHITE_BACKGROUND+"│" + thirdRow(getCell(whiteRoom, "lootcell1")) +  ". ." + thirdRow(getCell(whiteRoom, "lootcell2")) +  ". ." + thirdRow(getCell(whiteRoom, "lootcell3")) +  " " + ANSI_RESET + " " + ANSI_YELLOW_BACKGROUND + " " + thirdRow(getCell(yellowRoom, "spawncell")) +  "│" + ANSI_RESET + "\n" +
            ANSI_WHITE_BACKGROUND+"│" + fourthRow(getCell(whiteRoom, "lootcell1")) + ". ." + fourthRow(getCell(whiteRoom, "lootcell2")) + ". ." + fourthRow(getCell(whiteRoom, "lootcell3")) + " " + ANSI_RESET + "_" + ANSI_YELLOW_BACKGROUND + " " + fourthRow(getCell(yellowRoom, "spawncell")) + "│" + ANSI_RESET + "\n" +
            ANSI_WHITE_BACKGROUND+"│" + fifthRow(getCell(whiteRoom, "lootcell1")) +  ". ." + fifthRow(getCell(whiteRoom, "lootcell2")) +  ". ." + fifthRow(getCell(whiteRoom, "lootcell3")) +  "│" + ANSI_RESET + " " + ANSI_YELLOW_BACKGROUND + "│" + fifthRow(getCell(yellowRoom, "spawncell")) +  "│" + ANSI_RESET + "\n" +
            ANSI_WHITE_BACKGROUND+"│           Loot Cell 1  . .           Loot Cell 2  . .           Loot Cell 3  │" + ANSI_RESET + " " + ANSI_YELLOW_BACKGROUND + "│             Spawn Cell │" + ANSI_RESET + "\n" +
            ANSI_WHITE_BACKGROUND+"│______________________________________________________________________________│" + ANSI_RESET + " " + ANSI_YELLOW_BACKGROUND + "│________________________│" + ANSI_RESET + "\n";
    
}
