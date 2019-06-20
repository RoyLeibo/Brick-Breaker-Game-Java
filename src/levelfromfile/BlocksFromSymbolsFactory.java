package levelfromfile;

import gamesprites.Block;
import interfaces.BlockCreator;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type Blocks from symbols factory.
 *
 * @author Roy Leibovitz <royleibo212@gmail.com>
 */
public class BlocksFromSymbolsFactory {
    private Map<String, Integer> spacerWidths;
    private Map<String, BlockCreator> blockCreators;
    private Map<String, String> defaultMap;
    private int startX;
    private int startY;
    private int rowHeight;
    private String blockDefinition;
    private List<String> blockOrder;

    /**
     * Instantiates a new Blocks from symbols factory.
     *
     * @param startX          the start x
     * @param startY          the start y
     * @param rowHeight       the row height
     * @param blockDefinition the block definition
     * @param blockOrder      the block order
     */
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

    /**
     * Create blocks by file definition.
     */
    public void createBlocksByFileDefinition() {
        File file = new File(blockDefinition);
        // read all lines
        List<String> fileLines = this.getListFromFile(file.toPath().toString());
        for (int i = 0; i < fileLines.size(); i++) {
            if (fileLines.get(i).equals("")) {
                continue;
            }
            // find default definitions
            if (fileLines.get(i).charAt(0) == 'd') {
                createDefaultMap(fileLines.get(i).substring(fileLines.get(i).indexOf("t") + 2));
            } else if (fileLines.get(i).charAt(0) == 's') {
                // find spacers definitions
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
                // find blocks definitions
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
    }

    /**
     * This function creates the block definitions map.
     *
     * @param bdefList block definitions list
     */
    private void createBlockCreatorMap(List<String> bdefList) {
        int startIndex;
        String tempLine;
        String key;
        Map<String, String> blockDefinitions = new HashMap<>();
        Map<String, String> defaultsMap = this.defaultMap;
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
            this.blockCreators.put(key, new DynamicBlockCreator(defaultsMap, new HashMap<>(blockDefinitions)));
            blockDefinitions.clear();
        }
    }

    /**
     * This function creates default map.
     *
     * @param line the line
     */
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

    /**
     * This function creates space map.
     *
     * @param linesList the lines list
     */
    public void createSpaceMap(List<String> linesList) {
        int startIndex;
        String tempLine;
        for (int i = 0; i < linesList.size(); i++) {
            tempLine = linesList.get(i);
            startIndex = tempLine.indexOf("f") + 2;
            this.spacerWidths.put(tempLine.substring(tempLine.indexOf(":", startIndex) + 1,
                    tempLine.indexOf(" ", startIndex)), Integer.parseInt(
                    tempLine.substring(tempLine.lastIndexOf(":") + 1)));
        }
    }

    /**
     * Gets space width.
     *
     * @param s the s
     * @return the space width
     */
    public int getSpaceWidth(String s) {
        return this.spacerWidths.get(s);
    }

    /**
     * Gets block.
     *
     * @param s the s
     * @param x the x
     * @param y the y
     * @return the block
     */
    public Block getBlock(String s, int x, int y) {
        return this.blockCreators.get(s).create(x, y);
    }

    /**
     * Is space symbol boolean.
     *
     * @param s the s
     * @return the boolean
     */
    public boolean isSpaceSymbol(String s) {
        if (this.spacerWidths.containsKey(s)) {
            return true;
        }
        return false;
    }

    /**
     * Is block symbol boolean.
     *
     * @param s the s
     * @return the boolean
     */
// returns true if 's' is a valid block symbol.
    public boolean isBlockSymbol(String s) {
        if (this.blockCreators.containsKey(s)) {
            return true;
        }
        return false;
    }

    /**
     * Gets list from file.
     *
     * @param file the file
     * @return the list from file
     */
    public List<String> getListFromFile(String file) {
        List<String> fileLines = new ArrayList<>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(ClassLoader.getSystemClassLoader().
                    getResourceAsStream(file)));
            String line;
            if (reader != null) {
                try {
                    while ((line = reader.readLine()) != null) {
                        fileLines.add(line);
                    }
                } catch (IOException e) {
                    System.out.println();
                }
            }
        } catch (Exception e) {
            System.out.println();
        }
        return fileLines;
    }

    /**
     * This function creates blocks list list.
     *
     * @return the list
     */
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
            // a line space
            if (line.length() == 1) {
                startYpos += this.rowHeight;
            } else {
                for (int j = 0; j < line.length(); j++) {
                    // get the symbol type
                    symbol = String.valueOf(line.charAt(j));
                    // checks if the symbol is space
                    if (this.spacerWidths.containsKey(symbol)) {
                        startXpos += this.spacerWidths.get(symbol);
                    } else {
                        // creates a block according to the x and y calculated
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