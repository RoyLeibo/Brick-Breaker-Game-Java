package removers;
import others.Counter;
import interfaces.HitListener;
import gamesprites.Block;
import gamesprites.Ball;
import rungame.GameLevel;

/**
 * This class is the ball remover listener, which is in charge of remove a ball from the gameLevel when notify.
 *
 * @author Roy Leibovitz.
 */
public class BallRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBalls;

    /**
     * Instantiates a new Ball remover.
     *
     * @param gameLevel         the gameLevel
     * @param removedBalls the removed balls
     */
    public BallRemover(GameLevel gameLevel, Counter removedBalls) {
        this.gameLevel = gameLevel;
        this.remainingBalls = removedBalls;
    }

    /**
     * The function that works when notify and deletes the ball received.
     * @param beingHit the block that got hit
     * @param hitter the ball which hit it
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.gameLevel);
        remainingBalls.increase(1);
    }

    /**
     * Gets remaining balls.
     *
     * @return the remaining balls
     */
    public Counter getRemainingBalls() {
        return remainingBalls;
    }
}
