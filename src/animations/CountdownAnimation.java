package animations;

import biuoop.DrawSurface;
import biuoop.Sleeper;
import interfaces.Animation;
import rungame.SpriteCollection;

public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private int originalCountFrom;
    private SpriteCollection gameScreen;
    private boolean stop;

    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.stop = true;
        this.originalCountFrom = countFrom;
    }

    public void doOneFrame(DrawSurface d) {
        String string = "";
        if (this.countFrom == 0) {
            string = "GO!";
            d.drawText(350, 290, string, 32);
            this.stop = false;
        } else {
            string = this.countFrom + "...";
            d.drawText(350, 290, string, 32);
            this.countFrom--;
        }
    }

    public boolean shouldStop() {
        return stop;
    }
}
