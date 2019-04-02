import biuoop.DrawSurface;

import java.awt.Color;


/**
 * The type Block.
 * This is the Block class which implements the Collidable and Sprite interfaces and defines a Block.
 *
 * @author Roy Leibovitz.
 */
public class Block implements Collidable, Sprite {
    private Rectangle blockRectangle;
    private Color color;
    private String hitsLeft;
    private boolean isBlockAlive = true;

    /**
     * Instantiates a new Block.
     *
     * @param blockRectangle the block rectangle
     */
    Block(Rectangle blockRectangle) {
        this.blockRectangle = blockRectangle;
        this.hitsLeft = "5";
        this.color = Color.CYAN;
    }

    /**
     * Instantiates a new Block.
     *
     * @param blockRectangle the block rectangle
     * @param color          the color
     * @param hitsLeft       the hits left
     */
    Block(Rectangle blockRectangle, Color color, String hitsLeft) {
        this.blockRectangle = blockRectangle;
        this.color = color;
        this.hitsLeft = hitsLeft;
    }

    /**
     * Getter for the Block's rectangle.
     *
     * @return blockRectangle
     */
    public Rectangle getCollisionRectangle() {
        return this.blockRectangle;
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
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
//        if (!this.isBlockAlive) {
//            return currentVelocity;
//        }
        // the next 4 line creates each line of the block's rectangle.
        Line l1 = new Line(this.blockRectangle.getUpperLeft(), this.blockRectangle.getUpperRight());
        Line l2 = new Line(this.blockRectangle.getDownLeft(), this.blockRectangle.getDownRight());
        Line l3 = new Line(this.blockRectangle.getUpperLeft(), this.blockRectangle.getDownLeft());
        Line l4 = new Line(this.blockRectangle.getUpperRight(), this.blockRectangle.getDownRight());
        // checks if the collision is in the top/bottom line
        if ((l1.isPointOnInterval(collisionPoint)) || (l2.isPointOnInterval(collisionPoint))) {
            // if there are more then 1 hit left
            if ((hitsLeft != "X") && (Integer.parseInt(hitsLeft) > 1)) {
                // decrease the count by 1
                this.hitsLeft = String.valueOf((Integer.parseInt(hitsLeft) - 1));
            } else { // if it doesn't, change the hitsLeft to X
                this.hitsLeft = "X";
                this.isBlockAlive = false;
            }
            // change the y's velocity into it's negative
            return new Velocity(currentVelocity.getDx(), (-1) * currentVelocity.getDy());
        } else if ((l3.isPointOnInterval(collisionPoint)) || (l4.isPointOnInterval(collisionPoint))) {
            // checks if the collision is in the right/left line
            // if there are more then 1 hit left
            if ((hitsLeft != "X") && (Integer.parseInt(hitsLeft) > 1)) {
                // decrease the count by 1
                this.hitsLeft = String.valueOf((Integer.parseInt(hitsLeft) - 1));
            } else { // if it doesn't, change the hitsLeft to X
                this.hitsLeft = "X";
                this.isBlockAlive = false;
            }            // change the x's velocity into it's negative
            return new Velocity((-1) * currentVelocity.getDx(), currentVelocity.getDy());
            // if it didn't hit any of the 4 lines (which never happens), return the same velocity
        }
        return currentVelocity;
    }

    /**
     * The drawOne method is used to draw this block to screen.
     *
     * @param surface the DrawFace surface of the program.
     */
    public void drawOn(DrawSurface surface) {
        // if the block is still alive, sets the drawing color and shapes
//        if (this.isBlockAlive) {
        // draw a frame in color Black.
        surface.setColor(Color.BLACK);
        surface.drawRectangle((int) blockRectangle.getUpperLeft().getX(), (int) blockRectangle.getUpperLeft().getY(),
                (int) blockRectangle.getWidth(), (int) blockRectangle.getHeight());
        // draw the block it self using the color received by input (or the default)
        surface.setColor(this.color);
        surface.fillRectangle((int) blockRectangle.getUpperLeft().getX(), (int) blockRectangle.getUpperLeft().getY(),
                (int) blockRectangle.getWidth(), (int) blockRectangle.getHeight());
        // draw the number of hitsLeft in black
        surface.setColor(Color.BLACK);
        surface.drawText((int) blockRectangle.getUpperLeft().getX() + (int) (0.5 * this.blockRectangle.getWidth()),
                (int) blockRectangle.getUpperLeft().getY() + (int) (0.5 * this.blockRectangle.getHeight()),
                hitsLeft, 20);
//        }
    }

    /**
     * The timePassed method.
     */
    public void timePassed() {

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
     * @param color1 the color.
     */
    public void setColor(Color color1) {
        this.color = color1;
    }

    /**
     * Sets hits left.
     *
     * @param hitsLeft1 the hits left.
     */
    public void setHitsLeft(String hitsLeft1) {
        this.hitsLeft = hitsLeft1;
    }

    /**
     * Gets hits left.
     *
     * @return the hits left
     */
    public String getHitsLeft() {
        return this.hitsLeft;
    }
}
