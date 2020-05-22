import processing.core.PImage;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.List;

public abstract class Room {
    private PVector mapCoordinates;
    private String roomName;
    private boolean exitNorth;
    private boolean exitEast;
    private boolean exitSouth;
    private boolean exitWest;
    private boolean hasCharacter = false;
    private PImage img;
    private KeyforDoors keyforDoors;
    private Door door;
    private boolean hasNewStory = true;
    private static int roomCount = 0;
    private int roomID;
    private int storyID = 0;
    private List<Item> itemList = new ArrayList<>();
    private boolean hasSearched = false;
    private boolean canEscape;

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

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public boolean isExitNorth() {
        return exitNorth;
    }

    public void setExitNorth(boolean exitNorth) {
        this.exitNorth = exitNorth;
    }

    public boolean isHasSearched() {
        return hasSearched;
    }

    public void setHasSearched(boolean hasSearched) {
        this.hasSearched = hasSearched;
    }

    public boolean isExitEast() {
        return exitEast;
    }

    public void setExitEast(boolean exitEast) {
        this.exitEast = exitEast;
    }

    public boolean isExitSouth() {
        return exitSouth;
    }

    public void setExitSouth(boolean exitSouth) {
        this.exitSouth = exitSouth;
    }

    public boolean isExitWest() {
        return exitWest;
    }

    public void setExitWest(boolean exitWest) {
        this.exitWest = exitWest;
    }

    public static void setRoomCount(int roomCount) {
        Room.roomCount = roomCount;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
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
