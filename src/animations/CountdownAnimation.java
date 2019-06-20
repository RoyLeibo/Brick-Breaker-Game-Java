package animations;

import biuoop.DrawSurface;
import interfaces.Animation;
import rungame.SpriteCollection;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

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
    private Image img;

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
        String background = "image(resources/background_images/countdown_image.jpg)";
        try {
            this.img = ImageIO.read(new File(background.substring(background.indexOf("(") + 1
                    , background.indexOf(")"))));
        } catch (IOException e) {
            System.out.println();
        }

    }

    /**
     * The function draws the count down.
     *
     * @param d the d
     */
    public void doOneFrame(DrawSurface d) {
        d.drawImage(0, 0, this.img);
        String string = this.countFrom + "...";
        d.setColor(Color.black);
        d.drawText(350, 320, string, 50);
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
