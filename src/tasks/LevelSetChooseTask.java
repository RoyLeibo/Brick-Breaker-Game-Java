package tasks;

import biuoop.DrawSurface;
import biuoop.GUI;
import interfaces.Animation;
import interfaces.Task;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LevelSetChooseTask implements Animation {
    private GUI gui;
    private boolean isRunning;
    private List<String> fileLines;
    private Map<Character, Integer> linesMap;


    public LevelSetChooseTask(GUI gui) {
        this.linesMap = new HashMap<>();
        this.gui = gui;
        this.isRunning = true;
        try {
            this.fileLines = Files.readAllLines(new File("LevelsSets.txt").toPath());
            for(int i = 0 ; i < this.fileLines.size() ; i += 2) {
                linesMap.put(this.fileLines.get(i).charAt(0), i + 1);
            }
        } catch (IOException e) {
        }
    }

    public void doOneFrame(DrawSurface d) {
        String line;
        d.drawText(70, 100, "Please Choose Game Level:", 40);
        for(int i = 0 ; i < this.fileLines.size() ; i += 2){
            line = this.fileLines.get(i);
            d.drawText(100, 120 + (i + 1) * 30, "Press " + line, 30);
        }
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    public String getLine(int i){
        return this.fileLines.get(i);
    }

    public Map<Character, Integer> getLinesMap() {
        return linesMap;
    }

    public boolean shouldStop() {
        return isRunning;
    }
}
