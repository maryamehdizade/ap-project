package controller;

import model.characterModel.PlayerModel;
import view.charactersView.PlayerView;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Point2D;
import java.util.Objects;

public class Controller  {

    public static PlayerView createPlayerView(String id){
        return new PlayerView(id, Objects.requireNonNull(findPlayer(id)).getLocation());
    }
    public static PlayerModel findPlayer(String id){
        return PlayerModel.getPlayer();
    }
    public static Point2D playerViewLocation(PlayerModel playerModel){
        PlayerModel model = PlayerModel.getPlayer();
        return model.getLocation();

    }

}
