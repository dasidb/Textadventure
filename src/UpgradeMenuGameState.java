import processing.core.PApplet;
import processing.core.PVector;
import processing.event.KeyEvent;

import java.util.ArrayList;
import java.util.HashMap;

// When the character escapes he can upgrade his inventory size
public class UpgradeMenuGameState extends GameState {

    private Character character;
    private int choice = 0;
    private boolean successfullUpgrade;
    private int timesWeightUpgrade = 0;
    private int weightUpgradeCost = 500;
    private String upgradeMsg = "";
    private long test;



    public UpgradeMenuGameState(PApplet pApplet, GameManager manager, Character character){
        super(pApplet, manager);
        this.character = character;
        timesWeightUpgrade = character.getTimesWeightUpgraded();

        init();
    }

    // Renders stuff
    @Override
    protected void doRender() {
        pApplet.clear();
    displayMoney();
    displayChoices();
        if(upgradeMsg != "") {
            pApplet.text(upgradeMsg, 50, 50, 500, 800);
        }

        keyInstructions();
        System.out.println(character.getTimesWeightUpgraded());
    }

    // Updates the gamelogic
    @Override
    protected void doUpdate(long tpf) {


            if((System.currentTimeMillis() -test  ) / 1000 > 5)
                setUpgradeMsg("");

    }

    public String getUpgradeMsg() {
        return upgradeMsg;
    }

    public void setUpgradeMsg(String upgradeMsg) {
        this.upgradeMsg = upgradeMsg;
    }

    public void upgradeMenu(){

    }

    // Display the money ammount
    public void displayMoney(){
        pApplet.text("Current Money: " + character.getMoney(),30,30);

    }

    // Display the different choices
    public void displayChoices(){

        if(choice == 0){
            pApplet.fill(255,0,0);
        }
        else
            pApplet.fill(255,255,255);
        pApplet.text("Mehr Gewicht aktuell " + character.getMaxWeight() + " Das Upgrade Kostet " + (weightUpgradeCost + (character.getTimesWeightUpgraded() * 250)) + "€" , 200, 240);

        if(choice == 1){
            pApplet.fill(255,0,0);
        }
        else
            pApplet.fill(255,255,255);
        pApplet.text("Bonus Geld am ende des Raubzuges (Nicht implementiert) "  , 200, 270);
        if(choice == 2){
            pApplet.fill(255,0,0);
        }
        else
            pApplet.fill(255,255,255);
        pApplet.text("Neuen Raub starten"  , 200, 300);
        pApplet.fill(255,255,255);


    }

    // different action depending on key pressed
    public void keyPressed(KeyEvent event) {

        super.keyPressed(event);
        if (pApplet.keyPressed) {
            if (pApplet.keyCode == pApplet.UP && choice > 0)
                choice--;
            if (pApplet.keyCode == pApplet.DOWN && choice < 2)
                choice++;

            if (pApplet.key == pApplet.ENTER) {
                System.out.println(choice);
                if (choice == 0 ) {
                    if(character.getMoney() >= weightUpgradeCost + (character.getTimesWeightUpgraded()*250)) {
                        successfullUpgrade = character.upgradeWeight(5);



                        successfullUpgrade();
                        if (successfullUpgrade) {
                            character.setMoney(character.getMoney() - (weightUpgradeCost + (character.getTimesWeightUpgraded() * 250)));
                            //weightUpgradeCost = increaseUpgradeCost(weightUpgradeCost);
                            character.setTimesWeightUpgraded(character.getTimesWeightUpgraded() + 1);
                            timesWeightUpgrade++;
                        }
                    }else
                        notEnoughMoney();
                }

                if (choice == 1) {


                }
                if(choice == 2){
                    startNewGame();
                }
            }
        }
    }

    // legacy used for increasing the upgrade cost wasnt be able to used anymore after a player could start a new rob
    public int increaseUpgradeCost(int upgradeableValue){
        return upgradeableValue *= 1.5;
    }

    // displays the message that you dont have enough money for an upgrade
    public void notEnoughMoney(){
        setUpgradeMsg("Du hast nicht genug Geld");
        test = System.currentTimeMillis();
    }

    // displays when you successfully upgraded something
    public void successfullUpgrade(){
        setUpgradeMsg("Upgrade erfolgreich!");
        test = System.currentTimeMillis();
    }

    // here a new game gets started .. bunch of mess in this method needs to get refractored should try to do TDD here
    //the method is so fucked up cause it wasnt planned and got implemented lately.
    // what happens here is the room count gets set to 0 so a new map can get created. some character variables get saved
    // we create a new story gamestate so storys get displayed again
    // the character gets a new inventory gets his money back and his upgrade counts
    public void startNewGame(){
        Room.setRoomCount(0);
        MoneyRoom.setRoomCount(0);
        timesWeightUpgrade = character.getTimesWeightUpgraded();
        int tmpMoney = character.getMoney();
        System.out.println(tmpMoney + " das ist tmpmoney");

        gameManager.getGameStateMap().put("storyGameState",new StoryGameState(getProcessing(),gameManager));
        Room.setRoomCount(0);
        MoneyRoom.setRoomCount(0);
        PlayGameState newerGameState = new PlayGameState(getProcessing(),gameManager);
        gameManager.getGameStateMap().put("playGameState", newerGameState);
        gameManager.setCurrentGameState(gameManager.getGameStateMap().get("playGameState"));
        Room.setRoomCount(0);
        MoneyRoom.setRoomCount(0);
        ((PlayGameState) gameManager.getCurrentGameState()).getGameMap().setWorldMap(new HashMap<PVector,Room>());
        ((PlayGameState) gameManager.getCurrentGameState()).getGameMap().createManualGameMap1();
        ((PlayGameState) gameManager.getCurrentGameState()).getCharacter().setInventory(new ArrayList<Item>());
        ((PlayGameState) gameManager.getCurrentGameState()).getCharacter().setMaxWeight(35 + timesWeightUpgrade*5);
        ((PlayGameState) gameManager.getCurrentGameState()).getCharacter().setTimesWeightUpgraded(timesWeightUpgrade);
        ((PlayGameState) gameManager.getCurrentGameState()).getCharacter().setMoney(tmpMoney);
    }

    // Displays the controlls
    public void keyInstructions(){
        pApplet.fill(255,255,255);
        pApplet.text("To select, use the arrow keys  \n " +
                "Confirm selection with Enter",600,30,200,200);
    }

}
