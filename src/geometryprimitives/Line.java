package geometryprimitives;
import java.util.ArrayList;
import java.util.List;

/**
 * This class define a Line.
 *
 * @author Roy Leibovitz
 */
public class Line {
    /**
     * The Start of the line point.
     */
    private Point start;
    /**
     * The End of the line point.
     */
    private Point end;

    /**
     * Part of the calculation of the intersection.
     */
    private double t;
    private double radius;

    /**
     * Instantiates a new Line.
     *
     * @param start the start point     of line
     * @param end   the end point of line
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Instantiates a new Line.
     *
     * @param x1 the x of the start point
     * @param y1 the y of the start point
     * @param x2 the x of the end point
     * @param y2 the y of the end point
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * The function calculates the line length.
     *
     * @return the line's length
     */
    public double length() {
        // a length calculation formula
        return Math.sqrt(Math.pow(this.start.getX() - this.end.getX(), 2) + Math.pow(this.start.getY()
                - this.end.getY(), 2));
    }

    /**
     * the function calculates the middle point.
     *
     * @return the point
     */

    public Point middle() {
        // a middle calculation formula
        return new Point((this.start.getX() + this.end.getX()) / 2, (this.start.getY() + this.end.getY()) / 2);
    }

    /**
     * get Start point.
     *
     * @return the point
     */
    public Point start() {
        return this.start;
    }

    /**
     * get End point.
     *
     * @return the point
     */
    public Point end() {
        return this.end;
    }

    /**
     * The function check if 2 lines intersects.
     *
     * @param other the other line
     * @return true/false if the lines intersects
     */
    public boolean isIntersecting(Line other) {
        // the next 12 lines defines a variables for the calculation
        double l1StartX = this.start.getX();
        double l1StartY = this.start.getY();
        double l1EndX = this.end.getX();
        double l1EndY = this.end.getY();
        double l2StartX = other.start.getX();
        double l2StartY = other.start.getY();
        double l2EndX = other.end.getX();
        double l2EndY = other.end.getY();
        double l1X = l1StartX - l1EndX;
        double l1Y = l1StartY - l1EndY;
        double l2X = l2StartX - l2EndX;
        double l2Y = l2StartY - l2EndY;
        double s;
        // the next 2 lines is a formula to calculate if there is an intersection
        s = (-l1Y * (l1EndX - l2EndX) + l1X * (l1EndY - l2EndY)) / (-l2X * l1Y + l1X * l2Y);
        // the t result is needed to calculate the intersection itself, for doing that I
        // saved it as a member
        this.t = (l2X * (l1EndY - l2EndY) - l2Y * (l1EndX - l2EndX)) / (-l2X * l1Y + l1X * l2Y);

        // if this formula is true, there is an intersection
        if (s >= 0 && s <= 1 && this.t >= 0 && this.t <= 1) {
            return true;
        }
        return false;
    }

    /**
     * find an Intersection with some other line.
     * if there is not, the function return null.
     *
     * @param other the other line
     * @return the point of the intersection
     */
    public Point intersectionWith(Line other) {
        if (isIntersecting(other)) {
            // calculate the intersection point "x" and "y" using this formula
            double x = this.end.getX() + (this.t * (this.start.getX() - this.end.getX()));
            double y = this.end.getY() + (this.t * (this.start.getY() - this.end.getY()));
            return new Point(x, y);
        }
        // if there is not, returns null
        return null;
    }

    /**
     * check if 2 lines are Equal.
     *
     * @param other the other line
     * @return the boolean answer
     */
    public boolean equals(Line other) {
        //if the start point and the end point is equal, return true
        if ((this.start.equals(other.start)) && (this.end.equals(other.end))) {
            return true;
        }
        return false;
    }

    /**
     * Generate random line.
     *
     * @return the line
     */
    static Line generateRandomLine() {
        int x1, y1, x2, y2;
        x1 = (int) (Math.random() * 450 + 1);
        y1 = (int) (Math.random() * 450 + 1);
        x2 = (int) (Math.random() * 450 + 1);
        y2 = (int) (Math.random() * 450 + 1);
        return new Line(x1, y1, x2, y2);
    }

    /**
     * This function calculates the closest intersection to the start of this line
     * with the rectangle recevied.
     * @param rect the collision rectangle
     * @return the closest collision point to start of the line
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> intersectionsList = rect.intersectionPoints(this);
        int index = 0;
        for (int i = 0; i < intersectionsList.size(); i++) {
            if (intersectionsList.get(i) != null) {
                break;
            }
            index++;
        }
        if (index == intersectionsList.size()) {
            return null;
        } else {
            return this.start.findClosestPoint(intersectionsList);
        }
    }

    /**
     * this function clear all the null value from a list of points.
     * @param list a points list
     * @return the same list without null values
     */
    public java.util.List<Point> clearNullIntersections(List<Point> list) {
        List<Point> newList = new ArrayList<Point>();
        for (Point p : list) {
            if (p != null) {
                newList.add(p);
            }
        }
        return newList;
    }

    /**
     * Checks if a point is on the line itself (in the interval).
     *
     * @param p the point we want to check if its on the line
     * @return true if the point is on the line, false otherwise
     */
    public boolean isPointOnInterval(Point p) {
        // p isn't in x range
        if (!((this.start.getX() <= p.getX() && p.getX() <= this.end.getX())
                || (this.end.getX() <= p.getX() && p.getX() <= this.start.getX()))) {
            return false;
        }
        // p isn't in y range
        if (!((this.start.getY() <= p.getY() && p.getY() <= this.end.getY())
                || (this.end.getY() <= p.getY() && p.getY() <= this.start.getY()))) {
            return false;
        }
        // p is on the line itself (in the interval)
        return true;
    }

    /**
     * This function is a radius setter.
     * @param newRadius the new radius
     */
    public void setRadius(double newRadius) {
        this.radius = newRadius;
    }

    /**
     * the function check if there is an x or y value with the values of the line.
     *
     * @param p    the point to check its value of x or y
     * @param xOrY a parameter to know which one to check x or y
     * @return true if the line contain the value or false if its not
     */
    public boolean contains(Point p, String xOrY) {
        //epsilon to check deviation
        double e = 0.0001;
        //x and y values of the edges of the line
        double x1 = this.start.getX();
        double x2 = this.end.getX();
        double y1 = this.start.getY();
        double y2 = this.end.getY();
        //x and y values of the point received
        double xp = p.getX();
        double yp = p.getY();
        //check if the y of the point is within the y values of the line.
        if (xOrY == "y") {
            if ((yp < (y1 + e) && yp > (y2 - e)) || (yp > (y1 - e) && yp < (y2 + e)) || (yp == y1) || (yp == y2)) {
                return true;
            }
        }
        //check if the x of the point is withing the x values of the line.
        if (xOrY == "x") {
            if ((xp < (x1 + e) && xp > (x2 - e)) || (xp > (x1 - e) && xp < (x2 + e)) || (xp == x1) || (xp == x2)) {
                return true;
            }
        }
        //if they are not return false
        return false;
    }
}