package levelfromfile;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Color parser.
 */
public class ColorParser {
    private Map<String, Color> colors;

    /**
     * Instantiates a new Color parser.
     * Create a colors map.
     */
    public ColorParser() {
        colors = new HashMap<>();
        colors.put("BLACK", Color.BLACK);
        colors.put("BLUE", Color.BLUE);
        colors.put("CYAN", Color.CYAN);
        colors.put("DARK_GRAY", Color.DARK_GRAY);
        colors.put("GRAY", Color.GRAY);
        colors.put("GREEN", Color.GREEN);
        colors.put("LIGHT_GRAY", Color.LIGHT_GRAY);
        colors.put("MAGENTA", Color.MAGENTA);
        colors.put("ORANGE", Color.ORANGE);
        colors.put("PINK", Color.PINK);
        colors.put("RED", Color.RED);
        colors.put("WHITE", Color.WHITE);
        colors.put("YELLOW", Color.YELLOW);
    }

    /**
     * Creates color from a given string color.
     *
     * @param s the s
     * @return the color
     */
    public Color colorFromString(String s) {
        String temp = "";
        for (int i = 0; i < s.length(); i++) {
            temp += s.charAt(i);
            // if the color is RGB type
            if (temp.equals("RGB")) {
                return parseRGB(s.substring(i + 2));
            }
        }
        // get from map
        return this.colors.get(s.substring(s.indexOf('(') + 1, s.indexOf(')')).toUpperCase());
    }

    /**
     * Parse string and create color from RGB.
     *
     * @param s the s
     * @return the color
     */
    public Color parseRGB(String s) {
        int x = Integer.parseInt(s.substring(0, s.indexOf(',')));
        int y = Integer.parseInt(s.substring(s.indexOf(',') + 1, s.lastIndexOf(',')));
        int z = Integer.parseInt(s.substring(s.lastIndexOf(',') + 1, s.indexOf(')')));
        return new Color(x, y, z);
    }
}