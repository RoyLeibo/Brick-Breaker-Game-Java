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
     * @return the velocity
     */
    Velocity hit(Point collisionPoint, Velocity currentVelocity);
}