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
        setBackground(Color.black);
        setLayout(null);
        setUndecorated(true);
        setVisible(true);
        setSize(301,201);
        requestFocus();
        setFocusable(false);

        //addbuttons
        add();
    }
    private void add(){
        JLabel xp = new JLabel(String.valueOf(update.panel.playerModel.getXp()));
        xp.setSize(100,100);
        xp.setLocation(150,30);
        xp.setBackground(Color.black);

        JButton button = new JButton("menu");
        button.setSize(200,50);
        button.setBackground(Color.gray);
        button.setLocation(50,140);
        button.addActionListener(e -> {
            dispose();
            update.panel.game.dispose();
            update.panel.game.menu.setVisible(true);
        });

        add(button);
        add(xp);
    }
}

