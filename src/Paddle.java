import biuoop.DrawSurface;
import biuoop.GUI;

import java.awt.*;


public class Paddle implements Collidable, Sprite {
    private Rectangle paddleRectangle;
    private biuoop.KeyboardSensor keyboard;
    private Color color;

    Paddle(Rectangle paddleRectangle, GUI gui) {
        this.paddleRectangle = paddleRectangle;
        this.keyboard = gui.getKeyboardSensor();
    }


    Paddle(Rectangle blockRectangle, GUI gui, Color color) {
        this.paddleRectangle = blockRectangle;
        this.color = color;
        this.keyboard = gui.getKeyboardSensor();
    }

    public Rectangle getCollisionRectangle() {
        return this.paddleRectangle;
    }

    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        Line l1 = new Line(this.paddleRectangle.getUpperLeft(), this.paddleRectangle.getUpperRight());
//        Line l2 = new Line(this.paddleRectangle.getDownLeft(), this.paddleRectangle.getDownRight());
//        Line l3 = new Line(this.paddleRectangle.getUpperLeft(), this.paddleRectangle.getDownLeft());
//        Line l4 = new Line(this.paddleRectangle.getUpperRight(), this.paddleRectangle.getDownRight());
        if (l1.isPointOnInterval(collisionPoint)) { //|| (l2.isPointOnInterval(collisionPoint))) {
            int collisionSpot = checkCollisionSpot(l1, collisionPoint);
            switch (collisionSpot) {
                case 0:
                    return Velocity.fromAngleAndSpeed(300, currentVelocity.getDx());
                case 1:
                    return Velocity.fromAngleAndSpeed(330, currentVelocity.getDx());
                case 2:
                    return Velocity.fromAngleAndSpeed(360, currentVelocity.getDx());
                case 3:
                    return Velocity.fromAngleAndSpeed(390, currentVelocity.getDx());
                case 4:
                    return Velocity.fromAngleAndSpeed(420, currentVelocity.getDx());
            }
            return new Velocity(currentVelocity.getDx(), (-1) * currentVelocity.getDy());
        }
//        } else if ((l3.isPointOnInterval(collisionPoint)) || (l4.isPointOnInterval(collisionPoint))) {
//            return new Velocity((-1) * currentVelocity.getDx(), currentVelocity.getDy());
//        }
        else {
            return currentVelocity;
        }
    }

    private int checkCollisionSpot(Line paddleLine, Point collisionPoint) {
        double lineStartX = paddleLine.start().getX();
        double lineDistance = paddleLine.start().distance(paddleLine.end());
        double lineLengthRegions = lineDistance / 5;
        double collisionPointX = collisionPoint.getX();
        for (int i = 0; i < 5; i++) {
            if ((collisionPointX >= lineStartX + lineLengthRegions * i) &&
                    (collisionPointX <= lineStartX + lineLengthRegions * (i + 1))) {
                return i;
            }
        }
        return 2;
    }

    public void drawOn(DrawSurface surface) {
        // sets the drawing color
        surface.setColor(this.color);
        // draw the ball in the "x" and "y" defined and with the it's radius
        surface.fillRectangle((int) paddleRectangle.getUpperLeft().getX(), (int) paddleRectangle.getUpperLeft().getY(),
                (int) paddleRectangle.getWidth(), (int) paddleRectangle.getHeight());
    }

    public void timePassed() {
        moveLeft();
        moveRight();
    }

    public void moveLeft() {
        if (this.keyboard.isPressed(keyboard.LEFT_KEY)) {
            this.paddleRectangle.setNewLocation(-10);
        }
    }

    public void moveRight() {
        if (this.keyboard.isPressed(keyboard.RIGHT_KEY)) {
            this.paddleRectangle.setNewLocation(10);
        }
    }

    public Color getColor() {
        return this.color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
