package animations;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;

public class EndScreen {

    public void printEndScreen(GUI gui){
        KeyboardSensor keyboardSensor = gui.getKeyboardSensor();
        DrawSurface d = gui.getDrawSurface();
        d.drawText(100, 300, "GAME OVER!", 100);
        gui.show(d);//
        while(!keyboardSensor.isPressed(keyboardSensor.SPACE_KEY)){}
        return;
    }
}
