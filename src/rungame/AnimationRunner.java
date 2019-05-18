package rungame;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import interfaces.Animation;

/**
 * The type Animation runner.
 *
 * @author Roy Leibovitz <royleibo212@gmail.com>
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper;
    private double sleepFor;

    /**
     * Instantiates a new Animation runner.
     *
     * @param framesPerSecond the frames per second
     */
    public AnimationRunner(int framesPerSecond) {
        this.framesPerSecond = framesPerSecond;
        this.gui = new GUI("", 800, 600);
        this.sleeper = new Sleeper();
        this.sleepFor = 0;
    }

    /**
     * Run.
     *
     * @param animation the animation
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / this.framesPerSecond;
        DrawSurface d;
        while (animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            d = this.gui.getDrawSurface();
            animation.doOneFrame(d); // one frame of animation
            this.gui.show(d); // show new drawing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
            if (sleepFor > 0) {
                sleeper.sleepFor((long) this.sleepFor * 1000);
            }
        }
        return;
    }

    /**
     * Sets sleep for.
     *
     * @param sleepFor1 the sleep for
     */
    public void setSleepFor(double sleepFor1) {
        this.sleepFor = sleepFor1;
    }

    /**
     * Gets gui.
     *
     * @return the gui
     */
    public GUI getGui() {
        return gui;
    }
}
