import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Color;
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
    private Counter counter;
    private BlockRemover blockRemover;

    /**
     * Instantiates a new Game.
     */
    public Game() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.counter = new Counter();
        this.blockRemover = new BlockRemover(this, this.counter);
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
        addMultipleBlocksPartialLine(6, 60, 30);
        // create a paddle and add it into each list needed.
        Rectangle a1 = new Rectangle(new Point(100, 545), 200, 25);
        Paddle p = new Paddle(a1, gui, Color.ORANGE);
        this.environment.addCollidable(p);
        this.sprites.addSprite(p);
        // creates a ball.
        Frame frame = new Frame(600, 1000, new Point(0, 0));
        this.sprites.addSprite(new Ball(150, 350, 5, Color.WHITE, frame, this.environment));
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
        double screenWidth = d.getWidth();
        Random rand = new Random();
        Point upperLeft;
        double numberOfBlocks = 12; // number of block in each row;
        Color color;
        for (int i = 0; i < numberOfRows; i++) {
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
                    color = Color.BLUE;
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
            upperLeft = new Point(0, height * i + 150);
            for (int j = 0; j < numberOfBlocks - i; j++) {
                Point blockUpperLeft = new Point(screenWidth - 90 - j * width, upperLeft.getY());
                Block tempBlock;
                if (i == 0) {
                    tempBlock = new Block(new Rectangle(blockUpperLeft, width, height), color, "2");
                } else {
                    tempBlock = new Block(new Rectangle(blockUpperLeft, width, height), color, "1");
                }
                this.sprites.addSprite(tempBlock);
                this.environment.addCollidable(tempBlock);
                tempBlock.addHitListener(this.blockRemover);
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
        Color black = new Color(0, 0, 0);
        Color darkBlue = new Color(0, 0, 153);
        for (int i = 0; i < numberOfRows; i++) {
            // random a block color for each row
            Color color = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
            if ((color != black) && (color != darkBlue)) {
                upperLeft = new Point(50, height * i + 50);
                // this loop is creating the block for each row
                for (int j = 0; j < numberOfBlocks; j++) {
                    Point blockUpperLeft = new Point(screenWidth - 50 - j * width, upperLeft.getY());
                    Block tempBlock = new Block(new Rectangle(blockUpperLeft, width, height), color, "3");
                    this.sprites.addSprite(tempBlock);
                    this.environment.addCollidable(tempBlock);
                    tempBlock.addHitListener(this.blockRemover);
                }
            } else {
                i--;
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
            d.setColor(new Color(0, 0, 153));
            d.fillRectangle(0, 0, 1000, 600);
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
        FrameBlocks fm1 = new FrameBlocks(new Rectangle(new Point(0, 0), width, 30));
        FrameBlocks fm2 = new FrameBlocks(new Rectangle(new Point(0, 30), 30, height - 60));
        FrameBlocks fm3 = new FrameBlocks(new Rectangle(new Point(width - 30, 30), 30, height - 60));
        FrameBlocks fm4 = new FrameBlocks(new Rectangle(new Point(0, height - 30), width, 30));
        this.sprites.addSprite(fm4);
        this.environment.addCollidable(fm4);
        this.sprites.addSprite(fm3);
        this.environment.addCollidable(fm3);
        this.sprites.addSprite(fm2);
        this.environment.addCollidable(fm2);
        this.sprites.addSprite(fm1);
        this.environment.addCollidable(fm1);
    }

    public void removeCollidable(Collidable c){
        this.environment.removeCollidable(c);
    }

    public void removeSprite(Sprite s){
        this.sprites.removeSprite(s);
    }

    public SpriteCollection getSpriteCollection(){
        return this.sprites;
    }

    public GameEnvironment getGameEnvironment(){
        return this.environment;
    }
}