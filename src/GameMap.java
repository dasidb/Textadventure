import processing.core.PApplet;
import processing.core.PVector;

import java.util.HashMap;
import java.util.Map;

public class GameMap {
    Map<PVector,Room> worldMap = new HashMap<>();
    PApplet pApplet;

    public GameMap(PApplet pApplet){
        this.pApplet = pApplet;
        createGameMap();
    }

    public Map<PVector, Room> getWorldMap() {
        return worldMap;
    }

    public void setWorldMap(Map<PVector, Room> worldMap) {
        this.worldMap = worldMap;
    }

    public void createGameMap(){
        for(int y = 0; y < 5; y++){
            for(int x = 0; x < 5; x++){
                PVector pVector = new PVector(x,y);
                if(y ==1 && x ==0) {
                    worldMap.put(pVector, new MoneyRoom(pVector, "TestRaun", false, false, true, false, new KeyforDoors(1)));
                }else

                worldMap.put(pVector, new MoneyRoom(pVector,"TestRaun",false,false,true,false));


            }
        }
    }

    public void drawGameMap(PVector characterPosi){
        for(Map.Entry<PVector,Room> entry : worldMap.entrySet()){
            if(characterPosi.x == entry.getValue().mapCoordinates.x && characterPosi.y == entry.getValue().mapCoordinates.y) {


                pApplet.image(entry.getValue().img, (entry.getValue().mapCoordinates.x * 100), 100 + (entry.getValue().mapCoordinates.y * 100));
                pApplet.fill(255,30,55,150);
                pApplet.rect( (characterPosi.x * 100), 100 + (characterPosi.y * 100) ,100,100);
            }else
            pApplet.image(entry.getValue().img, (entry.getValue().mapCoordinates.x * 100), 100 + (entry.getValue().mapCoordinates.y * 100));

        }
    }
}
