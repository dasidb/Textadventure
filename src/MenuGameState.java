import processing.core.PApplet;
import processing.event.KeyEvent;
// Displays the game menu (Start/ Quit)
public class MenuGameState extends GameState {

    private int selection = 0;
    private boolean thereIsNoQuit = false;

    public MenuGameState(PApplet pApplet, GameManager manager) {
        super(pApplet, manager);
    }

    // Render Method renders the stuff on the screen
    @Override
    protected void doRender() {
        createMenu();
        if(thereIsNoQuit)
            thereIsNoQuit();
    }

    // Update method updates game logic
    @Override
    protected void doUpdate(long tpf) {

    }

    // last thing to get called in the constructor
    @Override
    public void init() {
        super.init();
    }


    // Creates the menu fills it with different color depending on the selection
    public void createMenu() {
        if (selection == 0)
            pApplet.fill(255, 255, 255);
        else
            pApplet.fill(180, 180, 180);
        pApplet.rect(200, 200, 400, 100);
        pApplet.fill(0, 0, 0);
        pApplet.text("Start", 400, 250);

        if (selection == 1)
            pApplet.fill(255, 255, 255);
        else
            pApplet.fill(180, 180, 180);
        pApplet.rect(200, 300, 400, 100);
        pApplet.fill(0, 0, 0);
        pApplet.text("Quit", 400, 350);
    }

    // Used to change the selection either start or quit
    @Override
    public void keyPressed(KeyEvent event) {
        super.keyPressed(event);
        if (pApplet.keyPressed) {
            if (pApplet.keyCode == pApplet.UP && selection > 0)
                selection--;
            if (pApplet.keyCode == pApplet.DOWN && selection < 1)
                selection++;

            if (pApplet.key == pApplet.ENTER) {

                if (selection == 0) {
                    changeToPlayGameState();
                }
                if (selection == 1) {
                    thereIsNoQuit = true;

                }
            }
        }
    }

    // I had to much time to do stupid stuff...
    public void thereIsNoQuit() {
        for (int i = 0; i < 50; i++) {
            if(i % 10 == 1) {
                pApplet.textSize(26);
                pApplet.fill(255, 255, 255);
                pApplet.text("NÖ! Weiterspielen!", (int) (Math.random() * 1000), (int) (Math.random() * 1000));
            }
        }
    }

    // Changes explicit to the playgamestate
        public void changeToPlayGameState() {
            if (gameManager.getGameStateMap().containsKey("playGameState")) {
                gameManager.setCurrentGameState(gameManager.getGameStateMap().get("playGameState"));
            } else {
                PlayGameState playGameState = new PlayGameState(getProcessing(), gameManager);
                gameManager.getGameStateMap().put("playGameState", playGameState);
                gameManager.setCurrentGameState(playGameState);
            }

        }
    }



