import biuoop.DrawSurface;

import java.util.ArrayList;

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
     */
    public ArrayList<Sprite> getSpritesList() {
        return this.spritesList;
    }

    public void removeSprite(Sprite s){
        ArrayList<Sprite> newSpriteList = new ArrayList<>();
        for (Sprite sp: spritesList) {
            if(sp != s){
                newSpriteList.add(sp);
            }
        }
        this.spritesList  = newSpriteList;
    }
}