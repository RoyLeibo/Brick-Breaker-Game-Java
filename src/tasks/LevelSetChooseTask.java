package tasks;

import biuoop.DrawSurface;
import biuoop.GUI;
import interfaces.Animation;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type Level set choose task.
 *
 * @author Roy Leibovitz <royleibo212@gmail.com>
 */
public class LevelSetChooseTask implements Animation {
    private GUI gui;
    private boolean isRunning;
    private List<String> fileLines;
    private Map<Character, Integer> linesMap;
    private String path;


    /**
     * Instantiates a new Level set choose task.
     *
     * @param gui      the gui
     * @param filePath the file's path
     */
    public LevelSetChooseTask(GUI gui, String filePath) {
        this.linesMap = new HashMap<>();
        this.gui = gui;
        this.isRunning = true;
        this.path = filePath;
        this.fileLines = this.getListFromFile(filePath);
        for (int i = 0; i < this.fileLines.size(); i += 2) {
            linesMap.put(this.fileLines.get(i).charAt(0), i + 1);
        }
    }

    /**
     * Gets list from file.
     *
     * @param file the file
     * @return the list from file
     */
    public List<String> getListFromFile(String file) {
        List<String> linesForFile = new ArrayList<>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(ClassLoader.getSystemClassLoader()
                    .getResourceAsStream(file)));
            String line;
            if (reader != null) {
                try {
                    while ((line = reader.readLine()) != null) {
                        linesForFile.add(line);
                    }
                } catch (IOException e) {
                    System.out.println();
                }
            }
        } catch (Exception e) {
            System.out.println();
        }
        try {
            reader.close();
        } catch (IOException e) {
            System.out.println();
        }
        return linesForFile;
    }

    /**
     * DoOneFrame function, prints the menu.
     *
     * @param d drawsurface
     */
    public void doOneFrame(DrawSurface d) {
        String background = "image(background_images/start_game_background.jpg)";
        try {
            d.drawImage(0, 0, ImageIO.read(ClassLoader.getSystemClassLoader().getResourceAsStream(
                    background.substring(background.indexOf("(") + 1, background.indexOf(")")))));
        } catch (IOException e) {
            System.out.println();
        }
        String line;
        d.setColor(Color.cyan);
        d.drawText(20, 100, "Please Choose Game Level:", 40);
        int i;
        for (i = 0; i < this.fileLines.size(); i += 2) {
            line = this.fileLines.get(i);
            d.drawText(50, 160 + (i + 1) * 25, "Press " + line, 30);
        }
        d.drawText(70, 500, "Press SPACE To Return To Main Menu", 15);
    }

    /**
     * Sets running.
     *
     * @param running the running
     */
    public void setRunning(boolean running) {
        isRunning = running;
    }

    /**
     * Gets line.
     *
     * @param i the
     * @return the line
     */
    public String getLine(int i) {
        return this.fileLines.get(i);
    }

    /**
     * Gets lines map.
     *
     * @return the lines map
     */
    public Map<Character, Integer> getLinesMap() {
        return linesMap;
    }

    /**
     * return should stop.
     *
     * @return isRunning
     */
    public boolean shouldStop() {
        return isRunning;
    }
}
