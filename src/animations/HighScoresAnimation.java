package animations;

import biuoop.DrawSurface;
import gamecontrollers.HighScoresTable;
import gamecontrollers.ScoreInfo;
import interfaces.Animation;

public class HighScoresAnimation implements Animation {
    private HighScoresTable hst;
    private boolean isRunning;

    public HighScoresAnimation(HighScoresTable hst){
        this.hst = hst;
    }

    /**
     * Do one frame.
     *
     * @param d the d
     */
    public void doOneFrame(DrawSurface d){
        d.drawText(250, 100, "High Scores Table:", 40);
        for (int i = 0 ; i < this.hst.getHighScores().size(); i++) {
            d.drawText(280, 120 + (i+1)*30, (i+1)+".  " + this.hst.getHighScores().get(i).getName()
                    + ":   ", 30);
            d.drawText(480, 120 +(i+1)*30, String.valueOf(this.hst.getHighScores().get(i).getScore()), 30);
        }
    }

    /**
     * Should stop boolean.
     *
     * @return the boolean
     */
    public boolean shouldStop(){
        return isRunning;
    }
}
