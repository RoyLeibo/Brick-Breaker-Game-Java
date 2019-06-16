package rungame;

import animations.HighScoresAnimation;
import animations.KeyPressStoppableAnimation;
import biuoop.DrawSurface;
import biuoop.GUI;
import gamecontrollers.HighScoresTable;
import interfaces.Task;
import others.MenuItem;
import tasks.*;

import java.io.IOException;

public class RunGame {
    public RunGame(String[] args, int size) {
        AnimationRunner animationRunner = new AnimationRunner(60);
        HighScoresTable hst = new HighScoresTable(size);
        try {
            hst.createFile("highscores.txt");
        } catch (IOException e) {
        }
        GUI gui = animationRunner.getGui();
        DrawSurface d;
        MenuRun<Task<Void>> menuRun = new MenuRun(args, size, animationRunner);
        menuRun.addSelection("s", "Start New Game", new LevelsFromFileTask<Void>(animationRunner, hst, gui));
//        menuRun.addSelection("s", "Start New Game", new GameFlowTask<Void>(args, hst, animationRunner));
        menuRun.addSelection("h", "High Scores Table", new HighScoresTaks<Void>
                (animationRunner, new HighScoresAnimation(hst), gui));
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
