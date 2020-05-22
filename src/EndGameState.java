import processing.core.PApplet;
import processing.event.KeyEvent;

public class EndGameState extends GameState{

    private String result ="";
    public EndGameState(PApplet pApplet, GameManager gameManager, String result){
        super(pApplet,gameManager);
        this.result = result;
        init();
    }

    @Override
    protected void doRender() {
        pApplet.clear();
        pApplet.text(result,200,200);
    }

    @Override
    protected void doUpdate(long tpf) {

    }

    @Override
    public void keyPressed(KeyEvent event) {
        if(pApplet.keyPressed){
            if (pApplet.key == pApplet.ENTER){
                gameManager.setCurrentGameState(new UpgradeMenuGameState(getProcessing(),gameManager,((PlayGameState)gameManager.getGameStateMap().get("playGameState")).getCharacter()));




            }
        }
    }
}
