package animations;

import biuoop.DrawSurface;
import gamecontrollers.HighScoresTable;
import interfaces.Animation;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

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
        String background = "image(background_images/high_score_background.jpg)";
        try {
            d.drawImage(0, 0, ImageIO.read(new File(background.substring(background.indexOf("(") + 1
                    , background.indexOf(")")))));
        } catch (IOException e) {
            System.out.println();
        }
        d.setColor(Color.cyan);
        d.drawText(20, 100, "High Scores Table:", 40);
        int i;
        for (i = 0; i < this.hst.getHighScores().size(); i++) {
            d.drawText(50, 120 + (i + 1) * 30, (i + 1) + ".  " + this.hst.getHighScores().get(i).getName()
                    + ":   ", 30);
            d.drawText(200, 120 + (i + 1) * 30, String.valueOf(this.hst.getHighScores().get(i).getScore()), 30);
        }
        d.drawText(20, 160 + (i + 5) * 25, "Press SPACE To Return To Main Menu", 15);
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
