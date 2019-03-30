package it.polimi.se2019.Model;

/**
 * This class represents the map of the game, i.e the arena where the champions fight.
 * A map is an object composed by various rooms; every room is made of squares, the single cells where the
 * players can be.
 *
 */

public class Map {

    private Integer mapID;
    private Room greenRoom;
    private Room redRoom;
    private Room yellowRoom;
    private Room whiteRoom;
    private Room purpleRoom;
    private Room blueRoom;


    /**
     * ---------------------- METHODS
     */

    /**
     * ---------------------- CONSTRUCTOR
     * Instead of using setters, there is a custom constructor for the Map objects that assigns the
     * various rooms.
     */

    public Map(Integer mapID, Room greenRoom, Room redRoom, Room yellowRoom, Room whiteRoom, Room purpleRoom, Room blueRoom) {
        this.mapID = mapID;
        this.greenRoom = greenRoom;
        this.redRoom = redRoom;
        this.yellowRoom = yellowRoom;
        this.whiteRoom = whiteRoom;
        this.purpleRoom = purpleRoom;
        this.blueRoom = blueRoom;
    }

    /**
     *  ---------------------- GETTERS
     */

    public Integer getMapID() {
        return mapID;
    }

    public Room getGreenRoom() {
        return greenRoom;
    }

    public Room getRedRoom() {
        return redRoom;
    }

    public Room getYellowRoom() {
        return yellowRoom;
    }

    public Room getWhiteRoom() {
        return whiteRoom;
    }

    public Room getPurpleRoom() {
        return purpleRoom;
    }

    public Room getBlueRoom() {
        return blueRoom;
    }

}
