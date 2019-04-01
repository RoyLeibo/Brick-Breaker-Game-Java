import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.*;
import java.util.Random;

/**
 * The type Game.
 * This class controls the game itself, initiate the objects and run all together.
 *
 * @author Roy Leibovitz.
 */
public class Game {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;

    /**
     * Instantiates a new Game.
     */
    public Game() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
    }

    /**
     * Add collidable.
     *
     * @param c the new Collidable.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Add sprite.
     *
     * @param s the new Sprite.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Initialize all the objects required to run the game.
     */
    public void initialize() {
        this.gui = new GUI("", 1000, 600);
        // calling a method to create a frame of blocks.
        createFrame();
        // calling a method to create blocks.
        addMultipleBlocksPartialLine(5, 100, 40);
        // create a paddle and add it into each list needed.
        Rectangle a1 = new Rectangle(new Point(250, 550), 200, 50);
        Paddle p = new Paddle(a1, gui, Color.BLACK);
        this.environment.addCollidable(p);
        this.sprites.addSprite(p);
        // creates a ball.
        Frame frame = new Frame(600, 1000, new Point(0, 0));
        this.sprites.addSprite(new Ball(150, 550, 20, java.awt.Color.BLACK, frame, this.environment));
    }

    /**
     * Add multiple blocks partial line.
     *
     * @param numberOfRows the number of rows of block
     * @param width        the width of the block
     * @param height       the height of the block
     */
    public void addMultipleBlocksPartialLine(int numberOfRows, double width, double height) {
        DrawSurface d = this.gui.getDrawSurface();
        double screenWidth = d.getWidth() - 100;
        Random rand = new Random();
        Point upperLeft;
        double numberOfBlocks = (screenWidth) / width; // number of block in each row;
        for (int i = 0; i < numberOfRows; i++) {
            Color color = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
            upperLeft = new Point(0, height * i + 200);
            for (int j = 0; j < numberOfBlocks - i; j++) {
                Point blockUpperLeft = new Point(screenWidth - 50 - j * width, upperLeft.getY());
                Block tempBlock;
                if (i == 0) {
                    tempBlock = new Block(new Rectangle(blockUpperLeft, width, height), color, "2");
                } else {
                    tempBlock = new Block(new Rectangle(blockUpperLeft, width, height), color, "1");
                }
                this.sprites.addSprite(tempBlock);
                this.environment.addCollidable(tempBlock);
            }
        }
    }

    /**
     * Add multiple blocks full lines.
     *
     * @param numberOfRows the number of rows of blocks
     * @param width        the width of the block
     * @param height       the height of the block
     */
    public void addMultipleBlocksFullLines(int numberOfRows, double width, double height) {
        DrawSurface d = this.gui.getDrawSurface();
        double screenWidth = d.getWidth() - 100;
        Random rand = new Random();
        Point upperLeft;
        double numberOfBlocks = (screenWidth) / width; // number of block in each row
        // this loop is running for each row of blocks
        for (int i = 0; i < numberOfRows; i++) {
            // random a block color for each row
            Color color = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
            upperLeft = new Point(50, height * i + 50);
            // this loop is creating the block for each row
            for (int j = 0; j < numberOfBlocks; j++) {
                Point blockUpperLeft = new Point(screenWidth - 50 - j * width, upperLeft.getY());
                Block tempBlock = new Block(new Rectangle(blockUpperLeft, width, height), color, "3");
                this.sprites.addSprite(tempBlock);
                this.environment.addCollidable(tempBlock);
            }
        }
    }

    /**
     * Run the game after the initialization.
     */
    public void run() {
        //...
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        Sleeper sleeper = new Sleeper();
        // and infinite loop that drawing each object and calling the timePassed function in
        // each one of them.
        while (true) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            this.sprites.drawAllOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();
            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }

    /**
     * This function creates 3 block that defines the game frame.
     */

    private void createFrame() {
        DrawSurface d = gui.getDrawSurface();
        double width = d.getWidth();
        double height = d.getHeight();
        FrameBlocks fm1 = new FrameBlocks(new Rectangle(new Point(0, 0), width, 50));
        FrameBlocks fm2 = new FrameBlocks(new Rectangle(new Point(0, 0), 50, height));
        FrameBlocks fm3 = new FrameBlocks(new Rectangle(new Point(width - 50, 0), 50, height));
        this.sprites.addSprite(fm2);
        this.environment.addCollidable(fm2);
        this.sprites.addSprite(fm3);
        this.environment.addCollidable(fm3);
        this.sprites.addSprite(fm1);
        this.environment.addCollidable(fm1);
    }
}