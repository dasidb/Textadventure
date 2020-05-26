import processing.core.PApplet;
import processing.core.PVector;
import processing.event.KeyEvent;

// Is the gamestate to handle the story stuff
public class StoryGameState extends GameState {
    private  Story story;
    private  String currentStory ="";

    public StoryGameState(PApplet pApplet, GameManager gameManager, String currentStory){
        super(pApplet,gameManager);
        this.currentStory = currentStory;
    }
    public StoryGameState(PApplet pApplet, GameManager gameManager){
        super(pApplet,gameManager);

    }

    // last thing thats get called in the constructor
    @Override
    public void init() {
        super.init();
        story = new Story();
    }

    // Renders stuff
    @Override
    protected void doRender() {
        story.render(getProcessing());

    }

    // Updates gamelogic
    @Override
    protected void doUpdate(long tpf) {

    }

    // Method for keypresses
    @Override
    public void keyPressed(KeyEvent event) {
        super.keyPressed(event);


        if(story.isStoryPartFinished() && pApplet.key == pApplet.ENTER){
        story.setDelayedStory("");
        gameManager.setCurrentGameState(gameManager.getGameStateMap().get("playGameState"));
        story.setStoryPartFinished(false);

        }
    }

    public Story getStory(){
        return story;
    }
}
