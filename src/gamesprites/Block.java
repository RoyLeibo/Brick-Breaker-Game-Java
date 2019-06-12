package gamesprites;
import biuoop.DrawSurface;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import levelfromfile.ColorParser;
import others.Velocity;
import interfaces.Collidable;
import interfaces.Sprite;
import interfaces.HitNotifier;
import interfaces.HitListener;
import geometryprimitives.Rectangle;
import geometryprimitives.Point;
import geometryprimitives.Line;
import rungame.GameLevel;

import javax.imageio.ImageIO;

/**
 * The type Block.
 * This is the Block class which implements the Collidable and Sprite interfaces and defines a Block.
 *
 * @author Roy Leibovitz <royleibo212@gmail.com>
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle blockRectangle;
    private Color fontColor;
    private Color stroke;
    private String hitsLeft;
    private List<HitListener> hitListeners;
    private Map<Integer, String> backgroundMap;
    private Color backgroundColor;

    /**
     * Instantiates a new Block.
     *
     * @param blockRectangle the block rectangle
     */
    public Block(Rectangle blockRectangle, Map<Integer,String> backgroundMap, String hitPoint, Color stroke) {
        this.blockRectangle = blockRectangle;
        this.hitsLeft = hitPoint;
        this.hitListeners = new ArrayList<HitListener>();
        this.setFontColor(Color.BLACK);
        this.backgroundMap = backgroundMap;
        this.stroke = stroke;

    }

    public Block(Rectangle blockRectangle, Color stroke, String hitPoint) {
        this.blockRectangle = blockRectangle;
        this.hitsLeft = hitPoint;
        this.hitListeners = new ArrayList<HitListener>();
        this.setFontColor(Color.BLACK);
        this.backgroundMap = backgroundMap;
        this.stroke = stroke;
    }

    public Block(Rectangle blockRectangle){
        this.blockRectangle = blockRectangle;
        this.hitsLeft="3";
    }

    /**
     * Instantiates a new Block.
     *
     * @param blockRectangle the block rectangle
     * @param hitsLeft       the hits left
     */
    public Block(Rectangle blockRectangle, String hitsLeft) {
        this.blockRectangle = blockRectangle;
        this.hitsLeft = hitsLeft;
        this.hitListeners = new ArrayList<HitListener>();
        this.setFontColor(Color.BLACK);
        this.stroke = null;
        this.backgroundMap = new HashMap<>();
    }

    /**
     * Getter for the Block's rectangle.
     *
     * @return blockRectangle
     */
    public Rectangle getCollisionRectangle() {
        return this.blockRectangle;
    }

    public void setBackgroundMap(Map<Integer, String> backgroundMap) {
        this.backgroundMap = backgroundMap;
    }

    /**
     * This method is called after a collision has detected and used to determine
     * from which side of the block's rectangle the ball hit using the collisionPoint.
     * The method will create a new velocity depend of the hit location.
     *
     * @param collisionPoint  the collision point
     * @param currentVelocity the current velocity
     * @param hitter          the block hitter
     * @return new velocity after change.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
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
            // change the y's velocity into it's negative
            this.notifyHit(hitter);
            return new Velocity(currentVelocity.getDx(), (-1) * currentVelocity.getDy());
        } else if ((l3.isPointOnInterval(collisionPoint)) || (l4.isPointOnInterval(collisionPoint))) {
            // change the x's velocity into it's negative
            this.notifyHit(hitter);
            return new Velocity((-1) * currentVelocity.getDx(), currentVelocity.getDy());
            // if it didn't hit any of the 4 lines (which never happens), return the same velocity
        }
        this.notifyHit(hitter);
        return currentVelocity;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
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
        if(this.stroke == null){
            surface.setColor(Color.BLACK);
        }
        else {
            surface.setColor(this.stroke);
        }
        surface.drawRectangle((int) blockRectangle.getUpperLeft().getX(), (int) blockRectangle.getUpperLeft().getY(),
                (int) blockRectangle.getWidth(), (int) blockRectangle.getHeight());
        // draw the block it self using the color received by input (or the default)
        try {
            String background = this.backgroundMap.get(Integer.valueOf(this.hitsLeft));
            if (background.charAt(0) == 'c') {
                surface.setColor(new ColorParser().colorFromString(background.substring
                        (background.indexOf('(') + 1, background.indexOf(")") + 1)));
                surface.fillRectangle((int) blockRectangle.getUpperLeft().getX(), (int) blockRectangle.getUpperLeft().getY(),
                        (int) blockRectangle.getWidth(), (int) blockRectangle.getHeight());
            } else {
                try {
                    surface.drawImage((int) blockRectangle.getUpperLeft().getX(),
                            (int) blockRectangle.getUpperLeft().getY(),
                            ImageIO.read(new File(background.substring(background.indexOf("(") + 1
                                    , background.indexOf(")")))));
                } catch (IOException e) {
                    surface.setColor(Color.BLACK);
                    surface.fillRectangle((int) blockRectangle.getUpperLeft().getX(), (int) blockRectangle.getUpperLeft().getY(),
                            (int) blockRectangle.getWidth(), (int) blockRectangle.getHeight());
                }
            }
        }
        catch (Exception e){
            surface.setColor(this.backgroundColor);
            surface.fillRectangle((int) blockRectangle.getUpperLeft().getX(), (int) blockRectangle.getUpperLeft().getY(),
                    (int) blockRectangle.getWidth(), (int) blockRectangle.getHeight());
        }
        // draw the number of hitsLeft in black
        surface.setColor(this.fontColor);
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

    public void setStroke(Color stroke) {
        this.stroke = stroke;
    }

    /**
     * Set font color.
     *
     * @param color1 the color 1
     */
    public void setFontColor(Color color1) {
        this.fontColor = color1;
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

    /**
     * Remove from gameLevel.
     *
     * @param gameLevel the gameLevel
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeCollidable(this);
        gameLevel.removeSprite(this);
    }

    /**
     * This function adds a listener to the list.
     *
     * @param hl the hit listener
     */
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * This function removes a specific listener from the listeners list.
     *
     * @param hl the hit listener
     */
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * This function notifies all the Block's listeners that there was a hit.
     *
     * @param hitter the hit ball
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}
