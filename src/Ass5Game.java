import backgrounds.CirclesBackground;
import backgrounds.DirectHitBackground;
import backgrounds.Emoji;
import biuoop.DrawSurface;
import biuoop.GUI;
import levels.DirectHit;
import levels.FullLines;
import levels.PartialLines;
import levels.Pyramid;
import rungame.GameFlow;
import rungame.GameLevel;

/**
 * This class runs the game.
 *
 * @author Roy Leibovitz <royleibo212@gmail.com> Roy Leibovitz
 */
public class Ass5Game {

    /**
     * This is the main method which creates an instance of GameLevel and call
     * it's initialize and run function.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        DirectHit drb = new DirectHit();
//        PartialLines pl = new PartialLines();
//        CirclesBackground cb = new CirclesBackground();
//        Emoji e = new Emoji();
//        GUI g = new GUI("שומן יפה וקטן", 800,600);
//        DrawSurface d = g.getDrawSurface();
//        e.drawOn(d);
//        g.show(d);
//        Pyramid fl = new Pyramid();
//        FullLines fl = new FullLines();
//        GameLevel g = new GameLevel(fl);
//        g.initialize();
//        g.getAnimationRunner().run(g);
        GameFlow gl = new GameFlow();
        gl.runLevels();
//        GUI g = new GUI("blabla", 800,600);
//        DrawSurface d = g.getDrawSurface();
//        drb.drawOn(d);
//        g.show(d);
    }
}
