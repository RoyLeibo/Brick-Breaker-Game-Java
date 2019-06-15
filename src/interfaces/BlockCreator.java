package interfaces;

import gamesprites.Block;

import java.util.HashMap;
import java.util.Map;

public interface BlockCreator {
    Block create(int xpos, int ypos);
}
