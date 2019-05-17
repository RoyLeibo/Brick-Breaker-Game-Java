package rungame;
import java.util.ArrayList;
import java.util.List;
import others.CollisionInfo;
import interfaces.Collidable;
import geometryprimitives.Point;
import geometryprimitives.Line;

/**
 * The type GameEnvironment.
 * This is the GameEnvironment class.
 *
 * @author Roy Leibovitz.
 */
public class GameEnvironment {

    /**
     * The Collidables list.
     */
    private ArrayList<Collidable> collidablesList;

    /**
     * Instantiates a new GameLevel environment.
     */
    public GameEnvironment() {
        this.collidablesList = new ArrayList<Collidable>();
    }

    /**
     * Gets collidables list.
     *
     * @return the collidables list
     */
    public ArrayList<Collidable> getCollidablesList() {
        return this.collidablesList;
    }

    /**
     * Add collidable.
     *
     * @param c the new collidable to add
     */
    public void addCollidable(Collidable c) {
        this.collidablesList.add(c);
    }

    /**
     * Gets closest collision of the line received with all the collidables
     * from the collidableList.
     *
     * @param trajectory the line to get it's closest collision.
     * @return the closest collision
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        List<Point> collisionPointsList = new ArrayList<Point>();
        Point closestCollisionPoint;
        int collisionIndex;
        // for each collidable from the list, the loop adds to the collisionPointsList
        // the closest collision to the start of the list.
        for (int i = 0; i < this.collidablesList.size(); i++) {
            collisionPointsList.add(trajectory.closestIntersectionToStartOfLine(
                    this.collidablesList.get(i).getCollisionRectangle()));
        }
        // when there is no collision, the function returns null.
        // to avoid null exception, this function calls clearNullIntersections function.
        List<Point> collisionPointsListWithoutNull = trajectory.clearNullIntersections(collisionPointsList);
        // if there is no collision
        if (collisionPointsListWithoutNull.size() == 0) {
            return null;
        } else {
            // this function received a list of collision points and finds the closest one
            // between them.
            closestCollisionPoint = trajectory.start().findClosestPoint(collisionPointsListWithoutNull);
            collisionIndex = 0;
            // run throw all the collision points (include the null) to find the index of the
            // collision point found. this index will be the blocks index that the collision occur.
            for (int i = 0; i < collisionPointsList.size(); i++) {
                if ((collisionPointsList.get(i) != null) && closestCollisionPoint.equals(collisionPointsList.get(i))) {
                    collisionIndex = i;
                    break;
                }
            }
        }
        // returns a CollisionInfo object which is composed from the collision point and the
        // block that this point is on.
        return new CollisionInfo(closestCollisionPoint, this.collidablesList.get(collisionIndex));
    }

    /**
     * Remove collidable.
     *
     * @param c the c
     */
    public void removeCollidable(Collidable c) {
        ArrayList<Collidable> newCollidableList = new ArrayList<>();
        for (Collidable cl : collidablesList) {
            if (cl != c) {
                newCollidableList.add(cl);
            }
        }
        this.collidablesList = newCollidableList;
    }
}