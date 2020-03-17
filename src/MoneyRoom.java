import processing.core.PVector;

import java.util.ArrayList;

public class MoneyRoom extends Room {

    public MoneyRoom(PVector mapCoordinates, String roomName, boolean exitNorth, boolean exitEast, boolean exitSouth, boolean exitWest){
        super(mapCoordinates,roomName,exitNorth,exitEast,exitSouth,exitWest);
        setImg(Game.getImageMap().get("bathroom"));
    }

    public MoneyRoom(PVector mapCoordinates, String roomName, boolean exitNorth, boolean exitEast, boolean exitSouth, boolean exitWest, ArrayList<Item> itemList){
        super(mapCoordinates,roomName,exitNorth,exitEast,exitSouth,exitWest,itemList);
        setImg(Game.getImageMap().get("bathroom"));
    }


    public MoneyRoom(PVector mapCoordinates, String roomName, boolean exitNorth, boolean exitEast, boolean exitSouth, boolean exitWest, KeyforDoors keyforDoors) {
        super(mapCoordinates,roomName,exitNorth,exitEast,exitSouth,exitWest, keyforDoors);
        setImg(Game.getImageMap().get("bathroom"));

    }
    }
