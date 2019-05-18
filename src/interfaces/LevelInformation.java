package interfaces;

import gamesprites.Block;
import others.Velocity;

import java.awt.Color;
import java.util.List;

/**
 * The interface Level information.
 *
 * @author Roy Leibovitz <royleibo212@gmail.com>
 */
public interface LevelInformation {
    /**
     * Number of balls int.
     *
     * @return the int
     */
    int numberOfBalls();

    /**
     * Initial ball velocities list.
     *
     * @return the list
     */
    List<Velocity> initialBallVelocities();

    /**
     * Paddle speed int.
     *
     * @return the int
     */
    int paddleSpeed();

    /**
     * Paddle width int.
     *
     * @return the int
     */
    int paddleWidth();

    /**
     * Level name string.
     *
     * @return the string
     */
    String levelName();

    /**
     * Gets background.
     *
     * @return the background
     */
    Sprite getBackground();

    /**
     * Blocks list.
     *
     * @return the list
     */
    List<Block> blocks();

    /**
     * Number of blocks to remove int.
     *
     * @return the int
     */
    int numberOfBlocksToRemove();

    /**
     * Gets balls color.
     *
     * @return the balls color
     */
    Color getBallsColor();
}
