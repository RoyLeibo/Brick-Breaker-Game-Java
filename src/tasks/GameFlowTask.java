package tasks;

import gamecontrollers.HighScoresTable;
import interfaces.Task;
import rungame.AnimationRunner;
import rungame.GameFlow;

/**
 * The type Game flow task.
 *
 * @author Roy Leibovitz <royleibo212@gmail.com> Roy Leibovitz
 *
 * @param <T> the type parameter
 */
public class GameFlowTask<T> implements Task<T> {
    private String[] args;
    private HighScoresTable hst;
    private AnimationRunner animationRunner;

    /**
     * Instantiates a new Game flow task.
     *
     * @param args            the args
     * @param hst             the hst
     * @param animationRunner the animation runner
     */
    public GameFlowTask(String[] args, HighScoresTable hst, AnimationRunner animationRunner) {
        this.args = args;
        this.hst = hst;
        this.animationRunner = animationRunner;
    }

    /**
     * Run function.
     *
     * @return T value
     */
    public T run() {
        new GameFlow(this.args, this.hst, this.animationRunner).runLevels();
        return null;
    }
}
