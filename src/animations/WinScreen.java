package animations;

import biuoop.DrawSurface;
import gamecontrollers.ScoreTrackingListener;
import interfaces.Animation;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Image;
import java.io.IOException;

/**
 * The type Win screen.
 *
 * @author Roy Leibovitz <royleibo212@gmail.com>
 */
public class WinScreen implements Animation {
    private ScoreTrackingListener scoreTrackingListener;
    private Image img;

    /**
     * Instantiates a new Win screen.
     *
     * @param scoreTrackingListener the score tracking listener
     */
    public WinScreen(ScoreTrackingListener scoreTrackingListener) {
        this.scoreTrackingListener = scoreTrackingListener;
    }

    /**
     * should stop getter.
     *
     * @return is the game stopped
     */

    public boolean shouldStop() {
        return false;
    }

    /**
     * The function draws the win screen and waits to "SPACE" click.
     *
     * @param d the d
     */
    public void doOneFrame(DrawSurface d) {
        if (this.img == null) {
            String background = "image(background_images/you_win.jpg)";
            try {
                this.img = ImageIO.read(ClassLoader.getSystemClassLoader().getResourceAsStream(
                        background.substring(background.indexOf("(") + 1, background.indexOf(")"))));
            } catch (IOException e) {
                System.out.println();
            }
        }
        d.drawImage(0, 0, this.img);
        d.setColor(Color.BLACK);
        d.drawText(550, 550, "Press SPACE To Continue", 20);
    }
}
