package view;

import static controller.Constant.FRAME_DIMENSION;

import javax.swing.*;
import java.awt.*;

public class Game extends JFrame {
    private static Game INSTANCE;

    public static Game getINSTANCE() {
        if(INSTANCE == null)INSTANCE = new Game();
        return INSTANCE;
    }

    public Game(){
        setUndecorated(true);
        setBackground(new Color(0,0,0));
        setSize(FRAME_DIMENSION);
        setLocationRelativeTo(null);
        setVisible(true);

        add(new GamePanel());

    }
}
