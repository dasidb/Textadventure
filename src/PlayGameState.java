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
    String chooseResult = "";
    String currentRoomName = "";

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

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
        System.out.println(character + " playgame");



    }

    @Override
    protected void doUpdate(long tpf) {
    pApplet.clear();

    }

    @Override
    protected void doRender() {
        // renders the objects displays text etc
        if(Game.admin)
        gameMap.render(character.position);


        chooseMenu.render();
        checkForNewStory();
        drawCannotMove();
        displayCurrentRoomName();
        displayCurrentWeight();
        drawLineCursor();
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
        System.out.println(chooseMenu.getInputValue() + "input");
        if(keyPressed){

            if(keyCode !=  PApplet.SHIFT || key != PApplet.BACKSPACE) {
                if(chooseMenu.getInputValue() == "|")
                    chooseMenu.setInputValue("");

                chooseMenu.setInputValue(chooseMenu.inputValue + key);
            }

            if((key == PApplet.BACKSPACE) && chooseMenu.getInputValue().length() >1){
                //if((key == PApplet.BACKSPACE && canPress > 30) && chooseMenu.getInputValue().length() >1){

                    chooseMenu.setInputValue(chooseMenu.getInputValue().substring(0,chooseMenu.getInputValue().length() - 2));
                    chooseMenu.setInputValue(chooseMenu.getInputValue().trim());
                   System.out.println("|" + chooseMenu.getInputValue() + "|");





            }


            if(key == PApplet.ENTER){
                chooseResult = chooseMenu.chooseOption(chooseMenu.inputValue);
                if(chooseResult.startsWith("Du hast Sachen im wert von")){
                    gameManager.setCurrentGameState(new EndGameState(getProcessing(),gameManager, chooseResult));
                }
                chooseMenu.setInputValue("");

            }
            if(key == 'w'){

            }

            if(key == 's'){

            }

        }
    }

    public void drawLineCursor(){
        //hier
        if(chooseMenu.getInputValue() == "" || chooseMenu.getInputValue() == "|") {
            boolean anzeigen = (System.currentTimeMillis() / 1000) % 2 == 0;
            if(anzeigen){
                chooseMenu.setInputValue("|");
            }else{
                chooseMenu.setInputValue("");
            }

        }
    }

    public void drawCannotMove(){
        pApplet.text(chooseResult, 50,50, 550,600);
    }


    public void checkForNewStory(){

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

    public void displayCurrentRoomName(){
        if(character.getPosition().x == 3 && character.getPosition().y == 5) {
            pApplet.text("Du befindest dich aktuell in der " + gameMap.worldMap.get(character.getPosition()).roomName, 600,300,200,100);

        }else
        pApplet.text("Du befindest dich aktuell im " + gameMap.worldMap.get(character.getPosition()).roomName, 600,300,200,100);
    }

    public void displayCurrentWeight(){
        pApplet.text("Du hast aktuell ein Gewicht von " + character.getWeight() + " Kilo dabei.",600,600,200,200);
    }

}
