import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Game extends PApplet {

    GameMap gameMap;
    static Map<String, PImage> imageMap;
    PImage img;
    Character character;
    ChooseMenu chooseMenu;
    int canPress = 0;



    public static void main(String[] args) {
    PApplet.main(Game.class, args);
    }

    public static Map<String,PImage> getImageMap(){
        return imageMap;
    }
    @Override
    public void settings(){
    super.settings();

    size(800,800);

    }

    @Override
    public void setup() {
        super.setup();
        fillImageMap();
        chooseMenu = new ChooseMenu(this);
        character = new Character(new PVector(0,0));
        gameMap = new GameMap(this);



        background(0,0,0);
        frameRate(30);
        loop();
    }

    @Override
    public void draw(){
    gameMap.drawGameMap(character.position);
    chooseMenu.drawChoices();
    canPress += 1;
    }

    public void fillImageMap(){
        imageMap = new HashMap<>();
         img = new PImage();
            img =    loadImage("Assets/BathroomImage.png");
        imageMap.put("bathroom",img);
    }

    @Override
    public void keyPressed() {
        super.keyPressed();

        if(keyPressed){

            chooseMenu.setInputValue(chooseMenu.inputValue + key);

            if(key == BACKSPACE && canPress > 30){
                chooseMenu.setInputValue(chooseMenu.getInputValue().substring(0,chooseMenu.getInputValue().length() - 2));
             //   clear();


            }
            if(key == ENTER){
                chooseMenu.chooseOption(chooseMenu.inputValue);
                chooseMenu.setInputValue("");
                //clear();
            }
            if(key == 'w'){

            }

            if(key == 's'){

            }

        }

    }

    @Override
    public void keyReleased() {
        super.keyReleased();
    }
}
