package levelfromfile;

import gamesprites.Block;
import geometryprimitives.Point;
import geometryprimitives.Rectangle;
import interfaces.BlockCreator;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlocksFromSymbolsFactory {
    private Map<String, Integer> spacerWidths;
    private Map<String, BlockCreator> blockCreators;
    private Map<String, String> defaultMap;
    private int startX;
    private int startY;
    private int rowHeight;
    private String blockDefinition;
    private List<String> blockOrder;

    public BlocksFromSymbolsFactory(int startX, int startY, int rowHeight, String blockDefinition,
                                    List<String> blockOrder) {
        this.startX = startX;
        this.startY = startY;
        this.blockDefinition = blockDefinition;
        this.rowHeight = rowHeight;
        this.blockOrder = blockOrder;
        this.spacerWidths = new HashMap<>();
        this.blockCreators = new HashMap<>();
        this.defaultMap = new HashMap<>();
        createBlocksByFileDefinition();
    }

    public void createBlocksByFileDefinition() {
        File file = new File(blockDefinition);
        try {
            List<String> fileLines = Files.readAllLines(file.toPath());
            for (int i = 0; i < fileLines.size(); i++) {
                if (fileLines.get(i).equals("")) {
                    continue;
                }
                if (fileLines.get(i).charAt(0) == 'd') {
                    createDefaultMap(fileLines.get(i).substring(fileLines.get(i).indexOf("t") + 2));
                } else if (fileLines.get(i).charAt(0) == 's') {
                    List<String> sdefList = new ArrayList<>();
                    for (int j = i; j < fileLines.size(); j++) {
                        if (fileLines.get(j).equals("")) {
                            i++;
                            continue;
                        }
                        if (fileLines.get(j).charAt(0) == 's') {
                            i++;
                            sdefList.add(fileLines.get(j));
                        }
                    }
                    createSpaceMap(sdefList);
                } else if (fileLines.get(i).charAt(0) == 'b') {
                    List<String> bdefList = new ArrayList<>();
                    for (int j = i; j < fileLines.size(); j++) {
                        if (fileLines.get(j).equals("")) {
                            i++;
                            continue;
                        }
                        if (fileLines.get(j).charAt(0) == 'b') {
                            i++;
                            bdefList.add(fileLines.get(j));
                        }
                    }
                    createBlockCreatorMap(bdefList);
                }
            }
        } catch (IOException e) {
        }

    }

    private void createBlockCreatorMap(List<String> bdefList) {
        int startIndex;
        String tempLine;
        String key;
        Map<String, String> blockDefinitions = new HashMap<>();
        Map<String, String> defaultMap = this.defaultMap;
        for (int i = 0; i < bdefList.size(); i++) {
            tempLine = bdefList.get(i);
            tempLine += " ";
            startIndex = tempLine.indexOf("f") + 2;
            key = tempLine.substring(tempLine.indexOf(":", startIndex) + 1, tempLine.indexOf(" ", startIndex));
            startIndex = tempLine.indexOf(" ", startIndex) + 1;
            while (startIndex < tempLine.length()) {
                blockDefinitions.put(tempLine.substring(startIndex, tempLine.indexOf(":", startIndex)), tempLine.
                        substring(tempLine.indexOf(":", startIndex) + 1, tempLine.indexOf(" ", startIndex)));
                startIndex = tempLine.indexOf(" ", startIndex) + 1;
            }
            this.blockCreators.put(key, new DynamicBlockCreator(defaultMap, new HashMap<>(blockDefinitions)));
            blockDefinitions.clear();
        }
    }

    public void createDefaultMap(String line) {
        int startIndex = 0;
        int indexOfSeparator;
        line += " ";
        for (int i = 0; i < line.length(); i++) {
            indexOfSeparator = line.indexOf(":", startIndex);
            if (indexOfSeparator != -1) {
                this.defaultMap.put(line.substring(startIndex, indexOfSeparator),
                        line.substring(indexOfSeparator + 1, line.indexOf(" ", startIndex)));
                startIndex = line.indexOf(" ", startIndex) + 1;
            } else {
                break;
            }
        }
    }

    public void createSpaceMap(List<String> linesList) {
        int startIndex;
        String tempLine;
        for (int i = 0; i < linesList.size(); i++) {
            tempLine = linesList.get(i);
            startIndex = tempLine.indexOf("f") + 2;
            this.spacerWidths.put(tempLine.substring(tempLine.indexOf(":", startIndex) + 1,
                    tempLine.indexOf(" ", startIndex)), Integer.parseInt
                    (tempLine.substring(tempLine.lastIndexOf(":") + 1)));
        }
    }

    public int getSpaceWidth(String s) {
        return this.spacerWidths.get(s);
    }

    public Block getBlock(String s, int x, int y) {
        return this.blockCreators.get(s).create(x, y);
    }

    public boolean isSpaceSymbol(String s) {
        if (this.spacerWidths.containsKey(s)) {
            return true;
        }
        return false;
    }

    // returns true if 's' is a valid block symbol.
    public boolean isBlockSymbol(String s) {
        if (this.blockCreators.containsKey(s)) {
            return true;
        }
        return false;
    }

    public List<Block> createBlocksList() {
        createBlocksByFileDefinition();
        List<Block> blockList = new ArrayList<>();
        String symbol;
        String line;
        int startYpos = this.startY;
        int startXpos = this.startX;
        Block b = null;
        for (int i = 0; i < blockOrder.size(); i++) {
            line = this.blockOrder.get(i);
            if(line.length() == 1){
                startYpos += Integer.parseInt(this.defaultMap.get("height"));
            }
            else {
                for (int j = 0; j < line.length(); j++) {
                    symbol = String.valueOf(line.charAt(j));
                    if (this.spacerWidths.containsKey(symbol)) {
                        startXpos += this.spacerWidths.get(symbol);
                    }
                    else {
                        b = this.blockCreators.get(symbol).create(startXpos, startYpos);
                        blockList.add(b);
                        startXpos += b.getCollisionRectangle().getWidth();
                    }
                }
                startXpos = this.startX;
                startYpos += b.getCollisionRectangle().getHeight();
            }
        }
        return blockList;
    }
}
