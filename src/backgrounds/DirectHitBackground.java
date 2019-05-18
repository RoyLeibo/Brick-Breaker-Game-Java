package backgrounds;

import biuoop.DrawSurface;
import interfaces.Sprite;

import java.awt.Color;


/**
 * The type Direct hit background.
 *
 * @author Roy Leibovitz <royleibo212@gmail.com>
 */
public class DirectHitBackground implements Sprite {

    /**
     * time passed function.
     */
    public void timePassed() {

    }

    /**
     * Draws a the target background.
     *
     * @param surface the surface
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.BLACK);
        surface.fillRectangle(0, 0, 800, 600);
        surface.setColor(Color.RED);
        surface.fillRectangle(375, 145, 50, 50);
        surface.setColor(Color.BLUE);
        surface.drawCircle(400, 170, 70);
        surface.drawCircle(400, 170, 100);
        surface.drawCircle(400, 170, 130);
        surface.drawLine(260, 170, 370, 170);
        surface.drawLine(430, 170, 540, 170);
        surface.drawLine(400, 30, 400, 140);
        surface.drawLine(400, 200, 400, 310);
    }
}
