package rungame;

import animations.HighScoresAnimation;
import animations.MenuAnimation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import gamecontrollers.HighScoresTable;
import interfaces.Animation;
import interfaces.Task;
import others.MenuItem;
import tasks.GameFlowTask;
import tasks.HighScoresTaks;
import tasks.QuitTask;

import java.io.IOException;
import java.util.List;

public class MenuRun implements Animation {
    private HighScoresTable hst;
    private String[] args;
    private MenuAnimation menuAnimation;
    private boolean isRunning;
    private AnimationRunner animationRunner;

    public MenuRun(String[] args, int size, AnimationRunner animationRunner) {
        this.isRunning = true;
        this.animationRunner = animationRunner;
        this.hst = new HighScoresTable(size);
        try {
            this.hst.createFile("highscores.txt");
        } catch (IOException e) {
        }
        this.args = args;
        this.menuAnimation = new MenuAnimation<Task<Void>>();
        this.menuAnimation.addSelection("s", "Start New Game", new GameFlowTask<Void>(args, this.hst));
        this.menuAnimation.addSelection("h", "High Scores Table", new HighScoresTaks<Void>
                (this.animationRunner, new HighScoresAnimation(this.hst)));
        this.menuAnimation.addSelection("q", "Quit Game", new QuitTask<Void>());
    }

    public boolean shouldStop() {
        return isRunning;
    }

    public void doOneFrame(DrawSurface d) {
        this.menuAnimation.doOneFrame(d);
        List<MenuItem<Task<Void>>> menuItemList = this.menuAnimation.getMenuItemsList();
        for (MenuItem<Task<Void>> menu: menuItemList) {
            if(this.animationRunner.getGui().getKeyboardSensor().isPressed(menu.getKey())){
                this.isRunning = false;
                menu.getReturnValue().run();
            }
        }
    }
}
