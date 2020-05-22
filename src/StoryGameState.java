import processing.core.PApplet;
import processing.core.PVector;
import processing.event.KeyEvent;

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
