package levels;

import backgrounds.LinesBackground;
import gamesprites.Block;
import geometryprimitives.Point;
import geometryprimitives.Rectangle;
import interfaces.LevelInformation;
import interfaces.Sprite;
import others.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The type Partial lines.
 */
public class PartialLines implements LevelInformation {
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
     * Paddle width getter.
     *
     * @return paddle width.
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
        this.levelName = "Partial Line";
        return "Partial Line";
    }

    /**
     * Creates the level's background as a Sprite.
     *
     * @return the level's background.
     */
    public Sprite getBackground() {
        this.background = new LinesBackground();
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
        Random rand = new Random();
        double numberOfBlocks = 10; // number of block in each row;
        Color color;
        // first loop indicate which line is creating
        for (int i = 0; i < 7; i++) {
            // switch case determine different color for each line
            switch (i) {
                case 0:
                    color = Color.GRAY;
                    break;
                case 1:
                    color = Color.RED;
                    break;
                case 2:
                    color = Color.YELLOW;
                    break;
                case 3:
                    color = Color.WHITE;
                    break;
                case 4:
                    color = Color.pink;
                    break;
                case 5:
                    color = Color.green;
                    break;
                default:
                    color = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
            }
            // second loop creates each line blocks depending on the line number
            for (int j = 0; j < numberOfBlocks - i; j++) {
                Point blockUpperLeft = new Point(screenWidth - 140 - j * width, height * i + 150);
                Block tempBlock;
                if (i == 0) {
                    tempBlock = new Block(new Rectangle(blockUpperLeft, width, height), color, "2");
                } else {
                    tempBlock = new Block(new Rectangle(blockUpperLeft, width, height), color, "1");
                }
                blocksList.add(tempBlock);
            }
        }
        this.numberOfBlocksToRemove = 49;
        this.ballsColor = Color.YELLOW;
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
