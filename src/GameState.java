import processing.core.PApplet;

import processing.core.PApplet;
import processing.event.KeyEvent;
import processing.event.MouseEvent;

// abstract class delivers some methods for the different gamestates. Also implements some abstract methods which
// the other gamestates have to implement
public abstract class GameState {
    protected GameManager gameManager;
    protected PApplet pApplet;

    protected long lastFrameTime = System.currentTimeMillis();
    protected long tpf = 0;



    public GameState(PApplet pApplet, GameManager gameManager){
        this.pApplet = pApplet;
        this.gameManager = gameManager;


        init();
    }
    // Gets called as the last statement in the constructor
    public void init(){

    }
    // Render Method of the gamestate
    public void render(){
        doRender();
    }
    // doRender Method of the gamestate has to be implemented by the other gamestates
    protected abstract void doRender();

    // Update Method for updating the gamelogic
    public void update() {
        final long currentTime = System.currentTimeMillis();
        tpf = currentTime - lastFrameTime;
        lastFrameTime = currentTime;

        doUpdate(tpf);
    }
    // doUpdate Method of the gamestate has to be implemented by the other gamestates
    protected abstract void doUpdate(long tpf);

    // Mouse pressed for mouse actions not used
    public void mousePressed(MouseEvent event) {

    }

    // key press method for the different gamestates
    public void keyPressed(KeyEvent event){

    }
    // key released method for the different gamestates
    public void keyReleased(KeyEvent event){

    }
    // delivers papplet for the different gamestates to call the processing methods
    public PApplet getProcessing() {
        return pApplet;
    }

}
