package rungame;

import biuoop.DrawSurface;
import interfaces.Animation;
import interfaces.Menu;
import others.MenuItem;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Image;
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
    private T returnVal;
    private Image img;

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
        return this.returnVal;
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
     * @param key         key
     * @param message     message
     * @param returnValue return value
     */
    public void addSelection(String key, String message, T returnValue) {
        this.menuItemsList.add(new MenuItem(key, message, returnValue));
    }

    /**
     * Draws the menu.
     *
     * @param d the drawsurface
     */
    public void doOneFrame(DrawSurface d) {
        if (this.img == null) {
            String background = "image(background_images/main_menu_background.jpg)";
            try {
                this.img = ImageIO.read(ClassLoader.getSystemClassLoader().getResourceAsStream(
                        background.substring(background.indexOf("(") + 1, background.indexOf(")"))));
            } catch (IOException e) {
                System.out.println();
            }
        }
        d.drawImage(0, 0, this.img);
        d.setColor(Color.cyan);
        d.drawText(20, 100, "Welcome To Arkanoid!", 40);
        for (int i = 0; i < this.menuItemsList.size(); i++) {
            d.drawText(50, 120 + (i + 1) * 35, "(" + this.menuItemsList.get(i).getKey() + ")  "
                    + this.menuItemsList.get(i).getMessage(), 30);
        }
    }

    /**
     * Adding subMenu.
     *
     * @param key     key
     * @param message message
     * @param subMenu subMenu
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
