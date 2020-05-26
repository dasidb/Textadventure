import processing.core.PApplet;
import processing.event.KeyEvent;
import processing.event.MouseEvent;

import java.util.HashMap;
import java.util.Map;

// Gamemanager holds the different gamestates and set them
public class GameManager {
    private GameState currentGameState;
    private Map<String, GameState> gameStateMap = new HashMap();

    public GameManager(){
    }
    // Calls the Update method of the current gamestate
    void update(){
        currentGameState.update();
    }
    // Calls the render method of the current gamestate
    void render(PApplet pApplet){
        currentGameState.render();
    }

    // Calls the mouseppressed event of the current gamestate ( Not used but implemented for later uses)
    public void mousePressed(MouseEvent event){
        currentGameState.mousePressed(event);
    }
    // Calls the KeyPressed method of the current gamestate
    public void keyPressed(processing.event.KeyEvent event){
        currentGameState.keyPressed(event);
    }
    // Calls the KeyReleased method of the current gamestate
    public void keyReleased(KeyEvent event){
        currentGameState.keyReleased(event);
    }
    // Sets the current gamestate so that his methods gets called
    public void setCurrentGameState(GameState state){
        currentGameState = state;
    }
    // Map of gamestates so we dont have to create new gamestates everytime and can continue with the old ones
    public Map<String, GameState> getGameStateMap() {
        return gameStateMap;
    }
    // Sets the gamestate map (If we would neet to create a new one for example) not used
    public void setGameStateMap(Map<String, GameState> gameStateMap) {
        this.gameStateMap = gameStateMap;
    }
    // gives the current gamestate
    public GameState getCurrentGameState(){
        return currentGameState;
    }
}
