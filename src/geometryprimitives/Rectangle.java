package geometryprimitives;
import java.util.ArrayList;
import java.util.List;

/**
 * The class Rectangle.
 *
 * @author Roy Leibovitz.
 */
public class Rectangle {
    private double width;
    private double height;
    private Point upperLeft;
    private Point downRight;
    private Point downLeft;
    private Point upperRight;

    /**
     * Instantiates a new Rectangle.
     *
     * @param upperLeft the upper left
     * @param width     the width
     * @param height    the height
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        // create the missing 3 point using the parameters received.
        this.downRight = new Point(upperLeft.getX() + width, upperLeft.getY() + height);
        this.downLeft = new Point(upperLeft.getX(), upperLeft.getY() + height);
        this.upperRight = new Point(upperLeft.getX() + width, upperLeft.getY());
    }

    /**
     * This function creates an intersection points list.
     *
     * @param line the line that intersects with the rectangle
     * @return list of intersections.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        // creates the 4 lines of the rectangle
        Line l1 = new Line(upperLeft, upperRight);
        Line l2 = new Line(upperLeft, downLeft);
        Line l3 = new Line(upperRight, downRight);
        Line l4 = new Line(downLeft, downRight);
        List<Point> intersectionPoints = new ArrayList<Point>();
        // the next 4 lines call the intersectionWith function with each line of the rectangle
        // and the line received by input.
        intersectionPoints.add(l1.intersectionWith(line));
        intersectionPoints.add(l2.intersectionWith(line));
        intersectionPoints.add(l3.intersectionWith(line));
        intersectionPoints.add(l4.intersectionWith(line));
        // return the collision points list without null
        return line.clearNullIntersections(intersectionPoints);
    }

    /**
     * Gets width.
     *
     * @return the width
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Gets height.
     *
     * @return the height
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Gets upper left.
     *
     * @return the upper left
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * Gets down right.
     *
     * @return the down right
     */
    public Point getDownRight() {
        return this.downRight;
    }

    /**
     * Gets down left.
     *
     * @return the down left
     */
    public Point getDownLeft() {
        return this.downLeft;
    }

    /**
     * Gets upper right.
     *
     * @return the upper right
     */
    public Point getUpperRight() {
        return this.upperRight;
    }

    /**
     * Sets new location depends on the new x value.
     *
     * @param xChange the x change
     */
    public void setNewLocation(double xChange) {
        this.upperLeft = new Point(this.getUpperLeft().getX() + xChange, this.getUpperLeft().getY());
        this.downRight = new Point(upperLeft.getX() + width, upperLeft.getY() + height);
        this.downLeft = new Point(upperLeft.getX(), upperLeft.getY() + height);
        this.upperRight = new Point(upperLeft.getX() + width, upperLeft.getY());
    }

    /**
     * check if a point is inside of the rectangle.
     *
     * @param p the point to check if its inside
     * @return the boolean answer
     */
    public boolean insideOf(Point p) {
        double e = 0.0001;
        //check if it contains the x and y values that are withing the rect.
        Line bottomSide = new Line(downLeft, downRight);
        Line leftSide = new Line(upperLeft, downLeft);
        if ((bottomSide.contains(p, "x")) && (leftSide.contains(p, "y"))) {
            return true;
        }
        return false;
    }
}