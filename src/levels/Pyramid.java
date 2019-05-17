package levels;

import backgrounds.CirclesBackground;
import backgrounds.DirectHitBackground;
import backgrounds.Emoji;
import backgrounds.LinesBackground;
import gamesprites.Block;
import geometryprimitives.Point;
import geometryprimitives.Rectangle;
import interfaces.LevelInformation;
import interfaces.Sprite;
import others.Velocity;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Pyramid implements LevelInformation {
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
        this.numberOfBalls = 4;
        return 4;
    }

    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocityList = new ArrayList<>();
        for (int i = 0; i < this.numberOfBalls; i++) {
            velocityList.add(new Velocity(4, 4));
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
        this.levelName = "Direct Hit";
        return "Direct Hit";
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
        this.Background = new Emoji();
        return this.Background;
    }

    public List<Block> createBlocks() {
        List<Block> blocksList = new ArrayList<>();
        int width = 60;
        int height = 30;
        int screenWidth = 800;
        int startXpoint;
        Point p;
        for (int i = 9; i > 0; i--) {
            startXpoint = (screenWidth - (i * width + (i - 1) * 20)) / 2;
            for (int j = 0; j < i; j++) {
                p = new Point(startXpoint + j * 20 + j* width, 90 + (9-i) * height + (9-i-1)*2);
                blocksList.add(new Block(new Rectangle(p, width, height)));
            }
        }
        this.numberOfBlocksToRemove = 45;
        this.ballsColor = Color.white;
        return blocksList;
    }

    public Color getBallsColor() {
        return ballsColor;
    }
}
