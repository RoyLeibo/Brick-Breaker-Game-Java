import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
     * Instantiates a new Game environment.
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
            collisionPointsList.add(trajectory.closestIntersectionToStartOfLine(this.collidablesList.get(i).getCollisionRectangle()));
        }
        List<Point> collisionPointsListWithoutNull = trajectory.clearNullIntersections(collisionPointsList);
        if (collisionPointsListWithoutNull.size() == 0) {
            return null;
        } else {
            closestCollisionPoint = trajectory.start().findClosestPoint(collisionPointsListWithoutNull);
            collisionIndex = 0;
            for (int i = 0; i < collisionPointsList.size(); i++) {
                if ((collisionPointsList.get(i) != null) && closestCollisionPoint.equals(collisionPointsList.get(i))) {
                    collisionIndex = i;
                    break;
                }
            }
        }
        return new CollisionInfo(closestCollisionPoint, this.collidablesList.get(collisionIndex));
    }
}