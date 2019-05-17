package rungame;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import interfaces.Animation;

public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper;
    private double sleepFor;

    public AnimationRunner(int framesPerSecond) {
        this.framesPerSecond = framesPerSecond;
        this.gui = new GUI("", 800, 600);
        this.sleeper = new Sleeper();
        this.sleepFor = 0;
    }

    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / this.framesPerSecond;
        DrawSurface d;
        while (animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            d = this.gui.getDrawSurface();
            animation.doOneFrame(d);
            this.gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
            if (sleepFor > 0) {
                sleeper.sleepFor((long)this.sleepFor * 1000);
            }
        }
        return;
    }

    public void setSleepFor(double sleepFor) {
        this.sleepFor = sleepFor;
    }

    public GUI getGui() {
        return gui;
    }
}
