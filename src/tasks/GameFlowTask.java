package tasks;

import gamecontrollers.HighScoresTable;
import interfaces.Task;
import rungame.GameFlow;

public class GameFlowTask<T> implements Task<T> {
    private String[] args;
    private HighScoresTable hst;

    public GameFlowTask(String[] args, HighScoresTable hst){
        this.args = args;
        this.hst = hst;
    }

    public T run() {
        new GameFlow(this.args, this.hst).runLevels();
        return null;
    }
}
