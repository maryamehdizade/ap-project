package view.pages;

import controller.Update;

import javax.swing.*;
import java.awt.*;

public class GameOver extends JFrame {
    Update update;
    public GameOver(Update update) throws HeadlessException {
        this.update = update;

        setSize(300, 200);
        setLocation((int) ((update.panel.getDimension().getWidth())), (int) (update.panel.getDimension().getHeight() / 2));
        setResizable(false);
        setUndecorated(true);
        setVisible(true);
        setBackground(Color.black);
        setSize(301,201);
        requestFocus();
        setFocusable(false);

        //addbuttons
    }
}
