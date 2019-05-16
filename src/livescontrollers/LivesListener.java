package livescontrollers;
import others.Counter;
import interfaces.HitListener;
import gamesprites.Ball;
import gamesprites.Block;

/**
 * This class is the lives listener, which is in charge of maintaining the lives left when notify.
 *
 * @author Roy Leibovitz.
 */
public class LivesListener implements HitListener {
    private Counter livesCounter;

    /**
     * Instantiates a new Lives listener.
     *
     * @param livesCounter the lives counter
     */
    public LivesListener(Counter livesCounter) {
        this.livesCounter = livesCounter;
    }

    /**
     * The function that works when notify and updates the lives left.
     *
     * @param beingHit the block the got hit
     * @param hitter   the ball which hit it
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        livesCounter.decrease(1);
    }
}