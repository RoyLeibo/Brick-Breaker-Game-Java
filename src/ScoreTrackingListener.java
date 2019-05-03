public class ScoreTrackingListener implements HitListener {
    private Counter scoreCounter;
    private Block scoreBlock;

    public ScoreTrackingListener(Counter scoreCounter) {
        this.scoreCounter= scoreCounter;
    }

    public void hitEvent(Block beingHit, Ball hitter) {
        if (Integer.parseInt(beingHit.getHitsLeft()) == 1) {
            this.scoreCounter.increase(10);
            this.scoreBlock.setHitsLeft("Score: " + this.scoreCounter.getValue());
        } else {
            this.scoreCounter.increase(5);
            this.scoreBlock.setHitsLeft("Score: " + this.scoreCounter.getValue());
        }
    }

    public Counter getScoreCounter() {
        return this.scoreCounter;
    }

    public void setScoreBlock(Block b){
        this.scoreBlock = b;
    }
}
