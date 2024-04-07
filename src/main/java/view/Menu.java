package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame {

    private JButton exit = new JButton("exit");
    private JButton skillTree = new JButton("skill tree");
    private JButton play = new JButton("play");

    private JButton tutorial = new JButton("tutorial");

    private final int buttonWidth = 300;
    private final int buttonHieght = 70;
    private int xLoc = 200;
    private final Color color = Color.GRAY;

    private JPanel panel = new JPanel();
    public Menu(){
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
        play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Game.getINSTANCE();
            }
        });

        skillTree.setSize(buttonWidth,buttonHieght);
        skillTree.setLocation(xLoc,200);
        skillTree.setBackground(color);

        tutorial.setSize(buttonWidth,buttonHieght);
        tutorial.setLocation(xLoc,300);
        tutorial.setBackground(color);

        panel.add(exit);
        panel.add(play);
        panel.add(skillTree);
        panel.add(tutorial);


    }
}
