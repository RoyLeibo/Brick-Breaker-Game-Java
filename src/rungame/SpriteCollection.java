package rungame;

import biuoop.DrawSurface;

import java.util.ArrayList;

import interfaces.Sprite;

/**
 * The Sprite collection class.
 *
 * @author Roy Leibovitz.
 */
public class SpriteCollection {

    /**
     * The Sprites list.
     */
    private ArrayList<Sprite> spritesList;

    /**
     * Instantiates a new Sprite collection.
     */
    public SpriteCollection() {
        this.spritesList = new ArrayList<Sprite>();
    }

    /**
     * Add sprite.
     *
     * @param s the new Sprite
     */
    public void addSprite(Sprite s) {
        this.spritesList.add(s);
    }

    /**
     * Notify all Sprites in the list that time passed.
     */
    public void notifyAllTimePassed() {
        for (Sprite sprite : this.spritesList) {
            sprite.timePassed();
        }
    }

    /**
     * Draw all sprites in the list.
     *
     * @param d the draw surface
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite : this.spritesList) {
            sprite.drawOn(d);
        }
    }

    /**
     * Gets sprites list.
     *
     * @return the sprites list
     */
    public ArrayList<Sprite> getSpritesList() {
        return this.spritesList;
    }

    /**
     * Remove sprite.
     *
     * @param s the s
     */
    public void removeSprite(Sprite s) {
        ArrayList<Sprite> newSpriteList = new ArrayList<>();
        for (Sprite sp : spritesList) {
            if (sp != s) {
                newSpriteList.add(sp);
            }
        }
        this.spritesList = newSpriteList;
    }

    /**
     * Set sprites list.
     *
     * @param sprites the sprites
     */
    public void setSpritesList(ArrayList<Sprite> sprites) {
        this.spritesList = sprites;
    }
}