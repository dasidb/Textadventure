import processing.core.PVector;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Character {
    private PVector position;
    private String name;
    private Set<KeyforDoors> keyList = new HashSet<>();
    private List<Item> inventory = new ArrayList();
    private int weight = 0;
    private int maxWeight = 35;
    private int timesWeightUpgraded = 0;
    private int money = 0;

    public int getTimesWeightUpgraded() {
        return timesWeightUpgraded;
    }

    public void setTimesWeightUpgraded(int timesWeightUpgraded) {
        this.timesWeightUpgraded = timesWeightUpgraded;
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(int maxWeight) {
        this.maxWeight = maxWeight;
    }

    public Character(PVector position){
        this.position = position;
    }

    public PVector getPosition() {
        return position;
    }

    public void setPosition(PVector position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<KeyforDoors> getKeyList() {
        return keyList;
    }

    public void setKeyList(Set<KeyforDoors> keyList) {
        this.keyList = keyList;
    }

    public List<Item> getInventory() {
        return inventory;
    }

    public void setInventory(List<Item> inventory) {
        this.inventory = inventory;
    }

    public void moveCharacter(PVector vector){
        PVector tmpVec = getPosition();
        tmpVec.x = tmpVec.x + vector.x;
        tmpVec.y = tmpVec.y + vector.y;
        setPosition(tmpVec);
    }

    public int calculateWeight(){
        int tmpWeight = 0;
        for(Item item : inventory){
            tmpWeight += item.getWeight();
        }
        return tmpWeight;
    }

    public int calculateInventoryValue(){
        int tmpValue = 0;
        for(Item item : inventory){
            tmpValue += item.getValue();
        }
        return tmpValue;
    }

    public int getWeight() {
        return calculateWeight();
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int calculateValue(){
        int tmpValue = getMoney();
        for(Item item : inventory){
            tmpValue += item.getValue();
        }
        setMoney(tmpValue);
        return tmpValue;
    }

    public int getInventoryValue() {
        return calculateValue();
    }

    public int getMoney(){
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public boolean upgradeWeight(int valueOfUpgrade){
        if(maxWeight < 50) {
            maxWeight += valueOfUpgrade;
            return true;
        }else
            return false;
    }
}
