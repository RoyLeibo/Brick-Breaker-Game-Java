package interfaces;

import gamesprites.Block;

/**
 * The interface Block creator.
 *
 * @author Roy Leibovitz <royleibo212@gmail.com>
 */
public interface BlockCreator {
    /**
     * Create block.
     *
     * @param xpos the xpos
     * @param ypos the ypos
     * @return the block
     */
    Block create(int xpos, int ypos);
}
