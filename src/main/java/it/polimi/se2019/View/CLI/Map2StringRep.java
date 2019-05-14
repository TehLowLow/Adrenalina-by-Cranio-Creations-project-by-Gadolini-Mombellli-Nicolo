package it.polimi.se2019.View.CLI;

import it.polimi.se2019.Model.Board;
import it.polimi.se2019.Model.Map;
import it.polimi.se2019.Model.Room;
import static it.polimi.se2019.View.CLI.Location.*;
public class Map2StringRep {


    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[41m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[43m";
    public static final String ANSI_BLUE ="\u001B[44m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_BOLD = "\033[0;1m";

    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND =  "\u001B[31m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[33m";
    public static final String ANSI_BLUE_BACKGROUND =  "\u001B[34m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[30m";

    static Map map = Board.getMap();
    static Room BlueRoom = map.getBlueRoom();
    static Room YellowRoom = map.getYellowRoom();
    static Room RedRoom = map.getRedRoom();
    static Room WhiteRoom = map.getWhiteRoom();
    static Room GreenRoom = map.getGreenRoom();


    static String map2 =


                    ANSI_BLUE_BACKGROUND + " ______________________________________________________________________________ " + ANSI_RESET + "  " + ANSI_GREEN_BACKGROUND + "_________________________ " + ANSI_RESET + "\n" +
                    ANSI_BLUE_BACKGROUND + "│" + firstRow(getCell(BlueRoom, "lootCell1")) + ". ." +  firstRow(getCell(BlueRoom, "lootCell2")) + ". ." +  firstRow(getCell(BlueRoom, "spawnCell")) +  "│" +  ANSI_RESET + "  " + ANSI_GREEN_BACKGROUND + "│" + firstRow(getCell(GreenRoom, "lootCell")) + "│" + ANSI_RESET + "\n" +
                    ANSI_BLUE_BACKGROUND + "│" + secondRow(getCell(BlueRoom, "lootCell1")) + ". ." + secondRow(getCell(BlueRoom, "lootCell2")) + ". ." + secondRow(getCell(BlueRoom, "spawnCell")) + "│" +  ANSI_RESET + "__" + ANSI_GREEN_BACKGROUND + "│" + secondRow(getCell(GreenRoom, "lootCell")) + "│" + ANSI_RESET + "\n" +
                    ANSI_BLUE_BACKGROUND + "│" + thirdRow(getCell(BlueRoom, "lootCell1")) + ". ." + thirdRow(getCell(BlueRoom, "lootCell2")) + ". ." + thirdRow(getCell(BlueRoom, "spawnCell")) + " " + ANSI_RESET + "  " + ANSI_GREEN_BACKGROUND + " " + thirdRow(getCell(GreenRoom, "lootCell")) + "│" + ANSI_RESET + "\n" +
                    ANSI_BLUE_BACKGROUND + "│" + fourthRow(getCell(BlueRoom, "lootCell1")) + ". ." + fourthRow(getCell(BlueRoom, "lootCell2")) + ". ." + fourthRow(getCell(BlueRoom, "spawnCell")) + " " + ANSI_RESET + "__" + ANSI_GREEN_BACKGROUND + " " + fourthRow(getCell(GreenRoom, "lootCell")) + "│" + ANSI_RESET + "\n" +
                    ANSI_BLUE_BACKGROUND + "│" + fifthRow(getCell(BlueRoom, "lootCell1")) + ". ." + fifthRow(getCell(BlueRoom, "lootCell2")) + ". ." + fifthRow(getCell(BlueRoom, "spawnCell")) + "│" + ANSI_RESET + "  " + ANSI_GREEN_BACKGROUND + "│" + fifthRow(getCell(GreenRoom, "lootCell")) + "│" + ANSI_RESET + "\n" +
                    ANSI_BLUE_BACKGROUND + "│            loot cell 1 . .            loot cell 2 . .              spawn cell│" + ANSI_RESET + "  " + ANSI_GREEN_BACKGROUND + "│               loot cell│" + ANSI_RESET + "\n" +
                    ANSI_BLUE_BACKGROUND + "│_______        ______________________________________________        _________│" + ANSI_RESET + "  " + ANSI_GREEN_BACKGROUND + "│_______         ________│" + ANSI_RESET + "\n" +
                                           "        │      │                                              │      │                    │       │         \n" +
                    ANSI_RED_BACKGROUND  + " _______│      │____________________________________ " + ANSI_RESET + "  " + ANSI_YELLOW_BACKGROUND + " ______│      │____________________│       │________ " + ANSI_RESET + "\n" +
                    ANSI_RED_BACKGROUND  + "│" + firstRow(getCell(RedRoom, "spawnCell")) + ". ." + firstRow(getCell(RedRoom, "lootCell")) + "│" + ANSI_RESET + "  " + ANSI_YELLOW_BACKGROUND + "│" + firstRow(getCell(YellowRoom, "lootCell1")) + ". ." + firstRow(getCell(YellowRoom, "lootCell2")) + "│" + ANSI_RESET + "\n" +
                    ANSI_RED_BACKGROUND  + "│" + secondRow(getCell(RedRoom, "spawnCell")) + ". ." + secondRow(getCell(RedRoom, "lootCell")) + "│" + ANSI_RESET + "  " + ANSI_YELLOW_BACKGROUND + "│" + secondRow(getCell(YellowRoom, "lootCell1")) + ". ." + secondRow(getCell(YellowRoom, "lootCell2")) + "│" + ANSI_RESET + "\n" +
                    ANSI_RED_BACKGROUND  + "│" + thirdRow(getCell(RedRoom, "spawnCell")) + ". ." + thirdRow(getCell(RedRoom, "lootCell")) + "│" + ANSI_RESET + "  " + ANSI_YELLOW_BACKGROUND + "│" + thirdRow(getCell(YellowRoom, "lootCell1")) + ". ." + thirdRow(getCell(YellowRoom, "lootCell2")) + "│" + ANSI_RESET + "\n" +
                    ANSI_RED_BACKGROUND  + "│" + fourthRow(getCell(RedRoom, "spawnCell")) + ". ." + fourthRow(getCell(RedRoom, "lootCell")) + "│" + ANSI_RESET + "  " + ANSI_YELLOW_BACKGROUND + "│" + fourthRow(getCell(YellowRoom, "lootCell1")) + ". ." + fourthRow(getCell(YellowRoom, "lootCell2")) + "│" + ANSI_RESET + "\n" +
                    ANSI_RED_BACKGROUND  + "│" + fifthRow(getCell(RedRoom, "spawnCell")) + ". ." + fifthRow(getCell(RedRoom, "lootCell")) + "│" + ANSI_RESET + "  " + ANSI_YELLOW_BACKGROUND + "│" + fifthRow(getCell(YellowRoom, "lootCell1")) + ". ." + fifthRow(getCell(YellowRoom, "lootCell2")) + "│" + ANSI_RESET + "\n" +
                    ANSI_RED_BACKGROUND  + "│              spawn cell. .               loot cell│" + ANSI_RESET + "  " + ANSI_YELLOW_BACKGROUND + "│             loot cell 1. .             loot cell 2│" + ANSI_RESET + "\n" +
                    ANSI_RED_BACKGROUND  + "│__________________________________        _________│" + ANSI_RESET + "  " + ANSI_YELLOW_BACKGROUND + "│ . . . . . . . . . . . .   . . . . . . . . . . . . │" + ANSI_RESET + "\n" +
                                           "                                   │      │            "  + ANSI_YELLOW_BACKGROUND + "│                                                   │" + ANSI_RESET + "\n" +

                                           "                           " + ANSI_WHITE_BACKGROUND + "________│      │_________ " + ANSI_RESET + "  " + ANSI_YELLOW_BACKGROUND + "│ . . . . . . . . . . . .   . . . . . . . . . . . . │" + ANSI_RESET + "\n" +
                                           "                           " + ANSI_WHITE_BACKGROUND + "│" + firstRow(getCell(WhiteRoom, "lootCell")) + "│" + ANSI_RESET + "  " + ANSI_YELLOW_BACKGROUND + "│" + firstRow(getCell(YellowRoom, "lootCell3")) + ". ." + firstRow(getCell(YellowRoom, "spawnCell")) + "│" + ANSI_RESET + "\n" +
                                           "                           " + ANSI_WHITE_BACKGROUND + "│" + secondRow(getCell(WhiteRoom, "lootCell")) + "│" + ANSI_RESET + "__" + ANSI_YELLOW_BACKGROUND + "│" + secondRow(getCell(YellowRoom, "lootCell3")) + ". ." + secondRow(getCell(YellowRoom, "spawnCell")) + "│" + ANSI_RESET + "\n" +
                                           "                           " + ANSI_WHITE_BACKGROUND + "│" + thirdRow(getCell(WhiteRoom, "lootCell")) + " " + ANSI_RESET + "  " + ANSI_YELLOW_BACKGROUND + " " + thirdRow(getCell(YellowRoom, "lootCell3")) + ". ." + thirdRow(getCell(YellowRoom, "spawnCell")) + "│" + ANSI_RESET + "\n" +
                                           "                           " + ANSI_WHITE_BACKGROUND + "│" + fourthRow(getCell(WhiteRoom, "lootCell")) + " " + ANSI_RESET + "__" + ANSI_YELLOW_BACKGROUND + " " + fourthRow(getCell(YellowRoom, "lootCell3")) + ". ." + fourthRow(getCell(YellowRoom, "spawnCell")) + "│" + ANSI_RESET + "\n" +
                                           "                           " + ANSI_WHITE_BACKGROUND + "│" + fifthRow(getCell(WhiteRoom, "lootCell")) + "│" + ANSI_RESET + "  " + ANSI_YELLOW_BACKGROUND + "│" + fifthRow(getCell(YellowRoom, "lootCell3")) + ". ." + fifthRow(getCell(YellowRoom, "spawnCell")) + "│" + ANSI_RESET + "\n" +
                                           "                           " + ANSI_WHITE_BACKGROUND + "│               loot cell│" + ANSI_RESET + "  " + ANSI_YELLOW_BACKGROUND + "│             loot cell 3. .              spawn cell│" + ANSI_RESET + "\n" +
                                           "                           " + ANSI_WHITE_BACKGROUND + "│________________________│" + ANSI_RESET + "  " + ANSI_YELLOW_BACKGROUND + "│___________________________________________________│" + ANSI_RESET + "\n";
}
