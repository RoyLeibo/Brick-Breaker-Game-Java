package rungame;

import animations.CountdownAnimation;
import animations.KeyPressStoppableAnimation;
import animations.PauseScreen;
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import gamecontrollers.LivesIndicator;
import gamecontrollers.LivesListener;
import gamecontrollers.ScoreIndicator;
import gamecontrollers.ScoreTrackingListener;
import gamecontrollers.LevelNameIndicator;
import gamesprites.Paddle;
import gamesprites.Block;
import gamesprites.Ball;
import geometryprimitives.Rectangle;
import geometryprimitives.Point;
import interfaces.Animation;
import interfaces.LevelInformation;
import removers.BlockRemover;
import removers.BallRemover;
import others.Counter;
import interfaces.Sprite;
import interfaces.Collidable;

/**
 * The type GameLevel.
 * This class controls the game itself, initiate the objects and run all together.
 *
 * @author Roy Leibovitz <royleibo212@gmail.com>
 */
public class GameLevel implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;
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
    private LevelInformation levelInformation;
    private GameFlow gameFlow;
    private LevelNameIndicator levelNameIndicator;

    /**
     * Instantiates a new GameLevel.
     *
     * @param levelInformation      the level information
     * @param scoreTrackingListener the score tracking listener
     * @param livesListener         the lives listener
     * @param gameFlow              the game flow
     * @param animationRunner       the animation runner
     */
    public GameLevel(LevelInformation levelInformation, ScoreTrackingListener scoreTrackingListener,
                     LivesListener livesListener, GameFlow gameFlow, AnimationRunner animationRunner) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.blockRemover = new BlockRemover(this, new Counter());
        this.ballRemover = new BallRemover(this, new Counter());
        this.scoreTracker = scoreTrackingListener;
        this.scoreIndicator = new ScoreIndicator(this.scoreTracker.getScoreCounter());
        this.livesListener = livesListener;
        this.livesIndicator = new LivesIndicator(this.livesListener.getLivesCounter());
        this.listBalls = new ArrayList<>();
        this.levelInformation = levelInformation;
        this.animationRunner = animationRunner;
        this.gameFlow = gameFlow;
        this.levelNameIndicator = new LevelNameIndicator(this.levelInformation.levelName());
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
        this.sprites.addSprite(this.levelInformation.getBackground());
        this.numberOfBallsInGame = this.levelInformation.numberOfBalls();
        this.isStageCompleted = true;
        this.numberOfBallsToCreate = 0;
        this.isRunning = true;
        // Countdown animation
        this.animationRunner.setSleepFor(2.0 / 3.0);
        this.animationRunner.run(new CountdownAnimation(2, 3, this.sprites));
        this.animationRunner.setSleepFor(0);
        createGameConfiguration(0.1, 0.1, "", Color.BLACK);
        // calling a method to create blocks.
        this.addBlocksToGame(this.levelInformation.blocks());
        this.blocksInStage = this.levelInformation.numberOfBlocksToRemove();
        // create balls
        createBalls(this.numberOfBallsInGame);
    }

    /**
     * This function is drawing the game and checking if the client had disqualified or finish stage.
     *
     * @param d the d
     */
    public void doOneFrame(DrawSurface d) {
        Counter blockLeftCounter = this.blockRemover.getRemainingBlocks();
        Counter ballRemoved = this.ballRemover.getRemainingBalls();
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        // if there are no more blocks in game
        if (this.blocksInStage == blockLeftCounter.getValue()) {
            this.scoreTracker.getScoreCounter().increase(100);
            blockLeftCounter.setCounter(0);
            this.isStageCompleted = true;
            this.isStageFinished();
        }
        // if there are no balls in game
        if (ballRemoved.getValue() == this.numberOfBallsInGame) {
            ballRemover.getRemainingBalls().setCounter(0);
            this.isStageCompleted = false;
            this.isStageFinished();
        }
        // if the "p" button is pressed, pause the game.
        if (this.animationRunner.getGui().getKeyboardSensor().isPressed("p")) {
            this.animationRunner.run(new KeyPressStoppableAnimation(this.animationRunner.getGui().getKeyboardSensor(),
                    "SPACE_KEY", new PauseScreen()));
        }
    }

    /**
     * Play one turn.
     * This function starts the level.
     */
    public void playOneTurn() {
        this.initialize();
        this.isRunning = true;
        this.animationRunner.run(this);
    }

    /**
     * Is running getter.
     *
     * @return if game is running
     */
    public boolean shouldStop() {
        return this.isRunning;
    }

    /**
     * This function checks if a stage is finished / client is out of lives / client has lives left
     * and act for each option.
     */
    private void isStageFinished() {
        Counter numberOfLivesLeft = this.livesIndicator.getLivesCounter();
        // if stage is not complete
        if (!this.isStageCompleted) {
            numberOfLivesLeft.decrease(1);
            // if there are no more lives left
            if (numberOfLivesLeft.getValue() == 0) {
                gameFlow.setIsAlive(false);
                this.isRunning = false;
            } else {
                // if there is more lives left
                this.numberOfBallsToCreate = this.numberOfBallsInGame;
                this.isStageCompleted = true;
                this.paddle.setPaddleRectangle(new Rectangle(new Point(300, 565),
                        this.levelInformation.paddleWidth(), 25));
                createBalls(this.numberOfBallsToCreate);
            }
        } else {
            // if the level is finished
            this.gameFlow.setIsAlive(true);
            this.isRunning = false;
        }
    }

    /**
     * Creates the game configurations: frame and upper bar information.
     *
     * @param width  screen width
     * @param height screen height
     * @param text   text on frame
     * @param color  color for frame
     */
    private void createGameConfiguration(double width, double height, String text, Color color) {
        DrawSurface d = this.animationRunner.getGui().getDrawSurface();
        double dWidth = d.getWidth();
        double dHeight = d.getHeight();
        // 4 frame blocks
        Block fm1 = new Block(new Rectangle(new Point(0, 25), dWidth, height));
        Block fm2 = new Block(new Rectangle(new Point(0, 25 + height), width, dHeight - 25 - height - 0.1));
        Block fm3 = new Block(new Rectangle(new Point(dWidth - height, 25 + height), width,
                dHeight - 25 - height - 0.1));
        Block fm4 = new Block(new Rectangle(new Point(0, dHeight - 0.1), dWidth, 0.1));
        fm1.setBackgroundColor(color);
        fm2.setBackgroundColor(color);
        fm3.setBackgroundColor(color);
        fm4.setBackgroundColor(color);
        fm4.addHitListener(this.ballRemover);
        fm1.setHitsLeft(text);
        fm2.setHitsLeft(text);
        fm3.setHitsLeft(text);
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
        this.sprites.addSprite(this.levelNameIndicator);
        int x = 400 - this.levelInformation.paddleWidth() / 2;
        // creates paddle
        this.paddle = new Paddle(new Rectangle(new Point(x, 565), this.levelInformation.paddleWidth()
                , 25), this.animationRunner.getGui(), Color.MAGENTA, this.levelInformation.paddleSpeed(), 0.1);
        this.environment.addCollidable(this.paddle);
        this.sprites.addSprite(this.paddle);
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
        Color color = this.levelInformation.getBallsColor();
        for (int i = 0; i < numberOfBalls; i++) {
            Ball b = new Ball(r.nextInt(500) + 100, 50
                    , 5, color, this.environment);
            b.setVelocity(this.levelInformation.initialBallVelocities().get(i));
            this.sprites.addSprite(b);
            this.listBalls.add(b);
        }
    }


    /**
     * Gets animation runner.
     *
     * @return the animation runner
     */
    public AnimationRunner getAnimationRunner() {
        return animationRunner;
    }


    /**
     * Add blocks to game.
     *
     * @param blocksList the blocks list
     */
    public void addBlocksToGame(List<Block> blocksList) {
        for (Block b : blocksList) {
            this.sprites.addSprite(b);
            this.environment.addCollidable(b);
            b.addHitListener(this.scoreTracker);
            b.addHitListener(this.blockRemover);
        }
    }
}