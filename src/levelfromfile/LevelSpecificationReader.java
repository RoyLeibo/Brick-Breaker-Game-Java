package levelfromfile;

import biuoop.DrawSurface;
import gamesprites.Block;
import interfaces.LevelInformation;
import interfaces.Sprite;
import levelfromfile.ColorParser;
import others.Velocity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type Level specification reader.
 *
 * @author Roy Leibovitz <royleibo212@gmail.com> Roy Leibovitz
 */
public class LevelSpecificationReader {
    /**
     * The Level information list.
     */
    List<LevelInformation> levelInformationList;

    /**
     * Create level information list list.
     *
     * @param filename the filename
     * @return the list
     * @throws IOException the io exception
     */
    public List<LevelInformation> createLevelInformationList(File filename) throws IOException {
        this.levelInformationList = new ArrayList<>();
        List<String> fileLines = Files.readAllLines(filename.toPath());
        for (int i = 0; i < fileLines.size(); i++) {
            // find where the level definitions starts
            if (fileLines.get(i).equals("START_LEVEL")) {
                for (int j = i + 1; j < fileLines.size(); j++) {
                    // find where the block definitions starts
                    if (fileLines.get(j).equals("START_BLOCKS")) {
                        for (int k = j + 1; k < fileLines.size(); k++) {
                            // find where the block definitions ends
                            if (fileLines.get(k).equals("END_BLOCKS")) {
                                // create level information
                                this.levelInformationList.add(createLevelInformation(parseLevelInformation
                                        (fileLines.subList(i + 1, j)), fileLines.subList(j + 1, k)));
                                i += k + 1;
                                break;
                            }
                        }
                        break;
                    }
                }
            }
        }
        return levelInformationList;
    }

    /**
     * Parse level information list to a map.
     *
     * @param levelLines the level lines
     * @return the map
     */
    public Map<String, String> parseLevelInformation(List<String> levelLines) {
        String line;
        Map<String, String> levelInformationMap = new HashMap<>();
        for (int i = 0; i < levelLines.size(); i++) {
            line = levelLines.get(i);
            for (int j = 0; j < line.length(); j++) {
                // find the separator symbol
                if (line.charAt(j) == ':') {
                    // add to map left side as key and right side as value
                    levelInformationMap.put(line.substring(0, j), line.substring(j + 1));
                    break;
                }
            }
        }
        return levelInformationMap;
    }

