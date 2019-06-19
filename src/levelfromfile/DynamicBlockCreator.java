package levelfromfile;

import gamesprites.Block;
import geometryprimitives.Point;
import interfaces.BlockCreator;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Dynamic block creator.
 *
 * @author Roy Leibovitz <royleibo212@gmail.com>
 */
public class DynamicBlockCreator implements BlockCreator {
    private Map<String, String> defaultBlockMap;
    private Map<String, String> blockDefinitionsMap;

    /**
     * Instantiates a new Dynamic block creator.
     *
     * @param defaultBlock        the default block
     * @param blockDefinitionsMap the block definitions map
     */
    public DynamicBlockCreator(Map<String, String> defaultBlock, Map<String, String> blockDefinitionsMap) {
        this.defaultBlockMap = defaultBlock;
        this.blockDefinitionsMap = blockDefinitionsMap;
    }

    /**
     * This function creates a single block.
     * The function checks for each information about the block at the definitionMap.
     * If it doesn't contains the data needed, the imformation takes from the default map.
     *
     * @param xpos upper left x
     * @param ypos upper left y
     * @return block
     */
    public Block create(int xpos, int ypos) {
        int width;
        if (blockDefinitionsMap.containsKey("width")) {
            width = Integer.parseInt(blockDefinitionsMap.get("width"));
        } else if (defaultBlockMap.containsKey("width")) {
            width = Integer.parseInt(defaultBlockMap.get("width"));
        } else {
            width = 100;
        }
        int height;
        if (blockDefinitionsMap.containsKey("height")) {
            height = Integer.parseInt(blockDefinitionsMap.get("height"));
        } else if (defaultBlockMap.containsKey("height")) {
            height = Integer.parseInt(defaultBlockMap.get("height"));
        } else {
            height = 100;
        }
        Color stroke;
        if (blockDefinitionsMap.containsKey("stroke")) {
            stroke = new ColorParser().colorFromString(blockDefinitionsMap.get("stroke"));
        } else if (defaultBlockMap.containsKey("stroke")) {
            stroke = new ColorParser().colorFromString(defaultBlockMap.get("stroke"));
        } else {
            stroke = Color.BLACK;
        }
        String hitPoints;
        if (blockDefinitionsMap.containsKey("hit_points")) {
            hitPoints = blockDefinitionsMap.get("hit_points");
        } else if (defaultBlockMap.containsKey("hit_points")) {
            hitPoints = defaultBlockMap.get("hit_points");
        } else {
            hitPoints = "3";
        }
        String isText;
        if (defaultBlockMap.containsKey("text")) {
            isText = "false";
        } else {
            isText = "true";
        }
        Map<Integer, String> backgroundMap = createBackgroundMap(Integer.parseInt(hitPoints));
        Block b = new Block(new geometryprimitives.Rectangle(new Point(xpos, ypos), width, height),
                createImgMap(backgroundMap), createColorsFromMap(backgroundMap), hitPoints, stroke, isText);
        return b;
    }

    /**
     * Create backgroundMap map.
     * for each fill-i (0 < i <= hitPoints) the function will add the color to a map as a string.
     *
     * @param hitPoints the hit points
     * @return the map
     */
    public Map<Integer, String> createBackgroundMap(int hitPoints) {
        Map<Integer, String> backgroundMap = new HashMap<>();
        // define a "master key" which is the default background when it's not defined.
        String masterKey = "fill";
        String masterValue = "";
        if (this.blockDefinitionsMap.containsKey(masterKey)) {
            masterValue = this.blockDefinitionsMap.get(masterKey);
        } else if (this.defaultBlockMap.containsKey(masterKey)) {
            masterValue = this.defaultBlockMap.get(masterKey);
        }
        for (int i = 0; i < hitPoints; i++) {
            String key = "fill-" + (i + 1);
            if (this.blockDefinitionsMap.containsKey(key)) {
                backgroundMap.put(i + 1, this.blockDefinitionsMap.get(key));
            } else if (this.defaultBlockMap.containsKey(key)) {
                backgroundMap.put(i + 1, this.defaultBlockMap.get(key));
            } else {
                backgroundMap.put(i + 1, masterValue);
            }
        }

        return backgroundMap;
    }

    /**
     * Create imgMap for each fill-i as defined.
     *
     * @param imgsMap the imgs map
     * @return the map
     */
    public Map<Integer, String> createImgMap(Map<Integer, String> imgsMap) {
        Map<Integer, String> imgMap = new HashMap<>();
        for (Map.Entry<Integer, String> entry : imgsMap.entrySet()) {
            // if the value is a image
            if (entry.getValue().charAt(0) != 'c') {
                imgMap.put(entry.getKey(), entry.getValue());
            }
        }
        return imgMap;
    }

    /**
     * Create colorsFromMap for each fill-i as defined.
     *
     * @param backgroundMap the background map
     * @return the map
     */
    public Map<Integer, Color> createColorsFromMap(Map<Integer, String> backgroundMap) {
        Map<Integer, Color> newBackgroundMap = new HashMap<>();
        ColorParser cp = new ColorParser();
        String temp;
        for (Map.Entry<Integer, String> entry : backgroundMap.entrySet()) {
            temp = entry.getValue();
            // if the value is a color
            if (entry.getValue().charAt(0) == 'c') {
                newBackgroundMap.put(entry.getKey(), cp.colorFromString(temp.substring(
                        temp.indexOf('(') + 1, temp.indexOf(")") + 1)));
            }
        }
        return newBackgroundMap;
    }
}
