import processing.core.PImage;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.List;

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
    boolean hasNewStory = true;
    static int roomCount = 0;
    final int roomID;
    int storyID = 0;
    List<Item> itemList = new ArrayList<>();
    boolean hasSearched = false;
    boolean canEscape;

    public Room(PVector mapCoordinates, String roomName, boolean exitNorth, boolean exitEast, boolean exitSouth, boolean exitWest, boolean canEscape) {
        roomID = roomCount;
        roomCount++;

        this.mapCoordinates = mapCoordinates;
        this.roomName = roomName;
        this.exitNorth = exitNorth;
        this.exitEast = exitEast;
        this.exitSouth = exitSouth;
        this.exitWest = exitWest;
        this.canEscape = canEscape;




    }

    public Room(PVector mapCoordinates, String roomName, boolean exitNorth, boolean exitEast, boolean exitSouth, boolean exitWest, List<Item> itemList) {
        roomID = roomCount;
        roomCount++;

        this.mapCoordinates = mapCoordinates;
        this.roomName = roomName;
        this.exitNorth = exitNorth;
        this.exitEast = exitEast;
        this.exitSouth = exitSouth;
        this.exitWest = exitWest;
        this.itemList = itemList;


    }

    public Room(PVector mapCoordinates, String roomName, boolean exitNorth, boolean exitEast, boolean exitSouth, boolean exitWest, int roomCount, int storyID) {
        roomID = roomCount;
        roomCount++;

        this.mapCoordinates = mapCoordinates;
        this.roomName = roomName;
        this.exitNorth = exitNorth;
        this.exitEast = exitEast;
        this.exitSouth = exitSouth;
        this.exitWest = exitWest;


    }
    public Room(PVector mapCoordinates, String roomName, boolean exitNorth, boolean exitEast, boolean exitSouth, boolean exitWest, KeyforDoors keyforDoors) {
        roomID = roomCount;
        roomCount++;

        this.mapCoordinates = mapCoordinates;
        this.roomName = roomName;
        this.exitNorth = exitNorth;
        this.exitEast = exitEast;
        this.exitSouth = exitSouth;
        this.exitWest = exitWest;
        this.keyforDoors = keyforDoors;

    }
    public Room(PVector mapCoordinates, String roomName, boolean exitNorth, boolean exitEast, boolean exitSouth, boolean exitWest, KeyforDoors keyforDoors, Door door) {
        roomID = roomCount;
        roomCount++;

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
        roomID = roomCount;
        roomCount++;

        this.mapCoordinates = mapCoordinates;
        this.roomName = roomName;
        this.exitNorth = exitNorth;
        this.exitEast = exitEast;
        this.exitSouth = exitSouth;
        this.exitWest = exitWest;
        this.door = door;

    }

    public boolean isHasNewStory() {
        return hasNewStory;
    }

    public void setHasNewStory(boolean hasNewStory) {
        this.hasNewStory = hasNewStory;
    }

    public static int getRoomCount() {
        return roomCount;
    }


    public int getStoryID() {
        return storyID;
    }

    public void setStoryID(int storyID) {
        this.storyID = storyID;
    }

    public boolean isHasenteredYet() {
        return hasNewStory;
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

    // String is needed for the Story.prop
    public String getRoomAndStoryString(){
     String roomAndStory = "[" + this.roomID + "][" + this.storyID + "]";

     return roomAndStory;
    }
}
