import processing.core.PVector;

import java.util.ArrayList;
import java.util.List;
// standart room there was no need for inherence but i used it cause there could be different rooms. Name is Legacy.
public class MoneyRoom extends Room {

    public MoneyRoom(PVector mapCoordinates, String roomName, boolean exitNorth, boolean exitEast, boolean exitSouth, boolean exitWest, boolean canEscape){
        super(mapCoordinates,roomName,exitNorth,exitEast,exitSouth,exitWest, canEscape);
        setImg(Game.getImageMap().get("bathroom"));
    }

    public MoneyRoom(PVector mapCoordinates, String roomName, boolean exitNorth, boolean exitEast, boolean exitSouth, boolean exitWest, List<Item> itemList){
        super(mapCoordinates,roomName,exitNorth,exitEast,exitSouth,exitWest,itemList);
        setImg(Game.getImageMap().get("bathroom"));
    }


    public MoneyRoom(PVector mapCoordinates, String roomName, boolean exitNorth, boolean exitEast, boolean exitSouth, boolean exitWest, KeyforDoors keyforDoors) {
        super(mapCoordinates,roomName,exitNorth,exitEast,exitSouth,exitWest, keyforDoors);
        setImg(Game.getImageMap().get("bathroom"));

    }
    }
