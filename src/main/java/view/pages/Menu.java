package view.pages;

import controller.Minimize;

import javax.swing.*;
import java.awt.*;

public class Menu extends JFrame {

    private JButton exit = new JButton("exit");
    private JButton skillTree = new JButton("skill tree");
    private JButton play = new JButton("play");
    private JButton setting = new JButton("setting");
    private JButton tutorial = new JButton("tutorial");
    public boolean a = false;

    private final int buttonWidth = 300;
    private final int buttonHieght = 70;
    private int xLoc = 200;
    private final Color color = Color.GRAY;

    private JPanel panel = new JPanel();
    public Menu() throws Exception {
        setSize(700,700);
        setLocation(300,20);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        requestFocus();

        this.add(panel);
        panel.setLayout(null);
        panel.setBackground(Color.black);

        addButtons();

    }
    private void addButtons(){
        exit.setSize(buttonWidth,buttonHieght);
        exit.setLocation(xLoc,400);
        exit.setBackground(color);

        play.setSize(buttonWidth,buttonHieght);
        play.setLocation(xLoc,100);
        play.setBackground(color);
        play.addActionListener(e -> {
            setVisible(false);
            try {
                Minimize m = new Minimize(this);
                if(a) {
                    new Game(this);
                }
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        skillTree.setSize(buttonWidth,buttonHieght);
        skillTree.setLocation(xLoc,200);
        skillTree.setBackground(color);

        tutorial.setSize(buttonWidth,buttonHieght);
        tutorial.setLocation(xLoc,300);
        tutorial.setBackground(color);

        setting.setSize(buttonWidth, buttonHieght);
        setting.setLocation(xLoc, 500);
        setting.setBackground(color);
        setting.addActionListener(e -> {
            setVisible(false);
            try {
                new Setting(this);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        panel.add(exit);
        panel.add(setting);
        panel.add(play);
        panel.add(skillTree);
        panel.add(tutorial);


    }
}
