import java.util.ArrayList;
import java.util.List;

/**
 * This class represent a point.
 */
public class Point {
    /**
     * The X.
     */
    private double x;
    /**
     * The Y.
     */
    private double y;

    /**
     * Instantiates a new Point.
     *
     * @param x the x
     * @param y the y
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Distance double.
     *
     * @param other the other
     * @return the double
     */
// distance -- return the distance of this point to the other point
    public double distance(Point other) {
        return Math.sqrt(Math.pow(this.x - other.getX(), 2) + Math.pow(this.y - other.getY(), 2));
    }

    /**
     * Equals boolean.
     *
     * @param other the other
     * @return the boolean
     */
// equals -- return true is the points are equal, false otherwise
    public boolean equals(Point other) {
        if ((this.x == other.getX()) && (this.y == other.getY())) {
            return true;
        }
        return false;
    }

    /**
     * Gets x.
     *
     * @return the x
     */
// Return the x and y values of this point
    public double getX() {
        return this.x;
    }

    /**
     * Gets y.
     *
     * @return the y
     */
    public double getY() {
        return this.y;
    }

    public Point findClosestPoint(List<Point> pointsList){
        double min = Double.POSITIVE_INFINITY;
        int index = 0;
        List<Double> distanceList = new ArrayList<Double>();
        for (int i = 0 ; i < pointsList.size(); i++){
            distanceList.add(this.distance(pointsList.get(i)));
        }
        for (int i = 0 ; i < pointsList.size(); i++){
            if (distanceList.get(i) < min ){
                min = distanceList.get(i);
                index = i;
            }
        }
        return pointsList.get(index);
    }

    public void setX(double x){
        this.x = x;
    }

    public void setY(double y){
        this.y = y;
    }
}