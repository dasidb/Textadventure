import processing.core.PApplet;
import processing.event.KeyEvent;

public class MenuGameState extends GameState {

    int selection = 0;

    public MenuGameState(PApplet pApplet, GameManager manager){
        super(pApplet, manager);
    }
    @Override
    protected void doRender() {
        createMenu();
    }

    @Override
    protected void doUpdate(long tpf) {

    }

    @Override
    public void init() {
        super.init();
    }

    public void createMenu(){
        if(selection == 0)
            pApplet.fill(255,255,255);
        else
            pApplet.fill(180,180,180);
        pApplet.rect(200,200,400,100);
        pApplet.fill(0,0,0);
        pApplet.text("Start",400,250);

        if(selection == 1)
            pApplet.fill(255,255,255);
        else
            pApplet.fill(180,180,180);
        pApplet.rect(200,300,400,100);
        pApplet.fill(0,0,0);
        pApplet.text("Quit",400,350);
    }

    @Override
    public void keyPressed(KeyEvent event) {
        super.keyPressed(event);
        if(pApplet.keyPressed){
            if(pApplet.keyCode == pApplet.UP && selection >0)
                selection --;
            if(pApplet.keyCode == pApplet.DOWN && selection < 1)
                selection ++;

            if(pApplet.key == pApplet.ENTER){
                System.out.println(selection);
                if(selection == 0){
                    changeToPlayState();
                if(selection == 1){
                    pApplet.exit();
                }
            }
        }
    }


    }
    public void changeToPlayState(){
        if(gameManager.getGameStateMap().containsKey("playGameState")){
            gameManager.setCurrentGameState(gameManager.getGameStateMap().get("playGameState"));
        }else {
            PlayGameState playGameState = new PlayGameState(getProcessing(), gameManager);
            gameManager.getGameStateMap().put("playGameState", playGameState);
            gameManager.setCurrentGameState(playGameState);
        }

    }
}


