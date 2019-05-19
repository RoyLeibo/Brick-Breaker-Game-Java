package levels;

import backgrounds.Emoji;
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
 * The type Pyramid.
 *
 * @author Roy Leibovitz <royleibo212@gmail.com>
 */
public class Pyramid implements LevelInformation {
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
        this.numberOfBalls = 3;
        return 3;
    }

    /**
     * Initialize the balls velocities.
     *
     * @return a velocity list
     */
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocityList = new ArrayList<>();
        for (int i = 0; i < this.numberOfBalls; i++) {
            velocityList.add(new Velocity(i + 3, i + 3));
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
        this.levelName = "Pyramid";
        return "Pyramid";
    }

    /**
     * Creates the level's background as a Sprite.
     *
     * @return the level's background.
     */
    public Sprite getBackground() {
        this.background = new Emoji();
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
        List<Block> blocksList = new ArrayList<>();
        int width = 60;
        int height = 30;
        int screenWidth = 800;
        int startXpoint;
        Point p;
        Block b;
        // first loop is indicate how many block in each line
        for (int i = 9; i > 0; i--) {
            // calculate the start point x of the first block in line
            startXpoint = (screenWidth - (i * width + (i - 1) * 20)) / 2;
            // second loop creates each block of a single line
            for (int j = 0; j < i; j++) {
                p = new Point(startXpoint + j * 20 + j * width, 90 + (9 - i) * height + (9 - i - 1) * 2);
                b = new Block(new Rectangle(p, width, height));
                if (i % 3 == 0) {
                    b.setHitsLeft("5");
                } else if (i % 3 == 1) {
                    b.setHitsLeft("3");
                } else {
                    b.setHitsLeft("4");
                }
                blocksList.add(b);
            }
        }
        this.numberOfBlocksToRemove = 45;
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