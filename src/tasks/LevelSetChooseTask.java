package tasks;

import biuoop.DrawSurface;
import biuoop.GUI;
import interfaces.Animation;

import javax.imageio.ImageIO;
import java.awt.*;
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
    private String path;


    /**
     * Instantiates a new Level set choose task.
     *
     * @param gui the gui
     * @param filePath  the file's path
     */
    public LevelSetChooseTask(GUI gui, String filePath) {
        this.linesMap = new HashMap<>();
        this.gui = gui;
        this.isRunning = true;
        this.path = filePath;
        try {
            this.fileLines = Files.readAllLines(new File(filePath).toPath());
            for (int i = 0; i < this.fileLines.size(); i += 2) {
                linesMap.put(this.fileLines.get(i).charAt(0), i + 1);
            }
        } catch (IOException e) {
            System.out.println();
        }
    }

    /**
     * DoOneFrame function, prints the menu.
     *
     * @param d drawsurface
     */
    public void doOneFrame(DrawSurface d) {
        String background = "image(background_images/start_game_background.jpg)";
        try {
            d.drawImage(0, 0, ImageIO.read(new File(background.substring(background.indexOf("(") + 1
                    , background.indexOf(")")))));
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
        d.drawText(20, 160 + (i + 5) * 25, "Press SPACE To Return To Main Menu", 15);
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
