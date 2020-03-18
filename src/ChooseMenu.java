import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ChooseMenu {
    PApplet pApplet;
    String choice1 = "Move Up";
    String choice2 = "Move Down";
    String choice3 = "Move Left";
    String choice4 = "Move Right";
    String choice5 = "Search";
    String choiceEscape = "Escape";
    String takeInstruction = " Zum Auswählen die Zahlen 1, 2 oder 3 eingeben.";
    String inputValue = "";
    List<String> takeList = new ArrayList<>();
    String takeItem1 = "";
    String takeitem2 = "";
    String takeitem3 ="";
    Character character;
    GameMap gameMap;
    List<Item> tmpList;
    boolean wantToTake = false;
    String tmpName;


    public ChooseMenu(PApplet pApplet, Character character, GameMap gameMap){
        this.pApplet = pApplet;
        this.character = character;
        this.gameMap = gameMap;
    }

    public String getInputValue() {
        return inputValue;
    }

    public void setInputValue(String inputValue) {
        this.inputValue = inputValue;
    }


    public void render(){
        drawChoices();
    }
    public void drawChoices(){
        pApplet.textSize(16);

        pApplet.fill(255,255,255);
        if(!wantToTake) {
            pApplet.text(choice1, 600, 30);
            pApplet.text(choice2, 600, 60);
            pApplet.text(choice3, 600, 90);
            pApplet.text(choice4, 600, 120);
            pApplet.text(choice5, 600, 150);
            if(character.getPosition().x == 1 && character.getPosition().y == 4)
            pApplet.text(choiceEscape,600,180);
            pApplet.fill(0, 0, 0);
            pApplet.rect(600, 190, 70, 80);
        }else{
            int posiTakeList = 120;
            int incrementOption = 1;
            pApplet.text(takeInstruction,600,30,200,200);
            for(String takeOption : takeList) {
                pApplet.text(Integer.toString(incrementOption) + takeOption, 600, posiTakeList);
                posiTakeList += 30;
                incrementOption += 1;
            }

        }
        pApplet.fill(255,255,255);
        pApplet.text(inputValue,600,210, 70,80);


    }
    public String chooseOption(String choiceSelected){
        System.out.println(character.inventory);
        System.out.println(character.getWeight());
        String userchoice = choiceSelected.toLowerCase();
        userchoice = userchoice.replace("\n", "");
        PVector tmpVec = new PVector();
        switch(userchoice) {
            case "move up":
                //mache das
                tmpVec.x = 0;
                tmpVec.y = -1;

                if(!gameMap.getWorldMap().get(character.getPosition()).exitNorth)
                    return "Du kannst nicht nach Norden gehen";
                   // break;

                character.moveCharacter(tmpVec);
                break;
            case "move down":
                tmpVec.x = 0;
                tmpVec.y = 1;
                System.out.println(character.getPosition());
                if(!gameMap.getWorldMap().get(character.getPosition()).exitSouth)
                    return "Du kannst nicht nach Süden gehen";
                   // break;
//
                character.moveCharacter(tmpVec);
                break;
            case "move left":
                tmpVec.x = -1;
                tmpVec.y = 0;

                if(!gameMap.getWorldMap().get(character.getPosition()).exitWest)
                    return "Du kannst nicht nach Westen gehen";
                  //  break;
                character.moveCharacter(tmpVec);
                break;
            case "move right":
                tmpVec.x = 1;
                tmpVec.y = 0;
                if(!gameMap.getWorldMap().get(character.getPosition()).exitEast)
                    return "Du kannst nicht nach Osten gehen";
                   // break;
                character.moveCharacter(tmpVec);
                break;
            case "search":
                if(gameMap.getWorldMap().get(character.getPosition()).hasSearched){
                    return "Du hast diesen Raum bereits durchsucht";
                }else {
                    gameMap.getWorldMap().get(character.getPosition()).hasSearched = true;
                    System.out.println("test");
                    System.out.println(character.getPosition());
                    if (character.getPosition().x == 1 && character.getPosition().y == 4) {
                        System.out.println("bdwaidiawjodwa");
                        return "Du befindest dich im Garten, hier gibt es leider nichts wertvolles.";
                    } else {

                        tmpList = gameMap.getWorldMap().get(character.position).itemList;
                        System.out.println(tmpList.size() + " länge tmplist");
                        System.out.println(tmpList);
                        System.out.println("Als du nach links blickst entdeckst du " + tmpList.get(0).name + " dein blick schweift weiter und du entdeckst " + tmpList.get(1).name + " als letztes fällt dir " +
                                tmpList.get(2).name + " ins Auge");
                        return "Als du nach links blickst entdeckst du " + tmpList.get(0).name + " dein blick schweift weiter und du entdeckst " + tmpList.get(1).name + " als letztes fällt dir " +
                                tmpList.get(2).name + " ins Auge";
                    }
                }

            case "take" :
                int increment = 0;
                takeList = new ArrayList<>();
                if(character.getPosition().x == 1 && character.getPosition().y == 4){
                    return "Hier gibt es nichts zu holen";
                }else {
                    wantToTake = true;
           /*         if(tmpList.get(0) == null){

                    }else{
                        takeItem1 = tmpList.get(0).name;
                    }


                    takeitem2 = tmpList.get(1).name;
                    takeitem3 = tmpList.get(2).name; */

                for(Item item : tmpList){
                    takeList.add(item.name);
                }

                }

                break;

            case "1":
                if(wantToTake)
                    if(character.maxWeight < character.getWeight())
                        return "du bist überladen.";
                    else {
                        character.inventory.add(tmpList.get(0));
                        tmpName = tmpList.get(0).name;
                        tmpList.remove(0);

                        wantToTake = false;
                        return "Du nimmst " + tmpName;
                    }


            case "2":
                if(wantToTake)
                    if(character.maxWeight < character.getWeight())
                        return "du bist überladen.";
                    else {
                        character.inventory.add(tmpList.get(1));
                        tmpName = tmpList.get(1).name;
                        tmpList.remove(1);
                        wantToTake = false;
                        return "Du nimmst " + tmpName;
                    }
            case "3":
                if(wantToTake)
                    if(character.maxWeight < character.getWeight())
                        return "du bist überladen.";
                    else {
                        character.inventory.add(tmpList.get(2));
                        tmpName = tmpList.get(2).name;
                        tmpList.remove(2);
                        wantToTake = false;
                        return "Du nimmst " + tmpName;
                    }
            case "escape" :
                if(character.getPosition().x == 1 && character.getPosition().y == 4)

                return "Du hast Sachen im wert von " + character.getValue() + "€ erbeutet.";
                else
                    return "Du musst dich dafür im Garten befinden.";

        }

    return "";
    }



}
