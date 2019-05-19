package it.polimi.se2019.View.CLI;

import it.polimi.se2019.Model.Board;
import it.polimi.se2019.Model.Map;
import it.polimi.se2019.Model.Room;

public class Colors {


    static final String ANSI_RESET = "\u001B[0m";
    static final String ANSI_BLACK = "\u001B[30m";
    static final String ANSI_RED = "\u001B[41m";
    static final String ANSI_GREEN = "\u001B[42m";
    static final String ANSI_YELLOW = "\u001B[43m";
    static final String ANSI_BLUE = "\u001B[44m";
    static final String ANSI_PURPLE = "\u001B[45m";
    static final String ANSI_CYAN = "\u001B[36m";
    static final String ANSI_WHITE = "\u001B[37m";
    static final String ANSI_BOLD = "\033[0;1m";

    static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    static final String ANSI_RED_BACKGROUND = "\u001B[31m";
    static final String ANSI_GREEN_BACKGROUND = "\u001B[32m";
    static final String ANSI_YELLOW_BACKGROUND = "\u001B[33m";
    static final String ANSI_BLUE_BACKGROUND = "\u001B[34m";
    static final String ANSI_PURPLE_BACKGROUND = "\u001B[35m";
    static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    static final String ANSI_WHITE_BACKGROUND = "\u001B[30m";


    static Map map = Board.getMap();
    static Room blueRoom = map.getBlueRoom();
    static Room yellowRoom = map.getYellowRoom();
    static Room redRoom = map.getRedRoom();
    static Room whiteRoom = map.getWhiteRoom();
    static Room greenRoom = map.getGreenRoom();
    static Room purpleRoom = map.getPurpleRoom();


}
