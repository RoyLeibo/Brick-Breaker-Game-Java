package animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import interfaces.Animation;

/**
 * The type Pause screen.
 *
 * @author Roy Leibovitz <royleibo212@gmail.com>
 */
public class PauseScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;

    /**
     * Instantiates a new Pause screen.
     *
     * @param k the k
     */
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = true;
    }

    /**
     * The function draws the pause screen and waits to "SPACE" click.
     *
     * @param d the d
     */
    public void doOneFrame(DrawSurface d) {
        // prints paused message
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
        // if the "space" button is pressed, returns to game
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = false;
        }
    }

    /**
     * should stop getter.
     *
     * @return is the game stopped
     */
    public boolean shouldStop() {
        return this.stop;
    }
}