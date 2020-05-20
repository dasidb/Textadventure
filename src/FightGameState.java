import processing.core.PApplet;

public class FightGameState extends GameState {


    public FightGameState(PApplet pApplet, GameManager gameManager) {
        super(pApplet, gameManager);
    }

    @Override
    protected void doRender() {
        pApplet.text("Du versuchst vorsichtig das Kuscheltier zu nehmen. Plötzlich packt dich eine hand und ringt dich zu Boden." , 200, 200);
    }

    @Override
    protected void doUpdate(long tpf) {

    }
}
