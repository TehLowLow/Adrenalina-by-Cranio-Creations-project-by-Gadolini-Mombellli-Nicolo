package it.polimi.se2019.View.CLI;

import it.polimi.se2019.Model.Board;
import it.polimi.se2019.Model.Map;
import it.polimi.se2019.Model.Room;

public class Colors {


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


}
