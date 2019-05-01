/**
 * The interface Collidable.
 *
 * @author Roy Leibovitz.
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