import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

import java.util.ArrayList;

public abstract class Room {
    PVector mapCoordinates;
    String roomName;
    boolean exitNorth;
    boolean exitEast;
    boolean exitSouth;
    boolean exitWest;
    boolean hasCharacter = false;
    PImage img;

    public Room(PVector mapCoordinates, String roomName, boolean exitNorth, boolean exitEast, boolean exitSouth, boolean exitWest) {
        this.mapCoordinates = mapCoordinates;
        this.roomName = roomName;
        this.exitNorth = exitNorth;
        this.exitEast = exitEast;
        this.exitSouth = exitSouth;
        this.exitWest = exitWest;

    }
    public Room(){

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
