package controller;

import model.characterModel.BulletModel;
import model.characterModel.PlayerModel;
import model.characterModel.enemy.RectangleModel;
import view.charactersView.BulletView;
import view.charactersView.PlayerView;
import view.charactersView.enemy.RectangleView;

import java.awt.geom.Point2D;
import java.util.Objects;

public class Controller  {

    public static PlayerView createPlayerView(String id){
        return new PlayerView(id, Objects.requireNonNull(findPlayer(id)).getLocation());
    }
    public static RectangleView createRectView(RectangleModel rectangleModel){
        return new RectangleView(rectangleModel.getId(), rectangleModel.getLoc());
    }
    public static PlayerModel findPlayer(String id){
        return PlayerModel.getPlayer();
    }
    public static Point2D playerViewLocation(PlayerModel playerModel){
        PlayerModel model = PlayerModel.getPlayer();
        return model.getLocation();

    }
    public static int playerViewXp(PlayerModel playerModel){
        PlayerModel model = PlayerModel.getPlayer();
        return model.getXp();

    }
    public static int playerViewHp(PlayerModel playerModel){
        PlayerModel model = PlayerModel.getPlayer();
        return model.getHp();

    }
    public static BulletView createBulletView(BulletModel bulletModel){
        return new BulletView(bulletModel.getId(), bulletModel.getLoc(), bulletModel.getDx(), bulletModel.getDy());
    }



}
