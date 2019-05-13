package it.polimi.se2019.View.CLI;
import it.polimi.se2019.Controller.Data.MapBuilders.Map1Builder;
import it.polimi.se2019.Model.*;

import java.util.ArrayList;

import static it.polimi.se2019.View.CLI.Location.*;

public class Map1StringRep {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_BOLD = "\033[0;1m";

    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";


    static Map map = Board.getMap();
    static Room BlueRoom = map.getBlueRoom();
    static Room YellowRoom = map.getYellowRoom();
    static Room RedRoom = map.getRedRoom();
    static Room WhiteRoom = map.getWhiteRoom();

    public static String map1 =  

                                ANSI_BLUE_BACKGROUND  + "________________________________________________________________________________"    + ANSI_RESET + "\n" +
             ANSI_BLUE_BACKGROUND  + "│" + firstRow(getCell(BlueRoom,"lootCell1"))   +    ". ." + firstRow(getCell(BlueRoom, "lootCell2"))   + ". ." + firstRow(getCell(BlueRoom, "spawnCell"))  + "│" + ANSI_RESET + "\n" +
             ANSI_BLUE_BACKGROUND  + "│" + secondRow(getCell(BlueRoom,"lootCell1"))  +    ". ." + secondRow(getCell(BlueRoom, "lootCell2"))  + ". ." + secondRow(getCell(BlueRoom, "spawnCell")) + "│" + ANSI_RESET + "\n" +
             ANSI_BLUE_BACKGROUND  + "│" + thirdRow(getCell(BlueRoom,"lootCell1"))   +    ". ." + thirdRow(getCell(BlueRoom, "lootCell2"))   + ". ." + thirdRow(getCell(BlueRoom, "spawnCell"))  + "│" + ANSI_RESET + "\n" +
             ANSI_BLUE_BACKGROUND  + "│" + fourthRow(getCell(BlueRoom,"lootCell1"))  +    ". ." + fourthRow(getCell(BlueRoom, "lootCell2"))  + ". ." + fourthRow(getCell(BlueRoom, "spawnCell")) + "│" + ANSI_RESET + "\n" +
             ANSI_BLUE_BACKGROUND  + "│" + fifthRow(getCell(BlueRoom,"lootCell1"))   +    ". ." + fifthRow(getCell(BlueRoom, "lootCell2"))   + ". ." + fifthRow(getCell(BlueRoom, "spawnCell"))  + "│" + ANSI_RESET + "\n" +
             ANSI_BLUE_BACKGROUND  + "│             loot cell 1. .             loot cell 2. .              spawn cell│" + ANSI_RESET + "\n" +
             ANSI_BLUE_BACKGROUND  + "│_________     __________________________________________________     _________│" + ANSI_RESET + "\n"
                                             + "          │   │                                                  │   │         \n" +

             ANSI_RED_BACKGROUND   + "__________│   │__________________________________________________│   │__________" + ANSI_RESET + "  " + ANSI_YELLOW_BACKGROUND + " _________________________" + ANSI_RESET + "\n" +
             ANSI_RED_BACKGROUND   + "│" + firstRow(getCell(RedRoom,"spawnCell"))   +    ". ." + firstRow(getCell(RedRoom, "lootCell1"))   + ". ." + firstRow(getCell(RedRoom, "lootCell2"))    + "│" + ANSI_RESET + "  " +   ANSI_YELLOW_BACKGROUND + "│" + firstRow(getCell(YellowRoom,"lootCell"))    + "│" + ANSI_RESET + "\n" +
             ANSI_RED_BACKGROUND   + "│" + secondRow(getCell(RedRoom,"spawnCell"))  +    ". ." + secondRow(getCell(RedRoom, "lootCell1"))   + ". ." + secondRow(getCell(RedRoom, "lootCell2"))  + "│" + ANSI_RESET + "__" +   ANSI_YELLOW_BACKGROUND + "│" + secondRow(getCell(YellowRoom,"lootCell")) + "│" + ANSI_RESET + "\n" +
             ANSI_RED_BACKGROUND   + "│" + thirdRow(getCell(RedRoom,"spawnCell"))   +    ". ." + thirdRow(getCell(RedRoom, "lootCell1"))   + ". ." + thirdRow(getCell(RedRoom, "lootCell2"))    + " " + ANSI_RESET + "  " +   ANSI_YELLOW_BACKGROUND + " " + thirdRow(getCell(YellowRoom,"lootCell"))    + "│" + ANSI_RESET + "\n" +
             ANSI_RED_BACKGROUND   + "│" + fourthRow(getCell(RedRoom,"spawnCell"))  +    ". ." + fourthRow(getCell(RedRoom, "lootCell1"))   + ". ." + fourthRow(getCell(RedRoom, "lootCell2"))  + " " + ANSI_RESET + "__" +   ANSI_YELLOW_BACKGROUND + " " + fourthRow(getCell(YellowRoom,"lootCell")) + "│" + ANSI_RESET + "\n" +
             ANSI_RED_BACKGROUND   + "│" + fifthRow(getCell(RedRoom,"spawnCell"))   +    ". ." + fifthRow(getCell(RedRoom, "lootCell1"))   + ". ." + fifthRow(getCell(RedRoom, "lootCell2"))    + "│" + ANSI_RESET + "  " +   ANSI_YELLOW_BACKGROUND + "│" + fifthRow(getCell(YellowRoom,"lootCell"))    + "│" + ANSI_RESET + "\n" +
             ANSI_RED_BACKGROUND   + "│              spawn cell. .             loot cell 1. .             loot cell 2│" +   ANSI_RESET + "  " + ANSI_YELLOW_BACKGROUND + "│               loot cell│" + ANSI_RESET + "\n" +
             ANSI_RED_BACKGROUND   + "│____________________________________      ____________________________________│" +   ANSI_RESET + "  " + ANSI_YELLOW_BACKGROUND + "│. . . . . . . . . . . . │" + ANSI_RESET + "\n" +


