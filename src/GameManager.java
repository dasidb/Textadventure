import processing.core.PApplet;
import processing.event.KeyEvent;
import processing.event.MouseEvent;

import java.util.HashMap;
import java.util.Map;

public class GameManager {
    GameState currentGameState;
    Map<String, GameState> gameStateMap = new HashMap();

    public GameManager(){
    }

    void update(){
        currentGameState.update();
    }

    void render(PApplet pApplet){
        currentGameState.render();
    }

    public void mousePressed(MouseEvent event){
        currentGameState.mousePressed(event);
    }
    public void keyPressed(processing.event.KeyEvent event){
        currentGameState.keyPressed(event);
    }
    public void keyReleased(KeyEvent event){
        currentGameState.keyReleased(event);
    }
    public void setCurrentGameState(GameState state){
        currentGameState = state;
    }

    public Map<String, GameState> getGameStateMap() {
        return gameStateMap;
    }

    public void setGameStateMap(Map<String, GameState> gameStateMap) {
        this.gameStateMap = gameStateMap;
    }

    public GameState getCurrentGameState(){
        return currentGameState;
    }
}
