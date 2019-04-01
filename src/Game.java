import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.*;
import java.util.Random;

public class Game {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;

    public Game(){
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
    }

    public void addCollidable(Collidable c){
        this.environment.addCollidable(c);
    }
    public void addSprite(Sprite s){
        this.sprites.addSprite(s);
    }

    // Initialize a new game: create the Blocks and Ball (and Paddle)
    // and add them to the game.
    public void initialize(){
//        for(int i = 0; i<6;i++){
//            Rectangle a = new Rectangle(new Point(i*100,0), 100,i*100-70);
//            Block b = new Block(a);
//            this.environment.addCollidable(b);
//            this.sprites.addSprite(b);
//      }
        this.gui = new GUI("", 1000, 600);
//        Rectangle b1 = new Rectangle(new Point(0,0), 600,30);
//        Rectangle b2 = new Rectangle(new Point(0,570), 600,30);
//        Rectangle b3 = new Rectangle(new Point(0,0), 30,600);
//        Rectangle b4 = new Rectangle(new Point(570,0), 30,600);
//        Block c1 = new Block(b1, Color.GRAY);
//        Block c2 = new Block(b2, Color.GRAY);
//        Block c3 = new Block(b3, Color.GRAY);
//        Block c4 = new Block(b4, Color.GRAY);
//        this.environment.addCollidable((c1));
//        this.environment.addCollidable((c2));
//        this.environment.addCollidable((c3));
//        this.environment.addCollidable((c4));
//        this.sprites.addSprite(c1);
//        this.sprites.addSprite(c2);
//        this.sprites.addSprite(c3);
//        this.sprites.addSprite(c4);
        createFrame();
        addMultipleBlocksFullLines(5, 100,40);
        Rectangle a1 = new Rectangle(new Point(250, 550), 200, 50);
//        Paddle p = new Paddle(a1, gui, Color.BLACK);
//        this.environment.addCollidable(p);
//        this.sprites.addSprite(p);
        Frame frame = new Frame(600, 1000, new Point(0, 0));
        this.sprites.addSprite(new Ball(150, 550, 20, java.awt.Color.BLACK, frame, this.environment));
    }

    public void addMultipleBlocksPartialLine(int numberOfRows, double width, double height){
        DrawSurface d = this.gui.getDrawSurface();
        double screenWidth = d.getWidth();
        Random rand = new Random();
        Point upperLeft;
        double numberOfBlocks = 9;
        for(int i = 0 ; i < numberOfRows ; i++){
            Color color = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
            upperLeft = new Point (0, height*i+200);
            for(int j = 0 ; j < numberOfBlocks-i ; j++){
                Point blockUpperLeft = new Point(screenWidth - j*width, upperLeft.getY());
                Block tempBlock;
                if(i == 0 ){
                    tempBlock = new Block(new Rectangle(blockUpperLeft, width, height), color,"2");
                }
                else {
                    tempBlock = new Block(new Rectangle(blockUpperLeft, width, height), color, "1");
                }
                this.sprites.addSprite(tempBlock);
                this.environment.addCollidable(tempBlock);
            }
        }
    }

    public void addMultipleBlocksFullLines(int numberOfRows, double width, double height){
        DrawSurface d = this.gui.getDrawSurface();
        double screenWidth = d.getWidth()-100;
        Random rand = new Random();
        Point upperLeft;
        double numberOfBlocks = (screenWidth)/width;
        for(int i = 0 ; i < numberOfRows ; i++){
            Color color = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
            upperLeft = new Point (50, height*i+50);
            for(int j = 0 ; j < numberOfBlocks ; j++){
                Point blockUpperLeft = new Point(screenWidth-50 - j*width, upperLeft.getY());
                Block tempBlock = new Block(new Rectangle(blockUpperLeft, width, height), color, "3");
                this.sprites.addSprite(tempBlock);
                this.environment.addCollidable(tempBlock);
            }
        }
    }

    // Run the game -- start the animation loop.
    public void run() {
        //...
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        Sleeper sleeper = new Sleeper();
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

    private void createFrame(){
        DrawSurface d = gui.getDrawSurface();
        double width = d.getWidth();
        double height = d.getHeight();
        FrameBlocks fm1 = new FrameBlocks(new Rectangle(new Point(0,0), width,50));
        FrameBlocks fm2 = new FrameBlocks(new Rectangle(new Point(0,0), 50,height));
        FrameBlocks fm3 = new FrameBlocks(new Rectangle(new Point(width-50,0), 50,height));
        this.sprites.addSprite(fm1);
        this.environment.addCollidable(fm1);
        this.sprites.addSprite(fm2);
        this.environment.addCollidable(fm2);
        this.sprites.addSprite(fm3);
        this.environment.addCollidable(fm3);
    }
}