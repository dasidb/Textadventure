import java.util.*;

public class ItemManager {
    Map<Integer, Item> itemMap;
    Set<Integer> itemSet;

    public ItemManager(){
        itemMap = new HashMap<>();
        createItemMap();
    }

    public void createItemMap(){
        itemMap.put(4,new Item(" 100€",100,0));
        itemMap.put(5,new Item(" 200€",200,0));
        itemMap.put(6,new Item(" 500€",500,0));
        itemMap.put(7,new Item(" ein Ring",500,1));
        itemMap.put(8,new Item(" eine Uhr",80,1));
        itemMap.put(9,new Item(" ein Smartphone",600,1));

        itemMap.put(200,new Item(" ein Schlüssel",5,1));
        itemMap.put(201,new Item(" Schuhe",50,1));
        itemMap.put(202,new Item(" eine Jacke",120,3));
        itemMap.put(203,new Item(" ein Regenschirm",15,2));

        itemMap.put(300,new Item(" ein Fernseher",700,20));
        itemMap.put(301,new Item(" eine Xbox One",400,5));
        itemMap.put(302,new Item(" eine Switch",300,5));
        itemMap.put(303,new Item(" eine PS 4", 400,5));

        itemMap.put(400,new Item(" Klopapier",1337,1));
        itemMap.put(401,new Item(" Klopapier",1337,1));
        itemMap.put(402,new Item(" Klopapier",1337,1));
        itemMap.put(403,new Item(" Klopapier",1337,1));

        itemMap.put(500,new Item(" ein Toaster",80,3));
        itemMap.put(501,new Item(" ein Mixxer",90,3));
        itemMap.put(502,new Item(" eine Küchenrolle",30,1));
        itemMap.put(503,new Item(" Mikrowelle", 60,10));

        itemMap.put(600,new Item(" ein Lottoschein",1,1));
        itemMap.put(601,new Item(" ein Spielzeug",30,2));
        itemMap.put(602,new Item(" ein Fernseher",300,20));
        itemMap.put(603,new Item(" ein Laptop", 900,5));

        itemMap.put(700,new Item(" ein Router",200,2));
        itemMap.put(701,new Item(" ein Computer",800,10));
        itemMap.put(702,new Item(" eine Schreibtischlampe",300,3));
        itemMap.put(703,new Item(" ein Geheimes Firmendokument", 2400,1));

    }

    public Set<Integer> fillRoomList(int roomID) {
        int realchoice;
        int tmpIncremnt = 3;
        itemSet = new HashSet<>();
        for (int i = 0; i < tmpIncremnt; i++) {
            int firstChoose = (int) (Math.random() * 10);

            if(itemSet.contains(firstChoose)){
                tmpIncremnt++;
            }else {
                if (firstChoose > 3) {
                    realchoice = firstChoose;
                    itemSet.add(realchoice);
                } else {
                    // firstChoose += (roomID * 100);
                    realchoice = firstChoose + (roomID * 100);
                    itemSet.add(realchoice);
                }
            }
        }

        // Work around da er manchmal nur 2 added...
        if(itemSet.size() <3) {
            if (!itemSet.contains(9)) {
                itemSet.add(9);
            }
        }
        if(itemSet.size() <3) {
            if(!itemSet.contains(8))
                itemSet.add(8);
        }
        if(itemSet.size() <3) {
            if(!itemSet.contains(7))
                itemSet.add(7);
        }
        if(itemSet.size() <3) {
            if(!itemSet.contains(6))
                itemSet.add(6);
        }
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
