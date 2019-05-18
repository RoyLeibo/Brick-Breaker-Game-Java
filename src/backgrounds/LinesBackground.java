package backgrounds;

import biuoop.DrawSurface;
import interfaces.Sprite;

import java.awt.Color;

/**
 * The type Lines background.
 *
 * @author Roy Leibovitz <royleibo212@gmail.com>
 */
public class LinesBackground implements Sprite {

    /**
     * time passed function.
     */
    public void timePassed() {

    }

    /**
     * Draws a lines background.
     *
     * @param surface the surface
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.LIGHT_GRAY);
        surface.fillRectangle(0, 0, 800, 600);
        surface.setColor(Color.DARK_GRAY);
        surface.drawLine(0, 600, 40, 0);
        surface.drawLine(0, 600, 80, 0);
        surface.drawLine(0, 600, 120, 0);
        surface.drawLine(0, 600, 160, 0);
        surface.drawLine(0, 600, 200, 0);
        surface.drawLine(0, 600, 240, 0);
        surface.drawLine(0, 600, 280, 0);
        surface.drawLine(0, 600, 320, 0);
        surface.drawLine(0, 600, 360, 0);
        surface.drawLine(0, 600, 400, 0);
        surface.drawLine(0, 600, 440, 0);
        surface.drawLine(0, 600, 480, 0);
        surface.drawLine(0, 600, 520, 0);
        surface.drawLine(0, 600, 560, 0);
        surface.drawLine(0, 600, 600, 0);
        surface.drawLine(0, 600, 640, 0);
        surface.drawLine(0, 600, 680, 0);
        surface.drawLine(0, 600, 720, 0);
        surface.drawLine(0, 600, 760, 0);
        surface.drawLine(0, 600, 800, 0);
        surface.drawLine(800, 600, 40, 0);
        surface.drawLine(800, 600, 80, 0);
        surface.drawLine(800, 600, 120, 0);
        surface.drawLine(800, 600, 160, 0);
        surface.drawLine(800, 600, 200, 0);
        surface.drawLine(800, 600, 240, 0);
        surface.drawLine(800, 600, 280, 0);
        surface.drawLine(800, 600, 320, 0);
        surface.drawLine(800, 600, 360, 0);
        surface.drawLine(800, 600, 400, 0);
        surface.drawLine(800, 600, 440, 0);
        surface.drawLine(800, 600, 480, 0);
        surface.drawLine(800, 600, 520, 0);
        surface.drawLine(800, 600, 560, 0);
        surface.drawLine(800, 600, 600, 0);
        surface.drawLine(800, 600, 640, 0);
        surface.drawLine(800, 600, 680, 0);
        surface.drawLine(800, 600, 720, 0);
        surface.drawLine(800, 600, 760, 0);
        surface.drawLine(800, 600, 800, 0);
        surface.drawLine(400, 600, 40, 0);
        surface.drawLine(400, 600, 80, 0);
        surface.drawLine(400, 600, 120, 0);
        surface.drawLine(400, 600, 160, 0);
        surface.drawLine(400, 600, 200, 0);
        surface.drawLine(400, 600, 240, 0);
        surface.drawLine(400, 600, 280, 0);
        surface.drawLine(400, 600, 320, 0);
        surface.drawLine(400, 600, 360, 0);
        surface.drawLine(400, 600, 400, 0);
        surface.drawLine(400, 600, 440, 0);
        surface.drawLine(400, 600, 480, 0);
        surface.drawLine(400, 600, 520, 0);
        surface.drawLine(400, 600, 560, 0);
        surface.drawLine(400, 600, 600, 0);
        surface.drawLine(400, 600, 640, 0);
        surface.drawLine(400, 600, 680, 0);
        surface.drawLine(400, 600, 720, 0);
        surface.drawLine(400, 600, 760, 0);
        surface.drawLine(400, 600, 800, 0);
    }
}
