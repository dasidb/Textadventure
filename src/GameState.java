import processing.core.PApplet;

import processing.core.PApplet;
import processing.event.KeyEvent;
import processing.event.MouseEvent;

// Shows if the game is in "game" mode or "intro, sequence" mode
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
    public void init(){

    }

    public void render(){
        doRender();
    }

    protected abstract void doRender();


    public void update() {
        final long currentTime = System.currentTimeMillis();
        tpf = currentTime - lastFrameTime;
        lastFrameTime = currentTime;

        doUpdate(tpf);
    }
    protected abstract void doUpdate(long tpf);

    public void mousePressed(MouseEvent event) {

    }

    public void keyPressed(KeyEvent event){

    }

    public void keyReleased(KeyEvent event){

    }

    public PApplet getProcessing() {
        return pApplet;
    }

}
