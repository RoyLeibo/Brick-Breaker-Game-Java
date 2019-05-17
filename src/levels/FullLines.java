package levels;

import backgrounds.CirclesBackground;
import backgrounds.LinesBackground;
import gamesprites.Block;
import geometryprimitives.Point;
import geometryprimitives.Rectangle;
import interfaces.LevelInformation;
import interfaces.Sprite;
import others.Velocity;

import java.awt.*;
import java.lang.invoke.SwitchPoint;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FullLines implements LevelInformation {

    private int numberOfBalls;
    private List<Velocity> ballsVelocities;
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private Sprite Background;
    private List<Block> blocks;
    private int numberOfBlocksToRemove;
    private Color ballsColor;

    public int numberOfBalls() {
        this.numberOfBalls = 3;
        return 3;
    }

    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocityList = new ArrayList<>();
        for (int i = 0; i < this.numberOfBalls; i++) {
            velocityList.add(new Velocity(6, 6));
        }
        this.ballsVelocities = velocityList;
        return velocityList;
    }

    public int paddleSpeed() {
        this.paddleSpeed = 12;
        return 12;
    }

    public int paddleWidth() {
        this.paddleWidth = 200;
        return 200;
    }

    public String levelName() {
        this.levelName = "Full Lines";
        return "Full Lines";
    }

    public Sprite getBackground() {
        this.Background = createBackground();
        return this.Background;
    }

    public List<Block> blocks() {
        this.blocks = createBlocks();
        return this.blocks;
    }

    public int numberOfBlocksToRemove() {
        return this.numberOfBlocksToRemove;
    }

    public Sprite createBackground() {
        this.Background = new CirclesBackground();
        return this.Background;
    }

    public List<Block> createBlocks() {
        int width = 60;
        int height = 30;
        List blocksList = new ArrayList<Block>();
        double screenWidth = 800;
        Random rand = new Random();
        double numberOfBlocks = 11; // number of block in each row;
        Color color;
        // this loop is running for each row of blocks
        Color black = new Color(0, 0, 0);
        Color darkBlue = new Color(0, 0, 153);
        for (int i = 0; i < 8; i++) {
            if(i % 2 == 0) {
                color = Color.WHITE;
            }
            else {
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

    public Color getBallsColor() {
        return ballsColor;
    }
}
