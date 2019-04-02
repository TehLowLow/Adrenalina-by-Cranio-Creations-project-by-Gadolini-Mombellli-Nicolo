package it.polimi.se2019.Model;

/**
 * This class represents the map of the game, i.e the arena where the champions fight.
 * A map is an object composed by various rooms; every room is made of squares, the single cells where the
 * players can be.
 *
 */

public class Map {

    /*
     * ---------------------- FIELDS
     * They are used to represent the structure of a map. The mapID is unique for each map.
     * It is possible to have some rooms set to NULL, if the map doesn't have a room of that specific colour.
     */

    /**
     * ID of the map.
     */
    private Integer mapID;

    /*
     * Rooms of the map.
     */

    private Room greenRoom;
    private Room redRoom;
    private Room yellowRoom;
    private Room whiteRoom;
    private Room purpleRoom;
    private Room blueRoom;


    /*
     * ---------------------- METHODS
     */

    /*
     * ---------------------- CONSTRUCTOR
     */

    /**
     * Custom constructor of the Map object.
     * @param mapID Unique ID which identifies a map.
     * @param greenRoom Pointer to the green room. It can also be NULL.
     * @param redRoom Pointer to the red room. It can also be NULL.
     * @param yellowRoom Pointer to the yellow room. It can also be NULL.
     * @param whiteRoom Pointer to the white room. It can also be NULL.
     * @param purpleRoom Pointer to the purple room. It can also be NULL.
     * @param blueRoom Pointer to the blue room. It can also be NULL.
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

    /*
     *  ---------------------- GETTERS
     */

    /**
     * Getter of MapID.
     * @return the mapID.
     */
    public Integer getMapID() {
        return mapID;
    }

    /**
     * Getter of the Green Room.
     * @return Green room if it exists, NULL otherwise.
     */
    public Room getGreenRoom() {
        return greenRoom;
    }

    /**
     * Getter of the Red Room.
     * @return Red room if it exists, NULL otherwise.
     */
    public Room getRedRoom() {
        return redRoom;
    }

    /**
     * Getter of the Yellow Room.
     * @return Yellow room if it exists, NULL otherwise.
     */
    public Room getYellowRoom() {
        return yellowRoom;
    }


    /**
     * Getter of the White Room.
     * @return White room if it exists, NULL otherwise.
     */
    public Room getWhiteRoom() {
        return whiteRoom;
    }

    /**
     * Getter of the Purple Room.
     * @return Purple room if it exists, NULL otherwise.
     */
    public Room getPurpleRoom() {
        return purpleRoom;
    }

    /**
     * Getter of the Blue Room.
     * @return Blue room if it exists, NULL otherwise.
     */
    public Room getBlueRoom() {
        return blueRoom;
    }

}
