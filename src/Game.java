import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import processing.event.KeyEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// Main class implements the PApplet lib
// https://processing.org/
// Dont forget to add the core.jar data, otherwhise the programm wont start.
public class Game extends PApplet {

    private GameMap gameMap;
    private static Map<String, PImage> imageMap;
    private PImage img;
    private Character character;
    private ChooseMenu chooseMenu;
    private int canPress = 0;
    private int canPress1;
    private GameManager gameManager;
    private static boolean admin = false; // type devmode

    public static void main(String[] args) {
    PApplet.main(Game.class, args);
    }

    public static Map<String,PImage> getImageMap(){
        return imageMap;
    }

    // Settings Method from papplet used to create the window only place where you can call the size method
    @Override
    public void settings(){
    super.settings();

    size(800,800);

    }
    // Used for testing
    public static boolean isAdmin() {
        return admin;
    }

    public static void toogleAdmin(){
        admin = !admin;
    }

    public static void setAdmin(boolean admin) {
        Game.admin = admin;
    }
    // Setup method from papplet used to create different objects (this is the place where you have to load the images) they
    //are not allowed inside of the draw loop
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

    // Draw Method from papplet this is where the magic happens and things are actually drawn to the screen
    @Override
    public void draw(){
        gameManager.update();
        gameManager.render(this);
  //  gameMap.drawGameMap(character.position);
    //chooseMenu.drawChoices();
    //canPress += 1;
    //checkCharactertoRoom();
    }

    // Fills the images for displaying the rooms (legacy cause its only used for the admin mode to have a map, thats
    // why the images are wrong
    public void fillImageMap(){
        imageMap = new HashMap<>();
         img = new PImage();
            img =    loadImage("Assets/BathroomImage.png");
        imageMap.put("bathroom",img);
        img =    loadImage("Assets/unknownRoomImage.png");
        imageMap.put("unknownRoom",img);
    }

    // KeyPressed method from papplet calls the gamemanager keypressed method so the current gamestate can change the
    //behaviour depending on the state
    @Override
    public void keyPressed(KeyEvent event) {
        gameManager.keyPressed(event);



    }

    // KeyRelease method from papplet calls the gamemanager keypressed method so the current gamestate can change the
    //behaviour depending on the state
    @Override
    public void keyReleased(KeyEvent event) {
        gameManager.keyReleased(event);
    }
}
