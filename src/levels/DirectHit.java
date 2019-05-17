package levels;

import backgrounds.DirectHitBackground;
import biuoop.DrawSurface;
import gamesprites.Block;
import geometryprimitives.Point;
import geometryprimitives.Rectangle;
import interfaces.LevelInformation;
import interfaces.Sprite;
import others.Velocity;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DirectHit implements LevelInformation {
    private int numberOfBalls;
    private List<Velocity> ballsVelocities;
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private Sprite Background;
    private List<Block> blocks;
    private int numberOfBlocksToRemove;
    private Color ballsColor;

    public int numberOfBalls(){
        this.numberOfBalls = 4;
        return 4;
    }
    public List<Velocity> initialBallVelocities(){
        List<Velocity> velocityList = new ArrayList<>();
        for(int i = 0 ; i < this.numberOfBalls; i++){
            velocityList.add(new Velocity(4,4));
        }
        this.ballsVelocities = velocityList;
        return velocityList;
    }

    public int paddleSpeed(){
        this.paddleSpeed = 12;
        return 12;
    }

    public int paddleWidth(){
        this.paddleWidth = 200;
        return 200;
    }

    public String levelName(){
        this.levelName = "Direct Hit";
        return "Direct Hit";
    }

    public Sprite getBackground(){
        this.Background = createBackground();
        return this.Background;
    }

    public List<Block> blocks(){
        this.blocks = createBlocks();
        return this.blocks;
    }
    public int numberOfBlocksToRemove(){
        return this.numberOfBlocksToRemove;
    }

    public Sprite createBackground(){
        this.Background = new DirectHitBackground();
        return this.Background;
    }

    public List<Block> createBlocks(){
        List blocksList = new ArrayList<Block>();
        Block b = new Block(new Rectangle(new Point(375, 145), 50, 50));
        b.setBackgroundColor(Color.RED);
        blocksList.add(b);
        this.numberOfBlocksToRemove = 1;
        this.ballsColor = Color.white;
        return blocksList;
    }

    public Color getBallsColor() {
        return ballsColor;
    }
}
