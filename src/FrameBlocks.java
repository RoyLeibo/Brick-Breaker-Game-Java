import biuoop.DrawSurface;

import java.awt.*;

public class FrameBlocks implements Collidable, Sprite {
    private Rectangle frameBlockRectangle;

    public FrameBlocks(Rectangle frameBlockRectangle){
        this.frameBlockRectangle = frameBlockRectangle;
    }
    public Rectangle getCollisionRectangle(){
        return this.frameBlockRectangle;
    }

    public void timePassed() {

    }

    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        Line l1 = new Line(this.frameBlockRectangle.getUpperLeft(), this.frameBlockRectangle.getUpperRight());
        Line l2 = new Line(this.frameBlockRectangle.getDownLeft(), this.frameBlockRectangle.getDownRight());
        Line l3 = new Line(this.frameBlockRectangle.getUpperLeft(), this.frameBlockRectangle.getDownLeft());
        Line l4 = new Line(this.frameBlockRectangle.getUpperRight(), this.frameBlockRectangle.getDownRight());
        if ((l1.isPointOnInterval(collisionPoint)) || (l2.isPointOnInterval(collisionPoint))) {
            return new Velocity(currentVelocity.getDx(), (-1) * currentVelocity.getDy());
        } else if ((l3.isPointOnInterval(collisionPoint)) || (l4.isPointOnInterval(collisionPoint))) {
            return new Velocity((-1) * currentVelocity.getDx(), currentVelocity.getDy());
        } else {
            return currentVelocity;
        }
    }

    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.BLACK);
        surface.drawRectangle((int) frameBlockRectangle.getUpperLeft().getX(), (int) frameBlockRectangle.getUpperLeft().getY(),
                (int) frameBlockRectangle.getWidth(), (int) frameBlockRectangle.getHeight());
        // draw the ball in the "x" and "y" defined and with the it's radius
        surface.setColor(Color.GRAY);
        surface.fillRectangle((int) frameBlockRectangle.getUpperLeft().getX(), (int) frameBlockRectangle.getUpperLeft().getY(),
                (int) frameBlockRectangle.getWidth(), (int) frameBlockRectangle.getHeight());
        surface.setColor(Color.BLACK);
        surface.drawText((int) frameBlockRectangle.getUpperLeft().getX() + (int) (0.5 * this.frameBlockRectangle.getWidth()),
                (int) frameBlockRectangle.getUpperLeft().getY() + (int) (0.5 * this.frameBlockRectangle.getHeight()),
                "X", 20);
    }
}
