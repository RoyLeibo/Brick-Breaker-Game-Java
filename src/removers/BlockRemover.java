package removers;
import others.Counter;
import interfaces.HitListener;
import gamesprites.Block;
import gamesprites.Ball;
import rungame.Game;

/**
 * The type Block remover.
 *
 * @author Roy Leibovitz
 */
public class BlockRemover implements HitListener {
    private Game game;
    private Counter remainingBlocks;

    /**
     * Instantiates a new Block remover.
     *
     * @param game          the game
     * @param removedBlocks the removed blocks
     */
    public BlockRemover(Game game, Counter removedBlocks) {
        this.game = game;
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
            beingHit.removeFromGame(game);
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