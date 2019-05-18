package animations;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import gamecontrollers.ScoreTrackingListener;

/**
 * The type End screen.
 *
 * @author Roy Leibovitz <royleibo212@gmail.com>
 */
public class EndScreen {

    /**
     * Print lose screen.
     *
     * @param gui the gui
     */
    public void printLoseScreen(GUI gui) {
        KeyboardSensor keyboardSensor = gui.getKeyboardSensor();
        DrawSurface d = gui.getDrawSurface();
        // prints "GAME OVER!" message
        d.drawText(100, 300, "GAME OVER!", 100);
        gui.show(d);
        // This loop stops only when the user press "space" button
        while (true) {
            if (keyboardSensor.isPressed(keyboardSensor.SPACE_KEY)) {
                break;
            }
        }
        return;
    }

    /**
     * Print win screen.
     *
     * @param gui                   the gui
     * @param scoreTrackingListener the score tracking listener
     */
    public void printWinScreen(GUI gui, ScoreTrackingListener scoreTrackingListener) {
        KeyboardSensor keyboardSensor = gui.getKeyboardSensor();
        DrawSurface d = gui.getDrawSurface();
        d.drawText(80, 300, "You Win! Your Score Is: " + scoreTrackingListener.getScoreCounter().getValue()
                , 50);
        gui.show(d);
        // This loop stops only when the user press "space" button
        while (true) {
            if (keyboardSensor.isPressed(keyboardSensor.SPACE_KEY)) {
                break;
            }
        }        return;
    }
}
