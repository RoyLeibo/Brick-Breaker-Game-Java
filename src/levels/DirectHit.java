package levels;

import backgrounds.DirectHitBackground;
import gamesprites.Block;
import geometryprimitives.Point;
import geometryprimitives.Rectangle;
import interfaces.LevelInformation;
import interfaces.Sprite;
import others.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type Direct hit.
 */
public class DirectHit implements LevelInformation {
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
        this.numberOfBalls = 2;
        return 2;
    }

    /**
     * Initialize the balls velocities.
     *
     * @return a velocity list
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
        this.levelName = "Direct Hit";
        return "Direct Hit";
    }

    /**
     * Creates the level's background as a Sprite.
     *
     * @return the level's background.
     */
    public Sprite getBackground() {
        this.background = new DirectHitBackground();
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
        List blocksList = new ArrayList<Block>();
        // create the level's single block
        Block b = new Block(new Rectangle(new Point(375, 145), 50, 50));
        b.setBackgroundColor(Color.RED);
        blocksList.add(b);
        this.numberOfBlocksToRemove = 1;
        this.ballsColor = Color.white;
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
