package rungame;

import animations.HighScoresAnimation;
import biuoop.DrawSurface;
import biuoop.GUI;
import gamecontrollers.HighScoresTable;
import interfaces.Task;
import others.MenuItem;
import tasks.HighScoresTask;
import tasks.LevelsFromFileTask;
import tasks.QuitTask;

import java.io.IOException;

/**
 * The type Run game.
 *
 * @author Roy Leibovitz <royleibo212@gmail.com> Roy Leibovitz
 */
public class RunGame {
    /**
     * Instantiates a new Run game.
     *
     * @param args the args
     * @param size the size
     */
    public RunGame(String[] args, int size) {
        AnimationRunner animationRunner = new AnimationRunner(60);
        HighScoresTable hst = new HighScoresTable(size);
        try {
            hst.createFile("resources/highscores.txt");
        } catch (IOException e) {
            System.out.println();
        }
        GUI gui = animationRunner.getGui();
        DrawSurface d;
        String levelsSetPath;
        if (args.length == 0) {
            levelsSetPath = "level_sets.txt";
        } else {
            levelsSetPath = args[0];
        }
        MenuRun<Task<Void>> menuRun = new MenuRun(args, size, animationRunner);
        menuRun.addSelection("s", "Start New Game", new LevelsFromFileTask<Void>(animationRunner, hst, gui
                , levelsSetPath));
//        menuRun.addSelection("s", "Start New Game", new GameFlowTask<Void>(args, hst, animationRunner));
        menuRun.addSelection("h", "High Scores Table", new HighScoresTask<Void>(animationRunner
                , new HighScoresAnimation(hst), gui));
        menuRun.addSelection("q", "Quit Game", new QuitTask<Void>());
        d = gui.getDrawSurface();
        menuRun.doOneFrame(d);
        gui.show(d);
        boolean notPressed = true;
        while (true) {
            for (MenuItem<Task<Void>> menu : menuRun.getMenuItemsList()) {
                if (gui.getKeyboardSensor().isPressed(menu.getKey())) {
                    menuRun.setRunning(false);
                    menu.getReturnValue().run();
                    notPressed = false;
                    break;
                }
            }
            if (!notPressed) {
                d = gui.getDrawSurface();
                menuRun.doOneFrame(d);
                gui.show(d);
                notPressed = true;
            }
        }
    }
}