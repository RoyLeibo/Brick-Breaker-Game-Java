package levelfromfile;

import gamesprites.Block;
import geometryprimitives.Point;
import interfaces.BlockCreator;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DynamicBlockCreator implements BlockCreator {
    private Map<String, String> defaultBlockMap;
    private Map<String, String> blockDefinitionsMap;

    public DynamicBlockCreator(Map<String, String> defaultBlock, Map<String, String> blockDefinitionsMap) {
        this.defaultBlockMap = defaultBlock;
        this.blockDefinitionsMap = blockDefinitionsMap;
    }

    public Block create(int xpos, int ypos) {
        int width;
        if (blockDefinitionsMap.containsKey("width")) {
            width = Integer.parseInt(blockDefinitionsMap.get("width"));
        } else {
            width = Integer.parseInt(defaultBlockMap.get("width"));
        }
        int height;
        if (blockDefinitionsMap.containsKey("height")) {
            height = Integer.parseInt(blockDefinitionsMap.get("height"));
        } else {
            height = Integer.parseInt(defaultBlockMap.get("height"));
        }
        Color stroke;
        if (blockDefinitionsMap.containsKey("stroke")) {
            stroke = new ColorParser().colorFromString(blockDefinitionsMap.get("stroke"));
        } else {
            stroke = new ColorParser().colorFromString(defaultBlockMap.get("stroke"));
        }
        String hitPoints;
        if (blockDefinitionsMap.containsKey("hit_points")) {
            hitPoints = blockDefinitionsMap.get("hit_points");
        } else {
            hitPoints = defaultBlockMap.get("hit_points");
        }
        Block b = new Block(new geometryprimitives.Rectangle(new Point(xpos, ypos), width, height),
                createBackgroundMap(Integer.parseInt(hitPoints)), hitPoints, stroke);
        return b;
    }

    public Map<Integer, String> createBackgroundMap(int hitPoints) {
        Map<Integer, String> backgroundMap = new HashMap<>();
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
}
