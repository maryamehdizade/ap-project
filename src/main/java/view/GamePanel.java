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



public final class GamePanel extends JPanel implements  ActionListener {
    PlayerModel playerModel = PlayerModel.getPlayer();
    PlayerView playerView = createPlayerView(playerModel.getId());

    public GamePanel() {
        setSize(Constant.PANEL_DIMENSION);
//        setToCenter(Game.getINSTANCE());
        setBackground(Color.black);
        setFocusable(true);
        requestFocusInWindow();
//        addKeyListener(this);
        new Update();
    }
    private void setToCenter(Game game){
        setLocation(game.getWidth()/2 - getWidth()/2,game.getHeight()/2 - getHeight()/2);
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
//        g.drawOval((int)player.getLocation().getX(), (int)player.getLocation().getY(),BALL_SIZE, BALL_SIZE);
       g.drawOval((int)playerView.getLocation().getX(),(int)playerView.getLocation().getY(),BALL_SIZE,BALL_SIZE);
    }

//    @Override
//    public void keyPressed(KeyEvent e) {
//        int keyCode = e.getKeyCode();
//        if (keyCode == KeyEvent.VK_A) {
//            player.setLocation(new Point2D.Double(player.getLocation().getX() - 10,player.getLocation().getY()));
//        } else if (keyCode == KeyEvent.VK_D) {
//            player.setLocation(new Point2D.Double(player.getLocation().getX() + 10,player.getLocation().getY()));
//        } else if (keyCode == KeyEvent.VK_W) {
//            player.setLocation(new Point2D.Double(player.getLocation().getX(),player.getLocation().getY()- 10));
//        } else if (keyCode == KeyEvent.VK_S) {
//            player.setLocation(new Point2D.Double(player.getLocation().getX(),player.getLocation().getY() + 10));
//        }
//        repaint();
//    }
//
//    @Override
//    public void keyReleased(KeyEvent e) {}
//    @Override
//    public void keyTyped(KeyEvent e) {}

    @Override
    public void actionPerformed(ActionEvent e) {
        new Timer(10, e1 -> repaint()).start();
    }
}
