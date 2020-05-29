import processing.core.PApplet;


import processing.core.PVector;
import processing.event.KeyEvent;
import processing.event.MouseEvent;

import java.util.ArrayList;

// Gamestate when you normaly play the game
public class PlayGameState extends GameState {

    // Variable
    private GameMap gameMap;
    private Character character;
    private ChooseMenu chooseMenu;
    private Story story;
    private String chooseResult = "";
    private String currentRoomName = "";
    private ArrayList<Enemy> enemyArrayList;
    private int characterItemListLenght = 0;


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

    public GameMap getGameMap() {
        return gameMap;
    }

    public void setGameMap(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    // Last thing that get called in the constructor is used here to create some game objects
    @Override
    public void init() {
        super.init();
        // holds the Arraylist and Objects
        gameMap = new GameMap(getProcessing());
        character = new Character(new PVector(1,4));
        chooseMenu = new ChooseMenu(getProcessing(),character,gameMap);
        //story = new Story();

        addEnemys();



    }
    // adds enemys to the enemy arraylist just in the moment i wrote this i realise that its dumb what i did here
    // i should get out the check for wakeup here and should use propper constructor for the enemys to bound them to a
    // specific position on the map ... fckn bad design
    public void addEnemys(){
        Enemy enemy = new SleepingPersonEnemy();
        Enemy enemy2 = new SleepingDog();
        enemyArrayList = new ArrayList<>();
        enemyArrayList.add(enemy);
        enemyArrayList.add(enemy2);

    }
    // Update of the gamelogic
    @Override
    protected void doUpdate(long tpf) {
    pApplet.clear();

    if(checkForPersonWakeup()){

        gameManager.setCurrentGameState(new FightGameState(getProcessing(),gameManager, "Du versuchst vorsichtig das Kuscheltier zu nehmen. Plötzlich packt dich eine hand und ringt dich zu Boden."));

    }

    if(character.getPosition().x == 4 && character.getPosition().y == 4){
    checkForDogWakeup();
    }else {
        applyArrayLenghtToInventory();
    }



    }
    // Renders stuff on the screen
    @Override
    protected void doRender() {
        // renders the objects displays text etc
        if(Game.isAdmin())
        gameMap.render(character.getPosition());



        chooseMenu.render();
        checkForNewStory();
        drawCannotMove();
        displayCurrentRoomName();
        displayCurrentWeight();
        drawLineCursor();
       // story.render(getProcessing());

        //chooseMenu.render();


    }
    // Mouse pressed method, not used
    @Override
    public void mousePressed(MouseEvent event) {
        super.mousePressed(event);

    }
    // gets triggered when a key is released
    @Override
    public void keyReleased(KeyEvent event) {
        super.keyReleased(event);
        keyInputs(event.getKey(), event.getKeyCode(), true);

    }
    // Gets triggered when a key is pressed
    @Override
    public void keyPressed(KeyEvent event) {
        super.keyPressed(event);
        keyInputs(event.getKey(), event.getKeyCode(), false);

    }

    // reads the key inputs and take action depending on what key got pressed
    public void keyInputs(char key, int keyCode, boolean keyPressed){

        if(keyPressed){

            if(keyCode !=  PApplet.SHIFT || key != PApplet.BACKSPACE) {
                if(chooseMenu.getInputValue() == "|")
                    chooseMenu.setInputValue("");

                chooseMenu.setInputValue(chooseMenu.getInputValue() + key);
            }

            if((key == PApplet.BACKSPACE) && chooseMenu.getInputValue().length() >1){
                //if((key == PApplet.BACKSPACE && canPress > 30) && chooseMenu.getInputValue().length() >1){

                    chooseMenu.setInputValue(chooseMenu.getInputValue().substring(0,chooseMenu.getInputValue().length() - 2));
                    chooseMenu.setInputValue(chooseMenu.getInputValue().trim());






            }


            if(key == PApplet.ENTER){
                chooseResult = chooseMenu.chooseOption(chooseMenu.getInputValue());
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
    // Draws the | to make clear that you can write there
    public void drawLineCursor(){
        //hier
        if(chooseMenu.getInputValue().equals("") || chooseMenu.getInputValue().equals("|")) {
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

    // checks if there is a new story and displys it
    public void checkForNewStory(){

        if(gameMap.getWorldMap().get(character.getPosition()).isHasNewStory()){
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
                        .get(character.getPosition()).getRoomID(), gameMap.getWorldMap().get(character.getPosition()).getStoryID());
                gameMap.getWorldMap().get(character.getPosition()).setHasNewStory(false);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    // Displays the current room name
    public void displayCurrentRoomName(){
        if(character.getPosition().x == 3 && character.getPosition().y == 5) {
            pApplet.text("Du befindest dich aktuell in der " + gameMap.getWorldMap().get(character.getPosition()).getRoomName(), 600,300,200,100);

        }else
        pApplet.text("Du befindest dich aktuell im " + gameMap.getWorldMap().get(character.getPosition()).getRoomName(), 600,300,200,100);
    }

    // Displays the current weight
    public void displayCurrentWeight(){
        pApplet.text("Du hast aktuell ein Gewicht von " + character.getWeight() + " Kilo dabei.",600,600,200,200);
    }

    // Checks if a Person (Enemy) wakesup if the Character has " ein Kuscheltier" in the inventory it gets triggered
    public boolean checkForPersonWakeup(){
        for(Enemy enemy : enemyArrayList) {
            if(enemy instanceof SleepingPersonEnemy) {
                for (Item item : character.getInventory()) {

                    if (item.getName().equals(" ein Kuscheltier")) {
                        enemy.getWakeupBehaviour().wakeup();
                        return true;
                    }
                }
            }
        }
        return false;

    }

    // Checks if the Dog wakesup this happens if youre in the same room as the dog and steal items ( There is a 20 %)
    // chance that happens
    public void checkForDogWakeup(){

        if(characterItemListLenght < character.getInventory().size()) {
            for(Enemy enemy : enemyArrayList){
                if(enemy instanceof SleepingDog){
                    if(enemy.getWakeupBehaviour().wakeup()){
                        gameManager.setCurrentGameState(new FightGameState(getProcessing(), gameManager, "Leider warst du beim klauen zu Laut, Der Hund fängt an zu bellen. Du Versuchst den Hund zu beruhigen, doch bevor du weißt wie dir geschieht steht auch schon der Besitzer vor dir und stürmt auf dich zu "));

                    }
                }
            }
        }
        characterItemListLenght = character.getInventory().size();
    }

    // Workaround cause every room could trigger the dog when entering the room
    public void applyArrayLenghtToInventory(){

            characterItemListLenght = character.getInventory().size();
    }





}
