import biuoop.DrawSurface;

public interface Sprite {
    // draw the sprite to the screen
    void drawOn(DrawSurface surface);
    // notify the sprite that time has passed
    void timePassed();
}