import backgrounds.DirectHitBackground;
import biuoop.DrawSurface;
import biuoop.GUI;
import levels.DirectHit;
import rungame.GameLevel;

/**
 * This class runs the game.
 *
 * @author Roy Leibovitz
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
        GameLevel g = new GameLevel(drb);
        g.initialize();
        g.getAnimationRunner().run(g);
//        GUI g = new GUI("blabla", 800,600);
//        DrawSurface d = g.getDrawSurface();
//        drb.drawOn(d);
//        g.show(d);
    }
}
