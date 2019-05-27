package animations;

import biuoop.DrawSurface;
import interfaces.Animation;
import rungame.SpriteCollection;

/**
 * The type Countdown animation.
 *
 * @author Roy Leibovitz <royleibo212@gmail.com>
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private int originalCountFrom;
    private SpriteCollection gameScreen;
    private boolean stop;

    /**
     * Instantiates a new Countdown animation.
     *
     * @param numOfSeconds the num of seconds
     * @param countFrom    the count from
     * @param gameScreen   the game screen
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.stop = true;
        this.originalCountFrom = countFrom;
    }

    /**
     * The function draws the count down.
     *
     * @param d the d
     */
    public void doOneFrame(DrawSurface d) {
        String string = this.countFrom + "...";
        d.drawText(350, 70, string, 50);
        if (this.countFrom == 1) {
            this.stop = false;
        } else {
            this.countFrom--;
        }
    }

    /**
     * should stop getter.
     *
     * @return is the game stopped
     */
    public boolean shouldStop() {
        return stop;
    }
}
