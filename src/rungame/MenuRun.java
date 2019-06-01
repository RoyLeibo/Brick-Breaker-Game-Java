package rungame;

import animations.HighScoresAnimation;
import animations.MenuAnimation;
import gamecontrollers.HighScoresTable;
import interfaces.Task;
import tasks.GameFlowTask;
import tasks.HighScoresTaks;
import tasks.QuitTask;

import java.io.IOException;
import java.util.List;

public class MenuRun {
    private HighScoresTable hst;
    private String[] args;
    private MenuAnimation menuAnimation;

    public MenuRun(String[] args, int size) {
        this.hst = new HighScoresTable(size);
        try {
            this.hst.createFile("highscores.txt");
        } catch (IOException e) {
        }
        this.args = args;
        this.menuAnimation.addSelection("s", "Start New Game", new GameFlowTask<Void>(args, this.hst));
        this.menuAnimation.addSelection("h", "High Scores Table", new HighScoresTaks<Void>
                (new AnimationRunner(10), new HighScoresAnimation(this.hst)));
        this.menuAnimation.addSelection("q", "Quit Game", new QuitTask<Void>());
    }

    public void runMenu(){
        new AnimationRunner(10).run(this.menuAnimation);
    }
}
