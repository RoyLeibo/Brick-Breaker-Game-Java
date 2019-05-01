import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The type FrameBlock.
 * This is the FrameBlock class which implements the Collidable and Sprite interfaces
 * and defines a FrameBlock.
 *
 * @author Roy Leibovitz.
 */
public class FrameBlocks implements Collidable, Sprite {
    private Rectangle frameBlockRectangle;

    /**
     * Instantiates a new Frame blocks.
     *
     * @param frameBlockRectangle the frame block rectangle
     */
    public FrameBlocks(Rectangle frameBlockRectangle) {
        this.frameBlockRectangle = frameBlockRectangle;
    }

    /**
     * Getter for the FrameBlock's rectangle.
     *
     * @return frameBlockRectangle.
     */
    public Rectangle getCollisionRectangle() {
        return this.frameBlockRectangle;
    }

    /**
     * The timePassed method.
     */
    public void timePassed() {

    }

    /**
     * This method is called after a collision has detected and used to determine
     * from which side of the block's rectangle the ball hit using the collisionPoint.
     * The method will create a new velocity depend of the hit location.
     *
     * @param collisionPoint  the collision point
     * @param currentVelocity the current velocity
     * @return new velocity after change.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        // the next 4 line creates each line of the block's rectangle.
        Line l1 = new Line(this.frameBlockRectangle.getUpperLeft(), this.frameBlockRectangle.getUpperRight());
        Line l2 = new Line(this.frameBlockRectangle.getDownLeft(), this.frameBlockRectangle.getDownRight());
        Line l3 = new Line(this.frameBlockRectangle.getUpperLeft(), this.frameBlockRectangle.getDownLeft());
        Line l4 = new Line(this.frameBlockRectangle.getUpperRight(), this.frameBlockRectangle.getDownRight());
        // checks if the collision is in the top/bottom line
        if ((l1.isPointOnInterval(collisionPoint)) || (l2.isPointOnInterval(collisionPoint))) {
            // change the y's velocity into it's negative
            return new Velocity(currentVelocity.getDx(), (-1) * currentVelocity.getDy());
        } else if ((l3.isPointOnInterval(collisionPoint)) || (l4.isPointOnInterval(collisionPoint))) {
            // checks if the collision is in the right/left line
            // change the x's velocity into it's negative
            return new Velocity((-1) * currentVelocity.getDx(), currentVelocity.getDy());
        } else {
            // if it didn't hit any of the 4 lines (which never happens), return the same velocity
            return currentVelocity;
        }
    }

    /**
     * The drawOne method is used to draw this block to screen.
     *
     * @param surface the DrawFace surface of the program.
     */
    public void drawOn(DrawSurface surface) {
        // draw a frame in color Black.
        surface.setColor(Color.BLACK);
        surface.drawRectangle((int) frameBlockRectangle.getUpperLeft().getX(),
                (int) frameBlockRectangle.getUpperLeft().getY(),
                (int) frameBlockRectangle.getWidth(), (int) frameBlockRectangle.getHeight());
        // draw the block it's self using the color received by input (or the default)
        surface.setColor(Color.GRAY);
        surface.fillRectangle((int) frameBlockRectangle.getUpperLeft().getX(),
                (int) frameBlockRectangle.getUpperLeft().getY(), (int) frameBlockRectangle.getWidth(),
                (int) frameBlockRectangle.getHeight());
        // draw the number of hitsLeft in black
        surface.setColor(Color.WHITE);
        surface.drawText((int) frameBlockRectangle.getUpperLeft().getX()
                        + (int) (0.5 * this.frameBlockRectangle.getWidth()),
                (int) frameBlockRectangle.getUpperLeft().getY() + (int) (0.5 * this.frameBlockRectangle.getHeight()),
                "X", 20);
    }
}