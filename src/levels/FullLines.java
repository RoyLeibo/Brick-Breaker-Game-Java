package levels;

import backgrounds.CirclesBackground;
import gamesprites.Block;
import geometryprimitives.Point;
import geometryprimitives.Rectangle;
import interfaces.LevelInformation;
import interfaces.Sprite;
import others.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Full lines.
 */
public class FullLines implements LevelInformation {

    private int numberOfBalls;
    private List<Velocity> ballsVelocities;
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private Sprite background;
    private List<Block> blocks;
    private int numberOfBlocksToRemove;
    private Color ballsColor;

    /**
     * Number of balls in game.
     *
     * @return number of balls
     */
    public int numberOfBalls() {
        this.numberOfBalls = 4;
        return 4;
    }

    /**
     * Initialize the balls velocities.
     *
     * @return a velocity list.
     */
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocityList = new ArrayList<>();
        for (int i = 0; i < this.numberOfBalls; i++) {
            velocityList.add(new Velocity(4, 4));
        }
        this.ballsVelocities = velocityList;
        return velocityList;
    }

    /**
     * Paddle speed getter.
     *
     * @return paddle speed.
     */

    public int paddleSpeed() {
        this.paddleSpeed = 12;
        return 12;
    }

    /**
     * Paddle speed getter.
     *
     * @return paddle speed.
     */
    public int paddleWidth() {
        this.paddleWidth = 200;
        return 200;
    }

    /**
     * Level name getter.
     *
     * @return the level's name.
     */
    public String levelName() {
        this.levelName = "Full Lines";
        return "Full Lines";
    }

    /**
     * Creates the level's background as a Sprite.
     *
     * @return the level's background.
     */
    public Sprite getBackground() {
        this.background = new CirclesBackground();
        return this.background;
    }

    /**
     * Creates the level's block.
     *
     * @return the blocks list.
     */
    public List<Block> blocks() {
        this.blocks = createBlocks();
        return this.blocks;
    }

    /**
     * Number of block to remove getter.
     *
     * @return number of block to remove.
     */
    public int numberOfBlocksToRemove() {
        return this.numberOfBlocksToRemove;
    }

    /**
     * Create blocks list.
     *
     * @return the list
     */
    public List<Block> createBlocks() {
        int width = 60;
        int height = 30;
        List blocksList = new ArrayList<Block>();
        double screenWidth = 800;
        double numberOfBlocks = 11; // number of block in each row;
        Color color;
        // this loop is running for each row of blocks
        for (int i = 0; i < 8; i++) {
            if (i % 2 == 0) {
                color = Color.WHITE;
            } else {
                color = Color.GREEN;
            }
            // this loop is creating the block for each row
            for (int j = 0; j < numberOfBlocks; j++) {
                Point blockUpperLeft = new Point(screenWidth - 130 - j * width, height * i + 150);
                blocksList.add(new Block(new Rectangle(blockUpperLeft, width, height), color, "1"));
            }
        }
        this.numberOfBlocksToRemove = 88;
        this.ballsColor = Color.cyan;
        return blocksList;
    }

    /**
     * Color of balls getter.
     *
     * @return color of balls
     */
    public Color getBallsColor() {
        return ballsColor;
    }
}
