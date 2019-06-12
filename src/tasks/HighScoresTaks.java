package tasks;

import animations.HighScoresAnimation;
import animations.KeyPressStoppableAnimation;
import biuoop.DrawSurface;
import biuoop.GUI;
import gamecontrollers.HighScoresTable;
import interfaces.Task;
import rungame.AnimationRunner;

public class HighScoresTaks<T> implements Task<T> {
    private int size;
    private AnimationRunner animationRunner;
    private HighScoresAnimation highScoresAnimation;
    private GUI gui;

    public HighScoresTaks(AnimationRunner animationRunner, HighScoresAnimation hsa, GUI gui) {
        this.animationRunner = animationRunner;
        this.highScoresAnimation = hsa;
        this.gui = gui;
    }

    public T run() {
        this.animationRunner.run(new KeyPressStoppableAnimation(gui.getKeyboardSensor(), "SPACE_KEY"
                , this.highScoresAnimation));
        return null;
    }
}
