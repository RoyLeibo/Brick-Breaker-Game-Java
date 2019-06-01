package gamecontrollers;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class HighScoresTable {

    private List<ScoreInfo> scoreInfoList;
    private int size;

    public HighScoresTable(int size) {
        this.scoreInfoList = new ArrayList<>();
        this.size = size;
    }

    static public List<String> parseLine(String line){
        List<String> lineData = new ArrayList<>();
        lineData.add(line.substring(0, line.indexOf("$")));
        lineData.add(line.substring(line.indexOf("$")+1, line.lastIndexOf("$")));
        return lineData;
    }

    // Read a table from file and return it.
    // If the file does not exist, or there is a problem with
    // reading it, an empty table is returned.
    public static HighScoresTable loadFromFile(File filename) {
        if(filename.exists()){
            try {
                List<String> lineData = new ArrayList<>();
                List<String> fileLines = Files.readAllLines(filename.toPath());
                HighScoresTable HST = new HighScoresTable(fileLines.size());
                for (String line : fileLines) {
                    lineData = parseLine(line);
                    HST.add(new ScoreInfo(lineData.get(0), Integer.parseInt(lineData.get(1))));
                }
                return HST;
            }
            catch (IOException e){
                return new HighScoresTable(0);
            }
        }
        else {
            return new HighScoresTable(0);
        }
    }

    // Add a high-score.
    public void add(ScoreInfo score) {
        if (this.scoreInfoList.size() == this.size) {
            if (this.scoreInfoList.get(this.scoreInfoList.size() - 1).getScore() <= score.getScore()) {
                this.scoreInfoList.remove(this.scoreInfoList.size() - 1);
                this.scoreInfoList.add(score);
            }
        } else {
            this.scoreInfoList.add(score);
        }
        this.scoreInfoList.sort(Comparator.comparing(ScoreInfo::getScore).reversed());
    }

    // Return table size.
    public int size() {
        return this.size;
    }

    // Return the current high scores.
    // The list is sorted such that the highest
    // scores come first.
    public List<ScoreInfo> getHighScores() {
        return this.scoreInfoList;
    }

    // return the rank of the current score: where will it
    // be on the list if added?
    // Rank 1 means the score will be highest on the list.
    // Rank `size` means the score will be lowest.
    // Rank > `size` means the score is too low and will not
    //      be added to the list.
    public int getRank(int score) {
        for (int i = 0; i < this.scoreInfoList.size(); i++) {
            if (this.scoreInfoList.get(i).getScore() <= score) {
                return i + 1;
            }
        }
        return this.scoreInfoList.size() + 1;
    }

    // Clears the table
    public void clear() {
        this.scoreInfoList.clear();
    }

    // Load table data from file.
    // Current table data is cleared.
    public void load(File filename) throws IOException {
        List<String> fileLines = Files.readAllLines(filename.toPath());
        List<String> lineData = new ArrayList();
        for (String line: fileLines) {
            lineData = parseLine(line);
            this.scoreInfoList.add(new ScoreInfo(lineData.get(0), Integer.parseInt(lineData.get(1))));
        }
    }

    // Save table data to the specified file.
    public void save(File filename) throws IOException {
        FileWriter FW = new FileWriter(filename.getName(), true);
        for (int i = 0; i < this.scoreInfoList.size(); i++) {
            FW.write(this.scoreInfoList.get(i).getName() + "$" + this.scoreInfoList.get(i).getScore() + "$");
            FW.write(System.lineSeparator());
        }
        FW.flush();
    }

    public void createFile(String fileName) throws IOException {
        FileWriter FW = new FileWriter(fileName, true);
    }
}
