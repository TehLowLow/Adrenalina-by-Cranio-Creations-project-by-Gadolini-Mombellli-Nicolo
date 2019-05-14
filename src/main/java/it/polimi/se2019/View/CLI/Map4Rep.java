package it.polimi.se2019.View.CLI;

import it.polimi.se2019.Model.Board;
import it.polimi.se2019.Model.LootCell;
import it.polimi.se2019.Model.Map;
import it.polimi.se2019.Model.Room;

import static it.polimi.se2019.View.CLI.Location.*;

public class Map4Rep {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[41m";
    public static final String ANSI_GREEN = "\u001B[42m";
    public static final String ANSI_YELLOW = "\u001B[43m";
    public static final String ANSI_BLUE = "\u001B[44m";
    public static final String ANSI_PURPLE = "\u001B[45m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_BOLD = "\033[0;1m";

    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[31m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[32m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[33m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[34m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[35m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[30m";

    static Map map = Board.getMap();
    static Room BlueRoom = map.getBlueRoom();
    static Room YellowRoom = map.getYellowRoom();
    static Room RedRoom = map.getRedRoom();
    static Room WhiteRoom = map.getWhiteRoom();
    static Room GreenRoom = map.getGreenRoom();
    static Room PurpleRoom = map.getPurpleRoom();

    public static String map4 = ANSI_BOLD +

            ANSI_RED_BACKGROUND + " " + "________________________" + " " + ANSI_RESET + " " + ANSI_BLUE_BACKGROUND + " " + "___________________________________________________" + " " + ANSI_RESET + " " + ANSI_GREEN_BACKGROUND + " " + "________________________" + " " + ANSI_RESET + "\n" +
            ANSI_RED_BACKGROUND + "│" + firstRow(getCell(RedRoom, "LootCell")) + "│" + ANSI_RESET + " " + ANSI_BLUE_BACKGROUND + "│" + firstRow(getCell(BlueRoom, "LootCell")) + ". ." + firstRow(getCell(BlueRoom, "SpawnCell")) + "│" + ANSI_RESET + " " + ANSI_GREEN_BACKGROUND + "│" + firstRow(getCell(GreenRoom, "LootCell")) + "│" + ANSI_RESET + "\n" +
            ANSI_RED_BACKGROUND + "│" + secondRow(getCell(RedRoom, "LootCell")) + "│" + ANSI_RESET + " " + ANSI_BLUE_BACKGROUND + "│" + secondRow(getCell(BlueRoom, "LootCell")) + ". ." + secondRow(getCell(BlueRoom, "SpawnCell")) + "│" + ANSI_RESET + " " + ANSI_GREEN_BACKGROUND + "│" + secondRow(getCell(GreenRoom, "LootCell")) + "│" + ANSI_RESET + "\n" +
            ANSI_RED_BACKGROUND + "│" + thirdRow(getCell(RedRoom, "Lootcell")) + ANSI_RESET + "͞" + "͞" + "͞" + ANSI_BLUE_BACKGROUND + thirdRow(getCell(BlueRoom, "LootCell")) + ". ." + thirdRow(getCell(BlueRoom, "SpawnCell")) + ANSI_RESET + "͞" + "͞" + "͞" + ANSI_GREEN_BACKGROUND + thirdRow(getCell(GreenRoom, "LootCell")) + "│" + ANSI_RESET + "\n" +
            ANSI_RED_BACKGROUND + "│" + fourthRow(getCell(RedRoom, "Lootcell")) + ANSI_RESET + "͟͟͟" + ANSI_BLUE_BACKGROUND + fourthRow(getCell(BlueRoom, "LootCell")) + ". ." + fourthRow(getCell(BlueRoom, "Spawncell")) + ANSI_RESET + "͟͟͟" + ANSI_GREEN_BACKGROUND + fourthRow(getCell(GreenRoom, "LootCell")) + "│" + ANSI_RESET + "\n" +
            ANSI_RED_BACKGROUND + "│" + fifthRow(getCell(RedRoom, "LootCell")) + "│" + ANSI_RESET + " " + ANSI_BLUE_BACKGROUND + "│" + fifthRow(getCell(BlueRoom, "LootCell")) + ". ." + fifthRow(getCell(BlueRoom, "SpawnCell")) + "│" + ANSI_RESET + " " + ANSI_GREEN_BACKGROUND + "│" + fifthRow(getCell(GreenRoom, "LootCell")) + "│" + ANSI_RESET + "\n" +
            ANSI_RED_BACKGROUND + "│               Loot Cell│" + ANSI_RESET + " " + ANSI_BLUE_BACKGROUND + "│               Loot Cell. .              Spawn Cell│" + ANSI_RESET + " " + ANSI_GREEN_BACKGROUND + "│               Loot Cell│" + ANSI_RESET + "\n" +
            ANSI_RED_BACKGROUND + "│. . . . . . . . . . . . │" + ANSI_RESET + " " + ANSI_BLUE_BACKGROUND + "│_________       ____________________       ________│" + ANSI_RESET + " " + ANSI_GREEN_BACKGROUND + "│_________       ________│" + ANSI_RESET + "\n" +
            ANSI_RED_BACKGROUND + "│                        │" + ANSI_RESET + "           │     │                    │     │                    │     │        " + "\n" +
            ANSI_RED_BACKGROUND + "│. . . . . . . . . . . . │" + ANSI_RESET + " " + ANSI_PURPLE_BACKGROUND + "│͞͞͞͞͞͞͞͞͞" + ANSI_RESET + "       " + ANSI_PURPLE_BACKGROUND + "͞͞͞͞͞͞͞͞│" + ANSI_RESET + " " + ANSI_YELLOW_BACKGROUND + "│͞͞͞͞͞͞͞͞͞" + ANSI_RESET + "       " + ANSI_YELLOW_BACKGROUND + "͞͞͞͞͞͞͞͞͞͞͞͞͞͞͞͞͞͞͞͞" + ANSI_RESET + "       " + ANSI_YELLOW_BACKGROUND + "͞͞͞͞͞͞͞͞│" + ANSI_RESET + "\n" +
            ANSI_RED_BACKGROUND + "│" + firstRow(getCell(RedRoom, "SpawnCell")) + "│" + ANSI_RESET + " " + ANSI_PURPLE_BACKGROUND + "│" + firstRow(getCell(PurpleRoom, "LootCell")) + "│" + ANSI_RESET + " " + ANSI_YELLOW_BACKGROUND + "│" + firstRow(getCell(YellowRoom, "LootCell1")) + ". ." + firstRow(getCell(YellowRoom, "Lootcell2")) + "│" + ANSI_RESET + "\n" +
            ANSI_RED_BACKGROUND + "│" + secondRow(getCell(RedRoom, "Spawncell")) + "│" + ANSI_RESET + " " + ANSI_PURPLE_BACKGROUND + "│" + secondRow(getCell(PurpleRoom, "LootCell")) + "│" + ANSI_RESET + " " + ANSI_YELLOW_BACKGROUND + "│" + secondRow(getCell(YellowRoom, "LootCell1")) + ". ." + secondRow(getCell(YellowRoom, "LootCell2")) + "│" + ANSI_RESET + "\n" +
            ANSI_RED_BACKGROUND + "│" + thirdRow(getCell(RedRoom, "SpawnCell")) + "│" + ANSI_RESET + " " + ANSI_PURPLE_BACKGROUND + "│" + thirdRow(getCell(PurpleRoom, "Lootcell")) + "│" + ANSI_RESET + " " + ANSI_YELLOW_BACKGROUND + "│" + thirdRow(getCell(YellowRoom, "Lootcell1")) + ". ." + thirdRow(getCell(YellowRoom, "Lootcell2")) + "│" + ANSI_RESET + "\n" +
            ANSI_RED_BACKGROUND + "│" + fourthRow(getCell(RedRoom, "SpawnCell")) + "│" + ANSI_RESET + " " + ANSI_PURPLE_BACKGROUND + "│" + fourthRow(getCell(PurpleRoom, "LootCell")) + "│" + ANSI_RESET + " " + ANSI_YELLOW_BACKGROUND + "│" + fourthRow(getCell(YellowRoom, "Lootcell1")) + ". ." + fourthRow(getCell(YellowRoom, "Lootcell2")) + "│" + ANSI_RESET + "\n" +
            ANSI_RED_BACKGROUND + "│" + fifthRow(getCell(RedRoom, "Spawncell")) + "│" + ANSI_RESET + " " + ANSI_PURPLE_BACKGROUND + "│" + fifthRow(getCell(PurpleRoom, "Lootcell")) + "│" + ANSI_RESET + " " + ANSI_YELLOW_BACKGROUND + "│" + fifthRow(getCell(YellowRoom, "LootCell1")) + ". ." + fifthRow(getCell(YellowRoom, "lootcell2")) + "│" + ANSI_RESET + "\n" +
            ANSI_RED_BACKGROUND + "│              Spawn Cell│" + ANSI_RESET + " " + ANSI_PURPLE_BACKGROUND + "│               Loot Cell│" + ANSI_RESET + " " + ANSI_YELLOW_BACKGROUND + "│             Loot Cell 1. .             Loot Cell 2│" + ANSI_RESET + "\n" +
            ANSI_RED_BACKGROUND + "│________       _________│" + ANSI_RESET + " " + ANSI_PURPLE_BACKGROUND + "│_________       ________│" + ANSI_RESET + " " + ANSI_YELLOW_BACKGROUND + "│. . . . . . . . . . . . . . . . . . . . . . . . . .│" + ANSI_RESET + "\n" +
            "         │     │                     │     │          " + ANSI_YELLOW_BACKGROUND + "│                        . .                        │" + ANSI_RESET + "\n" +
            ANSI_WHITE_BACKGROUND + "│͞͞͞͞͞͞͞͞      ͞͞͞͞͞͞͞͞͞͞͞͞͞͞͞͞͞͞͞͞͞͞       ͞͞͞͞͞͞͞͞│" + ANSI_RESET + " " + ANSI_YELLOW_BACKGROUND + "│. . . . . . . . . . . . . . . . . . . . . . . . . .│" + ANSI_RESET + "\n" +
            ANSI_WHITE_BACKGROUND + "│" + firstRow(getCell(WhiteRoom, "lootcell1")) + ". ." + firstRow(getCell(WhiteRoom, "LootCell2")) + "│" + ANSI_RESET + " " + ANSI_YELLOW_BACKGROUND + "│" + firstRow(getCell(YellowRoom, "lootcell3")) + ". ." + firstRow(getCell(YellowRoom, "Spawncell")) + "│" + ANSI_RESET + "\n" +
            ANSI_WHITE_BACKGROUND + "│" + secondRow(getCell(WhiteRoom, "Lootcell1")) + ". ." + secondRow(getCell(WhiteRoom, "Lootcell2")) + ANSI_RESET + "͞͞͞" + ANSI_YELLOW_BACKGROUND + secondRow(getCell(YellowRoom, "Lootcell3")) + ". ." + secondRow(getCell(YellowRoom, "SpawnCell")) + "│" + ANSI_RESET + "\n" +
            ANSI_WHITE_BACKGROUND + "│" + thirdRow(getCell(WhiteRoom, "Lootcell1")) + ". ." + thirdRow(getCell(WhiteRoom, "Lootcell2")) + ANSI_RESET + "͟͟͟" + ANSI_YELLOW_BACKGROUND + thirdRow(getCell(YellowRoom, "Lootcell3")) + ". ." + thirdRow(getCell(YellowRoom, "Spawncell")) + "│" + ANSI_RESET + "\n" +
            ANSI_WHITE_BACKGROUND + "│" + fourthRow(getCell(WhiteRoom, "Lootcell1")) + ". ." + fourthRow(getCell(WhiteRoom, "Lootcell2")) + "│" + ANSI_RESET + " " + ANSI_YELLOW_BACKGROUND + "│" + fourthRow(getCell(YellowRoom, "lootcell3")) + ". ." + fourthRow(getCell(YellowRoom, "Spawncell")) + "│" + ANSI_RESET + "\n" +
            ANSI_WHITE_BACKGROUND + "│" + fifthRow(getCell(WhiteRoom, "lootcell1")) + ". ." + fifthRow(getCell(WhiteRoom, "lootcell2")) + "│" + ANSI_RESET + " " + ANSI_YELLOW_BACKGROUND + "│" + fifthRow(getCell(YellowRoom, "lootcell3")) + ". ." + fifthRow(getCell(YellowRoom, "spawncell")) + "│" + ANSI_RESET + "\n" +
            ANSI_WHITE_BACKGROUND + "│            Loot Cell 1 . .             Loot Cell 2│" + ANSI_RESET + " " + ANSI_YELLOW_BACKGROUND + "│             Loot Cell 3. .              Spawn Cell│" + ANSI_RESET + "\n" +
            ANSI_WHITE_BACKGROUND + "│___________________________________________________│" + ANSI_RESET + " " + ANSI_YELLOW_BACKGROUND + "│___________________________________________________│" + ANSI_RESET + "\n";


}
