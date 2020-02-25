import processing.core.PApplet;

public class ChooseMenu {
    PApplet pApplet;
    String choice1 = "Move Up";
    String choice2 = "Move Down";
    String choice3 = "Move Left";
    String choice4 = "Move Right";
    String inputValue = "";

    public ChooseMenu(PApplet pApplet){
        this.pApplet = pApplet;
    }

    public String getInputValue() {
        return inputValue;
    }

    public void setInputValue(String inputValue) {
        this.inputValue = inputValue;
    }

    public void drawChoices(){
        pApplet.textSize(16);

        pApplet.fill(255,255,255);
        pApplet.text(choice1, 600,30);
        pApplet.text(choice2, 600,60);
        pApplet.text(choice3, 600,90);
        pApplet.text(choice4, 600,120);
        pApplet.fill(0,0,0);
        pApplet.rect(600,150,70,80);
        pApplet.fill(255,255,255);
        pApplet.text(inputValue,600,150, 70,80);

    }
    public void chooseOption(String choiceSelected){
        String userchoice = choiceSelected.toLowerCase();
        switch(userchoice) {
            case "move up":
                //mache das
                break;
            case "move down":
                //mache das
                break;
            case "move left":
                break;
            case "move right":
                break;
        }

    }



}
