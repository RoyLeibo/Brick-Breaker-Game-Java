import biuoop.DrawSurface;

import java.awt.*;

public class ScoreIndicator implements Sprite {
    private Counter scoreTrackerCounter;

    public ScoreIndicator(Counter scoreTrackerCounter) {
        this.scoreTrackerCounter = scoreTrackerCounter;
    }

    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.BLACK);
        surface.drawRectangle(0, 0, 800, 25);
        // draw the block it self using the color received by input (or the default)
        surface.setColor(Color.WHITE);
        // draw the score
        surface.fillRectangle(0, 0, 800, 25);
        surface.setColor(Color.BLACK);
        surface.drawText(350, 18, "Score: " + this.scoreTrackerCounter.getValue(), 20);
    }

    public void timePassed() {

    }
}
