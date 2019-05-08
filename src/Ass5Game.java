import rungame.Game;
/**
 * This class runs the game.
 *
 * @author Roy Leibovitz
 */
public class Ass5Game {

    /**
     * This is the main method which creates an instance of Game and call
     * it's initialize and run function.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Game g = new Game();
        g.initialize();
        g.run();
    }
}
