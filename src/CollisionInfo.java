public class CollisionInfo {

    Point collisionPoint;
    Collidable collidableObject;

    CollisionInfo(Point collisionPoint, Collidable collidableObject){
        this.collisionPoint = collisionPoint;
        this.collidableObject = collidableObject;
    }

    // the point at which the collision occurs.
    public Point collisionPoint(){
        return this.collisionPoint;
    }

    // the collidable object involved in the collision.
    public Collidable collisionObject(){
        return this.collidableObject;
    }
}