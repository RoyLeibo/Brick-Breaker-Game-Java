import animations.KeyPressStoppableAnimation;
import animations.LoseScreen;
import animations.PauseScreen;
import animations.WinScreen;
import backgrounds.CirclesBackground;
import backgrounds.DirectHitBackground;
import backgrounds.Emoji;
import biuoop.DrawSurface;
import biuoop.GUI;
import interfaces.Animation;
import levels.DirectHit;
import levels.FullLines;
import levels.PartialLines;
import levels.Pyramid;
import rungame.AnimationRunner;
import rungame.GameFlow;
import rungame.GameLevel;

/**
 * This class runs the game.
 *
 * @author Roy Leibovitz <royleibo212@gmail.com> Roy Leibovitz
 */
public class Ass6Game {

    /**
     * This is the main method which creates an instance of GameLevel and call
     * it's initialize and run function.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        GameFlow gl = new GameFlow(args);
        gl.runLevels();
    }
}
