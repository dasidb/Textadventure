import processing.core.PApplet;

public class EndGameState extends GameState{

    String result ="";
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


}
