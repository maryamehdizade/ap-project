package view;

import static controller.Constant.BALL_SIZE;
import static controller.Controller.createPlayerView;

import controller.Constant;
import controller.Update;
import model.characterModel.MovePlayer;
import model.characterModel.PlayerModel;
import view.charactersView.PlayerView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Point2D;



public  class GamePanel extends JPanel implements KeyListener {
    public PlayerModel playerModel;
    public PlayerView playerView;
    public MovePlayer movePlayer;


    public GamePanel() {

        setSize(Constant.PANEL_DIMENSION);
        setBackground(new Color(0,0,0,100));
        setFocusable(true);
        requestFocusInWindow();
        addKeyListener(this);
        new Update(this);


        playerModel = PlayerModel.getPlayer();
        playerView = createPlayerView(playerModel.getId());
        movePlayer = new MovePlayer(playerModel);
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
            movePlayer.setlForce(true);
        } else if (keyCode == KeyEvent.VK_D) {
            movePlayer.setrForce(true);
        } else if (keyCode == KeyEvent.VK_W) {
            movePlayer.setuForce(true);
        } else if (keyCode == KeyEvent.VK_S) {
            movePlayer.setdForce(true);
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_D) {
            movePlayer.setrForce(false);
            movePlayer.setXvelocity(0);
        }
        if (keyCode == KeyEvent.VK_A) {
            movePlayer.setlForce(false);
            movePlayer.setXvelocity(0);
        }
        if (keyCode == KeyEvent.VK_S) {
            movePlayer.setdForce(false);
            movePlayer.setYvelocity(0);
        }
        if (keyCode == KeyEvent.VK_W) {
            movePlayer.setuForce(false);
            movePlayer.setYvelocity(0);
        }
    }
}
