package controller;

import java.awt.*;
import java.awt.event.KeyEvent;
import view.pages.Menu;
public class Minimize {
    private Menu menu;

    public  Minimize(Menu m) {
        menu = m;
        try {
            Robot robot = new Robot();


            robot.keyPress(KeyEvent.VK_WINDOWS);
            robot.keyPress(KeyEvent.VK_D);

            robot.keyRelease(KeyEvent.VK_D);
            robot.keyRelease(KeyEvent.VK_WINDOWS);
            menu.a = true;

        } catch (
                AWTException e) {
            e.printStackTrace();
        }
    }
}
