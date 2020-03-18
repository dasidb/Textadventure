import processing.core.PApplet;
import processing.event.KeyEvent;

public class UpgradeMenuGameState extends GameState {

    Character character;
    int choice = 0;
    boolean successfullUpgrade;
    int weightUpgradeCost = 500;

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
    }

    @Override
    protected void doUpdate(long tpf) {

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


    }

    public void keyPressed(KeyEvent event) {

        super.keyPressed(event);
        if (pApplet.keyPressed) {
            if (pApplet.keyCode == pApplet.UP && choice > 0)
                choice--;
            if (pApplet.keyCode == pApplet.DOWN && choice < 1)
                choice++;

            if (pApplet.key == pApplet.ENTER) {
                System.out.println(choice);
                if (choice == 0 && character.getMoney() >= weightUpgradeCost) {
                 successfullUpgrade = character.upgradeWeight(5);
                 if(successfullUpgrade) {
                     character.setMoney(character.getMoney() - weightUpgradeCost);
                     weightUpgradeCost = increaseUpgradeCost(weightUpgradeCost);

                 }
                }
                if (choice == 1) {


                }
            }
        }
    }

    public int increaseUpgradeCost(int upgradeableValue){
        return upgradeableValue *= 1.5;

    }
}
