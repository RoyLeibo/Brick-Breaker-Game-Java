package animations;

import biuoop.DrawSurface;
import gamecontrollers.ScoreTrackingListener;
import interfaces.Animation;

/**
 * The type Win screen.
 *
 * @author Roy Leibovitz <royleibo212@gmail.com>
 */
public class WinScreen implements Animation {
    private ScoreTrackingListener scoreTrackingListener;

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
        // prints "YOU WIN! YOUR SCORE IS: X" message
        d.drawText(80, 300, "You Win! Your Score Is: " + scoreTrackingListener.getScoreCounter().getValue()
                , 50);
    }
}
