public class BallRemover implements HitListener {
    private Game game;
    private Counter remainingBalls;
    private int ballsRemoved;

    public BallRemover(Game game, Counter removedBalls) {
        this.game = game;
        this.remainingBalls = removedBalls;
    }

    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        ballsRemoved++;
    }

    public int getBallsRemoved() {
        return ballsRemoved;
    }

    public void setBallsRemoved(int ballsRemoved) {
        this.ballsRemoved = ballsRemoved;
    }
}
