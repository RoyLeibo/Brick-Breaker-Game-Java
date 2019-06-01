package tasks;

import animations.HighScoresAnimation;
import gamecontrollers.HighScoresTable;
import interfaces.Task;
import rungame.AnimationRunner;

public class HighScoresTaks<T> implements Task<T> {
    private int size;
    private AnimationRunner animationRunner;
    private HighScoresAnimation highScoresAnimation;

    public HighScoresTaks(AnimationRunner animationRunner, HighScoresAnimation hsa) {
        this.animationRunner = animationRunner;
        this.highScoresAnimation = hsa;
    }

    public T run() {
        this.animationRunner.run(this.highScoresAnimation);
        return null;
    }
}
