import processing.core.PVector;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Character {
    PVector position;
    String name;
    Set<KeyforDoors> keyList = new HashSet<>();


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

    public void moveCharacter(PVector vector){
        PVector tmpVec = getPosition();
        tmpVec.x = tmpVec.x + vector.x;
        tmpVec.y = tmpVec.y + vector.y;
        setPosition(tmpVec);
    }


}
