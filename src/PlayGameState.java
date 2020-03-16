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
        pApplet.clear();
    }

    @Override
    public void init() {
        super.init();
        // holds the Arraylist and Objects
        gameMap = new GameMap(getProcessing());
        character = new Character(new PVector(1,4));
        chooseMenu = new ChooseMenu(getProcessing(),character,gameMap);
        //story = new Story();



    }

    @Override
    protected void doUpdate(long tpf) {


    }

    @Override
    protected void doRender() {
        // renders the objects displays text etc
        gameMap.render(character.position);
        chooseMenu.render();
        checkForNewStory();
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
        /*        if(!gameMap.getWorldMap().get(character.position).hasNewStory) {
                    if (gameManager.getGameStateMap().containsKey("storyGameState")) {
                        try {

                            gameManager.setCurrentGameState(gameManager.getGameStateMap().get("storyGameState"));
                            ((StoryGameState) gameManager.getCurrentGameState()).getStory().readStoryFromFile(gameMap.getWorldMap()
                                    .get(character.getPosition()).roomID, gameMap.getWorldMap().get(character.getPosition()).storyID);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        gameManager.setCurrentGameState(gameManager.getGameStateMap().get("storyGameState"));


                    } else {


                        gameManager.getGameStateMap().put("storyGameState",new StoryGameState(getProcessing(),
                                gameManager, gameMap.getWorldMap().get(character.getPosition()).getRoomAndStoryString()));
                        gameManager.setCurrentGameState(gameManager.getGameStateMap().get("storyGameState"));
                    }
                } */
                chooseMenu.setInputValue("");

            }
            if(key == 'w'){

            }

            if(key == 's'){

            }

        }
    }


    public void checkForNewStory(){
        System.out.println(gameMap.worldMap.get(character.getPosition()).hasNewStory + " das ist new story");
        if(gameMap.worldMap.get(character.getPosition()).hasNewStory){
            if (gameManager.getGameStateMap().containsKey("storyGameState")) {
                gameManager.setCurrentGameState(gameManager.getGameStateMap().get("storyGameState"));
            }
            else {
                StoryGameState storyGameState = new StoryGameState(getProcessing(), gameManager);
                gameManager.getGameStateMap().put("storyGameState",storyGameState);
                gameManager.setCurrentGameState(storyGameState);
            }
            try {
                ((StoryGameState) gameManager.getCurrentGameState()).getStory().readStoryFromFile(gameMap.getWorldMap()
                        .get(character.getPosition()).roomID, gameMap.getWorldMap().get(character.getPosition()).storyID);
                gameMap.getWorldMap().get(character.getPosition()).setHasNewStory(false);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }



}
