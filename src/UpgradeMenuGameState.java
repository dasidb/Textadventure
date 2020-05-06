import processing.core.PApplet;
import processing.core.PVector;
import processing.event.KeyEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UpgradeMenuGameState extends GameState {

    Character character;
    int choice = 0;
    boolean successfullUpgrade;
    int weightUpgradeCost = 500;
    String upgradeMsg = "";
    long test;
    int timesWeightUpgrade = 0;


    public UpgradeMenuGameState(PApplet pApplet, GameManager manager, Character character){
        super(pApplet, manager);
        this.character = character;
        init();
    }
    @Override
    protected void doRender() {
        pApplet.clear();
    displayMoney();
    displayUpgrades();
        if(upgradeMsg != "") {
            pApplet.text(upgradeMsg, 50, 50, 500, 800);
        }

        keyInstructions();
    }

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

    public void displayMoney(){
        pApplet.text("Current Money: " + character.getMoney(),30,30);

    }

    public void displayUpgrades(){

        if(choice == 0){
            pApplet.fill(255,0,0);
        }
        else
            pApplet.fill(255,255,255);
        pApplet.text("Mehr Gewicht aktuell " + character.getMaxWeight() + " Das Upgrade Kostet " + weightUpgradeCost +"€" , 200, 240);

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
                    if(character.getMoney() >= weightUpgradeCost) {
                        successfullUpgrade = character.upgradeWeight(5);
                        timesWeightUpgrade++;

                        successfullUpgrade();
                        if (successfullUpgrade) {
                            character.setMoney(character.getMoney() - weightUpgradeCost);
                            weightUpgradeCost = increaseUpgradeCost(weightUpgradeCost);

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

    public int increaseUpgradeCost(int upgradeableValue){
        return upgradeableValue *= 1.5;
    }

    public void notEnoughMoney(){
        setUpgradeMsg("Du hast nicht genug Geld");
        test = System.currentTimeMillis();
    }
    public void successfullUpgrade(){
        setUpgradeMsg("Upgrade erfolgreich!");
        test = System.currentTimeMillis();
    }
    public void startNewGame(){
        Room.roomCount = 0;
        MoneyRoom.roomCount = 0;

        gameManager.getGameStateMap().put("storyGameState",new StoryGameState(getProcessing(),gameManager));
        Room.roomCount = 0;
        MoneyRoom.roomCount = 0;
    gameManager.setCurrentGameState(new PlayGameState(getProcessing(),gameManager));
        Room.roomCount = 0;
        MoneyRoom.roomCount = 0;
        ((PlayGameState) gameManager.getCurrentGameState()).gameMap.setWorldMap(new HashMap<PVector,Room>());
        ((PlayGameState) gameManager.getCurrentGameState()).gameMap.createManualGameMap1();
        ((PlayGameState) gameManager.getCurrentGameState()).getCharacter().setInventory(new ArrayList<Item>());
        ((PlayGameState) gameManager.getCurrentGameState()).getCharacter().setMaxWeight(timesWeightUpgrade*5);

    }
    public void keyInstructions(){
        pApplet.fill(255,255,255);
        pApplet.text("To select, use the arrow keys  \n " +
                "Confirm selection with Enter",600,30,200,200);
    }

}
