import biuoop.DrawSurface;
import biuoop.GUI;

import java.awt.Color;

/**
 * The type Paddle.
 * This is the Paddle class which implements the Collidable and Sprite interfaces and defines a Paddle.
 *
 * @author Roy Leibovitz.
 */
public class Paddle implements Collidable, Sprite {

    private static final int REGION1 = 0;
    private static final int REGION2 = 1;
    private static final int REGION3 = 2;
    private static final int REGION4 = 3;
    private static final int REGION5 = 4;

    private Rectangle paddleRectangle;
    private GUI gui;
    private Color color;

    /**
     * Instantiates a new Paddle.
     *
     * @param paddleRectangle the paddle rectangle
     * @param gui             the gui
     */
    Paddle(Rectangle paddleRectangle, GUI gui) {
        this.paddleRectangle = paddleRectangle;
        this.gui = gui;
    }

    /**
     * Instantiates a new Paddle.
     *
     * @param blockRectangle the block rectangle
     * @param gui            the gui
     * @param color          the color
     */
    Paddle(Rectangle blockRectangle, GUI gui, Color color) {
        this.paddleRectangle = blockRectangle;
        this.color = color;
        this.gui = gui;
    }

    /**
     * Getter for the Paddle's rectangle.
     *
     * @return paddleRectangle
     */
    public Rectangle getCollisionRectangle() {
        return this.paddleRectangle;
    }

    /**
     * This method is called after a collision has detected and used to determine
     * from which side of the block's rectangle the ball hit using the collisionPoint.
     * The method will create a new velocity depend of the hit location on the paddle.
     *
     * @param collisionPoint  the collision point
     * @param currentVelocity the current velocity
     * @return new velocity after change.
     */
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        // the next line creates the top line of the paddle.
        Line up = new Line(this.paddleRectangle.getUpperLeft(), this.paddleRectangle.getUpperRight());
        Line left = new Line(this.paddleRectangle.getUpperLeft(), this.paddleRectangle.getDownLeft());
        Line right = new Line(this.paddleRectangle.getUpperRight(), this.paddleRectangle.getDownRight());
        // calling a function to calculate the collision spot one the paddle
        int collisionSpot = checkCollisionSpot(up, collisionPoint);
        double speed = Math.sqrt(Math.pow(currentVelocity.getDx(), 2) + Math.pow(currentVelocity.getDy(), 2));
        // for each collision region create a proper velocity as required.
        if (up.isPointOnInterval(collisionPoint)) {
            switch (collisionSpot) {
                case REGION1:
                    return Velocity.fromAngleAndSpeed(300, speed);
                case REGION2:
                    return Velocity.fromAngleAndSpeed(330, speed);
                case REGION3:
                    return new Velocity(currentVelocity.getDx(), (-1) * currentVelocity.getDx());
                case REGION4:
                    return Velocity.fromAngleAndSpeed(30, speed);
                case REGION5:
                    return Velocity.fromAngleAndSpeed(60, speed);
                default:
                    return new Velocity(currentVelocity.getDx(), currentVelocity.getDy());
            }
        } else if ((left.isPointOnInterval(collisionPoint)) || (right.isPointOnInterval(collisionPoint))) {
            return new Velocity((-1) * currentVelocity.getDx(), currentVelocity.getDy());
        }
        return currentVelocity;
    }

    /**
     * This function divide the paddleLine to 5 regions and checks in which region
     * the collisionPoint is at.
     *
     * @param paddleLine     the collision line.
     * @param collisionPoint the collision point.
     * @return the collision region from 0-4
     */

    private int checkCollisionSpot(Line paddleLine, Point collisionPoint) {
        double lineStartX = paddleLine.start().getX();
        double lineDistance = paddleLine.start().distance(paddleLine.end());
        double lineLengthRegions = lineDistance / 5;
        double collisionPointX = collisionPoint.getX();
        // loop that run 5 times, one time for each region
        for (int i = 0; i < 5; i++) {
            // checks if the x value of the collision point is between in the i+1 region.
            if ((collisionPointX >= lineStartX + lineLengthRegions * i)
                    && (collisionPointX <= lineStartX + lineLengthRegions * (i + 1))) {
                // if it does, return i
                return i;
            }
        }
        return 2;
    }

    /**
     * The drawOne method is used to draw this block to screen.
     *
     * @param surface the DrawFace surface of the program.
     */
    public void drawOn(DrawSurface surface) {
        // draw the block it self using the color received by input (or the default)
        surface.setColor(this.color);
        // draw the paddle
        surface.fillRectangle((int) paddleRectangle.getUpperLeft().getX(), (int) paddleRectangle.getUpperLeft().getY(),
                (int) paddleRectangle.getWidth(), (int) paddleRectangle.getHeight());
    }

    /**
     * The timePasses function calls the moveLeft and moveRight function to check
     * if there was a press on the left/right keyboard button.
     */
    public void timePassed() {
        moveLeft();
        moveRight();
    }

    /**
     * Move left function.
     */
    public void moveLeft() {
        biuoop.KeyboardSensor keyboard = this.gui.getKeyboardSensor();
        // if the left key is pressed
        if (keyboard.isPressed(keyboard.LEFT_KEY)) {
            // set a new location of the paddle when it's x value is -10 than it was.
            if (this.paddleRectangle.getUpperLeft().getX() - 10 >= 30) {
                this.paddleRectangle.setNewLocation(-10);
            }
        }
    }

    /**
     * Move right function.
     */
    public void moveRight() {
        biuoop.KeyboardSensor keyboard = this.gui.getKeyboardSensor();
        // if the right key is pressed
        if (keyboard.isPressed(keyboard.RIGHT_KEY)) {
            // set a new location of the paddle when it's x value is +10 than it was.
            DrawSurface d = this.gui.getDrawSurface();
            double width = d.getWidth() - 30;
            if (this.paddleRectangle.getUpperRight().getX() + 10 <= width) {
                this.paddleRectangle.setNewLocation(10);
            }
        }
    }

    /**
     * Gets color.
     *
     * @return the color
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Sets color.
     *
     * @param color1 the color
     */
    public void setColor(Color color1) {
        this.color = color1;
    }
}