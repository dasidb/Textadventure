import processing.core.PApplet;

import java.io.*;
import java.util.Properties;

public class Story {

    String storyText = "";
    InputStream inputStream;
    String delayedStory = "";


    // Sets the story String for the Story class
    public void readStoryFromFile(int roomID, int storyCount) throws IOException {
        String propValue = "[" + roomID + "]" + "[" + storyCount + "]";
        try {
            Properties properties = new Properties();
            String propertieFieName = "story.properties";
            inputStream = getClass().getClassLoader().getResourceAsStream(propertieFieName);


            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                throw new FileNotFoundException("property file " + propertieFieName + " not found in classpath");

            }
            storyText = properties.getProperty(propValue);


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            inputStream.close();
        }
    }

    public void render(PApplet pApplet){
        pApplet.text(storyText,50,50);
        delayStoryDraw();

    }

    // Delays the draw of the story text so that it isnt displayed instantly
    public void delayStoryDraw() {
        if (!delayedStory.equals(storyText)) {
            for (int i = 0; i < storyText.length(); i++) {
                delayedStory = storyText.substring(0, i);
            }
        }
    }
}
