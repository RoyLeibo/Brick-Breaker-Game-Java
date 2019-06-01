package rungame;

//import animations.EndScreen;

import animations.HighScoresAnimation;
import animations.KeyPressStoppableAnimation;
import animations.LoseScreen;
import animations.WinScreen;
import biuoop.DialogManager;
import gamecontrollers.HighScoresTable;
import gamecontrollers.ScoreInfo;
import interfaces.LevelInformation;
import levels.DirectHit;
import levels.FullLines;
import levels.PartialLines;
import levels.Pyramid;
import gamecontrollers.LivesListener;
import others.Counter;
import gamecontrollers.ScoreTrackingListener;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type Game flow.
 *
 * @author Roy Leibovitz <royleibo212@gmail.com> Roy Leibovitz
 */
public class GameFlow {
    private LivesListener livesListener;
    private ScoreTrackingListener scoreTrackingListener;
    private boolean isAlive;
    private Map<Integer, LevelInformation> levelsMap;
    private List<Integer> levelsToRun;
    private HighScoresTable hst;

    /**
     * Instantiates a new Game flow.
     * The constructor creates the levels list to run.
     *
     * @param input the input
     */
    public GameFlow(String[] input, HighScoresTable hst) {
        this.livesListener = new LivesListener(new Counter());
        this.livesListener.getLivesCounter().setCounter(7);
        this.scoreTrackingListener = new ScoreTrackingListener(new Counter());
        this.levelsMap = new HashMap<>();
        this.levelsMap.put(1, new FullLines());
        this.levelsMap.put(2, new DirectHit());
        this.levelsMap.put(3, new PartialLines());
        this.levelsMap.put(4, new Pyramid());
        this.isAlive = true;
        this.levelsToRun = cleanArguments(input);
        this.hst = hst;
    }

    /**
     * Run levels.
     */
    public void runLevels() {
        GameLevel gameLevel;
        int counter = 0;
        LevelInformation levelInformation;
        for (Integer levelIndex : this.levelsToRun) {
            counter++;
            levelInformation = this.levelsMap.get(levelIndex);
            gameLevel = new GameLevel(levelInformation, this.scoreTrackingListener, this.livesListener, this);
            gameLevel.playOneTurn();
            // if the player has been disqualified
            if (counter == this.levelsToRun.size()) {
                if (this.hst.getRank(this.scoreTrackingListener.getScoreCounter().getValue()) >=
                        this.hst.getHighScores().size()) {
                    DialogManager dialog = gameLevel.getAnimationRunner().getGui().getDialogManager();
                    String name = dialog.showQuestionDialog("Name", "What is your name?", "");
                    this.hst.add(new ScoreInfo(name, this.scoreTrackingListener.getScoreCounter().getValue()));
                    try{
                        this.hst.save(new File(""));
                    }
                    catch (IOException e){}
                }
            }
            if (!this.isAlive) {
                // print "Game Over!"
                gameLevel.getAnimationRunner().run(new KeyPressStoppableAnimation(
                        gameLevel.getAnimationRunner().getGui().getKeyboardSensor(), "SPACE_KEY", new LoseScreen()));
                gameLevel.getAnimationRunner().run(new KeyPressStoppableAnimation
                        (gameLevel.getAnimationRunner().getGui().getKeyboardSensor(), "SPACE_KEY",
                                new HighScoresAnimation(this.hst)));
                gameLevel.getAnimationRunner().getGui().close();
                return;
            }
            // if the player finished all the game levels
            if (counter == levelsToRun.size() && this.isAlive) {
                // print "You Win! Your Score Is: X"
                gameLevel.getAnimationRunner().run(new KeyPressStoppableAnimation(
                        gameLevel.getAnimationRunner().getGui().getKeyboardSensor(), "SPACE_KEY", new WinScreen(
                        this.scoreTrackingListener)));
                gameLevel.getAnimationRunner().run(new KeyPressStoppableAnimation
                        (gameLevel.getAnimationRunner().getGui().getKeyboardSensor(), "SPACE_KEY",
                                new HighScoresAnimation(this.hst)));
                gameLevel.getAnimationRunner().getGui().close();
                return;
            }
        }
    }

    /**
     * Sets is alive.
     *
     * @param alive the alive
     */
    public void setIsAlive(boolean alive) {
        isAlive = alive;
    }

    /**
     * Clean arguments list.
     *
     * @param input the input
     * @return the list
     */
    public List<Integer> cleanArguments(String[] input) {
        List<Integer> levels = new ArrayList();
        int temp;
        for (String str : input) {
            try {
                temp = Integer.parseInt(str);
                if (temp > 0 && temp < 5) {
                    levels.add(temp);
                }
            } catch (NumberFormatException e) {
                continue;
            }
        }
        return levels;
    }
}