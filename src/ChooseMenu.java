import processing.core.PApplet;
import processing.core.PVector;

public class ChooseMenu {
    PApplet pApplet;
    String choice1 = "Move Up";
    String choice2 = "Move Down";
    String choice3 = "Move Left";
    String choice4 = "Move Right";
    String inputValue = "";
    Character character;
    GameMap gameMap;

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

    public void drawChoices(){
        pApplet.textSize(16);

        pApplet.fill(255,255,255);
        pApplet.text(choice1, 600,30);
        pApplet.text(choice2, 600,60);
        pApplet.text(choice3, 600,90);
        pApplet.text(choice4, 600,120);
        pApplet.fill(0,0,0);
        pApplet.rect(600,150,70,80);
        pApplet.fill(255,255,255);
        pApplet.text(inputValue,600,150, 70,80);

    }
    public void chooseOption(String choiceSelected){
        String userchoice = choiceSelected.toLowerCase();
        userchoice = userchoice.replace("\n", "");
        PVector tmpVec = new PVector();
        switch(userchoice) {
            case "move up":
                //mache das
                tmpVec.x = 0;
                tmpVec.y = -1;

                if(!gameMap.getWorldMap().get(character.getPosition()).exitNorth)
                    break;

                character.moveCharacter(tmpVec);
                break;
            case "move down":
                tmpVec.x = 0;
                tmpVec.y = 1;
                System.out.println(character.getPosition());
                if(!gameMap.getWorldMap().get(character.getPosition()).exitSouth)
                    break;
//
                character.moveCharacter(tmpVec);
                break;
            case "move left":
                tmpVec.x = -1;
                tmpVec.y = 0;

                if(!gameMap.getWorldMap().get(character.getPosition()).exitWest)
                    break;
                character.moveCharacter(tmpVec);
                break;
            case "move right":
                tmpVec.x = 1;
                tmpVec.y = 0;
                if(!gameMap.getWorldMap().get(character.getPosition()).exitEast)
                    break;
                character.moveCharacter(tmpVec);
                break;
            case "search":
                System.out.println("test");
                character.getKeyList().add(gameMap.getWorldMap().get(character.position).keyforDoors);
                System.out.println(gameMap.getWorldMap().get(character.position).keyforDoors);
                System.out.println(character.getKeyList().size());
                break;
        }

    }



}
