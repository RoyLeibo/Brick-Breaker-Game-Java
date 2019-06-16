package tasks;

import biuoop.DrawSurface;
import biuoop.GUI;
import interfaces.Animation;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
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


    /**
     * Instantiates a new Level set choose task.
     *
     * @param gui the gui
     */
    public LevelSetChooseTask(GUI gui) {
        this.linesMap = new HashMap<>();
        this.gui = gui;
        this.isRunning = true;
        try {
            this.fileLines = Files.readAllLines(new File("LevelsSets.txt").toPath());
            for (int i = 0; i < this.fileLines.size(); i += 2) {
                linesMap.put(this.fileLines.get(i).charAt(0), i + 1);
            }
        } catch (IOException e) {
        }
    }

    /**
     * DoOneFrame function, prints the menu
     */
    public void doOneFrame(DrawSurface d) {
        String line;
        d.drawText(70, 100, "Please Choose Game Level:", 40);
        for (int i = 0; i < this.fileLines.size(); i += 2) {
            line = this.fileLines.get(i);
            d.drawText(100, 160 + (i + 1) * 25, "Press " + line, 30);
        }
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
