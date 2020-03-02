import processing.core.PApplet;
import processing.core.PVector;

import java.util.HashMap;
import java.util.Map;

public class GameMap {
    Map<PVector,Room> worldMap = new HashMap<>();
    PApplet pApplet;

    public GameMap(PApplet pApplet){
        this.pApplet = pApplet;
        //createGameMap();
        createManualGameMap();
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
                    worldMap.put(pVector, new MoneyRoom(pVector, "TestRaun", false, true, false, false, new KeyforDoors(1)));
                }else

                worldMap.put(pVector, new MoneyRoom(pVector,"TestRaun",false,true,true,false));


            }
        }
    }

    public void createManualGameMap(){
        PVector tmpVec = new PVector(0,0);
    // 0 ROW
        tmpVec = new PVector(0,0);

        worldMap.put(tmpVec, new MoneyRoom(tmpVec,"TestRaum",false,true,true,false));

      //PVector tmp2 = new PVector(1,0);
      tmpVec = new PVector(1,0);
      System.out.println(tmpVec);
        worldMap.put(tmpVec, new MoneyRoom(tmpVec,"TestRaum",false,true,false,true));

        tmpVec = new PVector(2,0);
        System.out.println(tmpVec);
        worldMap.put(tmpVec, new MoneyRoom(tmpVec,"TestRaum",false,true,false,true));

        tmpVec = new PVector(3,0);
        System.out.println(tmpVec);
        worldMap.put(tmpVec, new MoneyRoom(tmpVec,"TestRaum",false,true,false,true));

        tmpVec = new PVector(4,0);
        System.out.println(tmpVec);
        worldMap.put(tmpVec, new MoneyRoom(tmpVec,"TestRaum",false,false,true,true));

        System.out.println(tmpVec);
        System.out.println(worldMap.size());


        //1 ROW
        tmpVec = new PVector(0,1);
        worldMap.put(tmpVec, new MoneyRoom(tmpVec,"TestRaum",true,true,true,false, new KeyforDoors(1)));

        tmpVec = new PVector(1,1);
        worldMap.put(tmpVec, new MoneyRoom(tmpVec,"TestRaum",false,true,true,true));

        tmpVec = new PVector(2,1);
        worldMap.put(tmpVec, new MoneyRoom(tmpVec,"TestRaum",false,true,false,true));

        tmpVec = new PVector(3,1);
        worldMap.put(tmpVec, new MoneyRoom(tmpVec,"TestRaum",false,false,false,true));

        tmpVec = new PVector(4,1);
        worldMap.put(tmpVec, new MoneyRoom(tmpVec,"TestRaum",true,false,true,false));



         //2 ROW
        tmpVec = new PVector(0,2);
        worldMap.put(tmpVec, new MoneyRoom(tmpVec,"TestRaum",true,false,false,false));

        tmpVec = new PVector(1,2);
        worldMap.put(tmpVec, new MoneyRoom(tmpVec,"TestRaum",true,false,true,false));

        tmpVec = new PVector(2,2);
        worldMap.put(tmpVec, new MoneyRoom(tmpVec,"TestRaum",false,true,false,false));

        tmpVec = new PVector(3,2);
        worldMap.put(tmpVec, new MoneyRoom(tmpVec,"TestRaum",false,false,true,true));

        tmpVec = new PVector(4,2);
        worldMap.put(tmpVec, new MoneyRoom(tmpVec,"TestRaum",true,false,true,false));



        //3 ROW
        tmpVec = new PVector(0,3);
        worldMap.put(tmpVec, new MoneyRoom(tmpVec,"TestRaum",false,true,false,false));

        tmpVec = new PVector(1,3);
        worldMap.put(tmpVec, new MoneyRoom(tmpVec,"TestRaum",true,true,false,true));

        tmpVec = new PVector(2,3);
        worldMap.put(tmpVec, new MoneyRoom(tmpVec,"TestRaum",false,true,true,true));

        tmpVec = new PVector(3,3);
        worldMap.put(tmpVec, new MoneyRoom(tmpVec,"TestRaum",true,false,false,true));

        tmpVec = new PVector(4,3);
        worldMap.put(tmpVec, new MoneyRoom(tmpVec,"TestRaum",true,false,false,false));



        //4 ROW
        tmpVec = new PVector(0,4);
        worldMap.put(tmpVec, new MoneyRoom(tmpVec,"TestRaum",false,true,false,false));

        tmpVec = new PVector(1,4);
        worldMap.put(tmpVec, new MoneyRoom(tmpVec,"TestRaum",false,true,false,true));

        tmpVec = new PVector(2,4);
        worldMap.put(tmpVec, new MoneyRoom(tmpVec,"TestRaum",true,true,false,true));
        tmpVec = new PVector(3,4);
        worldMap.put(tmpVec, new MoneyRoom(tmpVec,"TestRaum",false,true,false,true));

        tmpVec = new PVector(4,4);
        worldMap.put(tmpVec, new MoneyRoom(tmpVec,"TestRaum",false,false,false,true));


    }

    public void render(PVector characterPosi){
        drawGameMap(characterPosi);
    }

    public void drawGameMap(PVector characterPosi){
        for(Map.Entry<PVector,Room> entry : worldMap.entrySet()){
            if(entry.getValue().hasenteredYet)
            pApplet.image(entry.getValue().img, (entry.getValue().mapCoordinates.x * 100), 100 + (entry.getValue().mapCoordinates.y * 100));
            else
                pApplet.image(Game.getImageMap().get("unknownRoom"), (entry.getValue().mapCoordinates.x * 100), 100 + (entry.getValue().mapCoordinates.y * 100));
                System.out.println(worldMap.get(new PVector(0,0)));
            if(characterPosi.x == entry.getValue().mapCoordinates.x && characterPosi.y == entry.getValue().mapCoordinates.y) {

                entry.getValue().setHasenteredYet(true);
                pApplet.image(entry.getValue().img, (entry.getValue().mapCoordinates.x * 100), 100 + (entry.getValue().mapCoordinates.y * 100));
                pApplet.fill(255,30,55,150);
                pApplet.rect( (characterPosi.x * 100), 100 + (characterPosi.y * 100) ,100,100);

            }


        }
    }
}
