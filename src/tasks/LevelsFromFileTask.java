package tasks;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import gamecontrollers.HighScoresTable;
import interfaces.LevelInformation;
import interfaces.Task;
import levelfromfile.LevelSpecificationReader;
import rungame.AnimationRunner;
import rungame.GameFlow;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type Levels from file task.
 *
 * @author Roy Leibovitz <royleibo212@gmail.com> Roy Leibovitz
 *
 * @param <T> the type parameter
 */
public class LevelsFromFileTask<T> implements Task<T> {
    private HighScoresTable hst;
    private GUI gui;
    private LevelSetChooseTask levelSetChooseTask;
    private AnimationRunner animationRunner;

    /**
     * Instantiates a new Levels from file task.
     *
     * @param hst                the hst
     * @param gui                the gui
     */
    public LevelsFromFileTask(AnimationRunner animationRunner, HighScoresTable hst, GUI gui) {
        this.hst = hst;
        this.gui = gui;
        this.levelSetChooseTask = new LevelSetChooseTask(this.gui);
        this.animationRunner = animationRunner;
    }

    public T run() {
        Map<Character, Integer> linesMap = this.levelSetChooseTask.getLinesMap();
        int lineNum = 0;
        DrawSurface d;
        KeyboardSensor ks = this.gui.getKeyboardSensor();
        d = this.gui.getDrawSurface();
        this.levelSetChooseTask.doOneFrame(d);
        this.gui.show(d);
        while(this.levelSetChooseTask.shouldStop()){
            for (Map.Entry<Character, Integer> entry : linesMap.entrySet()) {
                if(ks.isPressed(String.valueOf(entry.getKey()))){
                    this.levelSetChooseTask.setRunning(false);
                    lineNum = entry.getValue();
                    break;
                }
            }
        }
        String path = this.levelSetChooseTask.getLine(lineNum);
        try {
            List<LevelInformation> setLevels = new LevelSpecificationReader().createLevelInformationList(new File(path));
            Map<Integer, LevelInformation> levelsMap = new HashMap<>();
            String[] levelsOrder = new String[setLevels.size()];
            for (int i = 0 ; i < setLevels.size(); i++) {
                levelsMap.put(i+1, setLevels.get(i));
                levelsOrder[i] = String.valueOf(i+1);
            }
            new GameFlow(levelsMap, levelsOrder, this.hst, this.animationRunner).runLevels();
        }
        catch (IOException e){}
        return null;
    }
}
