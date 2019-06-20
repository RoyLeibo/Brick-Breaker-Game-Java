import rungame.RunGame;

/**
 * This class runs the game.
 *
 * @author Roy Leibovitz <royleibo212@gmail.com> Roy Leibovitz
 */
public class Ass7Game {

    /**
     * This is the main method which creates an instance of GameLevel and call
     * it's initialize and run function.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        RunGame run = new RunGame(args, 6);
    }
}
