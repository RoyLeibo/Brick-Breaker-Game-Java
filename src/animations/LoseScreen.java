package animations;

import biuoop.DrawSurface;
import interfaces.Animation;

/**
 * The type Lose screen.
 *
 * @author Roy Leibovitz <royleibo212@gmail.com>
 */
public class LoseScreen implements Animation {

    /**
     * should stop function.
     * @return false
     */
    public boolean shouldStop() {
        return false;
    }

    /**
     * do one frame function.
     * @param d the d
     */
    public void doOneFrame(DrawSurface d) {
        // prints "GAME OVER!" message
        d.drawText(100, 300, "GAME OVER!", 100);
    }
}
