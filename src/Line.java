import java.util.ArrayList;
import java.util.List;

/**
 * This class define a Line.
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
        double otherStartX = other.start.getX();
        double otherStartY = other.start.getY();
        double otherEndX = other.end.getX();
        double otherEndY = other.end.getY();
        if ((otherStartX - this.start.getX() <= 10) && (this.start.getX() - otherStartX <= 10)){
            otherStartX = (int) Math.round(otherEndX / 100.0) * 100;
        }
        if ((otherStartY - this.start.getY() <= 10) && (this.start.getY() - otherStartY <= 10)) {
            otherStartY = (int) Math.round(otherStartX / 100.0) * 100;
        }
        if ((otherEndX - this.end.getX() <= 10) && (this.end.getX() - otherEndX <= 10)) {
            otherEndX = (int) Math.round(otherStartX / 100.0) * 100;
        }
        if((otherEndY - this.end.getY() <= 10) && (this.end.getY() - otherEndY <= 10)) {
            otherEndY = (int) Math.round(otherEndY / 100.0) * 100;
        }
        Line roundedLine = new Line(new Point(otherStartX, otherStartY), new Point(otherEndX, otherEndY));
        double l1StartX = this.start.getX();
        double l1StartY = this.start.getY();
        double l1EndX = this.end.getX();
        double l1EndY = this.end.getY();
        double l2StartX = roundedLine.start.getX();
        double l2StartY = roundedLine.start.getY();
        double l2EndX = roundedLine.end.getX();
        double l2EndY = roundedLine.end.getY();
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

//
//        if (other.end().getX() - radius <= frame.getStartPoint().getX()) {
//            //setVelocity((-1) * velocity.getDx(), velocity.getDy());
//            return true; // Collision detected
//        } else if (other.end().getX()+ radius >= this.frame.getWidth() + frame.getStartPoint().getX())  {
//            // if the ball's x is outside the edge of the frame, change it's
//            // x velocity to it's opposite direction
//            //setVelocity((-1) * velocity.getDx(), velocity.getDy());
//            return true; // Collision detected
//        }
//        // if the ball's y is outside the start point of the frame, change it's
//        // y velocity to it's opposite direction
//        if (other.end().getY()- radius <= frame.getStartPoint().getY()) {
//            //setVelocity(velocity.getDx(), (-1) * velocity.getDy());
//            return true; // Collision detected
//        } else if (other.end().getY() + radius >= this.frame.getHeight() + frame.getStartPoint().getX())  {
//            // if the ball's y is outside the edge of the frame, change it's
//            // y velocity to it's opposite direction
//            //setVelocity(velocity.getDx(), (-1) * velocity.getDy());
//            return true; // Collision detected
//        }
//        return false; // No collision
    }

    /**
     * find an Intersection with some other line.
     * if there is not, the function return null.
     *
     * @param other the other line
     * @return the point of the intersection
     */
// Returns the intersection point if the lines intersect,
    // and null otherwise.
    public Point intersectionWith(Line other) {
        // if there is an intersection
        double otherStartX = other.start.getX();
        double otherStartY = other.start.getY();
        double otherEndX = other.end.getX();
        double otherEndY = other.end.getY();
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

    // If this line does not intersect with the rectangle, return null.
    // Otherwise, return the closest intersection point to the
    // start of the line.
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

    public java.util.List<Point> clearNullIntersections(List<Point> list){
        List<Point> newList = new ArrayList<Point>();
        for (Point p:list) {
            if(p != null){
                newList.add(p);
            }
        }
        return newList;
    }

    /**
     * Checks if a point is on the line itself (in the interval).
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

    public void setRadius(double radius){
        this.radius = radius;
    }
}