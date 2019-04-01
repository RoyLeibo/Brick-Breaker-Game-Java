import biuoop.DrawSurface;

import java.util.ArrayList;

public class SpriteCollection {

    ArrayList<Sprite> spritesList;

    public SpriteCollection(){
        this.spritesList = new ArrayList<Sprite>();
    }
    public void addSprite(Sprite s){
        this.spritesList.add(s);
    }

    // call timePassed() on all sprites.
    public void notifyAllTimePassed(){
        for (Sprite sprite: this.spritesList) {
            sprite.timePassed();
        }
    }

    // call drawOn(d) on all sprites.
    public void drawAllOn(DrawSurface d){
        for (Sprite sprite: this.spritesList) {
            sprite.drawOn(d);
        }
    }
}