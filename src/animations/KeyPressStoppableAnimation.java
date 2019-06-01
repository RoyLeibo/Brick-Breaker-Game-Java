package animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import interfaces.Animation;

/**
 * The type Key press stoppable animation.
 *
 * @author Roy Leibovitz <royleibo212@gmail.com>
 */
public class KeyPressStoppableAnimation implements Animation {
    private boolean isAnimationRunning;
    private KeyboardSensor sensor;
    private String key;
    private Animation animation;
    private boolean isPressed;

    /**
     * Instantiates a new Key press stoppable animation.
     *
     * @param sensor    the sensor
     * @param key       the key
     * @param animation the animation
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.sensor = sensor;
        this.key = key;
        this.animation = animation;
        this.isAnimationRunning = true;
        this.isPressed = true;
    }

    /**
     * should stop getter.
     *
     * @return is the game stopped
     */
    public boolean shouldStop() {
        return isAnimationRunning;
    }

    /**
     * The function draws the count down.
     *
     * @param d the d
     */
    public void doOneFrame(DrawSurface d) {
        this.animation.doOneFrame(d);
        String ifCondition = "";
        switch (this.key) {
            case "SPACE_KEY":
                ifCondition = this.sensor.SPACE_KEY;
                break;
            case "DOWN_KEY":
                ifCondition = this.sensor.DOWN_KEY;
                break;
            case "ENTER_KEY":
                ifCondition = this.sensor.ENTER_KEY;
                break;
            case "LEFT_KEY":
                ifCondition = this.sensor.LEFT_KEY;
                break;
            case "RETURN_KEY":
                ifCondition = this.sensor.RETURN_KEY;
                break;
            case "RIGHT_KEY":
                ifCondition = this.sensor.RIGHT_KEY;
                break;
            case "UP_KEY":
                ifCondition = this.sensor.UP_KEY;
            default:
                ifCondition = this.key;
        }
        if (this.sensor.isPressed(ifCondition)) {
            if (!this.isPressed) {
                this.isAnimationRunning = false;
            }
        } else {
            this.isPressed = false;
        }
    }
}