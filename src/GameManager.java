import processing.core.PApplet;
import processing.event.KeyEvent;
import processing.event.MouseEvent;

public class GameManager {
    GameState currentGameState;

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


}
