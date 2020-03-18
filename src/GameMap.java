import processing.core.PApplet;
import processing.core.PVector;

import java.util.*;

public class GameMap {
    Map<PVector,Room> worldMap = new HashMap<>();
    PApplet pApplet;
    Map<Integer,Item> itemMap = new HashMap<>();
    Set<Integer> itemSet;

    public GameMap(PApplet pApplet){
        this.pApplet = pApplet;
        //createGameMap();
        //createManualGameMap();
        createItemMap();
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
      worldMap.put(tmpvec,new MoneyRoom(tmpvec,"Garten",false,true,false,false));
      //tmpvec = new PVector(11,10);


      //2. second room, here you meet 1 to 3 persons, they will drop somehow an item
      tmpvec = new PVector(2,4);
      worldMap.put(tmpvec,new MoneyRoom(tmpvec,"Flur",true,true,true,true, addItemsToRoomList(2)));

      //3.  Third room has an Interaction with an item of the 2. Room let you continue to room 5.
      tmpvec = new PVector(2,5);
      worldMap.put(tmpvec,new MoneyRoom(tmpvec,"Wohnzimmer",true,true,false,false, addItemsToRoomList(3)));

      //4. fourth room just some story room may continue a riddle
      tmpvec = new PVector(2,3);
      worldMap.put(tmpvec,new MoneyRoom(tmpvec,"Badezimmer",false,false,true,false, addItemsToRoomList(4)));

      //5. fivt room gets opened through a item from the 3. Room has some item
      tmpvec = new PVector(3,5);
      worldMap.put(tmpvec,new MoneyRoom(tmpvec,"Küche",false,false,false,true, addItemsToRoomList(5)));

      //6. sixt room
      tmpvec = new PVector(3,4);
      worldMap.put(tmpvec,new MoneyRoom(tmpvec,"Schlafzimmer",false,true,false,true, addItemsToRoomList(6)));

      //7. Seventh room
      tmpvec = new PVector(4,4);
      worldMap.put(tmpvec,new MoneyRoom(tmpvec,"Arbeitszimmer",false,false,false,true, addItemsToRoomList(7)));
  }

    public void render(PVector characterPosi){
        //drawGameMap(characterPosi);

    }
//test
    public void drawGameMap(PVector characterPosi){
      int displayFactor = 100;
        for(Map.Entry<PVector,Room> entry : worldMap.entrySet()){
            if(entry.getValue().hasNewStory)
            pApplet.image(entry.getValue().img, (entry.getValue().mapCoordinates.x * displayFactor), displayFactor + (entry.getValue().mapCoordinates.y * displayFactor));
            else
                pApplet.image(Game.getImageMap().get("unknownRoom"), (entry.getValue().mapCoordinates.x * displayFactor), displayFactor + (entry.getValue().mapCoordinates.y * displayFactor));

            if(characterPosi.x == entry.getValue().mapCoordinates.x && characterPosi.y == entry.getValue().mapCoordinates.y) {


                pApplet.image(entry.getValue().img, (entry.getValue().mapCoordinates.x * displayFactor), displayFactor + (entry.getValue().mapCoordinates.y * displayFactor));
                pApplet.fill(255,30,55,150);
                pApplet.rect( (characterPosi.x * displayFactor), displayFactor + (characterPosi.y * displayFactor) ,displayFactor,displayFactor);

            }


        }
    }

    // Maximal gewicht 35

    public void createItemMap(){
      itemMap.put(4,new Item("100",100,0));
      itemMap.put(5,new Item("200€",200,0));
      itemMap.put(6,new Item("500€",500,0));
      itemMap.put(7,new Item("Ring",500,1));
      itemMap.put(8,new Item("Uhr",80,1));
      itemMap.put(9,new Item("Smartphone",600,1));

      itemMap.put(200,new Item("Schlüssel",5,1));
      itemMap.put(201,new Item("Schuhe",50,1));
      itemMap.put(202,new Item("Jacke",120,3));
      itemMap.put(203,new Item("Regenschirm",15,2));

        itemMap.put(300,new Item("Fernseher",700,20));
        itemMap.put(301,new Item("Xbox One",400,5));
        itemMap.put(302,new Item("Switch",300,5));
        itemMap.put(303,new Item("PS 4", 400,5));

        itemMap.put(400,new Item("Klopapier",1337,1));
        itemMap.put(401,new Item("Klopapier",1337,1));
        itemMap.put(402,new Item("Klopapier",1337,1));
        itemMap.put(403,new Item("Klopapier",1337,1));

        itemMap.put(500,new Item("Toaster",80,3));
        itemMap.put(501,new Item("Mixxer",90,3));
        itemMap.put(502,new Item("Küchenrolle",30,1));
        itemMap.put(503,new Item("Mikrowelle", 60,10));

        itemMap.put(600,new Item("Lottoschein",1,1));
        itemMap.put(601,new Item("Spielzeug",30,2));
        itemMap.put(602,new Item("Fernseher",300,20));
        itemMap.put(603,new Item("Laptop", 900,5));

        itemMap.put(700,new Item("Router",200,2));
        itemMap.put(701,new Item("Computer",800,10));
        itemMap.put(702,new Item("Schreibtischlampe",300,3));
        itemMap.put(703,new Item("Geheime Firmendokumente", 2400,1));

    }

    public static void main(String[] args) {
      GameMap test = new GameMap();
    test.createItemMap();
      test.addItemsToRoomList(5);
    }


    public Set<Integer> fillRoomList(int roomID) {
      int realchoice;
      int tmpIncremnt = 3;
      itemSet = new HashSet<>();
        for (int i = 0; i < tmpIncremnt; i++) {
            int firstChoose = (int) (Math.random() * 10);
            if(roomID == 2)
            System.out.println(firstChoose + " before");
            if(itemSet.contains(firstChoose)){

                tmpIncremnt++;

            }else {


                if (firstChoose > 3) {
                    realchoice = firstChoose;

                    itemSet.add(realchoice);
                } else {
                   // firstChoose += (roomID * 100);
                    if( roomID == 2)
                    System.out.println(" ds ist firstchoose " + firstChoose);
                    realchoice = firstChoose + (roomID *100);

                    itemSet.add(realchoice);
                }
                if( roomID == 2)
                System.out.println("das ist realchoice" + realchoice);
            }


          //  itemSet.add(firstChoose);

        }
        System.out.println(itemSet);
        return itemSet;
    }

    public List<Item> addItemsToRoomList(int roomID){
      Set<Integer> numbers = fillRoomList(roomID);
      List<Item> roomItemList = new ArrayList<>();



        for(Integer itemID : numbers){
            roomItemList.add(itemMap.get(itemID));
        }

        return roomItemList;
    }

    }

