public class BlockRemover implements HitListener {
    private Game game;
    private Counter remainingBlocks;

    public BlockRemover(Game game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

    // Blocks that are hit and reach 0 hit-points should be removed
    // from the game. Remember to remove this listener from the block
    // that is being removed from the game.
    public void hitEvent(Block beingHit, Ball hitter) {
        if(Integer.parseInt(beingHit.getHitsLeft()) == 1) {
            beingHit.removeHitListener(this);
            beingHit.removeFromGame(game);
        }
        else {
            beingHit.setHitsLeft(String.valueOf(Integer.parseInt(beingHit.getHitsLeft())-1));
        }
    }
}