import processing.core.PApplet;
import processing.event.KeyEvent;

// Is used when the Character enters a "combat" happens when an enemy woke up
public class FightGameState extends GameState {
    private String fightMessage = "";
    // use it after refractor of choosemenu
    private ChooseMenu chooseMenu = new ChooseMenu(getProcessing());
    private int choice = 0;
    private boolean gameLost = false;
    int closeGameTimer = 0;
    long currentTime;
    boolean isEntryMessage;

    public FightGameState(PApplet pApplet, GameManager gameManager, String fightMessage) {
        super(pApplet, gameManager);
        this.fightMessage = fightMessage;
        currentTime = System.currentTimeMillis();
    }

    public boolean isGameLost() {
        return gameLost;
    }

    public void setGameLost(boolean gameLost) {
        this.gameLost = gameLost;
    }
    // Message that get displayed at first (Depends on the enemy type)
    public void entryFightMessage(){
        pApplet.text(fightMessage , 50, 200, 600,600);
        isEntryMessage = true;
    }

    //Renders things to the screen
    @Override
    protected void doRender() {
        if((System.currentTimeMillis()/1000) - (currentTime/1000) > 9) {
            isEntryMessage = false;
            if (!gameLost) {
                drawChoices();
            } else {
                failedToEscapeMessage();
            }
        }else{
            entryFightMessage();
        }

    }

    @Override
    protected void doUpdate(long tpf) {

    }
    // Draws different choices (bad design should create a class for that, cause its used on multiply spots)
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

    //takes action depending on keypresses
    @Override
    public void keyPressed(KeyEvent event) {

        super.keyPressed(event);
        if (pApplet.keyPressed) {
            if (pApplet.keyCode == pApplet.UP && choice > 0)
                choice--;
            if (pApplet.keyCode == pApplet.DOWN && choice < 2)
                choice++;

            if (pApplet.key == pApplet.ENTER && !isEntryMessage) {
                if (choice == 0) {
                    if (Math.round(Math.random() * 10) > 0) {
                        sucessfullEscaped();
                    } else {
                        failedToEscapeMessage();
                    }
                }
                if (choice == 1) {
                    if (Math.round(Math.random() * 10) > 6) {
                        sucessfullEscaped();
                    } else {
                        failedToEscapeMessage();
                    }
                }
                    if (choice == 2) {
                        failedToEscapeMessage();
                    }

            }
        }
    }

    //happens if the character could escape
        public void sucessfullEscaped(){
            getProcessing().clear();
            for(int i = 0 ; i < 500;i++){
                getProcessing().textSize(16);
               // getProcessing().text("Du konntest entkommen",400,200);
            }
            getProcessing().textSize(16);
            gameManager.setCurrentGameState(new EndGameState(getProcessing(),gameManager,"Du konntest entkommen, hast bei dem " +
                    "Fluchtversuch jedoch deine Beute verloren."));


        }

        //happens when the character couldnt escape leads to a game quit
        public void failedToEscapeMessage(){
            gameLost = true;
            getProcessing().clear();
            getProcessing().textSize(36);



            if(closeGameTimer < 600){
                getProcessing().text("Dein Fluchtversuch ist missglückt.",200,200);
                getProcessing().text("GAMEOVER",400,350);
                getProcessing().text("Das spiel wird sich in " + Math.round((600 - closeGameTimer)/30) + " Sekunden beenden." , 50, 400 );
                closeGameTimer++;
            }else {
                System.exit(0);
            }

        }
}
