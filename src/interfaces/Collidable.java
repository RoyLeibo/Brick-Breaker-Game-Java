package interfaces;
import others.Velocity;
import gamesprites.Ball;
import geometryprimitives.Rectangle;
import geometryprimitives.Point;

/**
 * The interface Collidable.
 *
 * @author Roy Leibovitz <royleibo212@gmail.com>
 */
public interface Collidable {

    /**
     * Gets collision rectangle.
     *
     * @return the collision rectangle
     */
    Rectangle getCollisionRectangle();

    /**
     * Hit function. this function notifies the object that a collision occur.
     *
     * @param collisionPoint  the collision point
     * @param currentVelocity the current velocity
     * @param hitter the block hitter
     * @return the velocity
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}