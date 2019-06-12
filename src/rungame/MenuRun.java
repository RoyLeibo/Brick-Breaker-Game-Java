package rungame;

import animations.HighScoresAnimation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import gamecontrollers.HighScoresTable;
import interfaces.Animation;
import interfaces.Menu;
import interfaces.Task;
import others.MenuItem;
import tasks.GameFlowTask;
import tasks.HighScoresTaks;
import tasks.QuitTask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Menu run.
 *
 * @param <T> the type parameter
 */
public class MenuRun<T> implements Animation, Menu<T> {
    private String[] args;
    private AnimationRunner animationRunner;
    private boolean isRunning;
    private List<MenuItem<T>> menuItemsList;
    private T ReturnVal;

    /**
     * Instantiates a new Menu run.
     *
     * @param args            the args
     * @param size            the size
     * @param animationRunner the animation runner
     */
    public MenuRun(String[] args, int size, AnimationRunner animationRunner) {
        this.isRunning = true;
        this.animationRunner = animationRunner;
        this.args = args;
        this.menuItemsList = new ArrayList<>();
    }

    /**
     * returns T.
     *
     * @return T value
     */

    public T getStatus() {
        return this.ReturnVal;
    }

    /**
     * Return isRunning.
     *
     * @return boolean isRunning
     */
    public boolean shouldStop() {
        return isRunning;
    }

    /**
     * Add a menu selection.
     *
     * @param key
     * @param message
     * @param returnVal
     */
    public void addSelection(String key, String message, T returnVal) {
        this.menuItemsList.add(new MenuItem(key, message, returnVal));
    }

    /**
     * Draws the menu.
     *
     * @param d the drawsurface
     */
    public void doOneFrame(DrawSurface d) {
        d.drawText(70, 100, "Welcome To Arkanoid!", 40);
        for (int i = 0; i < this.menuItemsList.size(); i++) {
            d.drawText(100, 120 + (i + 1) * 30, "(" + this.menuItemsList.get(i).getKey() + ")  "
                    + this.menuItemsList.get(i).getMessage(), 30);
        }
    }

    /**
     * Adding subMenu.
     *
     * @param key
     * @param message
     * @param subMenu
     */
    public void addSubMenu(String key, String message, Menu<T> subMenu) {
    }

    /**
     * Gets menu items list.
     *
     * @return the menu items list
     */
    public List<MenuItem<T>> getMenuItemsList() {
        return menuItemsList;
    }

    /**
     * Sets running.
     *
     * @param running the running
     */
    public void setRunning(boolean running) {
        isRunning = running;
    }
}
