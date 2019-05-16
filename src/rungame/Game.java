package rungame;

import animations.EndScreen;
import animations.PauseScreen;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import animations.CountdownAnimation;
import gamesprites.Paddle;
import gamesprites.Block;
import gamesprites.Ball;
import geometryprimitives.Rectangle;
import geometryprimitives.Point;
import interfaces.Animation;
import removers.BlockRemover;
import removers.BallRemover;
import scorecontrollers.ScoreTrackingListener;
import scorecontrollers.ScoreIndicator;
import livescontrollers.LivesListener;
import livescontrollers.LivesIndicator;
import others.Counter;
import interfaces.Sprite;
import interfaces.Collidable;

/**
 * The type Game.
 * This class controls the game itself, initiate the objects and run all together.
 *
 * @author Roy Leibovitz.
 */
public class Game implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private BlockRemover blockRemover;
    private BallRemover ballRemover;
    private ScoreTrackingListener scoreTracker;
    private int blocksInStage;
    private int numberOfBallsInGame;
    private ScoreIndicator scoreIndicator;
    private Paddle paddle;
    private LivesListener livesListener;
    private LivesIndicator livesIndicator;
    private List<Ball> listBalls;
    private AnimationRunner animationRunner;
    private boolean isRunning;
    private boolean isStageCompleted;
    private int numberOfBallsToCreate;

    /**
     * Instantiates a new Game.
     */
    public Game() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.blockRemover = new BlockRemover(this, new Counter());
        this.ballRemover = new BallRemover(this, new Counter());
        Counter scoreTrackerCounter = new Counter();
        this.scoreTracker = new ScoreTrackingListener(scoreTrackerCounter);
        this.scoreIndicator = new ScoreIndicator(scoreTrackerCounter);
        Counter numberOfLivesLeft = new Counter();
        this.livesIndicator = new LivesIndicator(numberOfLivesLeft);
        this.livesListener = new LivesListener(numberOfLivesLeft);
        this.livesIndicator.getLivesCounter().setCounter(4);
        this.listBalls = new ArrayList<>();
        this.animationRunner = new AnimationRunner(60);
        this.numberOfBallsInGame = 2;
        this.isStageCompleted = true;
        this.numberOfBallsToCreate = 0;
        this.isRunning = true;
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
        // calling a method to create a frame of blocks.
        createFrame();
        // calling a method to create blocks.
        addMultipleBlocksPartialLine(1, 60, 30);
        // create a paddle and add it into each list needed.
        this.paddle = new Paddle(new Rectangle(new Point(300, 574.9), 200, 25),
                this.animationRunner.getGui(), Color.ORANGE);
        this.environment.addCollidable(this.paddle);
        this.sprites.addSprite(this.paddle);
        createBalls(this.numberOfBallsInGame);
        this.animationRunner.run(new CountdownAnimation(2, 3, this.sprites));
    }

    /**
     * Add multiple blocks partial line.
     *
     * @param numberOfRows the number of rows of block
     * @param width        the width of the block
     * @param height       the height of the block
     */
    public void addMultipleBlocksPartialLine(int numberOfRows, double width, double height) {
        DrawSurface d = this.animationRunner.getGui().getDrawSurface();
        double screenWidth = d.getWidth();
        Random rand = new Random();
        Point upperLeft;
        double numberOfBlocks = 10; // number of block in each row;
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
                tempBlock.addHitListener(this.scoreTracker);
                tempBlock.addHitListener(this.blockRemover);
            }
            this.blocksInStage += numberOfBlocks - i;
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

    public void doOneFrame(DrawSurface d) {
        Counter blockLeftCounter = this.blockRemover.getRemainingBlocks();
        Counter ballRemoved = this.ballRemover.getRemainingBalls();
        d.setColor(new Color(0, 0, 153));
        d.fillRectangle(0, 0, 1000, 600);
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        // timing
        if (this.blocksInStage == blockLeftCounter.getValue()) {
            this.scoreTracker.getScoreCounter().increase(100);
            blockLeftCounter.setCounter(0);
            this.isStageCompleted = true;
            this.isStageFinished();
        }
        if (ballRemoved.getValue() == this.numberOfBallsInGame) {
            ballRemover.getRemainingBalls().setCounter(0);
            this.isStageCompleted = false;
            this.isStageFinished();
        }
        if (this.animationRunner.getGui().getKeyboardSensor().isPressed("p")) {
            this.animationRunner.run(new PauseScreen(this.animationRunner.getGui().getKeyboardSensor()));
        }
    }

    public boolean shouldStop() {
        return this.isRunning;
    }

    /**
     * Play one turn.
     */
    public void playOneTurn() {

    }

    private void isStageFinished() {
        Counter numberOfLivesLeft = this.livesIndicator.getLivesCounter();
        int i = 2;
        if (!this.isStageCompleted) {
            numberOfLivesLeft.decrease(1);
            if (numberOfLivesLeft.getValue() == 0) {
                new EndScreen().printEndScreen(this.animationRunner.getGui());
            } else {
                this.numberOfBallsToCreate = this.numberOfBallsInGame;
                this.isStageCompleted = true;
                this.paddle.setPaddleRectangle(new Rectangle(new Point(300, 574.9), 200, 25));
            }
        } else {
            this.blocksInStage = 0;
            addMultipleBlocksPartialLine(i, 60, 30);
            if (i < 7) {
                i++;
            }
            this.numberOfBallsToCreate = this.ballRemover.getRemainingBalls().getValue();
            this.ballRemover.getRemainingBalls().setCounter(0);
        }
        changeBallsLocation();
        createBalls(numberOfBallsToCreate);
    }

    /**
     * Changes the ball location.
     */
    private void changeBallsLocation() {
        Random r = new Random();
        for (Ball b : this.listBalls) {
            b.setCenter(new Point(r.nextInt(500) + 100, 100));
        }
    }

    /**
     * This function creates 3 block that defines the game frame.
     */

    private void createFrame() {
        DrawSurface d = this.animationRunner.getGui().getDrawSurface();
        double width = d.getWidth();
        double height = d.getHeight();
        Block fm1 = new Block(new Rectangle(new Point(0, 25), width, 30));
        Block fm2 = new Block(new Rectangle(new Point(0, 55), 30, height - 55.1));
        Block fm3 = new Block(new Rectangle(new Point(width - 30, 55), 30, height - 55.1));
        Block fm4 = new Block(new Rectangle(new Point(0, height - 0.1), width, 0.1));
        fm1.setBackgroundColor(Color.GRAY);
        fm2.setBackgroundColor(Color.GRAY);
        fm3.setBackgroundColor(Color.GRAY);
        fm4.setBackgroundColor(Color.GRAY);
        fm4.addHitListener(this.ballRemover);
        fm1.setHitsLeft("X");
        fm2.setHitsLeft("X");
        fm3.setHitsLeft("X");
        fm4.setHitsLeft("");
        fm1.setFontColor(Color.WHITE);
        fm2.setFontColor(Color.WHITE);
        fm3.setFontColor(Color.WHITE);
        this.sprites.addSprite(fm4);
        this.environment.addCollidable(fm4);
        this.sprites.addSprite(fm3);
        this.environment.addCollidable(fm3);
        this.sprites.addSprite(fm2);
        this.environment.addCollidable(fm2);
        this.sprites.addSprite(fm1);
        this.environment.addCollidable(fm1);
        this.sprites.addSprite(this.scoreIndicator);
        this.sprites.addSprite(this.livesIndicator);
    }

    /**
     * Remove collidable.
     *
     * @param c the c
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * Remove sprite.
     *
     * @param s the s
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * Gets sprite collection.
     *
     * @return the sprite collection
     */
    public SpriteCollection getSpriteCollection() {
        return this.sprites;
    }

    /**
     * Gets game environment.
     *
     * @return the game environment
     */
    public GameEnvironment getGameEnvironment() {
        return this.environment;
    }

    /**
     * Remove ball.
     *
     * @param b the b
     */
    public void removeBall(Ball b) {
        this.sprites.removeSprite(b);
    }

    /**
     * Create balls.
     *
     * @param numberOfBalls the number of balls
     */
    public void createBalls(int numberOfBalls) {
        Random r = new Random();
        Color color = Color.WHITE;
        for (int i = 0; i < numberOfBalls; i++) {
            switch (r.nextInt(6)) {
                case 0:
                    color = Color.BLACK;
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
                    break;
            }
            Ball b = new Ball(r.nextInt(500) + 100, 100
                    , 5, color, this.environment);
            this.sprites.addSprite(b);
            this.listBalls.add(b);
        }
    }

    public AnimationRunner getAnimationRunner() {
        return animationRunner;
    }
}