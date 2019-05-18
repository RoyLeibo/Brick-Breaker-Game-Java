package interfaces;

import biuoop.DrawSurface;

/**
 * The interface Animation.
 *
 * @author Roy Leibovitz <royleibo212@gmail.com>
 */
public interface Animation {
    /**
     * Do one frame.
     *
     * @param d the d
     */
    void doOneFrame(DrawSurface d);

    /**
     * Should stop boolean.
     *
     * @return the boolean
     */
    boolean shouldStop();
}