              "                           "+ ANSI_RESET +            "          │    │                                     " + ANSI_RESET + "  " + ANSI_YELLOW_BACKGROUND+ "│                        │" + ANSI_RESET + "\n" +
              "                           "+ ANSI_WHITE_BACKGROUND + "__________│    │____________________________________ " + ANSI_RESET + "  " + ANSI_YELLOW_BACKGROUND + "│. . . . . . . . . . . . │" + ANSI_RESET + "\n" +
              "                           "+ ANSI_WHITE_BACKGROUND + "│" + firstRow(getCell(WhiteRoom,"lootCell1"))   +    ". ." + firstRow(getCell(WhiteRoom, "lootCell2"))    + "│" + ANSI_RESET + "  " + ANSI_YELLOW_BACKGROUND + "│" + firstRow(getCell(YellowRoom,"spawnCell"))    + "│" + ANSI_RESET + "\n" +
              "                           "+ ANSI_WHITE_BACKGROUND + "│" + secondRow(getCell(WhiteRoom,"lootCell1"))  +    ". ." + secondRow(getCell(WhiteRoom, "lootCell2"))   + "│" + ANSI_RESET + "__" + ANSI_YELLOW_BACKGROUND + "│" + secondRow(getCell(YellowRoom,"spawnCell"))   + "│" + ANSI_RESET + "\n" +
              "                           "+ ANSI_WHITE_BACKGROUND + "│" + thirdRow(getCell(WhiteRoom,"lootCell1"))   +    ". ." + thirdRow(getCell(WhiteRoom, "lootCell2"))    + " " + ANSI_RESET + "  " + ANSI_YELLOW_BACKGROUND + " " + thirdRow(getCell(YellowRoom,"spawnCell"))    + "│" + ANSI_RESET + "\n" +
              "                           "+ ANSI_WHITE_BACKGROUND + "│" + fourthRow(getCell(WhiteRoom,"lootCell1"))  +    ". ." + fourthRow(getCell(WhiteRoom, "lootCell2"))   + " " + ANSI_RESET + "__" + ANSI_YELLOW_BACKGROUND + " " + fourthRow(getCell(YellowRoom,"spawnCell"))   + "│" + ANSI_RESET + "\n" +
              "                           "+ ANSI_WHITE_BACKGROUND + "│" + fifthRow(getCell(WhiteRoom,"lootCell1"))   +    ". ." + fifthRow(getCell(WhiteRoom, "lootCell2"))    + "│" + ANSI_RESET + "  " + ANSI_YELLOW_BACKGROUND + "│" + fifthRow(getCell(YellowRoom,"spawnCell"))    + "│" + ANSI_RESET + "\n" +
              "                           "+ ANSI_WHITE_BACKGROUND + "│             loot cell 1. .             loot cell 2│"+ ANSI_RESET + "  " + ANSI_YELLOW_BACKGROUND + "│              spawn cell│" + ANSI_RESET + "\n" +
              "                           "+ ANSI_WHITE_BACKGROUND + "│___________________________________________________│"+ ANSI_RESET + "  " + ANSI_YELLOW_BACKGROUND + "│________________________│" + ANSI_RESET + "\n";




}
