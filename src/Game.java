import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import processing.event.KeyEvent;

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
    int canPress1;
    GameManager gameManager;

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
        gameManager = new GameManager();

       final MenuGameState menuGameState = new MenuGameState(this,gameManager);
       gameManager.getGameStateMap().put("menuGameState",menuGameState);
       gameManager.setCurrentGameState(menuGameState);

        background(0,0,0);
        frameRate(30);
        loop();
    }

    @Override
    public void draw(){
        gameManager.update();
        gameManager.render(this);
  //  gameMap.drawGameMap(character.position);
    //chooseMenu.drawChoices();
    //canPress += 1;
    //checkCharactertoRoom();
    }

    public void fillImageMap(){
        imageMap = new HashMap<>();
         img = new PImage();
            img =    loadImage("Assets/BathroomImage.png");
        imageMap.put("bathroom",img);
        img =    loadImage("Assets/unknownRoomImage.png");
        imageMap.put("unknownRoom",img);
    }

    @Override
    public void keyPressed(KeyEvent event) {
        gameManager.keyPressed(event);



    }
    @Override
    public void keyReleased(KeyEvent event) {
        gameManager.keyReleased(event);
    }
}
