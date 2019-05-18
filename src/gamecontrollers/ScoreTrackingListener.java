package gamecontrollers;
import others.Counter;
import interfaces.HitListener;
import gamesprites.Ball;
import gamesprites.Block;
/**
 * This class is the score listener, which is in charge of maintaining the score when notify.
 *
 * @author Roy Leibovitz <royleibo212@gmail.com>
 */
public class ScoreTrackingListener implements HitListener {
    private Counter scoreCounter;

    /**
     * Instantiates a new Score tracking listener.
     *
     * @param scoreCounter the score counter
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.scoreCounter = scoreCounter;
    }

    /**
     * The function that works when notify and updates the score.
     * @param beingHit the block the got hit
     * @param hitter the ball which hit it
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (Integer.parseInt(beingHit.getHitsLeft()) == 1) {
            this.scoreCounter.increase(10);
        } else {
            this.scoreCounter.increase(5);
        }
    }

    /**
     * Gets score counter.
     *
     * @return the score counter
     */
    public Counter getScoreCounter() {
        return this.scoreCounter;
    }
}
