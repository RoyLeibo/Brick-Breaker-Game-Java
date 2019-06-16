package others;
import geometryprimitives.Point;
/**
 * The type Velocity.
 *
 * @author Roy Leibovitz <royleibo212@gmail.com> Roy Leibovitz
 */
public class Velocity {
    /**
     * The speed in x.
     */
    private double dx;
    /**
     * The speed in y.
     */
    private double dy;

    /**
     * Instantiates a new Velocity.
     *
     * @param dx the dx
     * @param dy the dy
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Apply to point it's new location after using it's speed.
     *
     * @param p the point
     * @return the new point after the location has been changed
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }

    /**
     * change the angle and speed to a 2D velocity.
     *
     * @param angle the angle
     * @param speed the speed
     * @return the velocity
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
//        double dx = speed * Math.sin(Math.toRadians(angle));
//        double dy = speed * Math.cos(Math.toRadians(angle)) * -1;
//        return new Velocity(dx, dy);
        double dx = speed * Math.cos(Math.PI * ((angle - 90) / 180));
        double dy = speed * Math.sin(Math.PI * ((angle - 90) / 180));
        return new Velocity(dx, dy);
    }

    /**
     * a getter to the dx.
     *
     * @return the dx
     */

    public double getDx() {
        return dx;
    }

    /**
     * a getter to the dy.
     *
     * @return the dy
     */
    public double getDy() {
        return dy;
    }

    /**
     * Sets velocity.
     *
     * @param dx1 the dx
     * @param dy1 the dy
     */
    public void setVelocity(double dx1, double dy1) {
        this.dx = dx1;
        this.dy = dy1;
    }
}