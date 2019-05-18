package interfaces;
import biuoop.DrawSurface;

/**
 * The interface Sprite.
 *
 * @author Roy Leibovitz <royleibo212@gmail.com> Roy Leibovitz
 */
public interface Sprite {

    /**
     * Draw on function.
     *
     * @param surface the surface
     */
    void drawOn(DrawSurface surface);

    /**
     * Time passed function.
     */
    void timePassed();
}