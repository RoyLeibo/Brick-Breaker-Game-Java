package geometryprimitives;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represent a point.
 *
 * @author Roy Leibovitz
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
    public double distance(Point other) {
        return Math.sqrt(Math.pow(this.x - other.getX(), 2) + Math.pow(this.y - other.getY(), 2));
    }

    /**
     * Equals boolean.
     *
     * @param other the other
     * @return the boolean
     */
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

    /**
     * This function calculate which point from the pointList is the closest to this point.
     *
     * @param pointsList list of points.
     * @return the closest point from the list
     */
    public Point findClosestPoint(List<Point> pointsList) {
        double min = Double.POSITIVE_INFINITY;
        int index = 0;
        List<Double> distanceList = new ArrayList<Double>();
        for (int i = 0; i < pointsList.size(); i++) {
            distanceList.add(this.distance(pointsList.get(i)));
        }
        for (int i = 0; i < pointsList.size(); i++) {
            if (distanceList.get(i) < min) {
                min = distanceList.get(i);
                index = i;
            }
        }
        return pointsList.get(index);
    }

    /**
     * x value setter.
     *
     * @param newX the new x value
     */

    public void setX(double newX) {
        this.x = newX;
    }

    /**
     * y value setter.
     *
     * @param newY the new x value
     */
    public void setY(double newY) {
        this.y = newY;
    }
}