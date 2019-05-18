package removers;
import others.Counter;
import interfaces.HitListener;
import gamesprites.Block;
import gamesprites.Ball;
import rungame.GameLevel;

/**
 * The type Block remover.
 *
 * @author Roy Leibovitz <royleibo212@gmail.com> Roy Leibovitz
 */
public class BlockRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBlocks;

    /**
     * Instantiates a new Block remover.
     *
     * @param gameLevel          the gameLevel
     * @param removedBlocks the removed blocks
     */
    public BlockRemover(GameLevel gameLevel, Counter removedBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * The function that works when notify and removes the block.
     *
     * @param beingHit the block the got hit
     * @param hitter   the ball which hit it
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (Integer.parseInt(beingHit.getHitsLeft()) == 1) {
            beingHit.removeHitListener(this);
            beingHit.removeFromGame(gameLevel);
            this.remainingBlocks.increase(1);
        } else {
            beingHit.setHitsLeft(String.valueOf(Integer.parseInt(beingHit.getHitsLeft()) - 1));
        }
    }

    /**
     * Get remaining blocks counter.
     *
     * @return the counter
     */
    public Counter getRemainingBlocks() {
        return this.remainingBlocks;
    }
}