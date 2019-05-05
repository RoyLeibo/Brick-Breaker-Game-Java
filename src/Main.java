import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        // write your code here

        Game g = new Game();
        g.run();
//        Frame frame = new Frame(300, 300, new Point(0, 0));
//        GUI gui = new GUI("שומן קטן ויפה", 600, 600);
//        Block b1 = new Block(new Rectangle(new Point(0,0), 200, 100));
//        Block b2 = new Block(new Rectangle(new Point(0,200), 200, 100));
//
//        GameEnvironment gm = new GameEnvironment();
//        gm.addCollidable(b1);
//gm.addCollidable(b2);
//        Sleeper sleeper = new Sleeper();
//        Ball ball = new Ball(150, 150, 20, java.awt.Color.BLACK, frame, gm);
//        ball.setVelocity(10, -10);
//        while (true) {
//            ball.moveOneStep();
//            DrawSurface d = gui.getDrawSurface();
//            b1.drawOn(d);
//          b2.drawOn(d);
//            ball.drawOn(d);
//            gui.show(d);
//            sleeper.sleepFor(50);  // wait for 50 milliseconds.
//        }
    }
}
