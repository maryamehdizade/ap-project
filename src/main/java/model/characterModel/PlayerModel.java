package model.characterModel;

import view.charactersView.PlayerView;

import java.awt.geom.Point2D;
import java.util.UUID;

import static controller.Controller.createPlayerView;

public final class PlayerModel {
    private String id;
    private int xp = 0;
    private int lp = 0;
    private Point2D location;

    public static PlayerModel getPlayer() {
        if(player == null){
            System.out.println("new player");
            player = new PlayerModel(new Point2D.Double(100,100));
        }
        return player;
    }

    private static PlayerModel player;

    public String getId() {
        return id;
    }



    public PlayerModel(Point2D location) {
        this.location = location;
        this.id = UUID.randomUUID().toString();;
    }

    public void setLocation(Point2D location) {
        this.location = location;
    }

    public Point2D getLocation() {
        return location;
    }



}
