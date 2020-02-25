import processing.core.PVector;

public class MoneyRoom extends Room {

    public MoneyRoom(PVector mapCoordinates, String roomName, boolean exitNorth, boolean exitEast, boolean exitSouth, boolean exitWest){
        super(mapCoordinates,roomName,exitNorth,exitEast,exitSouth,exitWest);
        setImg(Game.getImageMap().get("moneyroom"));
    };
}
