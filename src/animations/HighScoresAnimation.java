package animations;

import biuoop.DrawSurface;
import gamecontrollers.HighScoresTable;
import interfaces.Animation;

/**
 * The type High scores animation.
 *
 * @author Roy Leibovitz <royleibo212@gmail.com>
 */
public class HighScoresAnimation implements Animation {
    private HighScoresTable hst;
    private boolean isRunning;

    /**
     * Instantiates a new High scores animation.
     *
     * @param hst the hst
     */
    public HighScoresAnimation(HighScoresTable hst) {
        this.isRunning = true;
        this.hst = hst;
    }

    /**
     * Do one frame.
     *
     * @param d the d
     */
    public void doOneFrame(DrawSurface d) {
        d.drawText(250, 100, "High Scores Table:", 40);
        for (int i = 0; i < this.hst.getHighScores().size(); i++) {
            d.drawText(280, 120 + (i + 1) * 30, (i + 1) + ".  " + this.hst.getHighScores().get(i).getName()
                    + ":   ", 30);
            d.drawText(480, 120 + (i + 1) * 30, String.valueOf(this.hst.getHighScores().get(i).getScore()), 30);
        }
    }

    /**
     * Should stop boolean.
     *
     * @return the boolean
     */
    public boolean shouldStop() {
        return isRunning;
    }
}
