/**
 * The type Collision info.
 */
public class CollisionInfo {

    /**
     * The Collision point.
     */
    private Point collisionPoint;
    /**
     * The Collidable object.
     */
    private Collidable collidableObject;

    /**
     * Instantiates a new Collision info.
     *
     * @param collisionPoint   the collision point
     * @param collidableObject the collidable object
     */
    CollisionInfo(Point collisionPoint, Collidable collidableObject) {
        this.collisionPoint = collisionPoint;
        this.collidableObject = collidableObject;
    }

    /**
     * Collision point point.
     *
     * @return the point
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * Collision object collidable.
     *
     * @return the collidable
     */
    public Collidable collisionObject() {
        return this.collidableObject;
    }
}