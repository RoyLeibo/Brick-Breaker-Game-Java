package animations;

import biuoop.DrawSurface;
import interfaces.Menu;
import others.MenuItem;

import java.util.List;

public class MenuAnimation<T> implements Menu<T> {
    private boolean isRunning;
    private List<MenuItem<T>> menuItemsList;
    private T ReturnVal;

    public void addSelection(String key, String message, T returnVal) {
        this.menuItemsList.add(new MenuItem(key, message, returnVal));
    }

    public T getStatus() {
        return this.ReturnVal;
    }

    public void doOneFrame(DrawSurface d) {
        d.drawText(70, 100, "Welcome To Arkanoid!", 40);
        for (int i = 0 ; i < this.menuItemsList.size(); i++) {
            d.drawText(100, 120 + (i+1)*30, "(" + this.menuItemsList.get(i).getKey() + ")  "
                    + this.menuItemsList.get(i).getMessage(), 30);
        }

    }

    public boolean shouldStop() {
        return isRunning;
    }
}
