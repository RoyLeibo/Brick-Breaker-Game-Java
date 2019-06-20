package animations;

import biuoop.DrawSurface;
import interfaces.Animation;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Image;
import java.io.IOException;

/**
 * The type Lose screen.
 *
 * @author Roy Leibovitz <royleibo212@gmail.com>
 */
public class LoseScreen implements Animation {
    private Image img;

    /**
     * Instantiates a new Lose screen.
     */
    public LoseScreen() {
        this.img = null;
    }

    /**
     * should stop function.
     *
     * @return false
     */
    public boolean shouldStop() {
        return false;
    }

    /**
     * do one frame function.
     *
     * @param d the d
     */
    public void doOneFrame(DrawSurface d) {
        if (this.img == null) {
            String background = "image(background_images/game_over.jpg)";
            try {
                this.img = ImageIO.read(ClassLoader.getSystemClassLoader().getResourceAsStream(
                        background.substring(background.indexOf("(") + 1, background.indexOf(")"))));
            } catch (IOException e) {
                System.out.println();
            }
        }
        d.drawImage(-130, 0, this.img);
        d.setColor(Color.WHITE);
        d.drawText(70, 500, "Press SPACE To Continue", 15);
    }
}
