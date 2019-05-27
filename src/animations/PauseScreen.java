package animations;

import biuoop.DrawSurface;
import interfaces.Animation;

/**
 * The type Pause screen.
 *
 * @author Roy Leibovitz <royleibo212@gmail.com>
 */
public class PauseScreen implements Animation {
    /**
     * The function draws the pause screen and waits to "SPACE" click.
     *
     * @param d the d
     */
    public void doOneFrame(DrawSurface d) {
        // prints paused message
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
    }

    /**
     * should stop getter.
     *
     * @return is the game stopped
     */
    public boolean shouldStop() {
        return false;
    }
}