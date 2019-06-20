package animations;

import biuoop.DrawSurface;
import gamecontrollers.HighScoresTable;
import interfaces.Animation;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Image;
import java.io.IOException;

/**
 * The type High scores animation.
 *
 * @author Roy Leibovitz <royleibo212@gmail.com>
 */
public class HighScoresAnimation implements Animation {
    private HighScoresTable hst;
    private boolean isRunning;
    private Image img;

    /**
     * Instantiates a new High scores animation.
     *
     * @param hst the hst
     */
    public HighScoresAnimation(HighScoresTable hst) {
        this.isRunning = true;
        this.hst = hst;
        this.img = null;
    }

    /**
     * Do one frame.
     *
     * @param d the d
     */
    public void doOneFrame(DrawSurface d) {
        if (this.img == null) {
            String background = "image(background_images/high_score_background.jpg)";
            try {
                this.img = ImageIO.read(ClassLoader.getSystemClassLoader().getResourceAsStream(
                        background.substring(background.indexOf("(") + 1, background.indexOf(")"))));
            } catch (IOException e) {
                System.out.println();
            }
        }
        d.drawImage(0, 0, this.img);
        d.setColor(Color.cyan);
        d.drawText(20, 100, "High Scores Table:", 40);
        int i;
        for (i = 0; i < this.hst.getHighScores().size(); i++) {
            d.drawText(50, 120 + (i + 1) * 30, (i + 1) + ".  " + this.hst.getHighScores().get(i).getName()
                    + ":   ", 30);
            d.drawText(200, 120 + (i + 1) * 30, String.valueOf(this.hst.getHighScores().get(i).getScore()), 30);
        }
        d.drawText(20, 500, "Press SPACE To Return To Main Menu", 15);
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
