import processing.core.PApplet;
import processing.event.KeyEvent;

// Endgame state gets triggered when the person could escape
public class EndGameState extends GameState{

    private String result ="";
    public EndGameState(PApplet pApplet, GameManager gameManager, String result){
        super(pApplet,gameManager);
        this.result = result;
        init();
    }

    //Renders on Screen
    @Override
    protected void doRender() {
        pApplet.clear();
        pApplet.text(result,200,200);
    }

    // Updates the game logic
    @Override
    protected void doUpdate(long tpf) {

    }

    //takes action to key presses
    @Override
    public void keyPressed(KeyEvent event) {
        if(pApplet.keyPressed){
            if (pApplet.key == pApplet.ENTER){
                gameManager.setCurrentGameState(new UpgradeMenuGameState(getProcessing(),gameManager,((PlayGameState)gameManager.getGameStateMap().get("playGameState")).getCharacter()));




            }
        }
    }
}
