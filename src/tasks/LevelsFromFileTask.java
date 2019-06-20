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
 * @param <T> the type parameter
 * @author Roy Leibovitz <royleibo212@gmail.com> Roy Leibovitz
 */
public class LevelsFromFileTask<T> implements Task<T> {
    private HighScoresTable hst;
    private GUI gui;
    private LevelSetChooseTask levelSetChooseTask;
    private AnimationRunner animationRunner;
    private String path;

    /**
     * Instantiates a new Levels from file task.
     *
     * @param animationRunner the animation runner
     * @param hst             the hst
     * @param gui             the gui
     * @param filePath        the path
     */
    public LevelsFromFileTask(AnimationRunner animationRunner, HighScoresTable hst, GUI gui, String filePath) {
        this.hst = hst;
        this.gui = gui;
        this.levelSetChooseTask = new LevelSetChooseTask(this.gui, filePath);
        this.animationRunner = animationRunner;
        this.path = filePath;
    }

    /**
     * This function runs the menu and gets the user input.
     *
     * @return generic type T
     */
    public T run() {
        Map<Character, Integer> linesMap = this.levelSetChooseTask.getLinesMap();
        int lineNum = 0;
        DrawSurface d;
        KeyboardSensor ks = this.gui.getKeyboardSensor();
        d = this.gui.getDrawSurface();
        // prints the menu on the screen
        this.levelSetChooseTask.doOneFrame(d);
        this.gui.show(d);
        // while loop that stop only when the user entered a valid key
        while (this.levelSetChooseTask.shouldStop()) {
            for (Map.Entry<Character, Integer> entry : linesMap.entrySet()) {
                if (ks.isPressed(String.valueOf(entry.getKey()))) {
                    this.levelSetChooseTask.setRunning(false);
                    // the path to the line number from file LevelSets
                    lineNum = entry.getValue();
                    break;
                }
            }
            if (ks.isPressed(ks.SPACE_KEY)) {
                return null;
            }
        }
        this.levelSetChooseTask.setRunning(true);
        // finding the matching path to the chosen option from the LevelSets file
        String filePath = this.levelSetChooseTask.getLine(lineNum);
        try {
            // creates LevelInformation list from the path specified.
            List<LevelInformation> setLevels = new LevelSpecificationReader()
                    .createLevelInformationList(new File(filePath));
            Map<Integer, LevelInformation> levelsMap = new HashMap<>();
            String[] levelsOrder = new String[setLevels.size()];
            for (int i = 0; i < setLevels.size(); i++) {
                levelsMap.put(i + 1, setLevels.get(i));
                levelsOrder[i] = String.valueOf(i + 1);
            }
            // runs the game buy creating a new GameFlow
            new GameFlow(levelsMap, levelsOrder, this.hst, this.animationRunner).runLevels();
        } catch (IOException e) {
            return null;
        }
        return null;
    }
}