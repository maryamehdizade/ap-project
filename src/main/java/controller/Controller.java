package controller;

import model.characterModel.PlayerModel;
import view.charactersView.PlayerView;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Point2D;

public class Controller implements KeyListener {

    public static void createPlayerView(String id){
        new PlayerView(id);
    }
    public static PlayerModel findPlayer(String id){
        if(PlayerModel.getPlayer().getId().equals(id))return PlayerModel.getPlayer();
        else return null;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_A) {
            PlayerModel.getPlayer().setLocation(new Point2D.Double(PlayerModel.getPlayer().getLocation().getX() - 10,PlayerModel.getPlayer().getLocation().getY()));
        } else if (keyCode == KeyEvent.VK_D) {
            PlayerModel.getPlayer().setLocation(new Point2D.Double(PlayerModel.getPlayer().getLocation().getX() + 10,PlayerModel.getPlayer().getLocation().getY()));
        } else if (keyCode == KeyEvent.VK_W) {
            PlayerModel.getPlayer().setLocation(new Point2D.Double(PlayerModel.getPlayer().getLocation().getX(),PlayerModel.getPlayer().getLocation().getY()- 10));
        } else if (keyCode == KeyEvent.VK_S) {
            PlayerModel.getPlayer().setLocation(new Point2D.Double(PlayerModel.getPlayer().getLocation().getX(),PlayerModel.getPlayer().getLocation().getY() + 10));
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
