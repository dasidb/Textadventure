import processing.core.PApplet;


import processing.core.PApplet;
import processing.core.PVector;
import processing.event.KeyEvent;
import processing.event.MouseEvent;


public class PlayGameState extends GameState {

    // Variable
    GameMap gameMap;
    Character character;
    ChooseMenu chooseMenu;
    Story story;


    public PlayGameState(PApplet pApplet, GameManager gameManager){
        super(pApplet,gameManager);
    }

    @Override
    public void init() {
        super.init();
        // holds the Arraylist and Objects
        gameMap = new GameMap(getProcessing());
        character = new Character(new PVector(1,4));
        chooseMenu = new ChooseMenu(getProcessing(),character,gameMap);
        story = new Story();



    }

    @Override
    protected void doUpdate(long tpf) {
        // has the Game Logic for updating
    }

    @Override
    protected void doRender() {
        // renders the objects displays text etc
        gameMap.render(character.position);
        chooseMenu.render();
       // story.render(getProcessing());

        //chooseMenu.render();


    }

    @Override
    public void mousePressed(MouseEvent event) {
        super.mousePressed(event);

    }

    @Override
    public void keyReleased(KeyEvent event) {
        super.keyReleased(event);
        keyInputs(event.getKey(), event.getKeyCode(), true);

    }

    @Override
    public void keyPressed(KeyEvent event) {
        super.keyPressed(event);
        keyInputs(event.getKey(), event.getKeyCode(), false);

    }

    public void keyInputs(char key, int keyCode, boolean keyPressed){
        if(keyPressed){

            if(keyCode !=  PApplet.SHIFT || key != PApplet.BACKSPACE) {

                chooseMenu.setInputValue(chooseMenu.inputValue + key);
            }

            if((key == PApplet.BACKSPACE) && chooseMenu.getInputValue().length() >1){
                //if((key == PApplet.BACKSPACE && canPress > 30) && chooseMenu.getInputValue().length() >1){

                    chooseMenu.setInputValue(chooseMenu.getInputValue().substring(0,chooseMenu.getInputValue().length() - 2));



            }

            //Currently bad designed should split it into different methods
            if(key == PApplet.ENTER){
                chooseMenu.chooseOption(chooseMenu.inputValue);
                if(!gameMap.getWorldMap().get(character.position).hasenteredYet) {
                    if (gameManager.getGameStateMap().containsKey("storygamestate")) {
                        try {
// TODO: 09.03.2020 seems so be a cast exception i try to change it i need to assign the new first //
                            ((StoryGameState) gameManager.getCurrentGameState()).getStory().readStoryFromFile(gameMap.getWorldMap()
                                    .get(character.getPosition()).roomID, gameMap.getWorldMap().get(character.getPosition()).storyID);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        gameManager.setCurrentGameState(gameManager.getGameStateMap().get("storygamestate"));


                    } else {
                        System.out.println("dwadwakdwakpokdwapokdwapokdwak");

                        gameManager.getGameStateMap().put("storygamestate",new StoryGameState(getProcessing(),
                                gameManager, gameMap.getWorldMap().get(character.getPosition()).getRoomAndStoryString()));
                        gameManager.setCurrentGameState(gameManager.getGameStateMap().get("storygamestate"));
                    }
                }
                chooseMenu.setInputValue("");

            }
            if(key == 'w'){

            }

            if(key == 's'){

            }

        }
    }




}