    /**
     * This function creates level information level information.
     *
     * @param levelInformationMap the level information map
     * @param blocksOrder         the blocks order
     * @return the level information
     */
    public LevelInformation createLevelInformation(Map<String, String> levelInformationMap, List<String> blocksOrder) {
        String levelName = "";
        List<Velocity> ballVelocities = null;
        String background = "";
        int paddleSpeed = 200;
        int paddleWidth = 200;
        int numOfBlocks = 10;
        String blocksDefinitions = "";
        int startX = 0;
        int startY = 0;
        int rowHeight = 20;
        // for each key-value from the definition map
        for (Map.Entry<String, String> entry : levelInformationMap.entrySet()) {
            // check the matching data
            switch (entry.getKey()) {
                case "level_name":
                    levelName = entry.getValue();
                    break;
                case "ball_velocities":
                    ballVelocities = createVelocityList(entry.getValue());
                    break;
                case "background":
                    background = entry.getValue();
                    break;
                case "paddle_speed":
                    paddleSpeed = Integer.parseInt(entry.getValue());
                    break;
                case "paddle_width":
                    paddleWidth = Integer.parseInt(entry.getValue());
                    break;
                case "num_blocks":
                    numOfBlocks = Integer.parseInt(entry.getValue());
                    break;
                case "block_definitions":
                    blocksDefinitions = entry.getValue();
                    break;
                case "blocks_start_x":
                    startX = Integer.parseInt(entry.getValue());
                    break;
                case "blocks_start_y":
                    startY = Integer.parseInt(entry.getValue());
                    break;
                case "row_height":
                    rowHeight = Integer.parseInt(entry.getValue());
                    break;
            }
        }
        // copy each data to a final variable
        final String finalLevelName = levelName;
        final List<Velocity> finalBallVelocities = ballVelocities;
        final String finalBackground = background;
        final int finalPaddleSpeed = paddleSpeed;
        final int finalPaddleWidth = paddleWidth;
        final int finalNumOfBlocks = numOfBlocks;
        final int finalNumberOfBalls;
        if (ballVelocities != null) {
            finalNumberOfBalls = ballVelocities.size();
        } else {
            finalNumberOfBalls = 1;
        }
        final String finalBlockDefinitions = blocksDefinitions;
        final int finalStartX = startX;
        final int finalStartY = startY;
        final int finalRowHeight = rowHeight;
        // create new implementation for a LevelInformation using the data from the map
        return new LevelInformation() {
            private Image img;
            public int numberOfBalls() {
                return finalNumberOfBalls;
            }

            public List<Velocity> initialBallVelocities() {
                return finalBallVelocities;
            }

            public int paddleSpeed() {
                return finalPaddleSpeed;
            }

            public int paddleWidth() {
                return finalPaddleWidth;
            }

            public String levelName() {
                return finalLevelName;
            }

            public Sprite getBackground() {
                return new Sprite() {
                    public void drawOn(DrawSurface surface) {
                        // check if the background is a color or image
                        if (finalBackground.charAt(0) == 'c') {
                            // parse color
                            surface.setColor(new ColorParser().colorFromString(finalBackground.substring
                                    (finalBackground.indexOf('(') + 1, finalBackground.indexOf(")") + 1)));
                            surface.fillRectangle(0, 0, 800, 600);
                        } else if (finalBackground.charAt(0) == 'i') {
                            try {
                                // import image
                                if(img == null) {
                                    img = ImageIO.read(new File(finalBackground.substring
                                            (finalBackground.indexOf("(") + 1, finalBackground.indexOf(")"))));
                                }
                                surface.drawImage(0, 0, img); // draw the image at location 0, 0.
                            } catch (IOException e) {
                                surface.setColor(Color.WHITE);
                                surface.fillRectangle(0, 0, 800, 600);
                            }
                        } else {
                            surface.setColor(Color.WHITE);
                            surface.fillRectangle(0, 0, 800, 600);
                        }
                    }

                    public void timePassed() {
                    }
                };
            }

            public List<Block> blocks() {
                // create a list of block using the block factory
                return new BlocksFromSymbolsFactory(finalStartX, finalStartY, finalRowHeight, finalBlockDefinitions,
                        blocksOrder)
                        .createBlocksList();
            }

            public int numberOfBlocksToRemove() {
                return finalNumOfBlocks;
            }

            public Color getBallsColor() {
                return Color.white;
            }
        };
    }

    /**
     * Create velocity list.
     *
     * @param line the line
     * @return the list
     */
    public List<Velocity> createVelocityList(String line) {
        int startIndex = 0;
        List<Velocity> velocityList = new ArrayList<>();
        int separator;
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == ',') {
                separator = line.indexOf(' ', startIndex);
                if(separator == -1) {
                    velocityList.add(createVelocity(line.substring(startIndex)));
                    break;
                }
                else {
                    velocityList.add(createVelocity(line.substring(startIndex, separator)));
                }
                startIndex = separator + 1;
                i = startIndex;
            }
        }

        return velocityList;
    }

    /**
     * Create velocity.
     *
     * @param line the line
     * @return the velocity
     */
    public Velocity createVelocity(String line) {
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == ',') {
                return new Velocity(Double.parseDouble(line.substring(0, i)), Double.parseDouble(line.substring(i + 1)));
            }
        }
        return new Velocity(Double.parseDouble(line), Double.parseDouble(line));
    }

    /**
     * Gets level information list.
     *
     * @return the level information list
     */
    public List<LevelInformation> getLevelInformationList() {
        return levelInformationList;
    }
}
