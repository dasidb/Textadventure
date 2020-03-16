import processing.core.PApplet;
import processing.core.PVector;
import processing.event.KeyEvent;

public class StoryGameState extends GameState {
    Story story;
    String currentStory ="";

    public StoryGameState(PApplet pApplet, GameManager gameManager, String currentStory){
        super(pApplet,gameManager);
        this.currentStory = currentStory;
    }
    public StoryGameState(PApplet pApplet, GameManager gameManager){
        super(pApplet,gameManager);

    }

    @Override
    public void init() {
        super.init();
        story = new Story();
    }

    @Override
    protected void doRender() {
        story.render(getProcessing());

    }

    @Override
    protected void doUpdate(long tpf) {

    }


    @Override
    public void keyPressed(KeyEvent event) {
        super.keyPressed(event);


        if(story.storyPartFinished && pApplet.key == pApplet.ENTER){

        gameManager.setCurrentGameState(gameManager.getGameStateMap().get("playGameState"));
        story.storyPartFinished = false;

        }
    }

    public Story getStory(){
        return story;
    }
}
