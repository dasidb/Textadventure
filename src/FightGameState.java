import processing.core.PApplet;
import processing.event.KeyEvent;

public class FightGameState extends GameState {
    private String fightMessage = "";
    // use it after refractor of choosemenu
    private ChooseMenu chooseMenu = new ChooseMenu(getProcessing());
    private int choice = 0;
    private boolean gameLost = false;
    int closeGameTimer = 0;

    public FightGameState(PApplet pApplet, GameManager gameManager, String fightMessage) {
        super(pApplet, gameManager);
        this.fightMessage = fightMessage;
    }

    public boolean isGameLost() {
        return gameLost;
    }

    public void setGameLost(boolean gameLost) {
        this.gameLost = gameLost;
    }

    public void entryFightMessage(){
        pApplet.text(fightMessage , 200, 200);
    }

    @Override
    protected void doRender() {
        if(!gameLost){
            drawChoices();
        }else{
            failedToEscapeMessage();
        }

    }

    @Override
    protected void doUpdate(long tpf) {

    }

    public void drawChoices() {
        pApplet.clear();
        pApplet.fill(255,255,255);
        getProcessing().text("Wähle deinen Fluchtweg mit den Pfeiltasten", 600, 20);
        if (choice == 0) {
            pApplet.fill(255, 0, 0);
        } else
            pApplet.fill(255, 255, 255);

        getProcessing().text("Flüchte durch die Tür", 600,40);
        if(choice == 1){
            pApplet.fill(255,0,0);
        }
        else
            pApplet.fill(255,255,255);
        getProcessing().text("Flüchte durchs Fenster", 600,60);
        if(choice == 2){
            pApplet.fill(255,0,0);
        }
        else
            pApplet.fill(255,255,255);
        getProcessing().text("Greife an", 600,80);
    }

    @Override
    public void keyPressed(KeyEvent event) {

        super.keyPressed(event);
        if (pApplet.keyPressed) {
            if (pApplet.keyCode == pApplet.UP && choice > 0)
                choice--;
            if (pApplet.keyCode == pApplet.DOWN && choice < 2)
                choice++;

            if (pApplet.key == pApplet.ENTER) {
                if (choice == 0) {
                    if (Math.round(Math.random() * 10) > 0) {
                        sucessfullEscaped();
                    } else {
                        failedToEscapeMessage();
                    }
                }
                if (choice == 1) {
                    if (Math.round(Math.random() * 10) > 6){
                        sucessfullEscaped();
                    }else{
                        failedToEscapeMessage();
                }
                    if (choice == 2) {
                        failedToEscapeMessage();
                    }
                }
            }
        }
    }

        public void sucessfullEscaped(){
            getProcessing().clear();
            for(int i = 0 ; i < 500;i++){
                getProcessing().textSize(36);
                getProcessing().text("Du konntest entkommen",400,200);
            }
            getProcessing().textSize(16);
            gameManager.setCurrentGameState(new EndGameState(getProcessing(),gameManager,"Du konntest entkommen"));


        }

        public void failedToEscapeMessage(){
            gameLost = true;
            getProcessing().clear();
            getProcessing().textSize(36);



            if(closeGameTimer < 600){
                getProcessing().text("Dein Fluchtversuch ist missglückt.",200,200);
                getProcessing().text("GAMEOVER",400,350);
                getProcessing().text("Das spiel wird sich in kürze beenden" + Math.round((600 - closeGameTimer)/30) , 50, 400 );
                closeGameTimer++;
            }else {
                System.exit(0);
            }

        }
}
