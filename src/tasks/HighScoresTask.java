package tasks;

import animations.HighScoresAnimation;
import animations.KeyPressStoppableAnimation;
import biuoop.GUI;
import interfaces.Task;
import rungame.AnimationRunner;

/**
 * The type High scores taks.
 *
 * @param <T> the type parameter
 * @author Roy Leibovitz <royleibo212@gmail.com> Roy Leibovitz
 */
public class HighScoresTask<T> implements Task<T> {
    private AnimationRunner animationRunner;
    private HighScoresAnimation highScoresAnimation;
    private GUI gui;

    /**
     * Instantiates a new High scores task.
     *
     * @param animationRunner the animation runner
     * @param hsa             the hsa
     * @param gui             the gui
     */
    public HighScoresTask(AnimationRunner animationRunner, HighScoresAnimation hsa, GUI gui) {
        this.animationRunner = animationRunner;
        this.highScoresAnimation = hsa;
        this.gui = gui;
    }

    /**
     * This function runs the HighScoreAnimation using an Animation Runner.
     *
     * @return generic type T
     */
    public T run() {
        this.animationRunner.run(new KeyPressStoppableAnimation(gui.getKeyboardSensor(), "SPACE_KEY"
                , this.highScoresAnimation));
        return null;
    }
}
