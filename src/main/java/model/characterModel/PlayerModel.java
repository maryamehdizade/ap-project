package model.characterModel;

import view.charactersView.PlayerView;

import java.awt.geom.Point2D;
import java.util.UUID;

import static controller.Controller.createPlayerView;

public class PlayerModel {
    private String id;

    public static PlayerModel getPlayer() {
        if(player == null)player = new PlayerModel(new Point2D.Double(200,200));
        return player;
    }

    private static PlayerModel player;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private Point2D location;

    public PlayerModel(Point2D location) {
        this.location = location;
        this.id = UUID.randomUUID().toString();;
        createPlayerView(id);
    }

    public void setLocation(Point2D location) {
        this.location = location;
    }

    public Point2D getLocation() {
        return location;
    }

    private int xp = 0;
    private int lp = 0;

}
