package gamecontrollers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * The type High scores table.
 *
 * @author Roy Leibovitz <royleibo212@gmail.com>
 */
public class HighScoresTable {
    private List<ScoreInfo> scoreInfoList;
    private int size;
    private File fileName;

    /**
     * Instantiates a new High scores table.
     *
     * @param size the size
     */
    public HighScoresTable(int size) {
        this.scoreInfoList = new ArrayList<>();
        this.size = size;
    }

    /**
     * Parse line list.
     *
     * @param line the line
     * @return the list
     */
    public static List<String> parseLine(String line) {
        List<String> lineData = new ArrayList<>();
        lineData.add(line.substring(0, line.indexOf("$")));
        lineData.add(line.substring(line.indexOf("$") + 1, line.lastIndexOf("$")));
        return lineData;
    }

    /**
     * Load from file high scores table.
     *
     * @param filename the filename
     * @return the high scores table
     */
    public static HighScoresTable loadFromFile(File filename) {
        if (filename.exists()) {
            try {
                List<String> lineData = new ArrayList<>();
                List<String> fileLines = Files.readAllLines(filename.toPath());
                HighScoresTable hST = new HighScoresTable(fileLines.size());
                for (String line : fileLines) {
                    lineData = parseLine(line);
                    hST.add(new ScoreInfo(lineData.get(0), Integer.parseInt(lineData.get(1))));
                }
                return hST;
            } catch (IOException e) {
                return new HighScoresTable(0);
            }
        } else {
            return new HighScoresTable(0);
        }
    }

    /**
     * Add.
     *
     * @param score the score
     */
    public void add(ScoreInfo score) {
        if (this.scoreInfoList.size() == this.size) {
            if (this.scoreInfoList.get(this.scoreInfoList.size() - 1).getScore() <= score.getScore()) {
                this.scoreInfoList.remove(this.scoreInfoList.size() - 1);
                this.scoreInfoList.add(score);
            }
        } else {
            this.scoreInfoList.add(score);
        }
        Collections.sort(this.scoreInfoList, new ScoreComparator().reversed());
    }

    /**
     * Save.
     *
     * @param filename the filename
     * @throws IOException the io exception
     */
    public void save(File filename) throws IOException {
        boolean exist;
        FileWriter fW;
        if (fileName.getName() == "") {
            exist = this.fileName.delete();
            fW = new FileWriter(this.fileName, true);
        } else {
            exist = fileName.delete();
            fW = new FileWriter(fileName.getName(), true);
        }
        for (int i = 0; i < this.scoreInfoList.size(); i++) {
            fW.write(this.scoreInfoList.get(i).getName() + "$" + this.scoreInfoList.get(i).getScore() + "$");
            fW.write(System.lineSeparator());
        }
        fW.flush();
    }

    /**
     * Size int.
     *
     * @return the int
     */
    public int size() {
        return this.size;
    }

    /**
     * Gets high scores.
     *
     * @return the high scores
     */
    public List<ScoreInfo> getHighScores() {
        return this.scoreInfoList;
    }

    /**
     * Gets rank.
     *
     * @param score the score
     * @return the rank
     */
    public int getRank(int score) {
        for (int i = 0; i < this.scoreInfoList.size(); i++) {
            if (this.scoreInfoList.get(i).getScore() <= score) {
                return i + 1;
            }
        }
        return this.scoreInfoList.size() + 1;
    }

    /**
     * Clear.
     */
    public void clear() {
        this.scoreInfoList.clear();
    }

    /**
     * Load.
     *
     * @param filename the filename
     * @throws IOException the io exception
     */
    public void load(File filename) throws IOException {
        this.fileName = filename;
        List<String> fileLines = Files.readAllLines(filename.toPath());
        List<String> lineData = new ArrayList();
        for (String line : fileLines) {
            lineData = parseLine(line);
            this.scoreInfoList.add(new ScoreInfo(lineData.get(0), Integer.parseInt(lineData.get(1))));
        }
    }

    /**
     * Create file.
     *
     * @param file the file name
     * @throws IOException the io exception
     */
    public void createFile(String file) throws IOException {
        this.fileName = new File(file);
        this.load(this.fileName);
    }

    /**
     * The type Score comparator.
     */
    public class ScoreComparator implements Comparator<ScoreInfo> {
        /**
         * Compare function implementation.
         *
         * @param si1 first score info
         * @param si2 second score info
         * @return which one is bigger
         */
        public int compare(ScoreInfo si1, ScoreInfo si2) {
            return si1.getScore() - si2.getScore();
        }
    }

    /**
     * Gets size.
     *
     * @return the size
     */
    public int getSize() {
        return size;
    }
}