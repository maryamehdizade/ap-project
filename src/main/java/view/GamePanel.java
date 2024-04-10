package view;

import static controller.Constant.BALL_SIZE;
import static controller.Controller.createPlayerView;

import controller.Constant;
import controller.Update;
import model.characterModel.PlayerModel;
import view.charactersView.PlayerView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Point2D;



public  class GamePanel extends JPanel implements KeyListener {
    public PlayerModel playerModel;
    public PlayerView playerView;


    public GamePanel() {

        setSize(Constant.PANEL_DIMENSION);
        setBackground(new Color(0,0,0,100));
        setFocusable(true);
        requestFocusInWindow();
        addKeyListener(this);
        new Update(this);


        playerModel = PlayerModel.getPlayer();
        playerView = createPlayerView(playerModel.getId());
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        g.setColor(Color.gray);
        g.drawOval((int) playerView.getLocation().getX(), (int) playerView.getLocation().getY(), BALL_SIZE, BALL_SIZE);
        repaint();
    }



    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_A) {
            playerModel.setLocation(new Point2D.Double(playerModel.getLocation().getX() - 10,playerModel.getLocation().getY()));
        } else if (keyCode == KeyEvent.VK_D) {
            playerModel.setLocation(new Point2D.Double(playerModel.getLocation().getX() + 10,playerModel.getLocation().getY()));
        } else if (keyCode == KeyEvent.VK_W) {
            playerModel.setLocation(new Point2D.Double(playerModel.getLocation().getX(),playerModel.getLocation().getY()- 10));
        } else if (keyCode == KeyEvent.VK_S) {
            playerModel.setLocation(new Point2D.Double(playerModel.getLocation().getX(),playerModel.getLocation().getY() + 10));
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}
}
