package gamecontrollers;
import biuoop.DrawSurface;
import java.awt.Color;
import others.Counter;
import interfaces.Sprite;

/**
 * This class is the score indicator, which is in charge of drawing the score on screen.
 *
 * @author Roy Leibovitz <royleibo212@gmail.com>
 */
public class ScoreIndicator implements Sprite {
    private Counter scoreTrackerCounter;

    /**
     * Instantiates a new Score indicator.
     *
     * @param scoreTrackerCounter the score tracker counter
     */
    public ScoreIndicator(Counter scoreTrackerCounter) {
        this.scoreTrackerCounter = scoreTrackerCounter;
    }

    /**
     * This function draws on top of the screen the score.
     * @param surface the surface
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.BLACK);
        surface.drawRectangle(0, 0, 800, 25);
        // draw the block it self using the color received by input (or the default)
        surface.setColor(Color.WHITE);
        // draw the score
        surface.fillRectangle(0, 0, 800, 25);
        surface.setColor(Color.BLACK);
        surface.drawText(350, 23, "Score: " + this.scoreTrackerCounter.getValue(), 20);
    }

    /**
     * time Passed function.
     */
    public void timePassed() {

    }
}
