package gamecontrollers;
import biuoop.DrawSurface;
import java.awt.Color;
import others.Counter;
import interfaces.Sprite;

/**
 * This class is the lives indicator, which is in charge of drawing the lives left on screen.
 *
 * @author Roy Leibovitz <royleibo212@gmail.com>
 */
public class LivesIndicator implements Sprite {
    private Counter livesCounter;

    /**
     * Instantiates a new Lives indicator.
     *
     * @param livesCounter the lives counter
     */
    public LivesIndicator(Counter livesCounter) {
        this.livesCounter = livesCounter;
    }

    /**
     * This function draws on top of the screen the number of lives left.
     *
     * @param surface the surface
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.BLACK);
        surface.drawRectangle(650, 0, 150, 25);
        // draw the block it self using the color received by input (or the default)
        surface.setColor(Color.WHITE);
        // draw the score
        surface.fillRectangle(650, 0, 150, 25);
        surface.setColor(Color.RED);
        surface.drawText(650, 18, "Lives: " + this.livesCounter.getValue(), 20);
    }

    /**
     * time passed func.
     */
    public void timePassed() {

    }

    /**
     * Gets lives counter.
     *
     * @return the lives counter
     */
    public Counter getLivesCounter() {
        return this.livesCounter;
    }
}
