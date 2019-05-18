package backgrounds;

import biuoop.DrawSurface;
import interfaces.Sprite;

import java.awt.Color;

/**
 * The type Emoji.
 *
 * @author Roy Leibovitz <royleibo212@gmail.com>
 */
public class Emoji implements Sprite {

    /**
     * time passed function.
     */
    public void timePassed() {

    }

    /**
     * Draws a emoji background.
     *
     * @param surface the surface
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.DARK_GRAY);
        surface.fillRectangle(0, 0, 800, 600);
        surface.setColor(new Color(255, 218, 185));
        surface.fillCircle(400, 300, 200);
        surface.setColor(Color.BLACK);
        surface.drawCircle(320, 200, 40);
        surface.drawCircle(480, 200, 40);
        surface.drawLine(360, 330, 400, 280);
        surface.drawLine(360, 330, 400, 330);
        surface.setColor(new Color(135, 206, 250));
        surface.fillCircle(308, 222, 15);
        surface.fillCircle(468, 222, 15);
        surface.setColor(new Color(255, 192, 203));
        surface.fillOval(330, 420, 140, 30);

        surface.setColor(Color.YELLOW);
        surface.drawLine(400, 100, 400, 70);
        surface.drawLine(410, 101, 410, 71);
        surface.drawLine(420, 102, 420, 72);
        surface.drawLine(430, 103, 430, 73);
        surface.drawLine(440, 104, 440, 74);
        surface.drawLine(450, 106, 450, 76);
        surface.drawLine(460, 108, 460, 78);
        surface.drawLine(470, 111, 470, 81);
        surface.drawLine(480, 115, 480, 85);
        surface.drawLine(490, 120, 490, 90);
        surface.drawLine(500, 126, 500, 96);
        surface.drawLine(510, 133, 510, 103);
        surface.drawLine(520, 140, 520, 110);
        surface.drawLine(530, 148, 530, 118);
        surface.drawLine(540, 156, 540, 126);
        surface.drawLine(550, 166, 550, 136);

        surface.drawLine(390, 101, 390, 71);
        surface.drawLine(380, 102, 380, 72);
        surface.drawLine(370, 103, 370, 73);
        surface.drawLine(360, 104, 360, 74);
        surface.drawLine(350, 106, 350, 76);
        surface.drawLine(340, 108, 340, 78);
        surface.drawLine(330, 111, 330, 81);
        surface.drawLine(320, 115, 320, 85);
        surface.drawLine(310, 120, 310, 90);
        surface.drawLine(300, 126, 300, 96);
        surface.drawLine(290, 133, 290, 103);
        surface.drawLine(280, 140, 280, 110);
        surface.drawLine(270, 148, 270, 118);
        surface.drawLine(260, 156, 260, 126);
        surface.drawLine(250, 166, 250, 136);
    }
}
