import processing.core.PImage;
import processing.core.PVector;

public abstract class Room {
    PVector mapCoordinates;
    String roomName;
    boolean exitNorth;
    boolean exitEast;
    boolean exitSouth;
    boolean exitWest;
    boolean hasCharacter = false;
    PImage img;
    KeyforDoors keyforDoors;
    Door door;
    boolean hasenteredYet = false;
    int roomID;
    int storyID;

    public Room(PVector mapCoordinates, String roomName, boolean exitNorth, boolean exitEast, boolean exitSouth, boolean exitWest) {
        this.mapCoordinates = mapCoordinates;
        this.roomName = roomName;
        this.exitNorth = exitNorth;
        this.exitEast = exitEast;
        this.exitSouth = exitSouth;
        this.exitWest = exitWest;

    }
    public Room(PVector mapCoordinates, String roomName, boolean exitNorth, boolean exitEast, boolean exitSouth, boolean exitWest, KeyforDoors keyforDoors) {
        this.mapCoordinates = mapCoordinates;
        this.roomName = roomName;
        this.exitNorth = exitNorth;
        this.exitEast = exitEast;
        this.exitSouth = exitSouth;
        this.exitWest = exitWest;
        this.keyforDoors = keyforDoors;

    }
    public Room(PVector mapCoordinates, String roomName, boolean exitNorth, boolean exitEast, boolean exitSouth, boolean exitWest, KeyforDoors keyforDoors, Door door) {
        this.mapCoordinates = mapCoordinates;
        this.roomName = roomName;
        this.exitNorth = exitNorth;
        this.exitEast = exitEast;
        this.exitSouth = exitSouth;
        this.exitWest = exitWest;
        this.keyforDoors = keyforDoors;
        this.door = door;

    }
    public Room(PVector mapCoordinates, String roomName, boolean exitNorth, boolean exitEast, boolean exitSouth, boolean exitWest, Door door) {
        this.mapCoordinates = mapCoordinates;
        this.roomName = roomName;
        this.exitNorth = exitNorth;
        this.exitEast = exitEast;
        this.exitSouth = exitSouth;
        this.exitWest = exitWest;
        this.door = door;

    }
    public Room(){

    }

    public boolean isHasenteredYet() {
        return hasenteredYet;
    }

    public void setHasenteredYet(boolean hasenteredYet) {
        this.hasenteredYet = hasenteredYet;
    }

    public PVector getMapCoordinates() {
        return mapCoordinates;
    }

    public void setMapCoordinates(PVector mapCoordinates) {
        this.mapCoordinates = mapCoordinates;
    }

    public PImage getImg() {
        return img;
    }

    public void setImg(PImage img) {
        this.img = img;
    }
}
