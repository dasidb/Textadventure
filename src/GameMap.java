import processing.core.PApplet;
import processing.core.PVector;

import java.util.*;

public class GameMap {
    private Map<PVector,Room> worldMap = new HashMap<>();
    private PApplet pApplet;
    private Map<Integer,Item> itemMap = new HashMap<>();
    private Set<Integer> itemSet;
    private ItemManager itemManager;

    public GameMap(PApplet pApplet){
        this.pApplet = pApplet;
        itemManager = new ItemManager();
        //createGameMap();
        //createManualGameMap();
        //createItemMap();
        createManualGameMap1();


    }

    public GameMap(){

    }

    public Map<PVector, Room> getWorldMap() {
        return worldMap;
    }

    public void setWorldMap(Map<PVector, Room> worldMap) {
        this.worldMap = worldMap;
    }



  /*  public void createManualGameMap(){
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


    } */

  public void createManualGameMap1(){
      PVector tmpvec;

      //1. first room game starts here its youre home and you will have a short intro here
      tmpvec = new PVector(1,4);
      worldMap.put(tmpvec,new MoneyRoom(tmpvec,"Garten",false,true,false,false,true));
      //tmpvec = new PVector(11,10);


      //2. second room, here you meet 1 to 3 persons, they will drop somehow an item
      tmpvec = new PVector(2,4);
      worldMap.put(tmpvec,new MoneyRoom(tmpvec,"Flur",true,true,true,true, itemManager.addItemsToRoomList(2)));

      //3.  Third room has an Interaction with an item of the 2. Room let you continue to room 5.
      tmpvec = new PVector(2,5);
      worldMap.put(tmpvec,new MoneyRoom(tmpvec,"Wohnzimmer",true,true,false,false, itemManager.addItemsToRoomList(3)));

      //4. fourth room just some story room may continue a riddle
      tmpvec = new PVector(2,3);
      worldMap.put(tmpvec,new MoneyRoom(tmpvec,"Badezimmer",false,false,true,false, itemManager.addItemsToRoomList(4)));

      //5. fivt room gets opened through a item from the 3. Room has some item
      tmpvec = new PVector(3,5);
      worldMap.put(tmpvec,new MoneyRoom(tmpvec,"Küche",false,false,false,true, itemManager.addItemsToRoomList(5)));

      //6. sixt room
      tmpvec = new PVector(3,4);
      worldMap.put(tmpvec,new MoneyRoom(tmpvec,"Schlafzimmer",false,true,false,true, itemManager.addItemsToRoomList(6)));

      //7. Seventh room
      tmpvec = new PVector(4,4);
      worldMap.put(tmpvec,new MoneyRoom(tmpvec,"Arbeitszimmer",false,false,false,true, itemManager.addItemsToRoomList(7)));
  }

    public void render(PVector characterPosi){
        drawGameMap(characterPosi);

    }
//test
    public void drawGameMap(PVector characterPosi){
      int displayFactor = 100;
        for(Map.Entry<PVector,Room> entry : worldMap.entrySet()){
            if(entry.getValue().isHasNewStory())
            pApplet.image(entry.getValue().getImg(), (entry.getValue().getMapCoordinates().x * displayFactor), displayFactor + (entry.getValue().getMapCoordinates().y * displayFactor));
            else
                pApplet.image(Game.getImageMap().get("unknownRoom"), (entry.getValue().getMapCoordinates().x * displayFactor), displayFactor + (entry.getValue().getMapCoordinates().y * displayFactor));

            if(characterPosi.x == entry.getValue().getMapCoordinates().x && characterPosi.y == entry.getValue().getMapCoordinates().y) {


                pApplet.image(entry.getValue().getImg(), (entry.getValue().getMapCoordinates().x * displayFactor), displayFactor + (entry.getValue().getMapCoordinates().y * displayFactor));
                pApplet.fill(255,30,55,150);
                pApplet.rect( (characterPosi.x * displayFactor), displayFactor + (characterPosi.y * displayFactor) ,displayFactor,displayFactor);

            }


        }
    }
}

