import processing.core.PApplet;


import processing.core.PVector;
import processing.event.KeyEvent;
import processing.event.MouseEvent;

import java.util.ArrayList;


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

    @Override
    public void init() {
        super.init();
        // holds the Arraylist and Objects
        gameMap = new GameMap(getProcessing());
        character = new Character(new PVector(1,4));
        chooseMenu = new ChooseMenu(getProcessing(),character,gameMap);
        //story = new Story();
        System.out.println(character + " playgame");
        addEnemys();



    }

    public void addEnemys(){
        Enemy enemy = new SleepingPersonEnemy();
        Enemy enemy2 = new SleepingDog();
        enemyArrayList = new ArrayList<>();
        enemyArrayList.add(enemy);
        enemyArrayList.add(enemy2);

    }

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

                chooseMenu.setInputValue(chooseMenu.getInputValue() + key);
            }

            if((key == PApplet.BACKSPACE) && chooseMenu.getInputValue().length() >1){
                //if((key == PApplet.BACKSPACE && canPress > 30) && chooseMenu.getInputValue().length() >1){

                    chooseMenu.setInputValue(chooseMenu.getInputValue().substring(0,chooseMenu.getInputValue().length() - 2));
                    chooseMenu.setInputValue(chooseMenu.getInputValue().trim());
                   System.out.println("|" + chooseMenu.getInputValue() + "|");





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

    public void displayCurrentRoomName(){
        if(character.getPosition().x == 3 && character.getPosition().y == 5) {
            pApplet.text("Du befindest dich aktuell in der " + gameMap.getWorldMap().get(character.getPosition()).getRoomName(), 600,300,200,100);

        }else
        pApplet.text("Du befindest dich aktuell im " + gameMap.getWorldMap().get(character.getPosition()).getRoomName(), 600,300,200,100);
    }

    public void displayCurrentWeight(){
        pApplet.text("Du hast aktuell ein Gewicht von " + character.getWeight() + " Kilo dabei.",600,600,200,200);
    }

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
