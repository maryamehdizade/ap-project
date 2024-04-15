package model.characterModel;



import javax.swing.*;
import java.awt.geom.Point2D;
import java.util.UUID;

import static controller.Constant.FRAME_LOCATON;


public final class PlayerModel  {
    private String id;
    private int xp = 0;
    private int hp = 50;
    private Point2D location;
    private static PlayerModel player;

    public static PlayerModel getPlayer() {
        if(player == null){
            player = new PlayerModel(new Point2D.Double(350,350));
        }
        return player;
    }



    public String getId() {
        return id;
    }

    public PlayerModel(Point2D location) {
        this.location = location;
        this.id = UUID.randomUUID().toString();
    }

    public void setLocation(Point2D location) {
        this.location = location;
    }

    public Point2D getLocation() {
        return location;
    }

    public int getXp() {
        return xp;
    }

    public int getHp() {
        return hp;
    }
}
