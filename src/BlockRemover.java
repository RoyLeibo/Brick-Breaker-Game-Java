public class BlockRemover implements HitListener {
    private Game game;
    private Counter remainingBlocks;

    public BlockRemover(Game game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

    public void hitEvent(Block beingHit, Ball hitter) {
        if(Integer.parseInt(beingHit.getHitsLeft()) == 1) {
            beingHit.removeHitListener(this);
            beingHit.removeFromGame(game);
            this.remainingBlocks.increase(1);
        }
        else {
            beingHit.setHitsLeft(String.valueOf(Integer.parseInt(beingHit.getHitsLeft())-1));
        }
    }

    public Counter getRemainingBlocks(){
        return this.remainingBlocks;
    }
}