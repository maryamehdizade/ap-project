package controller;

import view.charactersView.PlayerView;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Point2D;

public class Controller implements KeyListener {
    static PlayerView player;
    public static void createPlayerView(){
        player = new PlayerView();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_A) {
            player.setLocation(new Point2D.Double(player.getLocation().getX() - 10,player.getLocation().getY()));
        } else if (keyCode == KeyEvent.VK_D) {
            player.setLocation(new Point2D.Double(player.getLocation().getX() + 10,player.getLocation().getY()));
        } else if (keyCode == KeyEvent.VK_W) {
            player.setLocation(new Point2D.Double(player.getLocation().getX(),player.getLocation().getY()- 10));
        } else if (keyCode == KeyEvent.VK_S) {
            player.setLocation(new Point2D.Double(player.getLocation().getX(),player.getLocation().getY() + 10));
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
