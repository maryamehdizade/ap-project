package view.pages;



import javax.swing.*;
import java.awt.*;

import static controller.Constant.FRAME_DIMENSION;


public  class Game extends JFrame {
    JPanel panel ;
    int y = 2;
    GamePanel gamePanel ;



    public Game(Menu menu) throws Exception {
        setUndecorated(true);
        setBackground(new Color(0, 0, 0, 0));
        setLayout(null);
        setFocusable(false);
        setResizable(false);
        setSize(FRAME_DIMENSION);
        setLocationRelativeTo(null);
        setVisible(true);


        this.gamePanel = new GamePanel();
        panel = gamePanel;
        add(panel);

    }
}
