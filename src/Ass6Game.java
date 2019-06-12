import animations.*;
import backgrounds.CirclesBackground;
import backgrounds.DirectHitBackground;
import backgrounds.Emoji;
import biuoop.DrawSurface;
import biuoop.GUI;
import gamecontrollers.HighScoresTable;
import gamecontrollers.LevelNameIndicator;
import gamecontrollers.ScoreInfo;
import gamesprites.Block;
import interfaces.Animation;
import interfaces.LevelInformation;
import levelfromfile.BlocksFromSymbolsFactory;
import levelfromfile.LevelSpecificationReader;
import levels.DirectHit;
import levels.FullLines;
import levels.PartialLines;
import levels.Pyramid;
import rungame.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class runs the game.
 *
 * @author Roy Leibovitz <royleibo212@gmail.com> Roy Leibovitz
 */
public class Ass6Game {

    /**
     * This is the main method which creates an instance of GameLevel and call
     * it's initialize and run function.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        RunGame run = new RunGame(args, 2);
//        LevelSpecificationReader lsr = new LevelSpecificationReader();
//        List<LevelInformation> list1 = null;
//        try{
//            list1 = lsr.createLevelInformationList(new File("b.txt"));
//        }
//        catch (IOException e){}
//        List<Block> list = list1.get(0).blocks();
//        GUI gui = new GUI("",800,600);
//        DrawSurface d = gui.getDrawSurface();
//        while(true) {
//            for (Block b : list) {
//                b.drawOn(d);
//            }
//            gui.show(d);
//            d = gui.getDrawSurface();
//        }


//        GameFlow gl = new GameFlow(args, new HighScoresTable(3));
//        gl.runLevels();
//        HighScoresTable HST = new HighScoresTable(3);
//        HST.add(new ScoreInfo("rotem", 10));
//        HST.add(new ScoreInfo("roy", 20));
//        HST.add(new ScoreInfo("nadav", 30));
//        AnimationRunner ar = new AnimationRunner(10);
//        ar.run(new KeyPressStoppableAnimation(ar.getGui().getKeyboardSensor(), "SPACE_KEY", new HighScoresAnimation(HST)));
//        try {
//            HST.save(new File("Test.txt"));
//            HighScoresTable HST1 = new HighScoresTable(2);
//            HST1.load(new File("Test.txt"));
//            for (int i = 0; i < 2; i++) {
//                System.out.println(HST1.getHighScores().get(i).getName() + " " + HST1.getHighScores().get(i).getScore());
//            }
//        } catch (IOException e) {
//            System.out.println("Something Went Wrong..");
//        }
//        HST.add(new ScoreInfo("roy", 1));
//        HST.add(new ScoreInfo("rotem", 3));
//        System.out.println("HighScoreTable After Sort:");
//        for(int i = 0 ; i < 2 ; i++){
//            System.out.println(HST.getHighScores().get(i).getName() + " " + HST.getHighScores().get(i).getScore());
//        }
//        System.out.println("If Someone With Score 5 Will Be Added, His Rank Will Be: " + HST.getRank(5));
//        HST.add(new ScoreInfo("nadav", 4));
//        HST.add(new ScoreInfo("sinai", 6));
//        System.out.println("HighScoreTable After Adding High Score:");
//        for(int i = 0 ; i < 3 ; i++){
//            System.out.println(HST.getHighScores().get(i).getName() + " " + HST.getHighScores().get(i).getScore());
//        }
//        System.out.println("If Someone With Score 5 Will Be Added, His Rank Will Be: " + HST.getRank(5));
//        System.out.println("HighScoreTable After Adding High Score:");
//        HST.add(new ScoreInfo("Ora", 6));
//        for(int i = 0 ; i < 3 ; i++){
//            System.out.println(HST.getHighScores().get(i).getName() + " " + HST.getHighScores().get(i).getScore());
//        }
//        System.out.println("If Someone With Score 5 Will Be Added, His Rank Will Be: " + HST.getRank(5));
//        System.out.println("If Someone With Score 5 Will Be Added, His Rank Will Be: " + HST.getRank(2));
    }
}
