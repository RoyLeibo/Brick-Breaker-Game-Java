import java.util.ArrayList;
import java.util.List;

public class Rectangle {
    private double width;
    private double height;
    private Point upperLeft;
    private Point downRight;
    private Point downLeft;
    private Point upperRight;

    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.downRight = new Point(upperLeft.getX() + width, upperLeft.getY() + height);
        this.downLeft = new Point(upperLeft.getX(), upperLeft.getY() + height);
        this.upperRight = new Point(upperLeft.getX() + width, upperLeft.getY());
    }

    // Return a (possibly empty) List of intersection points
    // with the specified line.
    public java.util.List<Point> intersectionPoints(Line line) {
        Line l1 = new Line(upperLeft, upperRight);
        Line l2 = new Line(upperLeft, downLeft);
        Line l3 = new Line(upperRight, downRight);
        Line l4 = new Line(downLeft, downRight);
        List<Point> intersectionPoints = new ArrayList<Point>();
        intersectionPoints.add(l1.intersectionWith(line));
        intersectionPoints.add(l2.intersectionWith(line));
        intersectionPoints.add(l3.intersectionWith(line));
        intersectionPoints.add(l4.intersectionWith(line));
        return line.clearNullIntersections(intersectionPoints);
    }

    // Return the width and height of the rectangle
    public double getWidth() {
        return this.width;
    }

    public double getHeight() {
        return this.height;
    }

    // Returns the upper-left point of the rectangle.
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    public Point getDownRight() {
        return this.downRight;
    }

    public Point getDownLeft() {
        return this.downLeft;
    }

    public Point getUpperRight() {
        return this.upperRight;
    }

    public void setNewLocation(double xChange){
        this.upperLeft = new Point(this.getUpperLeft().getX() + xChange, this.getUpperLeft().getY());
        this.downRight = new Point(upperLeft.getX() + width, upperLeft.getY() + height);
        this.downLeft = new Point(upperLeft.getX(), upperLeft.getY() + height);
        this.upperRight = new Point(upperLeft.getX() + width, upperLeft.getY());
    }
}