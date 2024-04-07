package view;

import controller.Constant;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    public GamePanel() {
        setSize(Constant.PANEL_DIMENSION);
//        setToCenter(Game.getINSTANCE());
        setBackground(Color.black);
    }
    private void setToCenter(Game game){
        setLocation(game.getWidth()/2 - getWidth()/2,game.getHeight()/2 - getHeight()/2);
    }

}
