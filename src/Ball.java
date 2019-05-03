import biuoop.DrawSurface;

import java.awt.Color;

/**
 * This class define a Ball.
 */
public class Ball implements Sprite {
    private static final int NOCOLLISION = 0;
    private static final int XCOLLISIONRIGHT = 1;
    private static final int XCOLLISIONLEFT = 2;
    private static final int YCOLLISIONUP = 3;
    private static final int YCOLLISIONDOWN = 4;

    /**
     * The point of the ball's center.
     */
    private Point center;
    /**
     * The ball's radius.
     */
    private int radius;
    /**
     * The ball's Color.
     */
    private Color color;
    /**
     * The ball's Velocity.
     */
    private Velocity velocity;
    /**
     * The ball's Frame.
     */
    private Frame frame;

    /**
     * The GameEnvironment.
     */
    private GameEnvironment thisGameEnvironment;

    /**
     * The Collidable.
     */
    private Collidable collisionWith;

    /**
     * Instantiates a new Ball.
     *
     * @param center              is the ball's center
     * @param r                   is the ball's radius
     * @param color               is the ball's color
     * @param frame               is the ball's frame
     * @param thisGameEnvironment this game environment
     */
    public Ball(Point center, int r, Color color, Frame frame, GameEnvironment thisGameEnvironment) {
        this.center = center;
        this.radius = r;
        this.color = color;
        this.frame = frame;
        this.thisGameEnvironment = thisGameEnvironment;
        this.velocity = new Velocity(3, 3);
    }

    /**
     * Instantiates a new Ball.
     *
     * @param x                   is the ball's x center
     * @param y                   is the ball's y center
     * @param r                   is the ball's radius
     * @param color               is the ball's color
     * @param frame               is the ball's frame
     * @param thisGameEnvironment the this game environment
     */
    public Ball(int x, int y, int r, Color color, Frame frame, GameEnvironment thisGameEnvironment) {
        this.center = new Point(x, y);
        this.radius = r;
        this.color = color;
        this.frame = frame;
        this.thisGameEnvironment = thisGameEnvironment;
        this.velocity = new Velocity(4, 4);
    }

    /**
     * Get x int.
     *
     * @return the int
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * Gets y.
     *
     * @return the y
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * Get size int.
     *
     * @return the int
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * Get color color.
     *
     * @return the color
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Draw the ball on the surface.
     *
     * @param surface the surface
     */
    public void drawOn(DrawSurface surface) {
        // sets the drawing color
        surface.setColor(color);
        // draw the ball in the "x" and "y" defined and with the it's radius
        surface.fillCircle((int) this.center.getX(), (int) this.center.getY(), this.radius);
    }

    /**
     * Set velocity.
     *
     * @param v the v
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * Sets velocity.
     *
     * @param dx the dx
     * @param dy the dy
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * Get velocity velocity.
     *
     * @return the velocity
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * Move the ball one step with it's velocity.
     */
    public void moveOneStep() {
//        Line trajectory = new Line(this.center, this.getVelocity().applyToPoint(this.center));
//        CollisionInfo collisionInfo = this.thisGameEnvironment.getClosestCollision(trajectory);
//        if (collisionInfo != null) {
//            this.setVelocity(collisionInfo.collisionObject().hit(collisionInfo.collisionPoint(), this.velocity));
//        }
//        this.center = this.getVelocity().applyToPoint(this.center);
//    }
        // variables with values of the ball parameters.
        double dx = this.velocity.getDx();
        double dy = this.velocity.getDy();
        double x = this.center.getX();
        double y = this.center.getY();
        // the trajectory of the ball is where it will be in the next movement
        Point trajectoryStart = new Point(x, y);
        Point trajectoryEnd = new Point(x + dx, y + dy);
        Point nextCenter;
        Line trajectory = new Line(trajectoryStart, trajectoryEnd);
        // if there is no collision move to the next point in the trajectory
        CollisionInfo colinfo = this.thisGameEnvironment.getClosestCollision(trajectory);
        if (colinfo == null) {
            this.center = this.velocity.applyToPoint(this.center);
            // if there is a collision get the collisionPoint the the collidable object
        } else {
            Collidable col = this.thisGameEnvironment.getClosestCollision(trajectory).collisionObject();
            Point colPoint = this.thisGameEnvironment.getClosestCollision(trajectory).collisionPoint();
            //deviation is to get the ball almost to the collision point but stop be4 it get there.
            double deviationY = (Math.abs(colPoint.getY() - this.center.getY()) / 4);
            double deviationX = (Math.abs(colPoint.getX() - this.center.getX()) / 4);
            /*
             * check the collision point values, and make the new center
             * near the collision point.
             */
            if (colPoint.getY() > this.center.getY()) {
                y = colPoint.getY() - deviationY;
            } else {
                y = colPoint.getY() + 3 * deviationY;
            }
            if (colPoint.getX() > this.center.getX()) {
                x = colPoint.getX() - deviationX;
            } else {
                x = colPoint.getX() + 3 * deviationX;
            }
            this.center = new Point(x, y);
            nextCenter = new Point(x + (dx), y + (dy));
            //set new velocity with the collidable hit function.
            this.setVelocity(col.hit(this, colPoint, this.velocity));
            //check if the ball is inside the rectangle and if it is make him go out
            if (col.getCollisionRectangle().insideOf(this.center)) {
                this.center = this.velocity.applyToPoint(this.center);
                //if the ball is going to enter a rectangle and the object is the paddle let him in and out.
            } else if (col.getCollisionRectangle().insideOf(nextCenter)) {
                if (col instanceof Paddle) {
                    this.center = this.velocity.applyToPoint(this.center);
                }
            }
        }
    }

    /**
     * Gets frame.
     *
     * @return the frame
     */
    public Frame getFrame() {
        return this.frame;
    }

    /**
     * The timePassed method.
     */
    public void timePassed() {
        moveOneStep();
    }

    public void removeFromGame(Game g){
        g.removeBall(this);
    }
}