import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.List;

public class ChooseMenu {
    private PApplet pApplet;
    private String choice1 = "Move Up";
    private String choice2 = "Move Down";
    private String choice3 = "Move Left";
    private String choice4 = "Move Right";
    private String choice5 = "Search";
    private String choice6 = "Take";
    private String choiceEscape = "Escape";
    private String takeInstruction = " Zum Auswählen die Zahlen 1, 2 oder 3 eingeben.";
    private String inputValue = "";
    private List<String> takeList = new ArrayList<>();
    private String takeItem1 = "";
    private String takeitem2 = "";
    private String takeitem3 ="";
    private Character character;
    private GameMap gameMap;
    private List<Item> tmpList;
    private boolean wantToTake = false;
    private String tmpName;




    public boolean isWantToTake() {
        return wantToTake;
    }

    public void setWantToTake(boolean wantToTake) {
        this.wantToTake = wantToTake;
    }

    public ChooseMenu(PApplet pApplet, Character character, GameMap gameMap){
        this.pApplet = pApplet;
        this.character = character;
        this.gameMap = gameMap;
    }

    public ChooseMenu(PApplet pApplet){
        this.pApplet = pApplet;
    }

    public String getInputValue() {
        return inputValue;
    }

    public void setInputValue(String inputValue) {
        this.inputValue = inputValue;
    }

    // Render Method for The Choices displays stuff on screen
    public void render(){
        drawChoices();
    }

    //Creates the Choices to draw
// needs a refactor maybe deliver it an array of choices with position to display the stuff super bad design
    public void drawChoices(){
        pApplet.textSize(16);

        pApplet.fill(255,255,255);
        if(!wantToTake) {
            pApplet.textSize(16);
            pApplet.text(choice1, 600, 30);
            pApplet.text(choice2, 600, 60);
            pApplet.text(choice3, 600, 90);
            pApplet.text(choice4, 600, 120);
            pApplet.text(choice5, 600, 150);
            pApplet.text(choice6,600,180);
            if(character.getPosition().x == 1 && character.getPosition().y == 4)
            pApplet.text(choiceEscape,600,210);
            pApplet.fill(0, 0, 0);
            pApplet.rect(600, 220, 70, 80);
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
        pApplet.text(inputValue,600,240, 70,50);


    }
    // actually react to the choice which the players write takes action seems like thats also very bad design
    // Method is way to long and  violate the SRP principle
    public String chooseOption(String choiceSelected){

        String userchoice = choiceSelected.toLowerCase();
        userchoice = userchoice.replace("\n", "");
        PVector tmpVec = new PVector();
        switch(userchoice) {
            case "move up":
                setWantToTake(false);
                tmpVec.x = 0;
                tmpVec.y = -1;
                if(!gameMap.getWorldMap().get(character.getPosition()).isExitNorth())
                    return "Du kannst nicht nach Norden gehen";
                character.moveCharacter(tmpVec);
                break;

            case "move down":
                setWantToTake(false);
                tmpVec.x = 0;
                tmpVec.y = 1;

                if(!gameMap.getWorldMap().get(character.getPosition()).isExitSouth())
                    return "Du kannst nicht nach Süden gehen";
                character.moveCharacter(tmpVec);
                break;

            case "move left":
                setWantToTake(false);
                tmpVec.x = -1;
                tmpVec.y = 0;
                if(!gameMap.getWorldMap().get(character.getPosition()).isExitWest())
                    return "Du kannst nicht nach Westen gehen";
                character.moveCharacter(tmpVec);
                break;

            case "move right":
                setWantToTake(false);
                tmpVec.x = 1;
                tmpVec.y = 0;
                if(!gameMap.getWorldMap().get(character.getPosition()).isExitEast())
                    return "Du kannst nicht nach Osten gehen";
                character.moveCharacter(tmpVec);
                break;

            case "search":
                setWantToTake(false);
                if(gameMap.getWorldMap().get(character.getPosition()).isHasSearched()){
                    return "Du hast diesen Raum bereits durchsucht";
                }else {
                    gameMap.getWorldMap().get(character.getPosition()).setHasSearched(true);
                    if (character.getPosition().x == 1 && character.getPosition().y == 4) {
                        return "Du befindest dich im Garten, hier gibt es leider nichts wertvolles.";
                    } else {
                        tmpList = gameMap.getWorldMap().get(character.getPosition()).getItemList();
                        return "Als du nach links blickst entdeckst du " + tmpList.get(0).getName() + " dein blick schweift weiter und du entdeckst " + tmpList.get(1).getName() + " als letztes fällt dir " +
                                tmpList.get(2).getName() + " ins Auge";
                    }
                }

            case "take":

                    if(character.getPosition().x == 1 && character.getPosition().y == 4) {
                       return  "Hier gibt es einfach nichts.";
                    }else {

                        if (!gameMap.getWorldMap().get(character.getPosition()).isHasSearched()) {
                            return "Du musst den Raum erst durchsuchen";
                        } else if (tmpList.size() == 0) {
                            return "Es gibt nichts mehr zum suchen.";
                        } else {
                            int increment = 0;
                            takeList = new ArrayList<>();
                            if (character.getPosition().x == 1 && character.getPosition().y == 4) {
                                return "Hier gibt es nichts zu holen";
                            } else {
                                setWantToTake(true);
           /*         if(tmpList.get(0) == null){

                    }else{
                        takeItem1 = tmpList.get(0).name;
                    }


                    takeitem2 = tmpList.get(1).name;
                    takeitem3 = tmpList.get(2).name; */
                                tmpList = gameMap.getWorldMap().get(character.getPosition()).getItemList();
                                for (Item item : tmpList) {
                                    takeList.add(item.getName());
                                }

                            }

                            break;
                        }
                    }

            case "1":
                    if (wantToTake)
                        if (character.getMaxWeight() < character.getWeight())
                            return "Du bist überladen. Es ist an der Zeit zu verschwinden.";
                        else {
                            try {
                            character.getInventory().add(tmpList.get(0));
                            tmpName = tmpList.get(0).getName();
                            tmpList.remove(0);
                            setWantToTake(false);
                            return "Du nimmst " + tmpName;
                            }catch (Exception e){
                                break;
                            }
                }

            case "2":
                if(wantToTake)
                    if(character.getMaxWeight() < character.getWeight())
                        return "Du bist überladen. Es ist an der Zeit zu verschwinden.";
                    else {
                        try {
                        character.getInventory().add(tmpList.get(1));
                        tmpName = tmpList.get(1).getName();
                        tmpList.remove(1);
                        setWantToTake(false);
                        return "Du nimmst " + tmpName;
                        }catch (Exception e){
                            break;
                        }

                    }
            case "3":
                    if (wantToTake)
                        if (character.getMaxWeight() < character.getWeight())
                            return "Du bist überladen. Es ist an der Zeit zu verschwinden.";
                        else {
                            try {
                                character.getInventory().add(tmpList.get(2));
                                tmpName = tmpList.get(2).getName();
                                tmpList.remove(2);
                                setWantToTake(false);
                                return "Du nimmst " + tmpName;
                            }catch (Exception e){
                                break;
                            }
                        }

            case "escape" :
                setWantToTake(false);
                if(character.getPosition().x == 1 && character.getPosition().y == 4) {
                    character.getInventoryValue();
                    return "Du hast Sachen im wert von " + character.calculateInventoryValue() + "€ erbeutet.";
                }

            case "devmode" :
                Game.toogleAdmin();
        }


    return "";
    }
}
