package interfaces;
import gamesprites.Ball;
import gamesprites.Block;
/**
 * The interface Hit listener.
 *
 * @author Roy Leibovitz <royleibo212@gmail.com> Roy Leibovitz
 */
public interface HitListener {
    /**
     * Hit event.
     *
     * @param beingHit the being hit
     * @param hitter   the hitter
     */
    void hitEvent(Block beingHit, Ball hitter);
}