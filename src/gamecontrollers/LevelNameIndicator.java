package gamecontrollers;

import biuoop.DrawSurface;
import interfaces.Sprite;

import java.awt.Color;

/**
 * The type Level name indicator.
 *
 * @author Roy Leibovitz <royleibo212@gmail.com>
 */
public class LevelNameIndicator implements Sprite {
    private String levelName;

    /**
     * Instantiates a new Level name indicator.
     *
     * @param levelName the level name
     */
    public LevelNameIndicator(String levelName) {
        this.levelName = levelName;
    }

    /**
     * time passed function.
     */
    public void timePassed() {

    }

    /**
     * Draws the level name on the screen.
     *
     * @param surface the surface
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.BLACK);
        surface.drawText(20, 23, "Level Name: " + this.levelName, 18);
    }
}
