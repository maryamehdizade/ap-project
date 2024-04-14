package view;



import javax.swing.*;
import java.awt.*;

import static controller.Constant.FRAME_DIMENSION;


public final class Game extends JFrame {
    private static Game INSTANCE;
    JPanel panel ;
    int y = 2;
    GamePanel gamePanel = GamePanel.getINSTANCE();

    public static Game getINSTANCE() {
        if(INSTANCE == null)INSTANCE = new Game();
        return INSTANCE;
    }

    public Game() {
        setUndecorated(true);
        setBackground(new Color(0, 0, 0, 0));
        setLayout(null);
        setFocusable(false);
        setResizable(false);
        setSize(FRAME_DIMENSION);
        setLocationRelativeTo(null);
        setVisible(true);


        panel = gamePanel;
        add(panel);

    }
}
