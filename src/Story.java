import processing.core.PApplet;

import java.io.*;
import java.util.Properties;

public class Story {

    private String storyText = "kfewafflkmfwflkmfelkmflkmfewlkmfewlkmfewlkmfewlkmlkmfewlkmfewlkmfewlkm";
    private InputStream inputStream;
    private String delayedStory = "";
    private int counter = 0;
    private  int storyStringLength = 0;
    private  boolean storyPartFinished = false;

    public Story(){
  //  storyText = "";
    }

    public boolean isStoryPartFinished() {
        return storyPartFinished;
    }

    public String getStoryText() {
        return storyText;
    }

    public void setStoryText(String storyText) {
        this.storyText = storyText;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getDelayedStory() {
        return delayedStory;
    }

    public void setDelayedStory(String delayedStory) {
        this.delayedStory = delayedStory;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public int getStoryStringLength() {
        return storyStringLength;
    }

    public void setStoryStringLength(int storyStringLength) {
        this.storyStringLength = storyStringLength;
    }

    public void setStoryPartFinished(boolean storyPartFinished) {
        this.storyPartFinished = storyPartFinished;
    }

    // Sets the story String for the Story class
    public void readStoryFromFile(int roomID, int storyCount) throws IOException {
        String propValue = "[" + roomID + "]" + "[" + storyCount + "]";
        System.out.println(propValue);
        try {
            Properties properties = new Properties();
            String propertieFieName;
            if(Game.isAdmin()) {
                 propertieFieName = "Assets/storyAdmin";
            }
            else {
                 propertieFieName = "Assets/story";
            }
            inputStream = new FileInputStream(propertieFieName);


            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                throw new FileNotFoundException("property file " + propertieFieName + " not found in classpath");

            }
            storyText = properties.getProperty(propValue);
           storyStringLength = 0;


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            inputStream.close();
        }
    }
    // Renders the story
    public void render(PApplet pApplet){

        //pApplet.clear();
        pApplet.fill(0,0,0);
        pApplet.rect(50,50,500,800);
        pApplet.fill(255,255,255);
        pApplet.text(delayedStory,50,50,500,800);
        if(counter > 1) {
            delayStoryDraw();
            counter = 0;
        }
        counter ++;

    }

    // Delays the draw of the story text so that it isnt displayed instantly
    public void delayStoryDraw() {
        int z = 0;
        if (!delayedStory.equals(storyText)) {


                    delayedStory = storyText.substring(0, storyStringLength);
                    storyStringLength ++;
                }else
        storyPartFinished = true;
            }


        }


