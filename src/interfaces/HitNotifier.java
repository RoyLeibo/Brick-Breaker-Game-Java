package interfaces;

/**
 * The interface Hit notifier.
 *
 * @author Roy Leibovitz <royleibo212@gmail.com> Roy Leibovitz
 */
public interface HitNotifier {
    /**
     * Add hit listener.
     *
     * @param hl the hl
     */
    void addHitListener(HitListener hl);

    /**
     * Remove hit listener.
     *
     * @param hl the hl
     */
    void removeHitListener(HitListener hl);
}